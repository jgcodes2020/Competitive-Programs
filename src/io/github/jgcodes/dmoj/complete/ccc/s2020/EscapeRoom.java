package io.github.jgcodes.dmoj.complete.ccc.s2020;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Math.*;

public class EscapeRoom {
	public static void main(String[] args) {
		// input
		int[][] board;
		int rows, columns;
		try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
			rows = parseInt(readIn.readLine());
			columns = parseInt(readIn.readLine());

			board = new int[rows][];

			for (int i = 0; i < rows; i++) {
				board[i] = Arrays.stream(readIn.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
		} catch (IOException e) {
			System.err.println("i/o error, how?");
			return;
		}
		// processing

		if (rows == 1 && columns == 1) {
			System.out.println("yes");
			return;
		}

		Queue<Integer> queue = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();

		queue.add(board[0][0]);
		visited.add(board[0][0]);

		while (!queue.isEmpty()) {
			int cell = queue.remove();

			List<IntPair> factors = genFactorPairs(cell).stream()
				.filter(pair -> pair.x <= columns && pair.y <= rows)
				.collect(Collectors.toUnmodifiableList());
			for (IntPair pair: factors) {
				if (pair.x == columns && pair.y == rows) {
					System.out.println("yes");
					return;
				}
				int val = pair.valueAt(board);
				if (!visited.contains(val)) {
					visited.add(val);
					queue.add(val);
				}
			}
		}
		System.out.println("no");
	}

	public static List<IntPair> genFactorPairs(int n) {
		if (n < 2) return Collections.emptyList();

		List<IntPair> result = new ArrayList<>();
		result.add(new IntPair(1, n));
		result.add(new IntPair(n, 1));
		for (int i = 2; i <= sqrt(n); i++) {
			if (n % i == 0) {
				final int q = n / i;
				result.add(new IntPair(i, q));
				if (i != q)
					result.add(new IntPair(q, i));
			}
		}
		return result;
	}
}

class IntPair {
	public final int x;
	public final int y;

	IntPair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int valueAt(int[][] grid) {
		return grid[y - 1][x - 1];
	}

	@Override
	public String toString() {
		return "IntPair{" +
			"x=" + x +
			", y=" + y +
			'}';
	}
}
