/**
 * Questa classe gestisce le intereazione con il protocollo HTTP
 */
package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReceiverHTTP {
	/**
	 * Questo metodo permette di inviare una richiesta HTTP e ricevere una risposta
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getResponse(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setConnectTimeout(200);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
