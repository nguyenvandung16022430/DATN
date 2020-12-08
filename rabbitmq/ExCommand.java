import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.io.BufferedWriter;
import java.io.FileWriter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import com.rabbitmq.client.BuiltinExchangeType;
import org.json.simple.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.stream.Stream;
import java.util.Iterator;

public class ExCommand {
	static boolean taskFlag = true;
	ProcessBuilder builder;
	JSONSimpleExample obJson;
	CheckFunction checkFunction = new CheckFunction();
	static String currentUsersHomeDir = System.getProperty("user.home");
	static boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
	public void excuteCommand(String massage) throws IOException, InterruptedException {
		this.builder = new ProcessBuilder();
		builder.redirectErrorStream(true);
		this.obJson = new JSONSimpleExample();
		if (isWindows) {
			builder.command("cmd.exe", "/c", "dir");
		} else {
			builder.command("sh", JSONSimpleExample.getComamand(massage, "param","command.json"), JSONSimpleExample.getComamand(massage, "cmd","command.json"));
		}
		builder.directory(new File(currentUsersHomeDir));
		Process process = builder.start();
		if(massage.equals("stop")){
			System.out.println("check");
			sendOutput("turn off suricata");
		}if(massage.equals("log")){
			String log = "";
			String temp;
			BufferedReader bw =	new BufferedReader(new InputStreamReader(process.getInputStream()));
			Stream<String> outPut = bw.lines();
			Iterator<String> it = outPut.iterator();
			while (it.hasNext()) {
				temp = log;
				log = temp.concat(it.next() + "#");
				System.out.println(log);
		 }
		 sendOutput(log);
		}if(massage.equals("start")){
			int flag = 0;
			boolean temp = true;
			String logOutPut="null";
			BufferedReader bw =	new BufferedReader(new InputStreamReader(process.getInputStream()));
			Stream<String> outPut = bw.lines();
			Iterator<String> it = outPut.iterator();
			while(true) {
				logOutPut = it.next();
				if(logOutPut.contains("No such device")){
					temp = false;
				}
				if(temp){
				if(logOutPut.contains("<Notice>")){
					flag+=1;
					if(flag==2){
						sendOutput(logOutPut);
						break;
					}
				}
			}
				if(!it.hasNext()){
					sendOutput(logOutPut);
					break;
				}
				}
		}
		int exitCode = process.waitFor();
		assert exitCode == 0;
	}
	public static void sendOutput(String s) {
		try{
		ConnectionFactory factory = new ConnectionFactory();
				factory.setHost(JSONSimpleExample.getEnv("host", "envconfig.json"));
				JSONObject obj = new JSONObject();
				obj.put("nodeName",JSONSimpleExample.getEnv("name", "envconfig.json"));
				obj.put("message", s);
				try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
						channel.basicPublish("", JSONSimpleExample .getEnv("queue.output", "envconfig.json"),null,obj.toJSONString().getBytes());
            System.out.println("[x]Sent" + s+ "test");
        }
	}catch( Exception e){
		System.out.println(e);
	};
}
}
