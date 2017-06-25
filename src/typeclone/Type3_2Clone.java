package typeclone;
import java.io.IOException;
import java.util.List;

import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.PairFunctionIdPathStartEnd;
import model.Result;
import support.FileUtil;
import clonecodeprocess.CloneCodeProcessUtil;

public class Type3_2Clone {

	public static void main(String[] args) throws IOException {

		String projectName = "ant";
		String type1ClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type1.xml";
		List<PairFunctionIdPathStartEnd> type1CloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type1ClonePairXMLPath);
		System.out.println("type1--"+type1CloneList.size());
		
		String type2ClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type2.xml";
		List<PairFunctionIdPathStartEnd> type2CloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type2ClonePairXMLPath);
		System.out.println("type2--"+type2CloneList.size());
		
		String type3_2ClonePairXMLPath = "/media/misu/MS/Masters/PaperDataset/36Projects/type-clone-projects/ant/ant-type3-2.xml";
		List<PairFunctionIdPathStartEnd> type3_2CloneList = FileUtil
				.getClonePairFunctionIdPathStartEnd(type3_2ClonePairXMLPath);
		System.out.println("type3-2--"+type3_2CloneList.size());

		
		type3_2CloneList.removeAll(type1CloneList);
		System.out.println("after removing type1--"+type3_2CloneList.size());
		type3_2CloneList.removeAll(type2CloneList);

		System.out.println("after removing type2--"+type3_2CloneList.size());

		List<ClonePairIdCode> clonePairIdCode = CloneCodeProcessUtil
				.getClonePairIdCode(type3_2CloneList);
		List<ClonePairMethod> clonePairMethods = CloneCodeProcessUtil
				.getClonePairMethod(clonePairIdCode);
		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = projectName + "," + result.toString();
		System.out.println(output);
		// FileUtil.writeToFile(output, resultPath);

	}
}
