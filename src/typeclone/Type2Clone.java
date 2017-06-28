package typeclone;

import java.io.IOException;
import java.util.List;

import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.PairFunctionIdPathStartEnd;
import model.Result;
import support.FileUtil;
import support.PropertyFileLoader;
import clonecodeprocess.CloneCodeProcessUtil;

public class Type2Clone {

	public static void main(String[] args) throws IOException {

		String projectListPath = PropertyFileLoader
				.getRequiredPropertyPathByPropertyName("projectListPath_SF110");
		List<String> projectList = FileUtil.getProjectList(projectListPath);
		String basePath = "/media/misu/MS/Masters/PaperDataset/SF110/type-clone-projects/";
		String resultPath = "/media/misu/MS/Masters/PaperDataset/SF110/SF110project_CloneResult_Type2.csv";

		for (String projectName : projectList) {

			String type1ClonePairXMLPath = basePath + projectName + "/"
					+ projectName + "-type1.xml";
			List<PairFunctionIdPathStartEnd> type1CloneList = FileUtil
					.getClonePairFunctionIdPathStartEnd(type1ClonePairXMLPath);
			System.out.println(type1CloneList.size());
			String type2ClonePairXMLPath = basePath + projectName + "/"
					+ projectName + "-type2.xml";
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
			FileUtil.writeToFile(output, resultPath);

		}

	}
}
