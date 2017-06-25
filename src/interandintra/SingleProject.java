import java.io.IOException;
import java.util.List;

import clonecodeprocess.CloneCodeProcessUtil;
import model.ClonePairId;
import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.FunctionIdPathStarEnd;
import model.Result;
import support.FileUtil;
import support.PropertyFileLoader;

public class SingleProject {

	public static void main(String[] args) {
		
		String projectName = "ant";
		String basePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("basePath");
		String resultPath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("resultPath");
		String clonePairIDPath = basePath + "\\" + projectName + "\\" + "ClonePairID.txt";
		String methodIDPath = basePath + "\\" + projectName + "\\" + "PathID.txt";
		try {
			runTest(projectName, clonePairIDPath, methodIDPath, resultPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void runTest(String projectName, String clonePairIDPath, String methodIDPath, String resultPath)
			throws IOException {

		List<ClonePairId> idPairs = FileUtil.getConePairsId(clonePairIDPath);
		List<FunctionIdPathStarEnd> idPathStartEnd = FileUtil.getFunctionIdPathStartEnd(methodIDPath);
		
		
		
		List<ClonePairIdCode> clonePairIdCode = CloneCodeProcessUtil.getClonePairIdCode(idPairs, idPathStartEnd);
		List<ClonePairMethod> clonePairMethods = CloneCodeProcessUtil.getClonePairMethod(clonePairIdCode);
		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = projectName + "," + result.toString();
		FileUtil.writeToFile(output, resultPath);

	}

}
