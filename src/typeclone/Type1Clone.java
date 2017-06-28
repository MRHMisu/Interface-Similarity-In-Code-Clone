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

public class Type1Clone {

	public static void main(String[] args) throws IOException {

		String projectListPath = PropertyFileLoader
				.getRequiredPropertyPathByPropertyName("projectListPath_SF110");
		List<String> projectList = FileUtil.getProjectList(projectListPath);
		String basePath = "/media/misu/MS/Masters/PaperDataset/SF110/type-clone-projects/";
		String resultPath = "/media/misu/MS/Masters/PaperDataset/SF110/SF110project_CloneResult_Type1.csv";

		for (String projectName : projectList) {
			String clonePairXMLPath = basePath + projectName + "/"
					+ projectName + "-type1.xml";

			List<PairFunctionIdPathStartEnd> list = FileUtil
					.getClonePairFunctionIdPathStartEnd(clonePairXMLPath);
			List<ClonePairIdCode> clonePairIdCode = CloneCodeProcessUtil
					.getClonePairIdCode(list);
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
