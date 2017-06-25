package model;

import java.util.List;
import java.util.Set;

public class Method {
	private Set<String> modifiers;
	private String methodName;
	private String returnType;
	private List<String> parameterTypes;
	private Set<String> rootWordsofMethodName;
	private Set<RootWordSynonymsModel> rootWordSynonymsModel;
	private Set<String> allRootWordsAndSynonymsMerger;

	public Method(Set<String> modifiers, String methodName, String returnType, List<String> parameterTypes,
			Set<String> rootWordsofMethodName, Set<RootWordSynonymsModel> rootWordSynonymsModel,Set<String> allRootWordsAndSynonymsMerger) {
		this.modifiers = modifiers;
		this.methodName = methodName;
		this.returnType = returnType;
		this.parameterTypes = parameterTypes;
		this.rootWordsofMethodName = rootWordsofMethodName;
		this.rootWordSynonymsModel = rootWordSynonymsModel;
		this.allRootWordsAndSynonymsMerger=allRootWordsAndSynonymsMerger;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public List<String> getParameterTypes() {
		return parameterTypes;
	}
	

	public Set<String> getModifiers() {
		return modifiers;
	}

	public Set<String> getRootWordsofMethodName() {
		return rootWordsofMethodName;
	}

	public Set<RootWordSynonymsModel> getRootWordSynonymsModel() {
		return rootWordSynonymsModel;
	}

	public Set<String> getAllRootWordsAndSynonymsMerger() {
		return allRootWordsAndSynonymsMerger;
	}
}
