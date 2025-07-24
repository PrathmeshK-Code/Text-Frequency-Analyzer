import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class AnalyzerDriver {
	public static void main(String[] args) {
		ProbeHashMap<String, Integer> wordFrequency = new ProbeHashMap<>();
		ProbeHashMap<Character, Integer> charFrequency = new ProbeHashMap<>();
		ArrayList<String> words = new ArrayList<>(); 
		ArrayList<String> chars = new ArrayList<>(); 
		
		System.out.println("Text Analyzer");

		try {
			Scanner kb = new Scanner(new File("PartA.txt"));
			while(kb.hasNextLine()) {
				String[] line = (kb.nextLine().split("[^a-zA-Z]+"));

				ArrayList<String> lineAL = new ArrayList<>();

				for(int i = 0; i<line.length; i++) {
					if(!line[i].equals("")) lineAL.add(line[i].toLowerCase());
				}

				words.addAll(lineAL);
			}

			for(int i=0;i<words.size(); i++) {
				String[] charInWord = words.get(i).split("");
				Collections.addAll(chars, charInWord);
			}
		}catch(IOException e) {
			System.out.println("File not found!!");		}
		
		for(int i=0;i<words.size();i++) {
			Integer frequency = wordFrequency.get(words.get(i));
			
			if(frequency!=null) {
				wordFrequency.put(words.get(i), ++frequency);
			}else {
				wordFrequency.put(words.get(i), 1);

			}
		}
		
		for(int i=0;i<chars.size();i++) {
			Integer frequency = charFrequency.get(chars.get(i).charAt(0));
			
			if(frequency!=null) {
				charFrequency.put(chars.get(i).charAt(0), ++frequency);
			}else {
				charFrequency.put(chars.get(i).charAt(0), 1);

			}
		}
		
		System.out.println("Total number of distinct words: "+wordFrequency.size());
		System.out.println("Total number of distinct letters: "+charFrequency.size());
		
		Entry<Character, Integer> maxChar = findMaxLeast(charFrequency, new OrderLettersByFrequency(), true);
		System.out.println("Most occuring character: "+ maxChar.getKey() + ", "+ maxChar.getValue());
		
		Entry<Character, Integer> minChar = findMaxLeast(charFrequency, new OrderLettersByFrequency(), false);
		System.out.println("Least occuring character: "+ minChar.getKey() + ", "+ minChar.getValue());
		
		Entry<String, Integer> maxWord = findMaxLeast(wordFrequency, new OrderWordsByFrequency(), true);
		System.out.println("Most occuring word: "+ maxWord.getKey() + ", "+ maxWord.getValue());
		
		Entry<String, Integer> minWord = findMaxLeast(wordFrequency, new OrderWordsByFrequency(), false);
		System.out.println("Least occuring word: "+ minWord.getKey() + ", "+ minWord.getValue());
		
		ArrayList<String> pronouns = new ArrayList<>();
		Collections.addAll(pronouns, "i", "we", "you", "he", "she", "it", "they");		
		
		Entry<String, Integer> maxPro = findCategoryMaxLeast(wordFrequency, new OrderWordsByFrequency(), true, pronouns);
		System.out.println("Most occurring pronoun: " + maxPro.getKey() + ", "+ maxPro.getValue());
		
		Entry<String, Integer> minPro = findCategoryMaxLeast(wordFrequency, new OrderWordsByFrequency(), false, pronouns);
		System.out.println("Least occurring pronoun: " + minPro.getKey() + ", "+ minPro.getValue());
		
		
		
	}
	
	public static <K,V> Entry<K,V> findMaxLeast(ProbeHashMap<K,V> map, Comparator comp, boolean max) {
		
		AbstractMap.MapEntry<K,V>[] entryA = new AbstractMap.MapEntry[map.size()];
		int count = 0;
		for(Entry<K,V> k: map.entrySet()) {
			entryA[count++] = (AbstractMap.MapEntry) k;
		}
		MergeSort<AbstractMap.MapEntry<K,V>> mergeSort = new MergeSort<>(comp);
		
		AbstractMap.MapEntry<K,V>[] sorted = mergeSort.sort(entryA);
		if(max) {
			return sorted[sorted.length-1];
		}
		return sorted[0];
	}

	public static <K,V> Entry<K,V> findCategoryMaxLeast(ProbeHashMap<K,V> map, Comparator comp, boolean max, ArrayList<K> keys){
		// array here due to the possibilities of different scenarios like
		// for instance, conditions like when only few of the keys' elements are presents in map, this will make some elements in the entriA array null
		// However, i will still use array as it is the requirement of the assignment
		AbstractMap.MapEntry<K,V>[] entryA = new AbstractMap.MapEntry[keys.size()];
		
		// not using arrayList
		//ArrayList<AbstractMap.MapEntry<K,V>> entryA = new ArrayList<>();
		int count = 0;
		for(Entry<K,V> k: map.entrySet()) {
			
			if(keys.contains(k.getKey())) entryA[count++] = (AbstractMap.MapEntry) k;
			
			// following is the code we would use if we used arrayLists
			//if(keys.contains(k.getKey())) entryA.add((AbstractMap.MapEntry) k);

		}
		
		MergeSort<AbstractMap.MapEntry<K,V>> mergeSort = new MergeSort<>(comp);
		AbstractMap.MapEntry<K,V>[] sorted = mergeSort.sort(entryA);
		if(max) {
			return sorted[sorted.length-1];
		}
		return sorted[0];
	}
}
