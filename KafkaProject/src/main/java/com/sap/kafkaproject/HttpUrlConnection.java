/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sap.kafkaproject;

import java.io.*;
import java.net.*;  
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
/**
 *
 * @author Manna
 */
public class HttpUrlConnection {
    private static String POST_URL;
    private static String POST_PARAM;
    private static final String GET_URL = "https://graph.facebook.com/endpoint?key=value&amp;access_token=" + "310700449350456" + "|" + "7efde528b03482c36a7a2e741f364222";
    public void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}
    public void sendPOST(String post_param, String sentimentAnalysisApi) throws IOException {
                POST_URL = sentimentAnalysisApi;
                String POST_PARAM = "text=" + post_param;
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAM.getBytes());
		os.flush();
		os.close();
		// For POST only - END
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
                        
			// print result
			
                    try {
                        JSONObject jsonObj = new JSONObject(response.toString());
                        String label = jsonObj.getString("label");
                        System.out.println(label);
                    } catch (JSONException ex) {
                        Logger.getLogger(HttpUrlConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
		} else {
			System.out.println("POST request not worked");
		}
	}
    
   

}
