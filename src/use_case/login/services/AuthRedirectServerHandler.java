package use_case.login.services;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.util.*;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;


public class AuthRedirectServerHandler {

    private static HttpServer server;
    private static final int PORT = 8888;
    public static final String REDIRECT_URI = "http://localhost:8888/callback";


    private static String authCode = "";
    private static String respState = "";

    private static CountDownLatch latch = new CountDownLatch(1);

    public static String[] handleRedirectAndGetResponse(String clientID, String redirectUri) throws IOException, URISyntaxException, InterruptedException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        LoginHandler loginHandler = new LoginHandler();
        server.createContext("/callback", loginHandler);
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port " + PORT);

        String state = generateRandomString();
        String scope = "user-read-private user-read-email";

        String redirectUrl = "response_type=code" +
                "&client_id=" + URLEncoder.encode(clientID, "UTF-8") +
                "&scope=" + URLEncoder.encode(scope, "UTF-8") +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                "&state=" + URLEncoder.encode(state, "UTF-8");
        String authURL = "https://accounts.spotify.com/authorize?" + redirectUrl;

        // Here you would open the browser to initiate OAuth, e.g.:
        Desktop.getDesktop().browse(new URI(authURL));
        System.out.println("line 45: "+ authCode);

        latch.await();
        return new String[]{authCode, respState};
    }
    private static class LoginHandler implements HttpHandler {

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
            authCode = parameters.get("code");
            respState = parameters.get("state");

            // Now you have the auth code, you can exchange it for an access token, etc.
            System.out.println("Captured code" + authCode);
            System.out.println("Captures state: "+ respState);

            latch.countDown();

            server.stop(0);
        }

        private static Map<String, String> queryToMap(String query) {
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
    }
    private static String generateRandomString() {
        byte[] bytes = new byte[16];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
