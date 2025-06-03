package com.dsa;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        int width = 3;
        int height = 3;
        int K = 5;
        Set<Position> obstacles = Set.of(new Position(0, 0));

        int i;
        int j = 0;
        int currStep = 0;

        boolean flag = true;
        int addend;

        outer:
        for (i = 0; i < width; i++) {
            addend = flag ? +1 : -1;
            while (j >= 0 && j < height) {
                if (!obstacles.contains(new Position(i, j))) {
                    System.out.println("current (i, j) : (" + i + ", " + j + ")");
                    if (currStep == K) break outer;
                    currStep++;
                }
                j = j + addend;
            }
            j -= addend;
            flag = !flag;
        }
        i = i >= width ? --i : i;
        System.out.println("ans (i, j) : (" + i + ", " + j + ")");
    }
}
