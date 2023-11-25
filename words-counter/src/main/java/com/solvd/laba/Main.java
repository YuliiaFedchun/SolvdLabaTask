package com.solvd.laba;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final String DATA_FILE_NAME = "data.txt";
    private static final String DATA_FILE_PATH = Main.class.getResource(DATA_FILE_NAME).getPath();
    private static final String RESULT_FILE_NAME = "result.txt";

    public static void main(String[] args) throws IOException {
        String text = FileUtils.readFileToString(new File(DATA_FILE_PATH), "UTF-8");
        FileUtils.writeStringToFile(new File(RESULT_FILE_NAME),
                "Number of unique words: " + uniqueWordsCount(text), "UTF-8");

    }

    private static int uniqueWordsCount(String text) {
        String[] words = StringUtils.split(StringUtils.lowerCase(text));
        Set<String> wordsSet = new HashSet<>();
        for (String word : words) {
            if (!word.equals("—")) {
                wordsSet.add(word);
            }
        }
        return wordsSet.size();

    }
}
