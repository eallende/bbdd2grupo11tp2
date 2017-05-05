package bd2.Muber.util;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class JsonUtil {
	
	public static String generateJson(String result, Object aSerializableObject) {
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("result", result);
		aMap.put("resultingObjects", aSerializableObject);
		
		return new Gson().toJson(aMap);
	}

}
