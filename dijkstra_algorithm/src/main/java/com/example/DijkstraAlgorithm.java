package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DijkstraAlgorithm {
   public void DijkstraAlgorithm2() {
   }

   public static int[] dijkstra(int start, List<List<Node>> graph, int vertices, Map<Integer, Integer> previousNodes) {
      int[] distances = new int[vertices];
      boolean[] visited = new boolean[vertices];
      Arrays.fill(distances, Integer.MAX_VALUE);
      distances[start] = 0;
      previousNodes.clear();

      for(int i = 0; i < vertices; ++i) {
         int u = -1;

         for(int j = 0; j < vertices; ++j) {
            if (!visited[j] && (u == -1 || distances[j] < distances[u])) {
               u = j;
            }
         }

         if (distances[u] == Integer.MAX_VALUE) {
            break;
         }

         visited[u] = true;
         Iterator var12 = ((List)graph.get(u)).iterator();

         while(var12.hasNext()) {
            Node neighbor = (Node)var12.next();
            int v = neighbor.vertex;
            int weight = neighbor.weight;
            if (distances[u] + weight < distances[v]) {
               distances[v] = distances[u] + weight;
               previousNodes.put(v, u);
            }
         }
      }

      return distances;
   }

   public static List<Character> getPath(int target, Map<Integer, Integer> previousNodes, Map<Integer, Character> indexToChar) {
      List<Character> path = new LinkedList();

      for(Integer current = target; current != null; current = (Integer)previousNodes.get(current)) {
         path.add((Character)indexToChar.get(current));
      }

      Collections.reverse(path);
      return path;
   }

   public static void main(String[] args) {
      int vertices = 1000;
      int edges = 1500;
      Random random = new Random(0L);
      Map<Character, Integer> charToIndex = new HashMap();
      Map<Integer, Character> indexToChar = new HashMap();

      int i;
      for( i = 0; i < vertices; ++i) {
         i = (char)(65 + i);
         charToIndex.put(Character.valueOf((char)i), i);
         indexToChar.put(i, Character.valueOf((char)i));
      }

      List<List<Node>> graph = new ArrayList();

      for(i = 0; i < vertices; ++i) {
         graph.add(new ArrayList());
      }

      for(i = 0; i < edges; ++i) {
         int u = random.nextInt(vertices);
         int v = random.nextInt(vertices);
         int weight = random.nextInt(100) + 1;
         ((List)graph.get(u)).add(new Node(v, weight));
      }

      Map<Integer, Integer> previousNodes = new HashMap();
      long startTime = System.currentTimeMillis();
      int[] distances = dijkstra(0, graph, vertices, previousNodes);
      long endTime = System.currentTimeMillis();
      System.out.println("Execution Time: " + (endTime - startTime) + " ms");
      char target = 'G';
      int targetIndex = (Integer)charToIndex.get(target);
      System.out.println("Shortest distance to vertex " + target + ": " + distances[targetIndex]);
      List<Character> path = getPath(targetIndex, previousNodes, indexToChar);
      System.out.println("Shortest path to " + target + ": " + String.valueOf(path));
   }
}


