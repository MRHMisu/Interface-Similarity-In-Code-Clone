package clonecodeprocess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.github.javaparser.JavaParser;
import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import model.Method;
import model.RootWordSynonymsModel;
import wordservice.DictionaryService;
import wordservice.WordService;

public class CloneCodeParse {

	public static Method getParsedMethod(String passedCode) throws IOException {

		Method _method = null;
		try {
			String code = "public class A{" + passedCode + "}";
			CompilationUnit cu = JavaParser.parse(code);

			MethodVisitor methodVisitor = new MethodVisitor();
			methodVisitor.visit(cu, null);

			_method = methodVisitor.getMethod();

		} catch (Exception e) {
		}

		return _method;
	}

	/**
	 * Simple visitor implementation for visiting MethodDeclaration nodes.
	 */
	private static class MethodVisitor extends VoidVisitorAdapter<Void> {
		Method methods;

		@Override
		public void visit(MethodDeclaration n, Void arg) {

			super.visit(n, arg);
			Set<Modifier> modifierList = n.getModifiers();
			String methodName = n.getNameAsString();
			String returnType = n.getType().toString();
			NodeList<Parameter> parametersList = n.getParameters();
			List<String> parameters = new ArrayList<String>();
			for (Parameter m : parametersList) {
				parameters.add(m.getType().toString());
			}
			Set<String> modifiers = new TreeSet<String>();
			for (Modifier m : modifierList) {
				modifiers.add(m.toString());
			}
			Range r = (Range) n.getRange().get();
			int startLine = r.begin.line;
			int endLine = r.end.line;

			Set<String> rootWordsofMethodName = WordService.getRootWords(methodName);

			Set<RootWordSynonymsModel> rootWordSynonymsModel = new HashSet<RootWordSynonymsModel>();
			Set<String> allRootWordsAndSynonymsMerger = new HashSet<String>();
			for (String x : rootWordsofMethodName) {
				Set<String> sysonyms = DictionaryService.getSynomyns(x);
				rootWordSynonymsModel.add(new RootWordSynonymsModel(x, sysonyms));
				allRootWordsAndSynonymsMerger.addAll(sysonyms);
			}
			methods = new Method(modifiers, methodName, returnType, parameters, rootWordsofMethodName,
					rootWordSynonymsModel, allRootWordsAndSynonymsMerger);

		}

		public Method getMethod() {
			return methods;
		}
	}

}
