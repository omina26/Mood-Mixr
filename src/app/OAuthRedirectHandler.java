package app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Base64;

public class OAuthRedirectHandler {

    private static final int PORT = 8888;
    private static final String CLIENT_ID = "ce5b82a8500d4fc1bbb089207a8e6260";
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
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
            Map<String, String> parameters = queryToMap(query);
            respCode = parameters.get("code");
            respState = parameters.get("state");

            // Now you have the auth code, you can exchange it for an access token, etc.
            System.out.println("Captured code" + respCode);
            System.out.println("Captures state: "+ respState);

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


}