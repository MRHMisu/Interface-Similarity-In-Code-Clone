package clonecodeprocess;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import model.Method;
import model.RootWordSynonymsModel;

public class CloneComparing {

	public static boolean onlyReturnTypeMatched(Method functionOne,
			Method functionTwo) {

		if (functionOne.getReturnType() != null
				&& functionTwo.getReturnType() != null) {
			if (functionOne.getReturnType().equals(functionTwo.getReturnType())) {
				return true;
			}
		}

		return false;

	}

	public static boolean atLeastOneRootWordMatched(Method functionOne,
			Method functionTwo) {
		boolean result = isASingleRootWordmatch(functionOne, functionTwo);
		return result;
	}

	public static boolean allRootWordMatched(Method functionOne,
			Method functionTwo) {

		boolean result = isAllRootWordmatch(functionOne, functionTwo);
		return result;

	}

	public static boolean atLeastOneSynonymMatched(Method functionOne,
			Method functionTwo) {
		boolean result = isASingleSynonymMatch(functionOne, functionTwo);
		return result;
	}

	public static boolean atLeastOneParameterMatched(Method functionOne,
			Method functionTwo) {
		boolean result = isASingleParameterMatch(functionOne, functionTwo);
		return result;

	}

	public static boolean allParameterMatched(Method functionOne,
			Method functionTwo) {

		boolean result = isAllParameterMatch(functionOne, functionTwo);
		return result;

	}

	public static boolean returnTyeAndAllParametersMatched(Method functionOne,
			Method functionTwo) {

		boolean result = isReturnTyeAndAllParametersMatched(functionOne,
				functionTwo);
		return result;

	}

	public static boolean returnTyeAndAtLeastAsingleParametersMatched(
			Method functionOne, Method functionTwo) {

		boolean result = isReturnTyeAndAtLeastAsingleParametersMatched(
				functionOne, functionTwo);
		return result;

	}

	public static boolean atLeastOneSynonymForAllRootWordMatched(
			Method functionOne, Method functionTwo) {

		if (functionOne.getRootWordSynonymsModel().size() > functionTwo
				.getRootWordSynonymsModel().size()) {

			boolean result = rootWordSynonymsModelMatched(
					functionOne.getRootWordSynonymsModel(),
					functionTwo.getRootWordSynonymsModel());
			return result;
		} else {
			boolean result = rootWordSynonymsModelMatched(
					functionOne.getRootWordSynonymsModel(),
					functionTwo.getRootWordSynonymsModel());
			return result;
		}
	}

	public static boolean returnTypeAndAllRootWordAndAllParameterMatched(
			Method functionOne, Method functionTwo) {

		boolean isReturnTypeMatched = onlyReturnTypeMatched(functionOne,
				functionOne);
		boolean isAllRootWordMatched = allRootWordMatched(functionOne,
				functionTwo);
		boolean isAllParameterMatched = allParameterMatched(functionOne,
				functionOne);
		boolean result = isReturnTypeMatched && isAllRootWordMatched
				&& isAllParameterMatched;
		return result;

	}

	public static boolean returnTypeAndAtleastOneRootWordAndAtLeastOneParameterMatched(
			Method functionOne, Method functionTwo) {
		boolean isReturnTypeMatched = onlyReturnTypeMatched(functionOne,
				functionOne);
		boolean isAtLeastOneRootWordMatched = atLeastOneRootWordMatched(
				functionOne, functionTwo);
		boolean isAtLeastOneParameterMatched = atLeastOneParameterMatched(
				functionOne, functionOne);
		boolean result = isReturnTypeMatched && isAtLeastOneRootWordMatched
				&& isAtLeastOneParameterMatched;
		return result;
	}

	public static boolean returnTypeAndAtLeastOneSynonymForAllRootWordAndAtLeastOneParameterMatched(
			Method functionOne, Method functionTwo) {
		boolean isReturnTypeMatched = onlyReturnTypeMatched(functionOne,
				functionOne);
		boolean isAtLeastOneSynonymForAllRootWordMatched = atLeastOneSynonymForAllRootWordMatched(
				functionOne, functionTwo);
		boolean isAtLeastOneParameterMatched = atLeastOneParameterMatched(
				functionOne, functionOne);
		boolean result = isReturnTypeMatched
				&& isAtLeastOneSynonymForAllRootWordMatched
				&& isAtLeastOneParameterMatched;
		return result;
	}

	public static boolean isClonePairPublic(Method functionOne,
			Method functionTwo) {

		if (functionOne.getModifiers().contains("PUBLIC")
				&& functionTwo.getModifiers().contains("PUBLIC"))
			return true;
		return false;

	}

	public static boolean isClonePairPrivate(Method functionOne,
			Method functionTwo) {

		if (functionOne.getModifiers().contains("PRIVATE")
				&& functionTwo.getModifiers().contains("PRIVATE"))
			return true;
		return false;

	}

