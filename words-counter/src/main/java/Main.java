import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        System.out.println(uniqueWordsCount("ddd ddd ddd gh dd trt"));

    }
    private static int uniqueWordsCount (String text) {
        Set<String> wordsSet = new HashSet<>();
        String[] words = new StringUtils().split(text);
        for (String word:
             words) {
            wordsSet.add(word);
        }
        return wordsSet.size();

    }
}
