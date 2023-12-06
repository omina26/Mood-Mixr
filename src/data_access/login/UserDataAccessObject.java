package data_access.login;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import entity.User;
import use_case.login.LoginDataAccessInterface;

/**
 * This class represents the Data Access Object that handles the User
 */
public class UserDataAccessObject implements LoginDataAccessInterface {

    private File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private User loggedInUser;

    /**
     * The constructor for the UserDataAccessObject type
     * @param csvFile The file to store the User attributes
     * @throws IOException
     */
    public UserDataAccessObject(File csvFile) throws IOException {
        this.csvFile = csvFile;
        headers.put("name", 0);
        headers.put("access_token", 1);
        headers.put("id", 1);

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
                String id = String.valueOf(col[headers.get("id")]);
                this.loggedInUser = new User(name, accessToken, id);
            }
        }
    }


    /**
     * Logs in the provided user into the program
     * @param user The user to be logged in
     */
    @Override
    public void loginUser(User user) throws IOException {
        BufferedWriter writer;
        this.loggedInUser = user;


            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            String line = String.format("%s,%s,%s,", user.getName(), user.getToken(), user.getID);
            writer.write(line);
            writer.newLine();

            writer.close();
    }

    /**
     * Gets the current logged in user
     * @return User currently logged in
     */
    @Override
    public User getCurrentUser() {
        return this.loggedInUser;
    }
}
