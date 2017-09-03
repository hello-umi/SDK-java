package requests;

import config.Config;
import requests.exceptions.ResponseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {	

	public static Response get(String urlStr) {
		return request(urlStr, "GET", null);
	}

	public static Response post(String urlStr, String requestBody) {
		return request(urlStr, "POST", requestBody);
	}

	public static Response put(String urlStr) {
		return put(urlStr, null);
	}


	public static Response put(String urlStr, String requestBody) {
		return request(urlStr, "PUT", requestBody);
	}
	

	public static Response patch(String urlStr, String requestBody) {
		return request(urlStr, "PATCH", requestBody);
	}
	

	public static Response delete(String urlStr) {
		return request(urlStr, "DELETE", null);
	}
	
	private static Response request(String urlStr, String method, String requestBody) {
		HttpURLConnection connection = null;
		int responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
		String responseBody = null;
		try {
			URL url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);

			connection.setRequestProperty("Content-Type", "application/json");

			String auth = Config.getAuthToken();
			if (auth != null && auth.length() > 0)
				connection.setRequestProperty("Authentication", "Token " + auth);

			
			if (requestBody != null && requestBody.length() > 0) {
				connection.setDoOutput(true);
				OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
				os.write(requestBody);
				os.close();
			}
			
			connection.connect();
			responseCode = connection.getResponseCode();

			InputStream is;
			if (responseCode < 300 && responseCode >= 200)
				is = connection.getInputStream();
			else
				is = connection.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null)
				sb.append(line);
			br.close();
			responseBody = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		Response response = new Response(responseCode, responseBody);
		if(response.getStatusCode() >= 300 || response.getStatusCode() < 200)
			throw ResponseException.get(response);
		return response;
	}

}

