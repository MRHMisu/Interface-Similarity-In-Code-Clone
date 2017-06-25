package model;

import java.util.Set;

public class RootWordSynonymsModel {

	private String rootWords;
	private Set<String> sysnonymsWords;

	public RootWordSynonymsModel(String rootWords, Set<String> sysnonymsWords) {

		this.rootWords = rootWords;
		this.sysnonymsWords = sysnonymsWords;
	}

	public String getRootWords() {
		return rootWords;
	}

	public Set<String> getSysnonymsWords() {
		return sysnonymsWords;
	}

	@Override
	public int hashCode() {

		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		RootWordSynonymsModel comparableObject = (RootWordSynonymsModel) obj;
		if (this.rootWords.equals(comparableObject.rootWords))
			return true;
		else if (this.sysnonymsWords.contains(comparableObject.rootWords))
			return true;
		else if (comparableObject.sysnonymsWords.contains(this.rootWords))
			return true;

		return false;
	}
	

}
