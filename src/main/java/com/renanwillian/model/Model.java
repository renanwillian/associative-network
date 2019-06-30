package com.renanwillian.model;

import java.util.List;
import java.util.Objects;

public class Model {

    private static final int INPUT_SIZE = 63;
    private static final int OUTPUT_SIZE = 15;
    private double[][] w;

    public void fit(List<int[]> x, List<int[]> y) {
        if (Objects.requireNonNull(x).size() != Objects.requireNonNull(y).size())
            throw new IllegalArgumentException("x and y sizes must be equal");

        // Initializing weights
        w = new double[INPUT_SIZE][OUTPUT_SIZE];
        for (int i = 0; i < INPUT_SIZE; i++) {
            for (int j = 0; j < OUTPUT_SIZE; j++) {
                w[i][j] = 0;
            }
        }

        // Training the network
        for (int k = 0; k < x.size(); k++) {
            for (int i = 0; i < INPUT_SIZE; i++) {
                for (int j = 0; j < OUTPUT_SIZE; j++) {
                    w[i][j] += x.get(k)[i] * y.get(k)[j];
                }
            }
        }
    }

    public int[] predict(int[] x) {
        int[] y = new int[OUTPUT_SIZE];

        for (int j = 0; j < OUTPUT_SIZE; j++) {
            double n = 0;
            for (int i = 0; i < INPUT_SIZE; i++) {
                n += x[i] * w[i][j];
            }
            y[j] = heavisideStepFunction(n);
        }
        return y;
    }

    public int[] reversePredict(int[] y) {
        int[] x = new int[INPUT_SIZE];

        for (int i = 0; i < INPUT_SIZE; i++) {
            double n = 0;
            for (int j = 0; j < OUTPUT_SIZE; j++) {
                n += y[j] * w[i][j];
            }
            x[i] = heavisideStepFunction(n);
        }
        return x;
    }

    private int heavisideStepFunction(double n) {
        return n < 0 ? -1 : 1;
    }
}