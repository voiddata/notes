package matrix.two.dimensional;

import java.util.AbstractMap;
import java.util.Set;

public class ZigZagTraversal {

    // tag::usage[]
    public static AbstractMap.SimpleImmutableEntry<Integer, Integer> zigZagTraversalWithObstacles(
            int width, int height, int totalPermittedSteps, Set<AbstractMap.SimpleImmutableEntry<Integer, Integer>> obstacles) {
        int i;
        int j = 0;
        int currStep = 0;

        boolean flag = true;
        int addend;

        outer:
        for (i = 0; i < height; i++) {
            addend = flag ? +1 : -1;
            while (j >= 0 && j < width) {
                if (!obstacles.contains(new AbstractMap.SimpleImmutableEntry<>(i, j))) {
                    if (currStep == totalPermittedSteps) break outer;
                    currStep++;
                }
                j = j + addend;
            }
            j -= addend;
            flag = !flag;
        }
        return new AbstractMap.SimpleImmutableEntry<>(i >= height ? --i : i, j);
    }
    // end::usage[]
}
