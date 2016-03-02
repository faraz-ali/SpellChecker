
import java.io.*;
import java.util.*;


/**
 * Created by Faraz on 2/7/16.
 * <p>
 *
 * A custom Spell Checker.
 * Dictionary taken from : http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt
 *
 * @author Faraz
 */
public class SpellChecker {

    private Trie trie = new Trie();
    private Set<Character> vowels = new HashSet<>();
    private StringBuilder correctWord = null;

    public SpellChecker() {
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
    }

    public static void main(String[] args) throws IOException {
        SpellChecker checker = new SpellChecker();
        checker.buildTrieFromDictionary();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = null;
        System.out.println("Welcome to the Spell Checker, please enter a word for a spelling suggestion. " +
                "\nType exit to exit the program");
        do {
            try {
                checker.setCorrectWord(new StringBuilder());
                System.out.print("> ");
                word = br.readLine();
            } catch (IOException ioe) {
                System.out.println("IO error trying to read your name!");
            }
            System.out.print("> ");
            if (checker.search(word)) {
                System.out.println(checker.getCorrectWord());
            } else {
                System.out.println("NO SUGGESTION");
            }

        } while (!word.equalsIgnoreCase("exit"));

    }

    public StringBuilder getCorrectWord() {
        return correctWord.reverse();
    }

    public void setCorrectWord(StringBuilder correctWord) {
        this.correctWord = correctWord;
    }

    public void buildTrieFromDictionary() {
        try {
            File file = new File("src/main/resources/dictionary_en.txt");
            InputStream in = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String word;
            while ((word = reader.readLine()) != null) {
                trie.insert(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches the trie for the given word,
     *
     * @param word
     * @return true if found, false otherwise
     */
    public boolean search(String word) {
        word = word.toLowerCase();
        if (trie.root.children.containsKey(word.charAt(0))) {
            TrieNode child = trie.root.children.get(word.charAt(0));
            return matcher(child, word, 1);
        }
        return false;
    }

    /**
     * Performs a match based on the following rules
     * 1. Repeated letters: jjooobbb -> job
     * 2. Replace vowels -> peeple -> people
     * 3. Case insensitive
     */
    private boolean matcher(TrieNode node, String word, int index) {

        if (node == null) {
            return false;
        }

        if (node != null && node.isLeaf && index == word.length()) {
            correctWord.append(node.c);
            return true;
        }

        if (index == word.length()) {
            return false;
        }
        char c = word.charAt(index);

        boolean flag = false;
        if (node.children.containsKey(c)) {
            flag = matcher(node.children.get(c), word, index + 1);
            if (flag) {
                correctWord.append(node.c);
                return flag;
            }

        }
        //repeated
        if (c == word.charAt(index - 1)) {
            flag = matcher(node, word, index + 1);
            if (flag) {
                return flag;
            }
        }
        //vowel
        if (vowels.contains(c)) {
            char[] replaced = word.toCharArray();
            for (char vowel : vowels) {
                if (vowel != word.charAt(index)) {
                    replaced[index] = vowel;
                    flag = matcher(node.children.get(vowel), new String(replaced), index + 1);
                    if (flag) {
                        correctWord.append(node.c);
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

}
