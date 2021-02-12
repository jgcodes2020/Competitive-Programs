package io.github.jgcodes.cf.uhscco1;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class CanWilburBeatKevin {
	public static void main(String[] args) throws Throwable {
		try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
			final int count = parseInt(readIn.readLine());
			for (int i = 0; i < count; i++) {
				test(readIn.readLine());
			}
		}
	}
	public static void test(String test) {
		StringBuilder str = new StringBuilder(test);
		// true if it is wilbur's turn
		boolean turn = true;
		while (str.length() > 0) {
			int bestChoice = -1;
			boolean reallyGoodChoice = false;
			for (int i = 0; i < str.length() - 1; i++) {
				if (str.charAt(i) != str.charAt(i + 1)) {
					final int prev = i - 1;
					final int next = i + 1;
					if (prev >= 0 && next < str.length()) {
						if (str.charAt(prev) != str.charAt(next)) {
							break;
						}
					}
					bestChoice = i;
				}
			}
			if (bestChoice == -1) {
				System.out.println(turn? "NET" : "DA");
				return;
			}
			else {
				str.delete(bestChoice, bestChoice + 2);
			}
			turn ^= true;
		}
		System.out.println(turn? "NET" : "DA");
	}
}
