package FBInterlocutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class APIUser {
	public static JSONObject useFBAPIs(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        BufferedReader in;
        if (connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
        	in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        	System.out.println(connection.getResponseCode());
        } else {
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        	System.out.println(connection.getResponseCode());
        	//bisognerebbe gestire le eccezioni come questa
        }
        
        StringBuilder sb = new StringBuilder();
        String line=null;
        while ((line = in.readLine()) != null) {
            sb.append(line);
            System.out.println(line);
        }
        in.close();
        
        JSONObject json=null;
        try {
			json = new JSONObject(sb.toString());
		} catch (JSONException e) {e.printStackTrace();}
        return json;
	}
}
