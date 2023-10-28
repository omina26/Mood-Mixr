package app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.SecureRandom;
import java.util.Base64;

public class OAuthRedirectHandler {

    private static final int PORT = 8888;
    private static String authCode = null;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/callback", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port " + PORT);

        // Here you would open the browser to initiate OAuth, e.g.:
        // Desktop.getDesktop().browse(new URI("https://accounts.spotify.com/authorize?..."));

        // After capturing the code, you'd typically shut down the server:
        // server.stop(0);
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
            authCode = parameters.get("code");

            // Now you have the auth code, you can exchange it for an access token, etc.
            System.out.println("Captured auth code: " + authCode);
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

    public static class SpotifyController {

        @GetMapping("/login")
        public RedirectView login() {
            String state = generateRandomString(16);
            String scope = "user-read-private user-read-email";

            String redirectUrl = SPOTIFY_AUTHORIZE_URL + "?" +
                    "response_type=code" +
                    "&client_id=" + CLIENT_ID +
                    "&scope=" + scope +
                    "&redirect_uri=" + REDIRECT_URI +
                    "&state=" + state;

            return new RedirectView(redirectUrl);
        }
    }

    private static String generateRandomString(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
