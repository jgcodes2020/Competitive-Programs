package io.github.jgcodes.dmoj.ccc.j2021;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;

import static java.lang.Integer.parseInt;

//j5/s2
public class ModernArt_Old {

  private static class Command {
    final boolean isColumn;
    final int index;

    private Command(boolean isColumn, int index) {
      this.isColumn = isColumn;
      this.index = index;
    }

    private static Command of(String line) {
      boolean a;
      int b;

      String[] tokens = line.split(" ");
      switch (tokens[0]) {
        case "R":
          a = false;
          break;
        case "C":
          a = true;
          break;
        default:
          throw new IllegalArgumentException("what is " + tokens[0]);
      }
      b = parseInt(tokens[1]);
      return new Command(a, b);
    }
  }

  public static void main(String[] args) throws Throwable {
    final int m, n;
    final List<Command> cmds = new ArrayList<>();
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      m = parseInt(readIn.readLine());
      n = parseInt(readIn.readLine());
      final int k = parseInt(readIn.readLine());
      for (int i = 0; i < k; i++) {
        cmds.add(Command.of(readIn.readLine()));
      }
    }
    System.out.println(test(cmds, m, n));
  }

  private static int test(List<Command> commands, int m, int n) {
    if (n == 1) {
      return test1Cell(commands);
    }
    else if (m == 1) {
      return test1Row(commands, n);
    }
    else
      return testGrid(commands, m, n);
  }

  private static int testGrid(List<Command> commands, int m, int n) {
    if (m > 100 || n > 100) return 0;
    BitSet[] rows = new BitSet[m];
    Arrays.setAll(rows, index -> new BitSet(n));

    int count = 0;
    for (Command cmd: commands) {
      final int arrIndex = cmd.index - 1;
      if (cmd.isColumn) {
        for (BitSet row: rows) {
          row.flip(arrIndex);
          if (row.get(arrIndex))
            count++;
          else
            count--;
        }
      }
      else {
        rows[arrIndex].flip(0, n);
        final int bitCount = rows[arrIndex].cardinality();
        count += (bitCount - (n - bitCount));
      }

      System.err.println(onLines(Arrays.stream(rows).map(row ->
        Arrays.stream(row.toLongArray())
          .mapToObj(p -> Long.toBinaryString(Long.reverse(p)))
          .collect(Collector.of(
            () -> new StringBuffer(),
            (sb, s) -> sb.append(s),
            (a, b) -> a.append(b),
            sb -> {
              int len = sb.length();
              return sb.toString().substring(0, Math.min(len, n));
            }
          ))).toArray()));
    }
    return count;
  }

  private static int test1Row(List<Command> commands, int n) {
    long[] mask = new long[2];
    int count = 0;
    for (Command cmd: commands) {
      final int arrIndex = cmd.index - 1;
      if (cmd.isColumn) {
        int arrIdx = arrIndex / 64;
        long toggleMask = (1L << (arrIndex % 64));
        mask[arrIdx] ^= toggleMask;
        if ((mask[arrIdx] & toggleMask) > 0)
          count++;
        else
          count--;

      }
      else {
        //flip all bits
        mask[0] ^= -1L;
        mask[1] ^= -1L;
        count = n - count;
      }
    }
    return count;
  }

  private static int test1Cell(List<Command> commands) {
    return commands.size() % 2;
  }

  private static <T> String onLines(T[] array) {
    return Arrays.stream(array)
      .map(o -> o.toString() + "\n")
      .collect(Collector.of(
        () -> new StringBuffer(),
        (sb, s) -> sb.append(s),
        (a, b) -> a.append(b),
        sb -> sb.toString()
      ));
  }
}
