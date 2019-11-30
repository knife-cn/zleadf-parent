package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONObject;
public class JsonUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 	
	}
	public static String toMap(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator it = jsonObject.keys();
        Map map = new HashMap();
        String quereyString="&";
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = jsonObject.get(key);
            quereyString=quereyString+key+value+"&";

          
        }
        return quereyString;
    }
}
