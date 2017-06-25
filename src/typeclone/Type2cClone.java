package typeclone;
import java.io.IOException;
import java.util.List;

import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.PairFunctionIdPathStartEnd;
import model.Result;
import support.FileUtil;
import clonecodeprocess.CloneCodeProcessUtil;

public class Type2cClone {

	public static void main(String[] args) throws IOException {

		String projectName = "ant";
		String type1ClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type1.xml";
		List<PairFunctionIdPathStartEnd> type1CloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type1ClonePairXMLPath);
		System.out.println(type1CloneList.size());
		String type2ClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type2c.xml";
		List<PairFunctionIdPathStartEnd> type2CloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type2ClonePairXMLPath);
		System.out.println(type2CloneList.size());

		type2CloneList.removeAll(type1CloneList);

		System.out.println(type2CloneList.size());

		List<ClonePairIdCode> clonePairIdCode = CloneCodeProcessUtil
				.getClonePairIdCode(type2CloneList);
		List<ClonePairMethod> clonePairMethods = CloneCodeProcessUtil
				.getClonePairMethod(clonePairIdCode);
		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = projectName + "," + result.toString();
		System.out.println(output);
		// FileUtil.writeToFile(output, resultPath);

	}
}
