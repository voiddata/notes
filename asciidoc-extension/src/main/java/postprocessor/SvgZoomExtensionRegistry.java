package postprocessor;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry;

public class SvgZoomExtensionRegistry implements ExtensionRegistry {

    @Override
    public void register(Asciidoctor asciidoctor) {
        JavaExtensionRegistry javaExtensionRegistry = asciidoctor.javaExtensionRegistry();
        javaExtensionRegistry.postprocessor(SvgZoomPostProcessor.class);
    }
}