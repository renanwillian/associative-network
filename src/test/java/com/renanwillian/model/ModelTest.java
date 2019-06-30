package com.renanwillian.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ModelTest {

    private int[] x = new int[]{-1, -1, -1, 1, -1, -1, -1, // ...#...
                                -1, -1, -1, 1, -1, -1, -1, // ...#...
                                -1, -1, 1, -1, 1, -1, -1,  // ..#.#..
                                -1, -1, 1, -1, 1, -1, -1,  // ..#.#..
                                -1, -1, 1, 1, 1, -1, -1,   // ..###..
                                -1, 1, -1, -1, -1, 1, -1,  // .#...#.
                                -1, 1, -1, -1, -1, 1, -1,  // .#...#.
                                1, -1, -1, -1, -1, -1, 1,  // #.....#
                                1, -1, -1, -1, -1, -1, 1}; // #.....#

    private int[] y = new int[]{-1, 1, -1, // .#.
                                1, -1, 1,  // #.#
                                1, 1, 1,   // ###
                                1, -1, 1,  // #.#
                                1, -1, 1}; // #.#

    private Model model = new Model();

    @BeforeEach
    void init() {
        model.fit(singletonList(x), singletonList(y));
    }

    @Test
    void predict() {
        assertArrayEquals(y, model.predict(x));
    }

    @Test
    void reversePredict() {
        assertArrayEquals(x, model.reversePredict(y));
    }
}