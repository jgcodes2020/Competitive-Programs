package io.github.jgcodes.cf.uhscco1;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CookiesAndTwoBrothers {
	public static void main(String[] args) throws Throwable {
		try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
			final int count = parseInt(readIn.readLine());
			for (int i = 0; i < count; i++) {
				final long n = parseLong(readIn.readLine());
				System.out.println(((n + 1) / 2) - 1);
			}
		}
		catch (NumberFormatException e) {
			System.out.println("definitely wrong answer");
		}
	}
}
