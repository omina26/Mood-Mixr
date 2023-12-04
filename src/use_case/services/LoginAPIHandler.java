package use_case.services;

import entity.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

/**
 * A Handler class for dealing with the login API for Spotify
 */
public class LoginAPIHandler implements LoginAPIInterface{

    final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    final String CLIENT_ID = "1c6992172cb240fd85caa34bae033b94";
    final String CLIENT_SECRET = "6f843aec369846528a70b621a721e788";
    static final String REDIRECT_URI = "http://localhost:8888/callback";

    private String getAccessToken() throws IOException, URISyntaxException, InterruptedException {
        String accessToken = null;


        String[] resp = AuthRedirectServerHandler.handleRedirectAndGetResponse(CLIENT_ID, REDIRECT_URI);
        String authCode = resp[0];
        String respState = resp[1];

        if (respState != null) {
            URL url = new URL(TOKEN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String postData = "code=" + authCode + "&redirect_uri=" + REDIRECT_URI + "&grant_type=authorization_code";
            byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

            System.out.println();

            connection.setRequestProperty("Content-Length", Integer.toString(postDataBytes.length));

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String authString = CLIENT_ID + ":" + CLIENT_SECRET;
            String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);

            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(postData);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Handle the response, which contains the access token and other information
                System.out.println("Response: " + response);
                accessToken = getJSONDataAccessToken(response.toString());


            } else {
                // Handle the error
                System.err.println("Error response code: " + responseCode);
            }
        }


        return accessToken;
    }

    private static String getJSONDataAccessToken(String responseString) {

            // Locate the "access_token" field
            int accessTokenIndex = responseString.indexOf("\"access_token\"");

            if (accessTokenIndex != -1) {
                // Find the opening quote of the access token value
                int valueStart = responseString.indexOf("\"", accessTokenIndex + 15); // 15 is the length of "access_token" plus the ":"

                if (valueStart != -1) {
                    // Find the closing quote of the access token value
                    int valueEnd = responseString.indexOf("\"", valueStart + 1);

                    if (valueEnd != -1) {
                        // Extract the access token
                        String accessToken = responseString.substring(valueStart + 1, valueEnd);
                        return accessToken;
                    }
                }

            }

        return null;
    }

    /**
     * Get the User info of the user that logged in
     * @return User object representing the user that was logged in
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    public User getLoginUserInfo() throws IOException, URISyntaxException, InterruptedException {
        // Replace "YOUR_ACCESS_TOKEN" with your actual Spotify access token
        String accessToken = getAccessToken();

        // API endpoint URL
        String apiUrl = "https://api.spotify.com/v1/me";

        // Set up the HTTP connection
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        // Get the response code
        int responseCode = connection.getResponseCode();
        String username = "";
        String userId = "";

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read and parse the JSON response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JsonReader jsonReader = Json.createReader(new java.io.StringReader(response.toString()));
            JsonObject jsonResponse = jsonReader.readObject();

            // Extract the name of the user
            username = jsonResponse.getString("display_name");

            // Print the user's name
            System.out.println("User Name: " + username);

            // Extract the user id of the user
            userId = jsonResponse.getString("id");

            // Print the user's user id
            System.out.println("User Id: " + userId);

        } else {
            System.out.println("Error: Unable to fetch user data. Response Code: " + responseCode);
        }

        // Close the connection
        connection.disconnect();


        return new User(username, accessToken, userId);
    }
}
