package com.renanwillian;

import com.renanwillian.model.Model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final int INPUT_SIZE = 63;
    private static Model model = new Model();

    public static void main(String[] args) {
        List<int[]> x = new ArrayList<>();
        x.add(readFile("training/a_input.txt"));
        x.add(readFile("training/b_input.txt"));
        x.add(readFile("training/c_input.txt"));

        List<int[]> y = new ArrayList<>();
        y.add(readFile("training/a_output.txt"));
        y.add(readFile("training/b_output.txt"));
        y.add(readFile("training/c_output.txt"));

        model.fit(x, y);

        List<int[]> tests = new ArrayList<>();
        tests.add(readFile("test/test1.txt"));
        tests.add(readFile("test/test2.txt"));
        tests.add(readFile("test/test3.txt"));
        tests.add(readFile("test/test4.txt"));
        tests.add(readFile("test/test5.txt"));
        tests.add(readFile("test/test6.txt"));

        for (int[] test : tests) {
            int[] prediction = test.length == INPUT_SIZE ? model.predict(test) : model.reversePredict(test);

            System.out.println();
            System.out.println("Input: " + getString(test));
            System.out.println("Output: " + getString(prediction));
        }
    }

    private static int[] readFile(String file) {
        String content = getFileContent(file).replaceAll("[\n\r]", "");
        int[] retorno = new int[content.length()];
        for (int i = 0; i < content.length(); i++) retorno[i] = mapCharacterToInt(content.charAt(i));
        return retorno;
    }

    private static int mapCharacterToInt(char character) {
        if (character == '#') return 1;
        else if (character == '.') return -1;
        else return 0;
    }

    private static char mapIntToCharacter(int integer) {
        if (integer == 1) return '#';
        else if (integer == -1) return '.';
        else return '*';
    }

    private static String getFileContent(String file) {
        try {
            URI uri = ClassLoader.getSystemResource(file).toURI();
            return new String(Files.readAllBytes(Paths.get(uri)));
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Error on reading the file");
        }
    }

    private static String getString(int[] y) {
        StringBuilder output = new StringBuilder();
        Arrays.stream(y).mapToObj(Main::mapIntToCharacter).forEach(output::append);

        if (y.length == INPUT_SIZE) addBreakLines(output, 9, 7);
        else addBreakLines(output, 5, 3);
        return output.toString();
    }

    private static void addBreakLines(StringBuilder output, int times, int charactersPerLine) {
        for (int i = 0; i < times; i++) {
            output.insert((charactersPerLine + 1) * i, '\n');
        }
    }
}