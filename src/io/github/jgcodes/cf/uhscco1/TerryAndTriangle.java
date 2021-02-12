package io.github.jgcodes.cf.uhscco1;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class TerryAndTriangle {
	public static void main(String[] args) throws Throwable {
		try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
			final int count = parseInt(readIn.readLine());
			for (int i = 0; i < count; i++) {
				// space separate + convert to array
				int[] nums = Arrays.stream(
					readIn.readLine().split(" ")
				).mapToInt(Integer::parseInt).toArray();
				test(nums[0], nums[1], nums[2], nums[3]);
			}
		}
	}

	public static void test(int a, int b, int c, int d) {
		for (int x = a; x <= b; x++) {
			for (int y = b; y <= c; y++) {
				int sum = a + b;
				if (c <= sum && sum <= d) {
					System.out.printf("%d %d %d\n", a, b, c);
					return;
				}
			}
		}
	}
}
