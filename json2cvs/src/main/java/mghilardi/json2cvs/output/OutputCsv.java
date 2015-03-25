package mghilardi.json2cvs.output;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class OutputCsv {

	private static final String COMMA = ",";

	public static void writeFile(StringBuilder csvContent) {
		File file = new File("fromJSON.csv");
		try {
			FileUtils.writeStringToFile(file, csvContent.toString(), "UTF-8");
			System.out.println("\nFile written successfully!");
		} catch (IOException e) {
			System.out.println("\nEXCEPTION while writing file" + e.getMessage());
		}
	}

	public static StringBuilder buildContent(JSONArray jsonArray) {
		StringBuilder csvContent = new StringBuilder("_id,name,type,latitude,longitude\n");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			JSONObject geoPosition = jsonObject.getJSONObject("geo_position");
			csvContent.append(jsonObject.get("_id").toString()).append(COMMA)
					.append(jsonObject.get("name").toString()).append(COMMA)
					.append(jsonObject.get("type").toString()).append(COMMA)
					.append(geoPosition.get("latitude").toString()).append(COMMA)
					.append(geoPosition.get("longitude").toString()).append("\n");
		}
		return csvContent;
	}
}
