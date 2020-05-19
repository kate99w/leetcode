package firstBatch;

import org.junit.jupiter.api.Test;

import java.util.*;

/*
 * 5/8/20
 * leetcode # 127. Word Ladder
 *            126. Word Ladder II
 * link: https://leetcode.com/problems/word-ladder/
 *       https://leetcode.com/problems/word-ladder-ii/
 * tags:
 * level: medium, hard
 */
public class WordLadder {
/*    Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list.
            Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.
    Example 1:

    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    Output: 5

    Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
            return its length 5.
    Example 2:

    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    Output: 0

    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

    hit
    hot
    dot, lot
    dog,log

    dog, log
    cog


    */


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // pre-processing, key is matching pattern and value is words in list
        Map<String, List<String>> dic = new HashMap<>();
        for (String s : wordList) {
            for (int i = 0; i < s.length(); i++) {
                String pattern = i < s.length() - 1 ? s.substring(0,i) + '*' + s.substring(i + 1)
                        : s.substring(0,i) + '*'; // remove i from s
                List<String> list = dic.getOrDefault(pattern, new ArrayList<>());
                list.add(s);
                dic.put(pattern, list);
            }
        }
        Map<String, Integer> visited = new HashMap<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.offerLast(beginWord);
        int level = 1;
        visited.put(beginWord, level);

        // for bi-direction
        if (!wordList.contains(endWord)) return 0;
        Map<String, Integer> vEnd = new HashMap<>();
        Deque<String> qEnd = new ArrayDeque<>();
        qEnd.offerLast(endWord);
        int lEnd = 1;
        vEnd.put(endWord, lEnd);

        // start the loop
        while(!queue.isEmpty() || !qEnd.isEmpty()) {
            // do from the end
            if (!qEnd.isEmpty()) {
                int sEnd = qEnd.size();
                while (sEnd > 0) {
                    String curEnd = qEnd.pollFirst();
                    if (visited.containsKey((curEnd))) return visited.get(curEnd) + lEnd - 1;
                    for(int i = 0; i < curEnd.length(); i++) {
                        String pattern = i < curEnd.length() - 1 ? curEnd.substring(0,i) + '*' + curEnd.substring(i + 1)
                                : curEnd.substring(0,i) + '*';
                        List<String> list = dic.getOrDefault(pattern, new ArrayList<>());
                        for (String transfer : list) {
                            if (!vEnd.containsKey(transfer)) {
                                vEnd.put(transfer, lEnd + 1);
                                qEnd.offerLast(transfer);
                            }
                        }
                    }
                    sEnd--;
                }
                lEnd++;
            }
            // do from the begin
            if (!queue.isEmpty() ) {
                int size = queue.size();
                while(size > 0) {
                    String cur = queue.pollFirst();
                    if (vEnd.containsKey(cur)) return vEnd.get(cur) + level - 1;
                    for(int i = 0; i < cur.length(); i++) {
                        String pattern = i < cur.length() - 1 ? cur.substring(0,i) + '*' + cur.substring(i + 1)
                                : cur.substring(0,i) + '*';
                        List<String> list = dic.getOrDefault(pattern, new ArrayList<>());
                        for (String transfer : list) {
                            if (!visited.containsKey(transfer)) {
                                visited.put(transfer, level + 1);
                                queue.offerLast(transfer);
                            }
                        }
                    }
                    size--;
                }

                level++;
            }
        }
        return 0;
    }

    @Test
    public void test() {
        System.out.println(
                this.ladderLength("talk", "tail",
                        Arrays.asList("talk","tons","fall","tail","gale","hall","negs")));

        System.out.println(
                this.ladderLength("hit", "cog",
                        Arrays.asList("hot","dot","dog","lot","log","cog")));

        System.out.println(
                this.ladderLength("hog", "cog",
                        Arrays.asList("cog")));
    }

}
