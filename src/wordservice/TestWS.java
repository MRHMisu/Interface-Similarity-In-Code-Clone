package wordservice;

import java.util.Set;

public class TestWS {

	public static void main(String[] args) {
		String methodName="matches";
		Set<String> rootWordsofMethodName = WordService.getRootWords(methodName);
		System.out.println(rootWordsofMethodName);
		Set<String> sysonyms = WordService.getSynonymWords(rootWordsofMethodName);
		System.out.println(sysonyms);
	}

}
