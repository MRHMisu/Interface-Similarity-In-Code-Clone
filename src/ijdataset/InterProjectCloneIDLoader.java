package ijdataset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.ClonePairId;
import model.FunctionIdPathStarEnd;

public class InterProjectCloneIDLoader {

	public static void main(String[] args) throws IOException {

		String clonePairIDPath = "E:\\CodeCloneData\\H2\\IJDataSet\\Type1\\Type1ClonePairID.txt";
		String methodIDPath = "E:\\CodeCloneData\\H2\\IJDataSet\\Type1\\Type1ClonePathID.txt";
		String resultPath = "E:\\CodeCloneData\\H2\\IJDataSet\\Intra-ProjectPairInType1.txt";
		List<String> outputIterClonePirs = new ArrayList<String>();

		List<ClonePairId> idPairs = getConePairsId(clonePairIDPath);
		List<FunctionIdPathStarEnd> idPathStartEnd = getFunctionIdPathStartEnd(methodIDPath);

		for (ClonePairId pairID : idPairs) {
			FunctionIdPathStarEnd one = findFunctionIdPathStarEnd(pairID.getFunctionOneId(), idPathStartEnd);
			FunctionIdPathStarEnd two = findFunctionIdPathStarEnd(pairID.getFuntionTwoId(), idPathStartEnd);
			if ((one.getProject().equals(two.getProject()))) {

				String result = pairID.getFunctionOneId() + "," + pairID.getFuntionTwoId();
				outputIterClonePirs.add(result);
			}

		}

		writeToFile(outputIterClonePirs, resultPath);

	}

	public static void writeToFile(List<String> list, String outputResultPath) throws IOException {
		Writer output = new BufferedWriter(new FileWriter(outputResultPath, true));

		for (String text : list) {

			output.append(text + '\n');

		}
		output.close();
	}

	public static FunctionIdPathStarEnd findFunctionIdPathStarEnd(int id, List<FunctionIdPathStarEnd> idPathStartEnd) {
		for (FunctionIdPathStarEnd fidp : idPathStartEnd) {
			if (fidp.getFunctionId() == id) {
				return fidp;
			}
		}
		return null;
	}

	public static List<ClonePairId> getConePairsId(String filepath) {
		List<ClonePairId> clonePairs = new ArrayList<ClonePairId>();
		File file = new File(filepath);
		if (file.exists()) {
			String line = "";
			BufferedReader reader;
			List<String> everyline;

			try {
				reader = new BufferedReader(new FileReader(filepath));
				while ((line = reader.readLine()) != null) {
					everyline = (Arrays.asList(line.split(",")));
					clonePairs.add(
							new ClonePairId(Integer.parseInt(everyline.get(0)), Integer.parseInt(everyline.get(1))));
				}

			} catch (Exception e) {
			}

		}
		return clonePairs;

	}

	public static List<FunctionIdPathStarEnd> getFunctionIdPathStartEnd(String filepath) {
		List<FunctionIdPathStarEnd> functions = new ArrayList<FunctionIdPathStarEnd>();
		File file = new File(filepath);
		if (file.exists()) {
			String line = "";
			BufferedReader reader;
			List<String> everyline;

			try {
				reader = new BufferedReader(new FileReader(filepath));
				while ((line = reader.readLine()) != null) {
					everyline = (Arrays.asList(line.split(",")));
					FunctionIdPathStarEnd k = new FunctionIdPathStarEnd(Integer.parseInt(everyline.get(0)),
							everyline.get(1), Integer.parseInt(everyline.get(2)), Integer.parseInt(everyline.get(3)),
							everyline.get(4));

					functions.add(k);
				}

			} catch (Exception e) {
			}

		}
		return functions;

	}

}
