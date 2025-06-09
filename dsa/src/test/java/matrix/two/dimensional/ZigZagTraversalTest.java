package matrix.two.dimensional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.AbstractMap;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZigZagTraversalTest {
    @ParameterizedTest(name = "{0} X {1} matrix | {2} steps | {3} obstacles")
    @MethodSource("argsForZigZagTraversalWithObstacles")
    void zigZagTraversalWithObstaclesTest(int width, int height, int totalPermittedSteps,
                                          Set<AbstractMap.SimpleImmutableEntry<Integer, Integer>> obstacles, int expectedPositionX, int expectedPositionY) {

        var actualResult =
                ZigZagTraversal.zigZagTraversalWithObstacles(width, height, totalPermittedSteps, obstacles);

        assertEquals(expectedPositionX, actualResult.getKey());
        assertEquals(expectedPositionY, actualResult.getValue());
    }

    private static Stream<Arguments> argsForZigZagTraversalWithObstacles() {
        return Stream.of(
                matrix_3X3_steps_5_obstacles_1(),
                matrix_3X3_steps_5_obstacles_0(),
                matrix_3X2_steps_5_obstacles_0(),
                matrix_2X3_steps_5_obstacles_0()
        );
    }

    private static Arguments matrix_3X3_steps_5_obstacles_0() {
        return Arguments.of(3, 3, 5, Set.of(), 1, 0);
    }

    private static Arguments matrix_3X3_steps_5_obstacles_1() {
        var oneObstacle = Set.of(new AbstractMap.SimpleImmutableEntry<>(0, 0));
        return Arguments.of(3, 3, 5, oneObstacle, 2, 0);
    }

    private static Arguments matrix_3X2_steps_5_obstacles_0() {
        return Arguments.of(3, 2, 5, Set.of(), 2, 1);
    }

    private static Arguments matrix_2X3_steps_5_obstacles_0() {
        return Arguments.of(3, 2, 5, Set.of(), 1, 0);
    }
}
