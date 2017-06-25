package model;

import java.util.List;

import clonecodeprocess.CloneComparing;

public class Result {

	List<ClonePairMethod> pairMethod;
	int numberOfClones = 0;
	float onlyReturnTypeMatched = 0;
	float atLeastOneRootWordMatched = 0;
	float allRootWordMatched = 0;
	float atLeastOneSynonymMatched = 0;
	float atLeastOneParameterMatched = 0;
	float allParameterMatched = 0;
	float atLeastOneSynonymForAllRootWordMatched = 0;
	float returnTypeAndAllRootWordAndAllParameterMatched = 0;
	float returnTypeAndAtleastOneRootWordAndAtLeastOneParameterMatched = 0;
	float returnTypeAndAtLeastOneSynonymForAllRootWordAndAtLeastOneParameterMatched = 0;
	float isClonePairPublic = 0;
	float isClonePairPrivate = 0;
	float isClonePairPublicPrivateCombination = 0;

	public Result(List<ClonePairMethod> pairMethod) {
		this.pairMethod = pairMethod;
		this.numberOfClones = pairMethod.size();
	}

	public void generateResult() {
		onlyReturnTypeMatched();
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
				+ onlyReturnTypeMatched
				+ ","
				+ atLeastOneRootWordMatched
				+ ","
				+ allRootWordMatched
				+ ","
				+ atLeastOneSynonymMatched
				+ ","
				+ atLeastOneParameterMatched
				+ ","
				+ allParameterMatched
				+ ","
				+ atLeastOneSynonymForAllRootWordMatched
				+ ","
				+ returnTypeAndAllRootWordAndAllParameterMatched
				+ ","
				+ returnTypeAndAtleastOneRootWordAndAtLeastOneParameterMatched
				+ ","
				+ returnTypeAndAtLeastOneSynonymForAllRootWordAndAtLeastOneParameterMatched
				+ "," + isClonePairPublic + "," + isClonePairPrivate + ","
				+ isClonePairPublicPrivateCombination;
		return result;
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
			onlyReturnTypeMatched = (count / totoalCount) * 100;
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
			atLeastOneRootWordMatched = (count / totoalCount) * 100;
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
			allRootWordMatched = (count / totoalCount) * 100;
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
			atLeastOneSynonymMatched = (count / totoalCount) * 100;
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
			atLeastOneParameterMatched = (count / totoalCount) * 100;
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
			allParameterMatched = (count / totoalCount) * 100;
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
			atLeastOneSynonymForAllRootWordMatched = (count / totoalCount) * 100;
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
			returnTypeAndAllRootWordAndAllParameterMatched = (count / totoalCount) * 100;

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
			returnTypeAndAtleastOneRootWordAndAtLeastOneParameterMatched = (count / totoalCount) * 100;

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
			returnTypeAndAtLeastOneSynonymForAllRootWordAndAtLeastOneParameterMatched = (count / totoalCount) * 100;

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
