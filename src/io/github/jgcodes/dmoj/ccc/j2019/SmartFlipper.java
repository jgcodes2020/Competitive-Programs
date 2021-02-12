package io.github.jgcodes.dmoj.ccc.j2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SmartFlipper {
	public static void main(String[] args) {
		boolean h = false, v = false;
		try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
			readIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	private static void swap(int[] array, int a, int b) {
		final int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}
