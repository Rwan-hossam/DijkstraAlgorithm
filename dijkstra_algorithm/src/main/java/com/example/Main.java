package com.example;
import com.example.DijkstraAlgorithm;
import com.example.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
         int vertices = 1000; 
        int edges = 1500; 
        Random random = new Random(0);

        Map<Character, Integer> charToIndex = new HashMap<>();
        Map<Integer, Character> indexToChar = new HashMap<>();
        
        
        for (int i = 0; i < vertices; i++) {
            char letter = (char) ('A' + i);
            charToIndex.put(letter, i);
            indexToChar.put(i, letter);
        }

        
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);
            int weight = random.nextInt(100) + 1; 
            graph.get(u).add(new Node(v, weight));
        }

        Map<Integer, Integer> previousNodes = new HashMap<>();

        long startTime = System.currentTimeMillis();
        int[] distances =DijkstraAlgorithm. dijkstra(0, graph, vertices, previousNodes);
        long endTime = System.currentTimeMillis();

        System.out.println("Execution Time: " + (endTime - startTime) + " ms");

      
        char target = 'G';
        int targetIndex = charToIndex.get(target);
        System.out.println("Shortest distance to vertex " + target + ": " + distances[targetIndex]);

        
        List<Character> path =DijkstraAlgorithm. getPath(targetIndex, previousNodes, indexToChar);
        System.out.println("Shortest path to " + target + ": " + path);
    }
    }
