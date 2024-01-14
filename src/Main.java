import models.AppHttpRequest;
import models.AppHttpResponse;
import sun.misc.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = clientSocket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

                String requestData = reader.readLine();

                AppHttpRequest request = AppHttpRequest.parse(requestData);
                System.out.println("Client requestData: " + requestData);

                switch (request.path) {
                    case "/":
                        AppHttpResponse response = new AppHttpResponse();
                        response.content = new String(Files.readAllBytes(Paths.get(".\\src\\pages\\index.html")));
                        writer.write(response.toString());
                        break;
                    case "/login":

                        response = new AppHttpResponse();
                        response.content = new String(Files.readAllBytes(Paths.get(".\\src\\pages\\index.html")));
                        writer.write(response.toString());
                        break;
                }



                String errorPage = generateErrorPage();
                //writer.write("HTTP/1.1 401 Unauthorized\r\nContent-Length: " + errorPage.getBytes().length + "\r\n\r\n" + errorPage);

                // Perform authentication based on the extracted username and password
                /*boolean isAuthenticated = authenticateUser(username, password); // Implement the authenticateUser function

                if (isAuthenticated) {
                    // Generate the HTML page with student information
                    String studentInfo = generateStudentInfoPage(username); // Implement the generateStudentInfoPage function
                    writer.write("HTTP/1.1 200 OK\r\nContent-Length: " + studentInfo.getBytes().length + "\r\n\r\n" + studentInfo);
                } else {
                    // Send the error HTML page for authentication failure
                    String errorPage = generateErrorPage();
                    writer.write("HTTP/1.1 401 Unauthorized\r\nContent-Length: " + errorPage.getBytes().length + "\r\n\r\n" + errorPage);
                }*/

                writer.flush();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateErrorPage() {
        // Generate the HTML page for authentication error
        return "<html><body><h1>Error</h1><p>Authentication failed</p></body></html>";
    }
}