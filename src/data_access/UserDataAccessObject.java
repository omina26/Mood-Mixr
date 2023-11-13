package data_access;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import entity.User;
import use_case.login.LoginDataAccessInterface;

public class UserDataAccessObject implements LoginDataAccessInterface {

    private File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    public UserDataAccessObject(String csvPath) {
        this.csvFile = new File(csvPath);
        headers.put("name", 0);
        headers.put("access_token", 1);
        headers.put("login_time", 2);
    }


    @Override
    public void loginUser(User user) {
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            String line = String.format("%s,%s,%s", user.getName(), user.getToken(), LocalTime.now());
            writer.write(line);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
