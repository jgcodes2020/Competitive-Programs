package io.github.jgcodes.dmoj.ccc.s2003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Poem {
  private static final Pattern WORD_PATTERN = Pattern.compile("\\b([a-z]+)$");

  public static void main(String[] args) throws Throwable {
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final int lines = parseInt(readIn.readLine());
      for (int i = 0; i < lines; i++) {
        final String[] verse = new String[4];
        for (int j = 0; j < 4; j++) {
          verse[j] = readIn.readLine();
        }
        System.out.println(test(verse));
      }
    }
  }

  public static String test(String[] verse) {
    // regex match last word
    Arrays.setAll(verse, idx -> {
      String line = verse[idx].toLowerCase(Locale.ROOT);
      Matcher lwm = WORD_PATTERN.matcher(line);
      if (lwm.find()) {
        String word = lwm.group(1);
        // look for last vowel
        int match;
        search:
        for (match = word.length() - 1; match >= 0; match--) {
          char c = word.charAt(match);
          switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
              break search;
          }
        }
        if (match == -1)
          return word;
        else
          return word.substring(match);

      }
      else {
        throw new IllegalStateException();
      }
    });
    if (verse[0].equals(verse[1])) {
      if (verse[2].equals(verse[3])) {
        if (verse[1].equals(verse[2]))
          //after this check all are guaranteed equal
          return "perfect";
        else
          return "even";
      }
      else return "free";
    }
    else if (verse[0].equals(verse[2])) {
      if (verse[1].equals(verse[3]))
        return "cross";
      else
        return "free";
    }
    else if (verse[0].equals(verse[3])) {
      if (verse[1].equals(verse[2]))
        return "shell";
      else
        return "free";
    }
    else return "free";
  }
}
