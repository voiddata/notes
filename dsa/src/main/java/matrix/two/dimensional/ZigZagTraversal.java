package matrix.two.dimensional;

import java.util.AbstractMap;
import java.util.Set;

public class ZigZagTraversal {
    public static AbstractMap.SimpleImmutableEntry<Integer, Integer> zigZagTraversalWithObstacles(
            int width, int height, int totalPermittedSteps, Set<AbstractMap.SimpleImmutableEntry<Integer, Integer>> obstacles) {
        int i;
        int j = 0;
        int currStep = 0;

        boolean flag = true;
        int addend;

        outer:
        for (i = 0; i < width; i++) {
            addend = flag ? +1 : -1;
            while (j >= 0 && j < height) {
                if (!obstacles.contains(new AbstractMap.SimpleImmutableEntry<>(i, j))) {
                    if (currStep == totalPermittedSteps) break outer;
                    currStep++;
                }
                j = j + addend;
            }
            j -= addend;
            flag = !flag;
        }
        return new AbstractMap.SimpleImmutableEntry<>(i >= width ? --i : i, j);
    }
}
