import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.stream.Stream;
import java.util.Iterator;

public class CheckFunction {
  String currentUsersHomeDir = System.getProperty("user.home");
  public Boolean checkStart()throws IOException, InterruptedException{
    ProcessBuilder builder = new ProcessBuilder();
    builder.command("sh", "-c", "ps aux | grep suricata");
    builder.directory(new File(this.currentUsersHomeDir));
    Process process = builder.start();
    String logOutPut="";
			BufferedReader bw =	new BufferedReader(new InputStreamReader(process.getInputStream()));
			Stream<String> outPut = bw.lines();
      Iterator<String> it = outPut.iterator();
      int index = 0;
			while(it.hasNext()) {
				logOutPut = it.next();
				if(logOutPut.contains("-c /etc/suricata/suricata.yaml")){
          index +=1;
        }if(index==2){
          return true;
        }
			}
    return false;
}
}
