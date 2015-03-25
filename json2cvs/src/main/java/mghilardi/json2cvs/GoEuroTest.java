package mghilardi.json2cvs;

import mghilardi.json2cvs.input.InputUrl;
import mghilardi.json2cvs.input.UrlException;
import mghilardi.json2cvs.output.OutputCsv;

import org.json.JSONArray;
import org.json.JSONException;

public class GoEuroTest {
	private static final String JSON_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	public static void main(String[] args) {
		try {
			System.out.println("STARTING APPLICATION");

			String jsonString = InputUrl.getUrl(JSON_URL + args[0]);
			// System.out.println("json result: " + jsonString);

			JSONArray jsonArray = new JSONArray(jsonString);
			printJson(jsonArray);

			OutputCsv.writeFile(OutputCsv.buildContent(jsonArray));
			
			System.out.println("\nEND APPLICATION");
			
		} catch (ArrayIndexOutOfBoundsException aiofbe) {
			System.out.println("\nPLEASE INSERT THE CITY NAME AFTER THE COMMAND IN THE FOLLOWING FORMAT:");
			System.out.println("java -jar GoEuroTest.jar CITY_NAME");
		} catch (JSONException jse) {
			System.out.println("\nINVALID JSON FORMAT:" + jse.getMessage());
		} catch (UrlException ue) {
			System.out.println(ue.getMessage());
		}
	}

	private static void printJson(JSONArray jsonArray) {
		System.out.println();
		System.out.println("API endpoint returns json: ");
		System.out.println(jsonArray.toString(2));
	}
}
