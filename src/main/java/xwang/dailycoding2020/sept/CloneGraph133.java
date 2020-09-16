package xwang.dailycoding2020.sept;

import org.junit.jupiter.api.Test;
import utils.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;

public class CloneGraph133 {

    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Node copy = map.get(node);
        if (copy == null) {
            copy = new Node(node.val, new ArrayList<Node>());
            map.put(node, copy);
            for (Node n : node.neighbors) {
                copy.neighbors.add(cloneGraph(n));
            }
        }
        return copy;
    }

    @Test
    public void test() {
        Node node = new Node(0);
        Node copied = cloneGraph(node);
        assertSame(node.val, copied.val);
    }
}
