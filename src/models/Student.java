package models;

public class Student {
    public String username, password, code, firstName, lastName, grade, field;

    public Student(String username, String password, String code, String firstName, String lastName, String grade, String field) {
        this.username = username;
        this.password = password;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.field = field;
    }

    public static Student parse(String data) {
        String[] split = data.split(",");
        return new Student(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
    }
}
