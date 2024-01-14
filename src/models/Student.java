package models;

import java.util.Arrays;

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

    public String toHtml() {
        return "<table>\n" +
                "  <tr>\n" +
                "    <th>Code</th>\n" +
                "    <th>First name</th>\n" +
                "    <th>Last name</th>\n" +
                "    <th>Grade</th>\n" +
                "    <th>Field</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>" + code + "</td>\n" +
                "    <td>" + firstName + "</td>\n" +
                "    <td>" + lastName + "</td>\n" +
                "    <td>" + grade + "</td>\n" +
                "    <td>" + field + "</td>\n" +
                "  </tr>\n" +
                "</table>";
    }
}
