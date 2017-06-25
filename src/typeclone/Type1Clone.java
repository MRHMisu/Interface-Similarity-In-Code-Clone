package typeclone;
import java.io.IOException;
import java.util.List;

import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.PairFunctionIdPathStartEnd;
import model.Result;
import support.FileUtil;
import clonecodeprocess.CloneCodeProcessUtil;


public class Type1Clone {

	
	public static void main(String[] args) throws IOException {
		
		String projectName = "ant";
		String clonePairXMLPath="/media/misu/MS/Masters/PaperDataset/36Projects/projects/ant_functions-clones/ant_functions-clones-0.00.xml";
		List<PairFunctionIdPathStartEnd> list=FileUtil.getClonePairFunctionIdPathStartEnd(clonePairXMLPath);
		List<ClonePairIdCode> clonePairIdCode=CloneCodeProcessUtil.getClonePairIdCode(list);
		List<ClonePairMethod> clonePairMethods = CloneCodeProcessUtil.getClonePairMethod(clonePairIdCode);
		Result result = new Result(clonePairMethods);
		result.generateResult();
		String output = projectName + "," + result.toString();
		System.out.println(output);
		//FileUtil.writeToFile(output, resultPath);
		
	}
	
}
