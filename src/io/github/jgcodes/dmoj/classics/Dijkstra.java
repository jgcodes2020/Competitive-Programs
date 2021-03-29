package io.github.jgcodes.dmoj.classics;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Dijkstra {
  private static class Pair<A, B> extends java.util.AbstractMap.SimpleImmutableEntry<A, B> {
    public Pair(A key, B value) { super(key, value); }

    public A first() { return getKey(); }

    public B second() { return getValue(); }
  }

  public static void main(String[] args) {
    final Map<Integer, Map<Integer, Integer>> graph;
    final int vertices, edges;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final Map<Integer, Map<Integer, Integer>> tmp = new HashMap<>();
      {
        final String[] line = readIn.readLine().split(" ");
        vertices = Integer.parseInt(line[0]);
        edges = Integer.parseInt(line[1]);
      }
      for (int i = 0; i < edges; i++) {
        final int u, v, weight;
        {
          final String[] line = readIn.readLine().split(" ");
          u = Integer.parseInt(line[0]);
          v = Integer.parseInt(line[1]);
          weight = Integer.parseInt(line[2]);
        }
        // add map if non-existent
        tmp.computeIfAbsent(u, key -> new HashMap<>());
        tmp.computeIfAbsent(v, key -> new HashMap<>());
        // in case of identical edges, take the minimum
        // shorter edge should always lead to shorter paths
        tmp.get(u).merge(v, weight, Math::min);
        tmp.get(v).merge(u, weight, Math::min);
      }
      graph = Collections.unmodifiableMap(tmp);
    } catch (IOException e) {
      System.err.println("i/o error, how?");
      return;
    }

    // Dijkstra's algorithm, graph is an adjacency list. (Map<Integer, Map<Integer, Integer>>)
    // Adjacency list maps node to adjacent nodes and their edge's respective weights.

    // init Dijkstra
    int[] dist = new int[vertices + 1];
    // use -1 to indicate unreachable
    dist[1] = 0;
    Arrays.fill(dist, 2, vertices + 1, -1);
    // keep track of what we settled
    Set<Integer> settled = new HashSet<>();
    // Priority queue
    PriorityQueue<Pair<Integer, Integer>> searchPQ =
      new PriorityQueue<>(Comparator.comparingInt(Pair::second));
    searchPQ.add(new Pair<>(1, 0));
    // Repeat until all nodes are processed
    while (settled.size() < vertices) {
      // pick a node from PQ
      System.out.print(searchPQ + " + ");
      System.out.println(settled);
      int next = searchPQ.remove().getKey();
      // mark this node as settled
      settled.add(next);
      // for each neighbour of next
      for (Entry<Integer, Integer> tmp: graph.get(next).entrySet()) {
        Pair<Integer, Integer> node = toPair(tmp);
        //if node has not been settled
        if (!settled.contains(node.first())) {
          // calculate new distance
          final int nDist = dist[next] + node.second();
          // check if new path is shorter and update
          if (Integer.compareUnsigned(nDist, dist[node.first()]) < 0) {
            dist[node.first()] = nDist;
          }
          // add this node (with current dist) to the PQ
          searchPQ.add(new Pair<>(node.first(), dist[node.first()]));
        }
      }
    }

    for (int i = 1; i <= vertices; i++) {
      System.out.println(dist[i]);
    }
  }

  private static <A, B> Pair<A, B> toPair(Entry<A, B> entry) {
    return new Pair<>(entry.getKey(), entry.getValue());
  }
}
