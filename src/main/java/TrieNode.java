import java.util.HashMap;

/**
 * Created by Faraz on 2/7/16.
 *
 * Represents a Trie Node
 * @author Faraz
 */
public class TrieNode {
    protected char c;
    protected HashMap<Character, TrieNode> children = new HashMap<>();
    protected boolean isLeaf;

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }

}
