import models.AppUser;
import models.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AuthenticationManager {
    private static AuthenticationManager manager;

    public static AuthenticationManager getInstance() throws FileNotFoundException {
        if (manager == null) {
            manager = new AuthenticationManager();
        }
        return manager;
    }

    public Student authenticate(String username, String password) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(".\\data\\student_list.txt"));
        while (scanner.hasNext()) {
            Student student = Student.parse(scanner.nextLine());
            if (student.username.equals(username) && student.password.equals(password)) {
                return student;
            }
        }
        return null;
    }
}
