// This program performs spell checking based on a spell-checker dictionary.

import java.util.*;

public class SpellChecker {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);

		int numberOfCorrectWords = sc.nextInt();
		HashSet<String> correctWords = new HashSet<String>();

		for (int i=0; i<numberOfCorrectWords; i++){
			correctWords.add(sc.next());
		}

		HashMap<String, Vector<String>> spellCheckerDictionary = generateSpellCheckerDictionary(correctWords);
		spellCheck(correctWords, spellCheckerDictionary, sc.next());
	}

	public static HashMap<String, Vector<String>> generateSpellCheckerDictionary(HashSet<String> correctWords){
		HashMap<String, Vector<String>> spellCheckerDictionary = new HashMap<String, Vector<String>>();

		for (String word:correctWords){
			HashSet<String> misspelledWords = generateMisspelledWords(word);

			for (String misspelledWord:misspelledWords){
				Vector<String> corrections = (spellCheckerDictionary.containsKey(misspelledWord))?spellCheckerDictionary.get(misspelledWord):new Vector<String>();
				corrections.add(word);
				spellCheckerDictionary.put(misspelledWord, corrections);
			}
		}

		return spellCheckerDictionary;
	}

	public static HashSet<String> generateMisspelledWords(String word){
		HashSet<String> misspelledWords = new HashSet<String>();

		for (int i=0; i<word.length(); i++){
			misspelledWords.add(word.substring(0,i)+word.substring(i+1, word.length()));
		}

		for (int i=0; i<=word.length();i++){
			for (int j=0; j<ALPHABET.length();j++){
				misspelledWords.add(word.substring(0,i)+ALPHABET.charAt(j)+word.substring(i, word.length()));
			}
		}

		for (int i=0; i<word.length()-1;i++){
			misspelledWords.add(word.substring(0,i)+word.charAt(i+1)+word.charAt(i)+word.substring(i+2,word.length()));
		}

		for (int i=0; i<word.length();i++){
			for (int j=0; j<ALPHABET.length();j++){
				if (word.charAt(i)!=ALPHABET.charAt(j)){
					misspelledWords.add(word.substring(0,i)+ALPHABET.charAt(j)+word.substring(i+1, word.length()));
				}
			}
		}
		return misspelledWords;
	}

	public static void spellCheck(HashSet<String> correctWords, HashMap<String, Vector<String>> spellCheckerDictionary, String word){
		if (correctWords.contains(word)){
			System.out.println("OK");
		} else if (!spellCheckerDictionary.containsKey(word)){
			System.out.println("Not found");
		} else {
			Vector<String> possibleCorrections = spellCheckerDictionary.get(word);
			Collections.sort(possibleCorrections);

			System.out.println("Possible corrections:");
			for (String correction:possibleCorrections){
				System.out.println(correction);
			}
		}
	}
}
