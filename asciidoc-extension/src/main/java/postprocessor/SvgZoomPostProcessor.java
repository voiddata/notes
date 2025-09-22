package postprocessor;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Postprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SvgZoomPostProcessor extends Postprocessor {

    private static final Logger LOGGER = Logger.getLogger(SvgZoomPostProcessor.class.getName());

    // Pattern to match img tags with SVG sources
    private static final Pattern IMG_SVG_PATTERN = Pattern.compile("<img\\s+([^>]*\\s+)?src=\"([^\"]*\\.svg)\"([^>]*)" +
            ">", Pattern.CASE_INSENSITIVE);

    // Pattern to match PlantUML generated files (common naming patterns)
    private static final Pattern PLANTUML_FILE_PATTERN = Pattern.compile(".*(?:plantuml|diag)-[a-f0-9]+\\.svg$|.*\\" +
            ".puml\\.svg$", Pattern.CASE_INSENSITIVE);

    @Override
    public String process(Document document, String output) {
        LOGGER.info("Processing document with SVG zoom postprocessor");

        // Add the svg-pan-zoom script if not already present
        if (!output.contains("svg-pan-zoom")) {
            output = addSvgZoomScript(output);
        }

        // Replace img tags with embedded SVG for PlantUML diagrams
        output = embedPlantUmlSvgs(output, document);

        return output;
    }

    private String addSvgZoomScript(String output) {
        String script = """
                <script src="https://cdn.jsdelivr.net/npm/svg-pan-zoom@3.6.1/dist/svg-pan-zoom.min.js"></script>
                <script>
                    document.addEventListener("DOMContentLoaded", function () {
                      document.querySelectorAll("svg").forEach(function (svg) {
                        svgPanZoom(svg, {
                          zoomEnabled: true,
                          controlIconsEnabled: true,
                          fit: true,
                          center: true,
                          minZoom: 0.5,
                          maxZoom: 10
                        });
                      });
                    });
                </script>
                </head>""";

        return output.replace("</head>", script);
    }

    private String embedPlantUmlSvgs(String output, Document document) {
        Matcher matcher = IMG_SVG_PATTERN.matcher(output);
        StringBuilder result = new StringBuilder();

        String baseDir = (String) document.getAttribute("docdir");
        String imagesDir = (String) document.getAttribute("imagesdir", "");
        String outDir = (String) document.getAttribute("outdir", baseDir);

        while (matcher.find()) {
            String preAttributes = matcher.group(1) != null ? matcher.group(1).trim() : "";
            String svgPath = matcher.group(2);
            String postAttributes = matcher.group(3) != null ? matcher.group(3).trim() : "";

            // Check if this looks like a PlantUML generated SVG
            if (isPlantUmlSvg(svgPath)) {
                try {
                    String svgContent = readSvgFile(baseDir, imagesDir, outDir, svgPath);
                    if (svgContent != null) {
                        String embeddedSvg = createEmbeddedSvg(svgContent, preAttributes, postAttributes);
                        matcher.appendReplacement(result, Matcher.quoteReplacement(embeddedSvg));
                        LOGGER.info("Successfully embedded SVG: " + svgPath);
                    } else {
                        // Keep original img tag if we can't read the SVG
                        matcher.appendReplacement(result, matcher.group(0));
                        LOGGER.warning("Could not read SVG file, keeping img tag: " + svgPath);
                    }
                } catch (Exception e) {
                    // Keep original img tag if embedding fails
                    matcher.appendReplacement(result, matcher.group(0));
                    LOGGER.log(Level.WARNING, "Failed to embed SVG: " + svgPath, e);
                }
            } else {
                // Not a PlantUML SVG, keep original img tag
                matcher.appendReplacement(result, matcher.group(0));
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private boolean isPlantUmlSvg(String svgPath) {
        // Check if the file path matches common PlantUML naming patterns
        return PLANTUML_FILE_PATTERN.matcher(svgPath).matches() || svgPath.contains("plantuml") || svgPath.contains(
                "diag-");
    }

    private String readSvgFile(String baseDir, String imagesDir, String outDir, String svgPath) {
        LOGGER.info("base Dir - " + baseDir);
        LOGGER.info("images Dir - " + imagesDir);
        LOGGER.info("svg path - " + svgPath);
        LOGGER.info("output path - " + outDir);
        try {
            // Try different path combinations
            Path imageFilePath = Paths.get(svgPath);
            Path[] possiblePaths = {
                    // Relative to base directory
                    Paths.get(baseDir, svgPath),
                    // Relative to images directory
                    Paths.get(baseDir, imagesDir, svgPath),
                    // Just the filename in images directory
                    Paths.get(baseDir, imagesDir, imageFilePath.getFileName().toString()),
                    // Absolute path
                    imageFilePath, Paths.get(outDir, svgPath)};

            for (Path path : possiblePaths) {
                if (Files.exists(path) && Files.isReadable(path)) {
                    LOGGER.fine("Reading SVG from: " + path);
                    return Files.readString(path);
                }
            }

            LOGGER.warning("SVG file not found at any of the attempted paths for: " + svgPath);
            return null;

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error reading SVG file: " + svgPath, e);
            return null;
        }
    }

    private String createEmbeddedSvg(String svgContent, String preAttributes, String postAttributes) {
        // Clean up the SVG content
        svgContent = cleanSvgContent(svgContent);

        // Extract any CSS classes or other attributes from the original img tag
        String cssClasses = extractCssClasses(preAttributes + " " + postAttributes);

        // Create the wrapper div with proper AsciiDoc structure
        StringBuilder wrapper = new StringBuilder();
        wrapper.append("<div class=\"imageblock");
        if (!cssClasses.isEmpty()) {
            wrapper.append(" ").append(cssClasses);
        }
        wrapper.append("\" style=\"max-width: 100%; overflow: hidden;\">");
        wrapper.append("<div class=\"content\" style=\"max-width: 100%; overflow: auto; cursor: pointer;\">");
        wrapper.append(svgContent);
        wrapper.append("</div>");
        wrapper.append("</div>");

        return wrapper.toString();
    }

    private String cleanSvgContent(String svgContent) {
        // Remove XML declaration if present
        svgContent = svgContent.replaceAll("<\\?xml[^>]*\\?>", "");

        // Remove DOCTYPE if present
        svgContent = svgContent.replaceAll("<!DOCTYPE[^>]*>", "");


        // Replace preserveAspectRatio="none" with "xMidYMid meet"
        svgContent = svgContent.replaceAll("preserveAspectRatio\\s*=\\s*['\"]none['\"]", "preserveAspectRatio=\"xMidYMid meet\"");

        // Ensure the SVG has proper attributes for responsive behavior
        if (!svgContent.contains("preserveAspectRatio")) {
            svgContent = svgContent.replaceFirst("<svg([^>]*?)>", "<svg$1 preserveAspectRatio=\"xMidYMid meet\">");
        }

        // Add a unique ID if not present (helpful for svg-pan-zoom)
        if (!svgContent.contains("id=")) {
            String uniqueId = "svg-" + System.nanoTime();
            svgContent = svgContent.replaceFirst("<svg([^>]*?)>", "<svg$1 id=\"" + uniqueId + "\">");
        }

        return svgContent.trim();
    }

    private String extractCssClasses(String attributes) {
        Pattern classPattern = Pattern.compile("class=[\"']([^\"']*)[\"']", Pattern.CASE_INSENSITIVE);
        Matcher matcher = classPattern.matcher(attributes);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

}