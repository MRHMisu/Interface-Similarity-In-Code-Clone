package support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.ClonePairId;
import model.FunctionIdPathStarEnd;
import model.PairFunctionIdPathStartEnd;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileUtil {

	public static List<String> getProjectList(String filepath) {
		List<String> projectList = new ArrayList<String>();
		File file = new File(filepath);
		if (file.exists()) {
			String line = "";
			BufferedReader reader;

			try {
				reader = new BufferedReader(new FileReader(filepath));
				while ((line = reader.readLine()) != null) {
					projectList.add(line);
				}

			} catch (Exception e) {
			}
		}
		return projectList;
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
					clonePairs.add(new ClonePairId(Integer.parseInt(everyline
							.get(0)), Integer.parseInt(everyline.get(1))));
				}

			} catch (Exception e) {
			}

		}
		return clonePairs;

	}

	public static List<FunctionIdPathStarEnd> getFunctionIdPathStartEnd(
			String filepath) {
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

					functions.add(new FunctionIdPathStarEnd(Integer
							.parseInt(everyline.get(0)), everyline.get(1),
							Integer.parseInt(everyline.get(2)), Integer
									.parseInt(everyline.get(3))));
				}

			} catch (Exception e) {
			}

		}
		return functions;

	}

	public static String getCodeByPathAndLineNumber(String filepath, int start,
			int end) throws IOException {

		File file = new File(filepath);
		StringBuilder code = new StringBuilder();
		if (file.exists()) {
			try (LineNumberReader lineNumberReader = new LineNumberReader(
					new FileReader(file))) {

				for (String line = null; (line = lineNumberReader.readLine()) != null;) {
					int currentLineNumber = lineNumberReader.getLineNumber();
					if (currentLineNumber >= start && currentLineNumber <= end) {
						code.append(line + '\n');
					}
				}
			} catch (Exception e) {
			}

		}
		return code.toString();

	}
	
	public static List<PairFunctionIdPathStartEnd> getClonePairFunctionIdPathStartEnd(
			String xmlFilePath) {
		List<PairFunctionIdPathStartEnd> list = new ArrayList<PairFunctionIdPathStartEnd>();
		//Set<PairFunctionIdPathStartEnd> list = new HashSet<PairFunctionIdPathStartEnd>();
		try {

			File fXmlFile = new File(xmlFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("clone");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					NodeList sourceList = eElement
							.getElementsByTagName("source");

					FunctionIdPathStarEnd firstElement = null;
					FunctionIdPathStarEnd secondElement = null;
					for (int j = 0; j < sourceList.getLength(); j++) {
						Node sourceNode = sourceList.item(j);
						if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
							Element sourceElement = (Element) sourceNode;
							String sourcePath = sourceElement
									.getAttribute("file");
							int startLine = Integer.parseInt(sourceElement
									.getAttribute("startline"));
							int endLine = Integer.parseInt(sourceElement
									.getAttribute("endline"));
							int pcid = Integer.parseInt(sourceElement
									.getAttribute("pcid"));

							if (j == 0)
								firstElement = new FunctionIdPathStarEnd(pcid,
										sourcePath, startLine, endLine);
							if (j == 1)
								secondElement = new FunctionIdPathStarEnd(pcid,
										sourcePath, startLine, endLine);
						}

					}
					list.add(new PairFunctionIdPathStartEnd(firstElement,
							secondElement));

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public static void writeToFile(String text, String outputResultPath)
			throws IOException {
		Writer output = new BufferedWriter(new FileWriter(outputResultPath,
				true));
		output.append(text + '\n');
		output.close();
	}

	public static void writeToFile(Set<String> set, String outputResultPath)
			throws IOException {
		Writer output = new BufferedWriter(new FileWriter(outputResultPath,
				true));
		
		for (String text : set) {

			output.append(text + '\n');
			
		}
		output.close();
	}

	public static void writeToFile(List<String> list, String outputResultPath)
			throws IOException {
		Writer output = new BufferedWriter(new FileWriter(outputResultPath,
				true));
		
		for (String text : list) {

			output.append(text + '\n');
			
		}
		output.close();
	}

}
