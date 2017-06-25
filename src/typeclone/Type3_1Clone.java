package typeclone;
import java.io.IOException;
import java.util.List;

import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.PairFunctionIdPathStartEnd;
import model.Result;
import support.FileUtil;
import clonecodeprocess.CloneCodeProcessUtil;

public class Type3_1Clone {

	public static void main(String[] args) throws IOException {

		String projectName = "ant";
		String type1ClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type1.xml";
		List<PairFunctionIdPathStartEnd> type1CloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type1ClonePairXMLPath);
		System.out.println(type1CloneList.size());
		String type3_1ClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type3-1.xml";
		List<PairFunctionIdPathStartEnd> type3_1CloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type3_1ClonePairXMLPath);
		System.out.println(type3_1CloneList.size());

		type3_1CloneList.removeAll(type1CloneList);

		System.out.println(type3_1CloneList.size());

		List<ClonePairIdCode> clonePairIdCode = CloneCodeProcessUtil
				.getClonePairIdCode(type3_1CloneList);
		List<ClonePairMethod> clonePairMethods = CloneCodeProcessUtil
				.getClonePairMethod(clonePairIdCode);
		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = projectName + "," + result.toString();
		System.out.println(output);
		// FileUtil.writeToFile(output, resultPath);

	}
}
