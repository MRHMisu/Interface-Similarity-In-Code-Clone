package wordservice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wordservice.wordprocess.PascleNameExtractor;
import wordservice.wordprocess.StopwordRemover;
import wordservice.wordprocess.WordStemmer;

public class WordService {

	public static Set<String> getSynonymWords(Set<String> rootWords) {

		Set<String> allRootWordSynonyms = new HashSet<String>();

		for (String word : rootWords) {
			allRootWordSynonyms.addAll(DictionaryService.getSynomyns(word));
		}
		return allRootWordSynonyms;
	}

	public static Set<String> getRootWords(String methodName) {
		Set<String> wordsAfterParsingFromPascleCase = PascleNameExtractor.getWorldListFromPascleCase(methodName);
		Set<String> wordsAfterRemovingStopWords = StopwordRemover.removeStopWord(wordsAfterParsingFromPascleCase);
		Set<String> wordsAfterStemming = WordStemmer.getStemmedWord(wordsAfterRemovingStopWords);
		return wordsAfterStemming;
	}

}