	public static boolean isClonePairPublicPrivateCombination(
			Method functionOne, Method functionTwo) {

		if (functionOne.getModifiers().contains("PUBLIC")
				&& functionTwo.getModifiers().contains("PRIVATE"))
			return true;
		if (functionOne.getModifiers().contains("PRIVATE")
				&& functionTwo.getModifiers().contains("PUBLIC"))
			return true;
		return false;

	}

	private static boolean rootWordSynonymsModelMatched(
			Set<RootWordSynonymsModel> large, Set<RootWordSynonymsModel> small) {
		int isFunMatch = 0;
		for (RootWordSynonymsModel rs1 : small) {
			for (RootWordSynonymsModel rs2 : large) {
				if (rs1.equals(rs2)) {
					isFunMatch++;
				}
			}
		}
		if (isFunMatch == small.size()) {
			return true;
		}
		return false;
	}

	private static boolean isASingleSynonymMatch(Method functionOne,
			Method functionTwo) {
		if (functionOne.getAllRootWordsAndSynonymsMerger().size() == 0
				&& functionTwo.getAllRootWordsAndSynonymsMerger().size() == 0) {
			return true;
		}

		Collection commonSynonyms = CollectionUtils.intersection(
				functionOne.getAllRootWordsAndSynonymsMerger(),
				functionTwo.getAllRootWordsAndSynonymsMerger());
		if (commonSynonyms.size() > 0) {
			return true;
		}

		return false;
	}

	private static boolean isASingleParameterMatch(Method functionOne,
			Method functionTwo) {
		if (functionOne.getParameterTypes().size() == 0
				&& functionTwo.getParameterTypes().size() == 0) {
			return true;
		}

		Collection commonParameter = CollectionUtils.intersection(
				functionOne.getParameterTypes(),
				functionTwo.getParameterTypes());
		if (commonParameter.size() > 0) {
			return true;
		}

		return false;
	}

	private static boolean isAllParameterMatch(Method functionOne,
			Method functionTwo) {
		if (functionOne.getParameterTypes().size() == 0
				&& functionTwo.getParameterTypes().size() == 0) {
			return true;
		}
		Collection commonParameter = CollectionUtils.intersection(
				functionOne.getParameterTypes(),
				functionTwo.getParameterTypes());
		if (commonParameter.size() == functionOne.getParameterTypes().size()
				|| commonParameter.size() == functionTwo.getParameterTypes()
						.size()) {
			return true;
		}
		return false;
	}

	private static boolean isASingleRootWordmatch(Method functionOne,
			Method functionTwo) {

		if (functionOne.getRootWordsofMethodName().size() == 0
				&& functionTwo.getRootWordsofMethodName().size() == 0) {
			return true;
		}
		Collection commonRootWord = CollectionUtils.intersection(
				functionOne.getRootWordsofMethodName(),
				functionTwo.getRootWordsofMethodName());
		if (commonRootWord.size() > 0) {
			return true;
		}
		return false;
	}

	private static boolean isAllRootWordmatch(Method functionOne,
			Method functionTwo) {
		if (functionOne.getRootWordsofMethodName().size() == 0
				&& functionTwo.getRootWordsofMethodName().size() == 0) {
			return true;
		}
		Collection commonRootWord = CollectionUtils.intersection(
				functionOne.getRootWordsofMethodName(),
				functionTwo.getRootWordsofMethodName());
		if (commonRootWord.size() == functionOne.getRootWordsofMethodName()
				.size()
				|| commonRootWord.size() == functionTwo
						.getRootWordsofMethodName().size()) {
			return true;
		}
		return false;
	}

	private static boolean isReturnTyeAndAllParametersMatched(
			Method functionOne, Method functionTwo) {

		boolean isReturnTypeMatched = false;
		boolean isParameterMatched = false;
		if (functionOne.getReturnType() != null
				&& functionTwo.getReturnType() != null) {
			if (functionOne.getReturnType().equals(functionTwo.getReturnType())) {
				isReturnTypeMatched = true;
			}
		}

		isParameterMatched = isAllParameterMatch(functionOne, functionTwo);

		boolean result = (isReturnTypeMatched && isParameterMatched);
		;
		return result;
	}

	private static boolean isReturnTyeAndAtLeastAsingleParametersMatched(
			Method functionOne, Method functionTwo) {

		boolean isReturnTypeMatched = false;
		boolean isParameterMatched = false;
		if (functionOne.getReturnType() != null
				&& functionTwo.getReturnType() != null) {
			if (functionOne.getReturnType().equals(functionTwo.getReturnType())) {
				isReturnTypeMatched = true;
			}
		}

		isParameterMatched = isASingleParameterMatch(functionOne, functionTwo);

		boolean result = (isReturnTypeMatched && isParameterMatched);
		;
		return result;
	}

}
