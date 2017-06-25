import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import support.FileUtil;
import support.PropertyFileLoader;

public class IntraProjectsTest {

	public static void main(String[] args) throws IOException {

		String projectListPath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("projectListPath");
		String basePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("basePath");
		String resultPath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("resultPath");
		List<String> projectList = FileUtil.getProjectList(projectListPath);
		
		for (String projectName : projectList) {
			String clonePairIDPath = basePath + "/" + projectName + "/" + "ClonePairID_Similarity_80.txt";
			String methodIDPath = basePath + "/" + projectName + "/" + "PathID.txt";
			SingleProject.runTest(projectName, clonePairIDPath, methodIDPath, resultPath);
			System.out.println(projectName+"--> completed");
		}

	}

}
