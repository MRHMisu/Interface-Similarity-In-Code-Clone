package typeclone;
import java.io.IOException;
import java.util.List;

import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.PairFunctionIdPathStartEnd;
import model.Result;
import support.FileUtil;
import clonecodeprocess.CloneCodeProcessUtil;

public class Type3_2cClone {

	public static void main(String[] args) throws IOException {

		String projectName = "ant";
		String type1ClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type1.xml";
		List<PairFunctionIdPathStartEnd> type1CloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type1ClonePairXMLPath);
		System.out.println("type1--" + type1CloneList.size());

		String type2cClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type2c.xml";
		List<PairFunctionIdPathStartEnd> type2cCloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type2cClonePairXMLPath);
		System.out.println("type2--" + type2cCloneList.size());

		String type3_2cClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type3-2c.xml";
		List<PairFunctionIdPathStartEnd> type3_2cCloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type3_2cClonePairXMLPath);
		System.out.println("type3-2--" + type3_2cCloneList.size());

		type3_2cCloneList.removeAll(type1CloneList);
		System.out.println("after removing type1--" + type3_2cCloneList.size());
		type3_2cCloneList.removeAll(type2cCloneList);

		System.out.println("after removing type2--" + type3_2cCloneList.size());

		List<ClonePairIdCode> clonePairIdCode = CloneCodeProcessUtil
				.getClonePairIdCode(type3_2cCloneList);
		List<ClonePairMethod> clonePairMethods = CloneCodeProcessUtil
				.getClonePairMethod(clonePairIdCode);
		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = projectName + "," + result.toString();
		System.out.println(output);
		// FileUtil.writeToFile(output, resultPath);

	}
}
