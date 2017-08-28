package ijdataset;

import interandintra.SingleProject;

import java.io.IOException;
import java.util.List;

import model.ClonePairId;
import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.FunctionIdPathStarEnd;
import model.Result;
import support.FileUtil;
import clonecodeprocess.CloneCodeProcessUtil;
import clonecodeprocess.CloneComparing;

public class Type1 {

	public static void main(String[] args) throws IOException {

		String cloneType = "Type2";
		String basePath = "/media/misu/MS/Masters/PaperDataset/IJDataSet";
		String resultPath = basePath + "/" + cloneType + "/"
				+ "Type2OneRootWordNotMatch_UnderScoreRemoval.txt";
		String clonePairIDPath = basePath + "/" + cloneType + "/" + cloneType
				+ "ClonePairID.txt";
		String methodIDPath = basePath + "/" + cloneType + "/" + cloneType
				+ "ClonePathID.txt";

		runTest(cloneType, clonePairIDPath, methodIDPath, resultPath);

	}

	public static void runTest(String cloneType, String clonePairIDPath,
			String methodIDPath, String resultPath) throws IOException {

		List<ClonePairId> idPairs = FileUtil.getConePairsId(clonePairIDPath);
		List<FunctionIdPathStarEnd> idPathStartEnd = FileUtil
				.getFunctionIdPathStartEnd(methodIDPath);

		List<ClonePairIdCode> clonePairIdCode = CloneCodeProcessUtil
				.getClonePairIdCode(idPairs, idPathStartEnd);
		List<ClonePairMethod> clonePairMethods = CloneCodeProcessUtil
				.getClonePairMethod(clonePairIdCode);

		String output = null;
		for (ClonePairMethod x : clonePairMethods) {

			if (!CloneComparing.atLeastOneRootWordMatched(x.getFunctionOne(),
					x.getFunctionTwo())) {
				String returnType = x.getFunctionOne().getReturnType() + ","
						+ x.getFunctionTwo().getReturnType();
				String name = x.getFunctionOne().getMethodName() + ","
						+ x.getFunctionTwo().getMethodName();
				String parameter = x.getFunctionOne().getParameterTypes()
						.toString()
						+ ","
						+ x.getFunctionTwo().getParameterTypes().toString();
				output = returnType + "--" + name + "--" + parameter;
				FileUtil.writeToFile(output, resultPath);
			}
		}

		// Result result = new Result(clonePairMethods);
		// result.generateResult();
		// String output = cloneType + "," + result.toString();
		// System.out.println(output);
		// FileUtil.writeToFile(output, resultPath);

	}

}
