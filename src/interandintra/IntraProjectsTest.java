package interandintra;

import java.io.IOException;
import java.util.List;

import support.FileUtil;
import support.PropertyFileLoader;

public class IntraProjectsTest {

	public static void main(String[] args) throws IOException {

		String projectListPath = PropertyFileLoader
				.getRequiredPropertyPathByPropertyName("projectListPath_SF110");
		String basePath = PropertyFileLoader
				.getRequiredPropertyPathByPropertyName("basePathDetectedClone_SF110");
		String resultPath = PropertyFileLoader
				.getRequiredPropertyPathByPropertyName("resultPath_intra_SF110");
		List<String> projectList = FileUtil.getProjectList(projectListPath);

		for (String projectName : projectList) {
			String clonePairIDPath = basePath + "/" + projectName + "/"
					+ "ClonePairID_Similarity_80.txt";
			String methodIDPath = basePath + "/" + projectName + "/"
					+ "PathID.txt";
			SingleProject.runTest(projectName, clonePairIDPath, methodIDPath,
					resultPath);
			System.out.println(projectName + "--> completed");
		}

	}

}
