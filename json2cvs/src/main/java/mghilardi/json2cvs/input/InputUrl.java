package mghilardi.json2cvs.input;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class InputUrl {

	public static String getUrl(String urlToCall) throws UrlException {
		System.out.println("\nRequested url: " + urlToCall);
		StringBuilder stringBuilder = new StringBuilder();
		URLConnection urlConnection = null;
		InputStreamReader inputStreamReader = null;

		try {
			URL url = new URL(urlToCall);
			urlConnection = url.openConnection();
			if (urlConnection != null) {
				urlConnection.setReadTimeout(10 * 1000);
			}
			if (urlConnection != null && urlConnection.getInputStream() != null) {
				inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8"));
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				if (bufferedReader != null) {
					int charAsInteger;
					while ((charAsInteger = bufferedReader.read()) != -1) {
						stringBuilder.append((char) charAsInteger);
					}
					bufferedReader.close();
				}
			}
			inputStreamReader.close();
			
			return stringBuilder.toString();
		} catch (Exception e) {
			throw new UrlException("\nINVALID URL: " + urlToCall);
		}

	}
}
