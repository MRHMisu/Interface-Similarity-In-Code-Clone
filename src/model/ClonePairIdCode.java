package model;

public class ClonePairIdCode {
	private int functionOneId;
	private String funtionOneCode;
	private int funtionTwoId;
	private String funtionTwoCode;

	public ClonePairIdCode(int functionOneId, String funtionOneCode,
			int funtionTwoId, String funtionTwoCode) {
		this.functionOneId = functionOneId;
		this.funtionOneCode = funtionOneCode;
		this.funtionTwoId = funtionTwoId;
		this.funtionTwoCode = funtionTwoCode;
	}

	public int getFunctionOneId() {
		return functionOneId;
	}

	public String getFuntionOneCode() {
		return funtionOneCode;
	}

	public int getFuntionTwoId() {
		return funtionTwoId;
	}

	public String getFuntionTwoCode() {
		return funtionTwoCode;
	}

	
	
	

}
