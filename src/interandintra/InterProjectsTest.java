import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import clonecodeprocess.CloneCodeProcessUtil;
import model.ClonePairId;
import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.FunctionIdPathStarEnd;
import model.Result;
import support.FileUtil;
import support.PropertyFileLoader;

public class InterProjectsTest {

	public static void main(String[] args) throws IOException {

		String commonDirectoryPath = "/media/misu/MW/CodeCloneData/SF110/";
		String clonePairIDPath = "/home/misu/Desktop/CloneOutputList_100_6line_80_Inter/SF100Inter/ClonePairID_Similarity_80.txt";
		String methodIDPath = "/home/misu/Desktop/CloneOutputList_100_6line_80_Inter/SF100Inter/PathID.txt";
		String outputInterProjectClonePairId = "/home/misu/Desktop/CloneOutputList_100_6line_80_Inter/SF100Inter/interProjectClonePairId.txt";
		String outputResultPath = "/home/misu/Desktop/CloneOutputList_100_6line_80_Inter/SF100Inter/100InterProjectCloneResult.csv";

		List<FunctionIdPathStarEnd> idPathStartEnd = FileUtil
				.getFunctionIdPathStartEnd(methodIDPath);

		List<ClonePairId> interProjectClonePairId = findInterProjectClonePairID(
				commonDirectoryPath, clonePairIDPath, methodIDPath);

		List<String> idList = new ArrayList<String>();
		for (ClonePairId cid : interProjectClonePairId) {
			idList.add(cid.getFunctionOneId() + "," + cid.getFuntionTwoId());
		}
		FileUtil.writeToFile(idList, outputInterProjectClonePairId);

		runTest(interProjectClonePairId, idPathStartEnd, outputResultPath);

	}

	public static void runTest(List<ClonePairId> idPairs,
			List<FunctionIdPathStarEnd> idPathStartEnd, String resultPath)
			throws IOException {
		List<ClonePairIdCode> clonePairIdCode = CloneCodeProcessUtil
				.getClonePairIdCode(idPairs, idPathStartEnd);
		List<ClonePairMethod> clonePairMethods = CloneCodeProcessUtil
				.getClonePairMethod(clonePairIdCode);
		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = "interProject" + "," + result.toString();
		FileUtil.writeToFile(output, resultPath);

	}

	public static List<ClonePairId> findInterProjectClonePairID(
			String commonDirectoryPath, String clonePairIDPath,
			String methodIDPath) {

		List<ClonePairId> interProjectClonePairId = new ArrayList<ClonePairId>();
		List<ClonePairId> idPairs = FileUtil.getConePairsId(clonePairIDPath);
		List<FunctionIdPathStarEnd> idPathStartEnd = FileUtil
				.getFunctionIdPathStartEnd(methodIDPath);

		for (ClonePairId cpi : idPairs) {
			FunctionIdPathStarEnd firstFunction = CloneCodeProcessUtil
					.findFunctionIdPathStarEnd(cpi.getFunctionOneId(),
							idPathStartEnd);
			FunctionIdPathStarEnd twoFunction = CloneCodeProcessUtil
					.findFunctionIdPathStarEnd(cpi.getFuntionTwoId(),
							idPathStartEnd);
			if (!isSameProjectPath(firstFunction.getPath(),
					twoFunction.getPath(), commonDirectoryPath)) {
				interProjectClonePairId.add(cpi);
			}

		}
		return interProjectClonePairId;

	}

	public static boolean isSameProjectPath(String firstFunctionPath,
			String secondFunctionPath, String commonDirectoryPath) {
		String firstPart = firstFunctionPath.split(commonDirectoryPath)[1];
		String secondPart = secondFunctionPath.split(commonDirectoryPath)[1];

		String projectNameFirstFunction = firstPart.split("/")[0];
		String projectNameSecondFunction = secondPart.split("/")[0];
		if (projectNameFirstFunction.equals(projectNameSecondFunction)) {
			return true;
		}
		return false;
	}

}
