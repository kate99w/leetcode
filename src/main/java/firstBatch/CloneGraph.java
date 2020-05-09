package firstBatch;

import utils.Node;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * 5/8/20
 * leetcode # 133. Clone Graph
 * link: https://leetcode.com/problems/clone-graph/
 * tags:
 * level: medium
 */
public class CloneGraph {

    /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, HashMap<Node, Node> map) {
        Node copy = map.get(node);
        if (copy == null) {
            copy = new Node(node.val);
            copy.neighbors = new ArrayList<>();
            map.put(node, copy);
            for (Node n : node.neighbors) {
                copy.neighbors.add(dfs(n, map));
            }
        }
        return copy;
    }
}
