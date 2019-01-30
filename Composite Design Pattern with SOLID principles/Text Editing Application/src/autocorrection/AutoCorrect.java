package autocorrection;

import java.util.*;
import java.io.*;

public class AutoCorrect {

    private String wordsFileName = "words.txt"; // Word list text file
    private String[] words; // Word list array
    private Search search; // For binary search
    private Stack<String> suggestions;
    
    public AutoCorrect() {
    	suggestions = new Stack<String>(50);
    	search = new Search();
    	words = new String[0];
    }
    
    public void initialize() {
        loadWords();
        sort(words);
    }

    /**
     * Loads a word list text file into memory for fast access.
     *
     */
    public void loadWords() {
        String line = null; // Temp variable for storing one line at a time
        ArrayList<String> temp = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(wordsFileName);
            BufferedReader buffReader = new BufferedReader(fileReader);

            while ((line = buffReader.readLine()) != null) {
                temp.add(line.trim());
            }

            buffReader.close();
            words = new String[temp.size()];
            temp.toArray(words);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts any E[] using insertion sort.
     *
     * @param <E> Type of Object to use.
     * @param array The E[] to be sorted in place.
     */
    public <E extends Comparable<E>> void sort(E[] array) {
        int n = array.length; // Get length of array

        // Insertion sort
        for (int i = 1; i < n; i++) {
            E temp = array[i]; // Save the element at index i
            int j = i - 1; // Let j be the element one index before i

            // Iterate through array
            while (j > -1 && (array[j].compareTo(temp) > 0)) {
                // Insert element at array[j] in proper place
                array[j + 1] = array[j];
                j--;
            }

            // Complete swap
            array[j + 1] = temp;
        }
    }

    /**
     * Searches for textWord in word list.<br>
     * Returns the word itself if word is spelled correctly.<br>
     * Returns a suggestion if word is close to a correct word.<br>
     * PrReturnsints "No suggestions." if word is clearly obscure.
     *
     */
    public String autoCorrect(String textWord) { 	
        int result = search.binarySearch(words, textWord);

        // First, check for an exact match
        if (result != -1) {
            /*System.out.println("Correct. Text looks fine!"
                + "\n");*/
        	return textWord;
        }
        // Else, check if the text's word is an anagram of a dictionary word
        else {
            for (String word : words) {
                char wordStart; // First char of word
                char textWordStart; // First char of textWord
                if (!word.isEmpty()) { // If word is NOT empty
                    wordStart = word.charAt(0);
                    textWordStart = textWord.charAt(0);

                    // if (userWordStart == wordStart) // Same starting char
                        if (textWord.length() == word.length()) // Same length
                            if (containsAllChars(textWord, word)) // Same chars
                                suggestions.push(word);
                }
            }

            if (suggestions.isEmpty()) {
                return "No suggestions.";
            }
            else {
                //System.out.print("Suggestions: ");
            	String suggestion = "";
                while (!suggestions.isEmpty()) {
                    suggestion += suggestions.pop() + " ";
                }
                return suggestion;
            }
        }
    }

    /**
     * Converts a String to its integer ASCII value.
     *
     * @param str String to convert.
     * @return int Returns integer ASCII value.
     */
    public int toInt(String str) {
        int asciiValue = 0;
        for (int i = 0; i < str.length(); i++) {
            asciiValue += str.charAt(i);
        }

        return asciiValue;
    }

    /**
     * Checks if strTwo contains exclusively all characters from strOne.
     *
     * @param strOne String to pull chars from.
     * @param strTwo String to check for containment.
     * @return boolean True if strTwo contains all chars of strOne.
     */
    public boolean containsAllChars(String strOne, String strTwo) {
        Character[] one = strToCharArray(strOne);
        Character[] two = strToCharArray(strTwo);

        sort(one);
        sort(two);

        for (int i = 0; i < one.length; i++) {
            if (search.binarySearch(two, one[i]) == -1)
                return false;
            two[i] = '0';
        }

        two = strToCharArray(strTwo);
        sort(two);

        for (int i = 0; i < two.length; i++) {
            if (search.binarySearch(one, two[i]) == -1)
                return false;
            one[i] = '0';
        }

        return true;
    }

    /**
     * Converts a String into a Character array.
     *
     * @param str String to convert.
     * @return Character[] Returns a Character[].
     */
    public Character[] strToCharArray(String str) {
        Character[] charArray = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            charArray[i] = new Character(str.charAt(i));
        }

        return charArray;
    }
}