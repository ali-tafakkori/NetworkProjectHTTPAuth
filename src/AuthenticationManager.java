import models.AppUser;
import models.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AuthenticationManager {

    Scanner scanner = new Scanner(new File(".\\data\\student_list.txt"));
    private static AuthenticationManager manager;

    public AuthenticationManager() throws FileNotFoundException {
    }

    public static AuthenticationManager getInstance() throws FileNotFoundException {
        if (manager == null) {
            manager = new AuthenticationManager();
        }
        return manager;
    }

    public Student authenticate(String username, String password) {
        do {
            Student student = Student.parse(scanner.nextLine());
            if (student.username.equals(username) && student.password.equals(password)) {
                return student;
            }
        } while (scanner.hasNext());
        return null;
    }
}
