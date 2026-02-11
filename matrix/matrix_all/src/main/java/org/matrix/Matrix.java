package org.sadovodBase;

import java.util.Arrays;

public class DijkstraMatrix {

    private static final int UNREACHABLE = 1_000_000_000;

    public static void main(String[] args) {

        int[][] graph = {
                {0, 7, 9, UNREACHABLE, UNREACHABLE, 14},
                {7, 0, 10, 15, UNREACHABLE, UNREACHABLE},
                {9, 10, 0, 11, UNREACHABLE, 2},
                {UNREACHABLE, 15, 11, 0, 6, UNREACHABLE},
                {UNREACHABLE, UNREACHABLE, UNREACHABLE, 6, 0, 9},
                {14, UNREACHABLE, 2, UNREACHABLE, 9, 0}
        };

        findShortestPaths(graph, 0);
    }

    static void findShortestPaths(int[][] graph, int source) {
        int size = graph.length;

        int[] shortest = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(shortest, UNREACHABLE);
        shortest[source] = 0;

        for (int step = 0; step < size; step++) {

            int current = getNearestVertex(shortest, visited);

            if (current == -1) break;

            visited[current] = true;

            for (int next = 0; next < size; next++) {
                if (!visited[next] && graph[current][next] != UNREACHABLE) {
                    int newDistance = shortest[current] + graph[current][next];
                    if (newDistance < shortest[next]) {
                        shortest[next] = newDistance;
                    }
                }
            }
        }

        printResult(shortest);
    }

    private static int getNearestVertex(int[] dist, boolean[] used) {
        int best = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!used[i] && (best == -1 || dist[i] < dist[best])) {
                best = i;
            }
        }
        return best;
    }

    private static void printResult(int[] distances) {
        for (int i = 0; i < distances.length; i++) {
            char from = 'A';
            char to = (char) (from + i);
            System.out.println("From A to " + to + " : " + distances[i]);
        }
    }
}
