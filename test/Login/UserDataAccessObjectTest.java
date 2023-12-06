package Login;

import data_access.login.UserDataAccessObject;
import entity.User;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class UserDataAccessObjectTest {

    @Test
    public void tesUserDataAccessObjectEmptyFile() throws IOException {
        File tempFile = new File("user.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.print("");
        writer.close();

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(tempFile);

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        String header = reader.readLine();
        assertEquals(header, "name,access_token");
    }

    @Test
    public void tesCreateMoodDataAccessObjectNonEmptyFile() throws IOException {
        File tempFile = new File("user.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.println("name,access_token");
        writer.print("mockname,1234");
        writer.close();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(tempFile);

        assertEquals(userDataAccessObject.getCurrentUser().name, "mockname");
        assertEquals(userDataAccessObject.getCurrentUser().getToken(), "1234");
    }

    @Test
    public void testLoginUser() throws IOException {
        File tempFile = new File("user.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.print("name,access_token");
        writer.close();

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(tempFile);

        User mockUser = new User("mock", "1234", id);

        userDataAccessObject.loginUser(mockUser);

       User actual = userDataAccessObject.getCurrentUser();

       assertEquals(mockUser.name, actual.name);
       assertEquals(mockUser.getToken(), actual.getToken());

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        String header = reader.readLine();
        assertEquals(header, "name,access_token");
        String nextLine = reader.readLine();
        assertEquals(nextLine, "mock,1234");
    }
}
