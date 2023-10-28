package app;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    private static final String CLIENT_ID = "ce5b82a8500d4fc1bbb089207a8e6260";
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private static final String SPOTIFY_AUTHORIZE_URL = "https://accounts.spotify.com/authorize";
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Spotify API with Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setVisible(true);

            JButton authButton = new JButton("Authenticate with Spotify");
            authButton.addActionListener(e -> {
                try {
                    authenticateWithSpotify();
                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                }
            });
            frame.add(authButton);

        });
    }

    private static void authenticateWithSpotify() throws UnsupportedEncodingException {
        String state = generateRandomString(16);
        String scope = "user-read-private user-read-email";

        String authURL = SPOTIFY_AUTHORIZE_URL +
                "?response_type=code" +
                "&client_id=" + URLEncoder.encode(CLIENT_ID, "UTF-8") +
                "&scope=" + URLEncoder.encode(scope, "UTF-8") +
                "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") +
                "&state=" + URLEncoder.encode(state, "UTF-8");

        try {
            Desktop.getDesktop().browse(new URI(authURL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String generateRandomString(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }


}

