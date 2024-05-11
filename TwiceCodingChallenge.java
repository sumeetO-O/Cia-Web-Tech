import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwiceCodingChallenge {

	private static final String FILENAME = "english.0";     // Replace with file name
	private static Set<String> dictionary = new HashSet<String>();

	public TwiceCodingChallenge(){       
		constructDictionary();
	}
	
	public static void main(String[] args) {
		//Uncomment to add sample words to dictionary. For Basic Testing.
		//addSampleWordsToDictionary(); 
		System.out.println( new TwiceCodingChallenge().jumbleWord("dog"));
	}


	public Set<String> jumbleWord(String word) throws IllegalArgumentException {
		if(word==null){
			throw new IllegalArgumentException("Input word is Null");
		}
		//Remove white spaces if any
		word = word.trim();
		if(word.length()==0){
			throw new IllegalArgumentException("Empty String.There are no empty words in the dictionary");
		}
		//Confirm there are no two words in input
		if(word.split(" ").length>1){
			throw new IllegalArgumentException("Only one word allowed");
		}
		 //This is to hold and calculate all unique permutations of String
		Set<String> wordPermutationList = new HashSet<String>();   
		//This is to hold all unique words that are also present in the dictionary
		Set<String> validWordSet = new HashSet<String>();      
		
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);
			//Temporary list for holding permutation for a single character
			List<String> listToBeAddedToWordLists = new ArrayList<String>();
			for (String wordInList : wordPermutationList) {
				for (int j = 0; j <= wordInList.length(); j++) {
					String potentialWord = insertCharIntoPosition(wordInList, j,
							character);
					listToBeAddedToWordLists.add(potentialWord);
					//Make necessary checks and add to valid result set
					checkIfWordIsValidAndAdd(validWordSet,potentialWord,word);
				}
			}
			//Adding all permutations that were gathered into the main set.
			wordPermutationList.addAll(listToBeAddedToWordLists);
			wordPermutationList.add(String.valueOf(character));
			//This is to include single character words such as "a", "I" etc.
			checkIfWordIsValidAndAdd(validWordSet, String.valueOf(character),word);
		}
		return validWordSet;
	}

	//This method inserts a specific character at a specfied point 'j' in the string
  	private String insertCharIntoPosition(String initWord, int insertPosition,char charToBeInserted) {
		StringBuilder returnValue= new StringBuilder();
		returnValue.append(initWord.substring(0, insertPosition));
		returnValue.append(charToBeInserted + initWord.substring(insertPosition));
		return returnValue.toString();
	}
	
	//Method that adds word to ValidWordset if the word is present in the dictionary
	private void checkIfWordIsValidAndAdd(Set<String> wordsInDictionary, String potentialWord,String word) {
		if(dictionary==null || dictionary.size()==0){
			wordsInDictionary.add(potentialWord);
		}
		//Also check whether the valid word is same as the initial word and if so do not add.
		if (dictionary.contains(potentialWord.toLowerCase()) && !potentialWord.equalsIgnoreCase(word))
			wordsInDictionary.add(potentialWord);
	}


	private static void constructDictionary() {
		if(dictionary==null){
			dictionary = new HashSet<String>();
		}
		File f = new File(FILENAME);
		try {
			BufferedReader input = new BufferedReader(new FileReader(f));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					dictionary.add(line.toLowerCase());
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			System.out.println("File not found. Dictionary will be empty!! Uncomment addSampleWordsToDictionary in constructor to test.");
		}
	}
	
	
	private static void addSampleWordsToDictionary() {
		if(dictionary==null){
			dictionary = new HashSet<String>();
		}
		System.out.println("Adding sample words");
		dictionary.add("god");
		dictionary.add("do");
		dictionary.add("go");
	}

}