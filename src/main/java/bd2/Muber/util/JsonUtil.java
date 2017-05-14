package bd2.Muber.util;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil<T> {
	
	public static String generateJson(String result, Object aSerializableObject) {
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("result", result);
		aMap.put("resultingObjects", aSerializableObject);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return	gson.toJson(aMap);
		
	}
	

}
