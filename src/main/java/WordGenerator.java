import java.util.*;

/**
 * Created by Faraz on 2/7/16.
 * <p>
 * Random Misspelled Word Generator
 *
 * @author Faraz
 */
public class WordGenerator {

    SpellChecker checker = null;

    public static void main(String[] args) {
        WordGenerator generator = new WordGenerator();

        generator.printer("people");
        generator.printer("wake");
        generator.printer("sheep");
        generator.printer("Conspiracy");
        generator.printer("job");
        generator.printer("inside");
    }

    public WordGenerator() {
        checker = new SpellChecker();
        checker.buildTrieFromDictionary();
    }


    /**
     * Takes in a correct word, generates a misspelled word
     * and suggests a corrected word.
     * Prints out all three on system out.
     *
     * @param correctWord
     */
    public void printer(String correctWord) {
        String misspelledWord = generateMisspelledWord(correctWord);
        String suggestedWord = checkMissplledWord(misspelledWord);

        System.out.println("Input word: " + correctWord
                + "\nGenerated misspelled word: " + misspelledWord
                + "\nSuggested word: " + suggestedWord);
        System.out.println("*******");
    }

    public String checkMissplledWord(String misspelledWord) {
        checker.setCorrectWord(new StringBuilder());
        if (checker.search(misspelledWord)) {
            return checker.getCorrectWord().toString();
        } else {
            return "NO SUGGESTION";
        }
    }

    /**
     * 1. Repeated letters
     * 2. Wrong vowels
     * 3. Mixed upper and lower Cases
     * <p>
     * Rules:
     * correctly spelled english word -> generateBadWord -> correct it.
     * should not produce No Suggestions
     *
     * @param word
     * @return
     */
    public String generateMisspelledWord(String word) {
        StringBuilder sb = new StringBuilder();
        List<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        Random r = new Random();
        r.nextInt(vowels.size());

        int max = 3;

        int flag = r.nextInt(2); // 0 or 1
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (vowels.contains(c)) {
                if (flag == 0) {
                    char v = vowels.get(r.nextInt(vowels.size()));
                    sb.append(v);
                }
                else {
                    int repeat = r.nextInt(max);
                    for (int j = 0; j <= repeat; j++) {
                        sb.append(c);
                    }
                }
            } else {
                int repeat = r.nextInt(max);
                for (int j = 0; j <= repeat; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
