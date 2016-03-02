import java.util.HashMap;
import java.util.Map;

/**
 * Created by Faraz on 2/7/16.
 *
 * Represents a Trie Data structure
 */
public class Trie {
    protected TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;
            //set leaf node
            if (i == word.length() - 1)
                t.isLeaf = true;
        }
    }
}