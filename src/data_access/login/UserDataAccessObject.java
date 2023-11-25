package data_access.login;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import entity.Mood;
import entity.User;
import use_case.login.LoginDataAccessInterface;

public class UserDataAccessObject implements LoginDataAccessInterface {

    private File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private User loggedInUser;

    public UserDataAccessObject(File csvFile) throws IOException {
        this.csvFile = csvFile;
        headers.put("name", 0);
        headers.put("access_token", 1);

        if (csvFile.length() == 0){
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            writer.close();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            // For later: clean this up by creating a new Exception subclass and handling it in the UI.
            String row = reader.readLine();
            if (row != null) {
                String[] col = row.split(",");
                String name = String.valueOf(col[headers.get("name")]);
                String accessToken = String.valueOf(col[headers.get("access_token")]);
                this.loggedInUser = new User(name, accessToken);
            }
        }
    }


    @Override
    public void loginUser(User user) {
        BufferedWriter writer;
        this.loggedInUser = user;

        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            String line = String.format("%s,%s", user.getName(), user.getToken());
            writer.write(line);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getCurrentUser() {
        return this.loggedInUser;
    }
}
