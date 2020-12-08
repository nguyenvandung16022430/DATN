import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONSimpleExample {
    public static String getComamand (String command,String param,String fileName) {
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;
            Map address = ((Map) jsonObject.get(command));
            Iterator<Map.Entry> itr1 = address.entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                String results = (String) pair.getKey();
                if (results.equals(param)){
                    return (String) pair.getValue();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getEnv (String command,String fileName) {
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;

            String value = (String) jsonObject.get(command);
            return value;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
