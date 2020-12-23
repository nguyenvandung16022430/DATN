import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class FileHandle {
  JSONSimpleExample obJson;
  public void addRules(String rule) {
		String otherFolder = JSONSimpleExample.getEnv("rulefilePath", "envconfig.json");
		BufferedWriter bw = null;
    FileWriter fw = null;
		try {
			File ruleFile = new File(otherFolder);
			if (!ruleFile.exists()) {
				ruleFile.createNewFile();
			}
			fw = new FileWriter(ruleFile, true);
			bw = new BufferedWriter(fw);
			bw.append(rule);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		}
}
