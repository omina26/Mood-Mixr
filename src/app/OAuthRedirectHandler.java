package app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Base64;

public class OAuthRedirectHandler {

    private static final int PORT = 8888;
    private static final String CLIENT_ID = "ce5b82a8500d4fc1bbb089207a8e6260";
    private static final String REDIRECT_URI = "http://localhost:8888/callback";

    private static final String CLIENT_SECRET = "b120c0262ebf45fd86bb7140185c9709";
    private static String respCode = null;
    private static String respState = null;

    public static void main(String[] args) throws IOException, URISyntaxException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/callback", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port " + PORT);

        String state = generateRandomString(16);
        String scope = "user-read-private user-read-email";

        String redirectUrl = "response_type=code" +
                "&client_id=" + URLEncoder.encode(CLIENT_ID, "UTF-8") +
                "&scope=" + URLEncoder.encode(scope, "UTF-8") +
                "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") +
                "&state=" + URLEncoder.encode(state, "UTF-8");
        String authURL = "https://accounts.spotify.com/authorize?" + redirectUrl;

        // Here you would open the browser to initiate OAuth, e.g.:
        Desktop.getDesktop().browse(new URI(authURL));

        // After capturing the code, you'd typically shut down the server:
        //server.stop(0);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            URI uri = t.getRequestURI();
            String response = "Received auth code. You can now close this page.";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

            // Capture the auth code from the URL
            String query = uri.getQuery();
            System.out.println(query);
            Map<String, String> parameters = queryToMap(query);
            respCode = parameters.get("code");
            respState = parameters.get("state");

            // Now you have the auth code, you can exchange it for an access token, etc.
            System.out.println("Captured code" + respCode);
            System.out.println("Captures state: "+ respState);
//------------------------------------------------------------------------
            // code below gets accessToken
            String accessToken = null;
            if (respState == null) {
                // Handle state mismatch error by redirecting
                String errorUrl = "/#error=state_mismatch"; // Modify this URL as needed
                System.out.println("Redirect to: " + errorUrl);
            } else {
                String tokenUrl = "https://accounts.spotify.com/api/token";
                try {
                    URL url = new URL(tokenUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    String authString = CLIENT_ID + ":" + CLIENT_SECRET;
                    String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
                    connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);

                    String postData = "code=" + respCode + "&redirect_uri=" + REDIRECT_URI + "&grant_type=authorization_code";
                    connection.setDoOutput(true);
                    try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                        wr.writeBytes(postData);
                    }

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                            String inputLine;
                            StringBuilder response2 = new StringBuilder();

                            while ((inputLine = in.readLine()) != null) {
                                response2.append(inputLine);
                            }

                            // Handle the response, which contains the access token and other information
                            System.out.println("Response: " + response2);
                            accessToken = getJSONDataAccessToken(response2.toString());
                            System.out.println("access token: " + accessToken);
                        }
                    } else {
                        // Handle the error
                        System.err.println("Error response code: " + responseCode);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//----------------------------------------------------------------
            //code below gets user info from spotify using access code
            try {
                // Create the URL for the Spotify API endpoint
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

                if (responseCode == 200) {
                    // The request was successful (HTTP status code 200)

                    // Read the response
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response3 = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response3.append(inputLine);
                    }
                    in.close();

                    // Print the response (JSON data)
                    System.out.println("Response: " + response3.toString());
                } else {
                    // Handle the error, e.g., by checking the response code
                    System.out.println("Error - Response Code: " + responseCode);
                }

                // Close the connection
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Convert a query string to a map of key-value pairs.
     */
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], URLDecoder.decode(entry[1]));
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    private static String generateRandomString(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private static String getJSONDataAccessToken(String responseString) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}