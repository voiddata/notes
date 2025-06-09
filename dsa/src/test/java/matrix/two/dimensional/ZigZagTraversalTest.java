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
        var oneObstacle = Set.of(new AbstractMap.SimpleImmutableEntry<>(0, 0));
        var noObstacle = Set.of();

        return Stream.of(
                Arguments.of(3, 3, 5, oneObstacle, 2, 0),
                Arguments.of(3, 3, 5, noObstacle, 1, 0)
        );
    }
}
