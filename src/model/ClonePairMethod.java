package model;

public class ClonePairMethod {

	private Method functionOne;
	private Method functionTwo;
	public ClonePairMethod(Method functionOne, Method functionTwo) {
		this.functionOne = functionOne;
		this.functionTwo = functionTwo;
	}

	public Method getFunctionOne() {
		return functionOne;
	}

	public Method getFunctionTwo() {
		return functionTwo;
	}

}
