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

public class Type1 {

	public static void main(String[] args) throws IOException {

		String cloneType = "IntraProject";
		String basePath = "/media/misu/MS/Masters/PaperDataset/IJDataSet";
		String resultPath = basePath + "/" + cloneType + "/"
				+ "Clone_Result_IJData_IntraProject.txt";
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
		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = cloneType + "," + result.toString();
		System.out.println(output);
		FileUtil.writeToFile(output, resultPath);

	}

}
