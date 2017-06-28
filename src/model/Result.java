package model;

import java.util.List;

import clonecodeprocess.CloneComparing;

public class Result {

	List<ClonePairMethod> pairMethod;
	int numberOfClones = 0;

	float returnTypesAreSimilar = 0;
	float numberAndTypesOfParametersAreSameOrSimilar = 0;
	float atLeastOneParameterIsSimilar = 0;
	float returnTypesAndParametersAreSimilar = 0;
	float returnTypesAndAtLeastOneParameterAreSimilar = 0;
	float allRootWordsAreSimilar = 0;
	float atLeastOneRootWordIsSimilar = 0;
	float atLeastOneSynonymIsSimilar = 0;
	float atLeastOneSynonymForAllRootWordsIsSimilar = 0;
	float returnTypesAndAllRootWordsAndAllParametersAreSimilar = 0;
	float returnTypesAndAtleastOneRootWordAndAtLeastOneParameterAreSimilar = 0;
	float returnTypesAndAtLeastOneSynonymForAllRootWordsAndAtLeastOneParameterAreSimilar = 0;
	float isClonePairPublic = 0;
	float isClonePairPrivate = 0;
	float isClonePairPublicPrivateCombination = 0;

	public Result(List<ClonePairMethod> pairMethod) {
		this.pairMethod = pairMethod;
		this.numberOfClones = pairMethod.size();
	}

	public void generateResult() {
		onlyReturnTypeMatched();
		onlyReturnTypeAndAllParametersMatched();
		onlyReturnTypeAndAtLeastASingleParameterMatched();
		atLeastOneRootWordMatched();
		allRootWordMatched();
		atLeastOneSynonymMatched();
		atLeastOneParameterMatched();
		allParameterMatched();
		atLeastOneSynonymForAllRootWordMatched();
		returnTypeAndAllRootWordAndAllParameterMatched();
		returnTypeAndAtleastOneRootWordAndAtLeastOneParameterMatched();
		returnTypeAndAtLeastOneSynonymForAllRootWordAndAtLeastOneParameterMatched();
		isClonePairPublic();
		isClonePairPrivate();
		isClonePairPublicPrivateCombination();
	}

	@Override
	public String toString() {
		String result = numberOfClones
				+ ","
				+ returnTypesAreSimilar
				+ ","
				+ numberAndTypesOfParametersAreSameOrSimilar
				+ ","
				+ atLeastOneParameterIsSimilar
				+ ","
				+ returnTypesAndParametersAreSimilar
				+ ","
				+ returnTypesAndAtLeastOneParameterAreSimilar
				+ ","
				+ atLeastOneRootWordIsSimilar
				+ ","
				+ allRootWordsAreSimilar
				+ ","
				+ atLeastOneSynonymIsSimilar
				+ ","
				+ atLeastOneSynonymForAllRootWordsIsSimilar
				+ ","
				+ returnTypesAndAllRootWordsAndAllParametersAreSimilar
				+ ","
				+ returnTypesAndAtleastOneRootWordAndAtLeastOneParameterAreSimilar
				+ ","
				+ returnTypesAndAtLeastOneSynonymForAllRootWordsAndAtLeastOneParameterAreSimilar
				+ "," + isClonePairPublic + "," + isClonePairPrivate + ","
				+ isClonePairPublicPrivateCombination;
		return result;
	}

	private void onlyReturnTypeAndAllParametersMatched() {

		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.returnTyeAndAllParametersMatched(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			returnTypesAndParametersAreSimilar = (count / totoalCount) * 100;
	}

	private void onlyReturnTypeAndAtLeastASingleParameterMatched() {

		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing
					.returnTyeAndAtLeastAsingleParametersMatched(
							pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			returnTypesAndAtLeastOneParameterAreSimilar = (count / totoalCount) * 100;
	}

	private void onlyReturnTypeMatched() {

		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.onlyReturnTypeMatched(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			returnTypesAreSimilar = (count / totoalCount) * 100;
	}

	private void atLeastOneRootWordMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.atLeastOneRootWordMatched(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			atLeastOneRootWordIsSimilar = (count / totoalCount) * 100;
	}

	private void allRootWordMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.allRootWordMatched(pm.getFunctionOne(),
					pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			allRootWordsAreSimilar = (count / totoalCount) * 100;
	};

	private void atLeastOneSynonymMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.atLeastOneSynonymMatched(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			atLeastOneSynonymIsSimilar = (count / totoalCount) * 100;
	}

	private void atLeastOneParameterMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.atLeastOneParameterMatched(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			atLeastOneParameterIsSimilar = (count / totoalCount) * 100;
	}

	private void allParameterMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.allParameterMatched(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			numberAndTypesOfParametersAreSameOrSimilar = (count / totoalCount) * 100;
	}

	private void atLeastOneSynonymForAllRootWordMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.atLeastOneSynonymForAllRootWordMatched(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			atLeastOneSynonymForAllRootWordsIsSimilar = (count / totoalCount) * 100;
	}

	private void returnTypeAndAllRootWordAndAllParameterMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing
					.returnTypeAndAllRootWordAndAllParameterMatched(
							pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			returnTypesAndAllRootWordsAndAllParametersAreSimilar = (count / totoalCount) * 100;

	}

	private void returnTypeAndAtleastOneRootWordAndAtLeastOneParameterMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing
					.returnTypeAndAtleastOneRootWordAndAtLeastOneParameterMatched(
							pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			returnTypesAndAtleastOneRootWordAndAtLeastOneParameterAreSimilar = (count / totoalCount) * 100;

	}

	private void returnTypeAndAtLeastOneSynonymForAllRootWordAndAtLeastOneParameterMatched() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.isClonePairPublicPrivateCombination(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			returnTypesAndAtLeastOneSynonymForAllRootWordsAndAtLeastOneParameterAreSimilar = (count / totoalCount) * 100;

	}

	private void isClonePairPublic() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.isClonePairPublic(pm.getFunctionOne(),
					pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			isClonePairPublic = (count / totoalCount) * 100;
	}

	private void isClonePairPrivate() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.isClonePairPrivate(pm.getFunctionOne(),
					pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			isClonePairPrivate = (count / totoalCount) * 100;
	}

	private void isClonePairPublicPrivateCombination() {
		float count = 0;
		float totoalCount = this.pairMethod.size();
		for (ClonePairMethod pm : this.pairMethod) {
			boolean rs = CloneComparing.isClonePairPublicPrivateCombination(
					pm.getFunctionOne(), pm.getFunctionTwo());
			if (rs)
				count++;
		}
		if (totoalCount != 0)
			isClonePairPublicPrivateCombination = (count / totoalCount) * 100;
	}

}
