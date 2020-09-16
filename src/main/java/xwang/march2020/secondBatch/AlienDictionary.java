package xwang.march2020.secondBatch;

import org.junit.jupiter.api.Test;

import java.util.*;

import static utils.PrintUtils.printArray;

/*
 * 5/8/20
 * leetcode # 269. Alien Dictionary
 * link: https://leetcode.com/problems/alien-dictionary/
 * tags:
 * level: medium
 */
public class AlienDictionary {
/*    There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

            Example 1:

    Input:
            [
            "wrt",
            "wrf",
            "er",
            "ett",
            "rftt"
            ]

    Output: "wertf"
    Example 2:

    Input:
            [
            "z",
            "x"
            ]

    Output: "zx"
    Example 3:

    Input:
            [
            "z",
            "x",
            "z"
            ]

    Output: ""

    Explanation: The order is invalid, so return "".
    Note:

    You may assume all letters are in lowercase.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.*/

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        if (words.length == 1) return words[0];
        // build the graph
        List<Character> letters = new ArrayList<>();
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (int i = 1; i < words.length; i++) {
            int j = 0;
            for (; j < words[i-1].length() && j < words[i].length(); j++) {
                if (!letters.contains(words[i-1].charAt(j))) letters.add(words[i-1].charAt(j));
                if (!letters.contains(words[i].charAt(j))) letters.add(words[i].charAt(j));
                if (words[i].charAt(j) != words[i-1].charAt(j)) {
                    Set<Character> set = graph.getOrDefault(words[i-1].charAt(j), new HashSet<>());
                    set.add(words[i].charAt(j));
                    graph.put(words[i-1].charAt(j), set);
                    break;
                }
            }
            if (j < words[i-1].length() && j == words[i].length()) return "";
            for (int k = j; k < words[i-1].length(); k++) {
                if (!letters.contains(words[i-1].charAt(k))) letters.add(words[i-1].charAt(k));
            }
            for (int k = j; k < words[i].length(); k++) {
                if (!letters.contains(words[i].charAt(k))) letters.add(words[i].charAt(k));
            }
        }
        if (graph.isEmpty()) return "";
        System.out.println("graph: " + graph);
        System.out.println("letters: " + letters);

        // topological sort
        int[] orders = new int[letters.size()];

        Arrays.fill(orders, -1); // never visited: -1, pending visiting: -2
        for (int i = 0; i < letters.size(); i++) {
            if (sort(letters, i, graph, orders) < 0) {
                return "";
            }
        }
        printArray(orders);

        // post-processing
        StringBuilder orderSb = new StringBuilder();
        for (int i = letters.size() - 1; i >= 0; i--) {
            for (int j = 0; j < orders.length; j++) {
                if (orders[j] == i) {
                    orderSb.append(letters.get(j));
                }
            }

        }
        return orderSb.toString();
    }

    private int sort(List<Character> letters, int root, Map<Character, Set<Character>> graph, int[] orders) {
//        System.out.println("order for letter: " + letters.get(root));
//        printArray(orders);
        if (orders[root] >= 0) return orders[root] + 1;
        if (orders[root] == -2) return -1;

        Set<Character> children = graph.get(letters.get(root));
        if (children == null || children.isEmpty()) {
            orders[root] = 0;
            return 1;
        }
        orders[root] = -2;
        int order = 0;
        for (Character child : children) {
            int index = letters.indexOf(child);
            int numOfChild = sort(letters, index, graph, orders);
            if (numOfChild >= 0) {
                order = Math.max(order, numOfChild);
            } else {
                return -1;
            }
        }
        orders[root] = order;
        return order + 1;
    }


    /*
    Input:
            [
            "wrt",
            "wrf",
            "er",
            "ett",
            "rftt"
            ]

    Output: "wertf"

    w -> e
    t -> f
    r -> t
    e -> r

    w e t f r
    0 0 0 0 0



    */

    @Test
    public void test() {
        System.out.println(this.alienOrder(new String[] {"abc","ab"}));
    }
}
