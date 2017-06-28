package clonecodeprocess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ClonePairId;
import model.ClonePairIdCode;
import model.ClonePairMethod;
import model.FunctionIdPathStarEnd;
import model.Method;
import model.PairFunctionIdPathStartEnd;
import support.FileUtil;

public class CloneCodeProcessUtil {

	public static boolean compareCloneIR(Method functionOne, Method functionTwo) {

		return CloneComparing.atLeastOneSynonymForAllRootWordMatched(
				functionOne, functionTwo);
		// return CloneComparing.onlyASingleSynonym(functionOne, functionTwo);
	}

	public static List<ClonePairMethod> getClonePairMethod(
			List<ClonePairIdCode> clonePairIdCode) {

		List<ClonePairMethod> clonePairMethod = new ArrayList<ClonePairMethod>();

		for (ClonePairIdCode c : clonePairIdCode) {

			try {
				Method functionOne = CloneCodeParse.getParsedMethod(c
						.getFuntionOneCode());
				Method functionTwo = CloneCodeParse.getParsedMethod(c
						.getFuntionTwoCode());
				if (functionOne != null && functionTwo != null) {

					clonePairMethod.add(new ClonePairMethod(functionOne,
							functionTwo));
				}
			} catch (IOException e) {
				System.out.println("Ex occured in " + c.getFunctionOneId()
						+ "." + c.getFuntionTwoId());
			}

		}

		return clonePairMethod;

	}

	public static List<ClonePairIdCode> getClonePairIdCode(
			List<ClonePairId> idPairs,
			List<FunctionIdPathStarEnd> idPathStartEnd) throws IOException {

		List<ClonePairIdCode> _clonePairIdCode = new ArrayList<ClonePairIdCode>();

		for (ClonePairId pairIds : idPairs) {
			FunctionIdPathStarEnd firstFunction = findFunctionIdPathStarEnd(
					pairIds.getFunctionOneId(), idPathStartEnd);
			String firstFunctionCode = FileUtil.getCodeByPathAndLineNumber(
					firstFunction.getPath(), firstFunction.getStartLineNo(),
					firstFunction.getEndLineNo());
			FunctionIdPathStarEnd secondFunction = findFunctionIdPathStarEnd(
					pairIds.getFuntionTwoId(), idPathStartEnd);
			String secondFunctionCode = FileUtil.getCodeByPathAndLineNumber(
					secondFunction.getPath(), secondFunction.getStartLineNo(),
					secondFunction.getEndLineNo());

			_clonePairIdCode.add(new ClonePairIdCode(
					pairIds.getFunctionOneId(), firstFunctionCode, pairIds
							.getFuntionTwoId(), secondFunctionCode));
		}

		return _clonePairIdCode;
	}

	public static Map<Integer, Method> getIdMethods(List<FunctionIdPathStarEnd> idPathStartEnd)
			throws IOException {
		Map<Integer, Method> map = new HashMap<Integer, Method>();
		for (FunctionIdPathStarEnd idPath : idPathStartEnd) {
			String functionCode = FileUtil.getCodeByPathAndLineNumber(
					idPath.getPath(), idPath.getStartLineNo(),
					idPath.getEndLineNo());
			Method parsedfunction = CloneCodeParse
					.getParsedMethod(functionCode);

			map.put(idPath.getFunctionId(), parsedfunction);

		}

		return map;

	}

	public static List<ClonePairIdCode> getClonePairIdCode(
			List<PairFunctionIdPathStartEnd> listPairElement)
			throws IOException {

		List<ClonePairIdCode> _clonePairIdCode = new ArrayList<ClonePairIdCode>();

		for (PairFunctionIdPathStartEnd pairElement : listPairElement) {
			FunctionIdPathStarEnd firstFunction = pairElement.getFirstElement();
			String firstFunctionCode = FileUtil.getCodeByPathAndLineNumber(
					firstFunction.getPath(), firstFunction.getStartLineNo(),
					firstFunction.getEndLineNo());
			FunctionIdPathStarEnd secondFunction = pairElement
					.getSecondElement();

			String secondFunctionCode = FileUtil.getCodeByPathAndLineNumber(
					secondFunction.getPath(), secondFunction.getStartLineNo(),
					secondFunction.getEndLineNo());

			_clonePairIdCode.add(new ClonePairIdCode(firstFunction
					.getFunctionId(), firstFunctionCode, secondFunction
					.getFunctionId(), secondFunctionCode));
		}

		return _clonePairIdCode;
	}

	public static FunctionIdPathStarEnd findFunctionIdPathStarEnd(int id,
			List<FunctionIdPathStarEnd> idPathStartEnd) {
		for (FunctionIdPathStarEnd fidp : idPathStartEnd) {
			if (fidp.getFunctionId() == id) {
				return fidp;
			}
		}
		return null;
	}

}
