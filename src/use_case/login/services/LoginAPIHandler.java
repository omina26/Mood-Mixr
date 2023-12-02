package use_case.login.services;

import entity.User;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
        String accessToken = getAccessToken();
        String endpoint = "https://api.spotify.com/v1/me";
        URL url = new URL(endpoint);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set the Authorization header with the access token
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        // Get the response code
        int responseCode = connection.getResponseCode();
        BufferedReader in;
        if (responseCode == 200) {
            // The request was successful (HTTP status code 200)

            // Read the response
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        else {
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        String inputLine;
        String[] splitLine;
        ArrayList<String[]> response = new ArrayList<>();
        StringBuilder stringRepresentation = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            stringRepresentation.append(inputLine);
            splitLine = inputLine.split(";");
            response.add(splitLine);
        }
        in.close();
        // Print the response (JSON data)
        System.out.println("Response: " + stringRepresentation);

        // Close the connection
        connection.disconnect();

        return new User(stringRepresentation.toString(), accessToken);
    }
}