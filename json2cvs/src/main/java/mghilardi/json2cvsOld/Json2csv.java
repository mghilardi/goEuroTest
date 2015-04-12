package mghilardi.json2cvsOld;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Json2csv {
	private static final String COMMA = ",";

	public static void main(String args[]) throws IOException {
//		String jsonString = "["
//				+ "{\"field1\": 11,\"field2\": 12,\"field3\": 13},"
//				+ "{\"field1\": 21,\"field2\": 22,\"field3\": 23},"
//				+ "{\"field1\": 31,\"field2\": 32,\"field3\": 33}]";

		String jsonString = "[{\"_id\":380614,\"key\":null,\"name\":\"Newcastle upon Tyne\",\"fullName\":\"Newcastle upon Tyne, United Kingdom\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"United Kingdom\",\"geo_position\":{\"latitude\":54.97328,\"longitude\":-1.61396},\"locationId\":12805,\"inEurope\":true,\"countryCode\":\"GB\",\"coreCountry\":true,\"distance\":null},{\"_id\":464168,\"key\":null,\"name\":\"Newport\",\"fullName\":\"Newport, United Kingdom\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"United Kingdom\",\"geo_position\":{\"latitude\":51.587807,\"longitude\":-2.9983957},\"locationId\":165731,\"inEurope\":true,\"countryCode\":\"GB\",\"coreCountry\":true,\"distance\":null},{\"_id\":441108,\"key\":null,\"name\":\"Newcastle-under-Lyme\",\"fullName\":\"Newcastle-under-Lyme, United Kingdom\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"United Kingdom\",\"geo_position\":{\"latitude\":53.0,\"longitude\":-2.23333},\"locationId\":140598,\"inEurope\":true,\"countryCode\":\"GB\",\"coreCountry\":true,\"distance\":null},{\"_id\":441096,\"key\":null,\"name\":\"Newtownabbey\",\"fullName\":\"Newtownabbey, United Kingdom\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"United Kingdom\",\"geo_position\":{\"latitude\":54.65983,\"longitude\":-5.90858},\"locationId\":140585,\"inEurope\":true,\"countryCode\":\"GB\",\"coreCountry\":true,\"distance\":null},{\"_id\":315105,\"key\":null,\"name\":\"Newcastle\",\"fullName\":\"Newcastle (NCL), United Kingdom\",\"iata_airport_code\":\"NCL\",\"type\":\"airport\",\"country\":\"United Kingdom\",\"geo_position\":{\"latitude\":55.03738,\"longitude\":-1.709616},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"GB\",\"coreCountry\":true,\"distance\":null},{\"_id\":316369,\"key\":null,\"name\":\"New York Kennedy\",\"fullName\":\"New York Kennedy (JFK), USA\",\"iata_airport_code\":\"JFK\",\"type\":\"airport\",\"country\":\"USA\",\"geo_position\":{\"latitude\":40.64441,\"longitude\":-73.78275},\"locationId\":null,\"inEurope\":false,\"countryCode\":\"US\",\"coreCountry\":false,\"distance\":null}]";
		// String jsonString = "[{\"_id\":377078,\"key\":null,\"name\":\"Potsdam\",\"fullName\":\"Potsdam, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.39886,\"longitude\":13.06566},\"locationId\":9254,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":410978,\"key\":null,\"name\":\"Potsdam\",\"fullName\":\"Potsdam, USA\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"USA\",\"geo_position\":{\"latitude\":44.66978,\"longitude\":-74.98131},\"locationId\":43214,\"inEurope\":false,\"countryCode\":\"US\",\"coreCountry\":false,\"distance\":null}][{\"_id\":377078,\"key\":null,\"name\":\"Potsdam\",\"fullName\":\"Potsdam, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.39886,\"longitude\":13.06566},\"locationId\":9254,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":410978,\"key\":null,\"name\":\"Potsdam\",\"fullName\":\"Potsdam, USA\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"USA\",\"geo_position\":{\"latitude\":44.66978,\"longitude\":-74.98131},\"locationId\":43214,\"inEurope\":false,\"countryCode\":\"US\",\"coreCountry\":false,\"distance\":null}]";
		/*String jsonString = "["
				+ "{\"_id\":377078,"
				+ "\"key\":null,"
				+ "\"name\":\"Potsdam\","
				+ "\"fullName\":\"Potsdam, Germany\","
				+ "\"iata_airport_code\":null,"
				+ "\"type\":\"location\","
				+ "\"country\":\"Germany\","
				+ "\"geo_position\": {"
					+ "\"latitude\":52.39886,"
					+ "\"longitude\":13.06566},"
				+ "\"locationId\":9254,"
				+ "\"inEurope\":true,"
				+ "\"countryCode\":\"DE\","
				+ "\"coreCountry\":true,"
				+ "\"distance\":null}]"; */
		
		JSONArray jsonArray = new JSONArray(jsonString);
		System.out.println(jsonArray.toString(2));
		// String csv = CDL.toString(jsonArray);
		// System.out.println(csv);
		StringBuilder csv = new StringBuilder("_id,name,type,latitude,longitude\n");
		for(int i=0 ; i< jsonArray.length(); i++){   // iterate through jsonArray 
			JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
			JSONObject geoPosition = jsonObject.getJSONObject("geo_position");
			System.out.println("======================================");
			System.out.println("jsonObject " + i + ": " + jsonObject);
			System.out.println("geoPosition " + i + ": " + geoPosition);
			String _id = jsonObject.get("_id").toString();
			String name = jsonObject.get("name").toString();
			String type = jsonObject.get("type").toString();
			String latitude = geoPosition.get("latitude").toString();
			String longitude = geoPosition.get("longitude").toString();
			csv.append(_id).append(COMMA).append(name).append(COMMA).append(type).append(COMMA).append(latitude).append(COMMA).append(longitude).append("\n");
			System.out.println("_id "+ _id + ", name "+ name+ ", type "+ type+ ", latitude "+ latitude+ ", longitude "+ longitude);
		}
		
		System.out.println(Charset.defaultCharset());
		File file = new File("fromJSON.csv");
		FileUtils.writeStringToFile(file, csv.toString());
		
	}
}
