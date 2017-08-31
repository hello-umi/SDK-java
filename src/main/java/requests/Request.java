package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Request {	

	public static final String BASE_URL = "http://localhost:8080/api/v1/";
	
	public static Response get(String urlStr) {
		return get(urlStr, null);
	}
	
	public static Response get(String urlStr, Map<String, String> headers) {
		return request(urlStr, "GET", "", headers);
	}
	
	public static Response post(String urlStr, String requestBody) {
		return post(urlStr, requestBody, null);
	}
	
	public static Response post(String urlStr, String requestBody, Map<String, String> headers) {
		return request(urlStr, "POST", requestBody, headers);
	}

	public static Response put(String urlStr, String requestBody) {
		return put(urlStr, requestBody, null);
	}

	public static Response put(String urlStr) {
		return put(urlStr, null);
	}


	public static Response put(String urlStr, String requestBody, Map<String, String> headers) {
		return request(urlStr, "PUT", requestBody, headers);
	}
	
	public static Response patch(String urlStr, String requestBody) {
		return patch(urlStr, requestBody, null);
	}
	
	public static Response patch(String urlStr, String requestBody, Map<String, String> headers) {
		return request(urlStr, "PATCH", requestBody, headers);
	}
	
	public static Response delete(String urlStr) {
		return delete(urlStr, null);
	}
	
	public static Response delete(String urlStr, Map<String, String> headers) {
		return request(urlStr, "DELETE", "",  headers);
	}
	
	private static Response request(String urlStr, String method, String requestBody, Map<String, String> headers) {
		HttpURLConnection connection = null;
		int responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
		String responseBody = null;
		try {
			URL url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			
			if (headers != null)
				for (String key :headers.keySet())
					connection.setRequestProperty(key, headers.get(key));
			
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
		return new Response(responseCode, responseBody);
	}

}

