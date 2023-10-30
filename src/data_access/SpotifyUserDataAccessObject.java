package data_access;

import entity.User;
import entity.UserFactory;
import use_case.login.LoginDataAccessInterface;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Base64;

public class SpotifyUserDataAccessObject implements LoginDataAccessInterface {
    private static final String CLIENT_ID = "1c6992172cb240fd85caa34bae033b94";
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private static final String SPOTIFY_AUTHORIZE_URL = "https://accounts.spotify.com/authorize";

    private String accessToken;
    private UserFactory userFactory;
    public SpotifyUserDataAccessObject(UserFactory userFactory){
        this.userFactory = userFactory;
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


    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public void logUserIn() throws IOException, URISyntaxException {
        OAuthRedirectHandler.RedirectHandler();
        this.accessToken = OAuthRedirectHandler.getAccessToken();

    }


}
