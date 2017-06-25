package model;

public class FunctionIdPathStarEnd {

	private int functionId;
	private String path;
	private int startLineNo;
	private int endLineNo;

	public FunctionIdPathStarEnd(int functionId, String path, int startLineNo,
			int endLineNo) {
		this.functionId = functionId;
		this.path = path;
		this.startLineNo = startLineNo;
		this.endLineNo = endLineNo;
	}

	public int getFunctionId() {
		return functionId;
	}

	public String getPath() {
		return path;
	}

	public int getStartLineNo() {
		return startLineNo;
	}

	public int getEndLineNo() {
		return endLineNo;
	}

	@Override
	public int hashCode() {

		int hash = 7;
		hash = 31 * hash + this.startLineNo;
		hash = 31 * hash + this.endLineNo;
		hash = 31 * hash + this.functionId;
		hash = 31 * hash + (null == this.path ? 0 : this.path.hashCode());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		FunctionIdPathStarEnd test = (FunctionIdPathStarEnd) obj;
		if ((this.path.equals(test.path)
				&& (this.startLineNo == test.startLineNo)
				&& (this.endLineNo == test.endLineNo) && (this.functionId == test.functionId)))
			return true;
		return false;
	}
}
