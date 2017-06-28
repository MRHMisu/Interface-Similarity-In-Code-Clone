package ijdataset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.ClonePairId;
import model.ClonePairMethod;
import model.FunctionIdPathStarEnd;
import model.Method;
import model.Result;
import support.FileUtil;
import clonecodeprocess.CloneCodeParse;
import clonecodeprocess.CloneCodeProcessUtil;

public class InterProjectWT3 {
	public static void main(String[] args) throws IOException {

		
		String cloneType = "InterProject";
		String basePath = "/media/misu/MS/Masters/PaperDataset/IJDataSet";
		String resultPath = basePath + "/" + cloneType + "/"
				+ "Clone_Result_IJData_InterProjectClones.txt";
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

		Map<Integer, Method> idMethods = CloneCodeProcessUtil
				.getIdMethods(idPathStartEnd);

		List<ClonePairMethod> clonePairMethods = new ArrayList<ClonePairMethod>();

		for (ClonePairId idPair : idPairs) {

			Method functionOne = idMethods.get(idPair.getFunctionOneId());
			Method functionTwo = idMethods.get(idPair.getFuntionTwoId());
			if (functionOne != null && functionTwo != null) {

				clonePairMethods.add(new ClonePairMethod(functionOne,
						functionTwo));
			}
		}

		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = cloneType + "," + result.toString();
		System.out.println(output);
		FileUtil.writeToFile(output, resultPath);

	}

}
