import models.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File(".\\data\\student_list.txt"));
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));


                StringBuilder requestData = new StringBuilder();
                do {
                    int c = input.read();
                    requestData.append((char) c);
                } while (input.available() > 0);

                System.out.println("Client requestData: " + requestData);

                AppHttpRequest request = AppHttpRequest.parse(requestData.toString());
                switch (request.path) {
                    case "/":
                        AppHttpResponse response = new AppHttpResponse();
                        response.content = new String(Files.readAllBytes(Paths.get(".\\src\\pages\\index.html")));
                        writer.write(response.toString());
                        break;
                    case "/login":
                        Student student = AuthenticationManager.getInstance().authenticate(request.params.get("username"), request.params.get("password"));

                        response = new AppHttpResponse();
                        if (student != null) {
                            response.content = "<!DOCTYPE html>\n" +
                                    "<html lang=\"en\">\n" +
                                    "<head>\n" +
                                    "    <meta charset=\"UTF-8\">\n" +
                                    "    <title>Authentication successful</title>\n" +
                                    "</head>\n" +
                                    "<body>\n" +
                                    "<h1>Welcome</h1>" + student.toHtml() + "\n" +
                                    "<div>\n" +
                                    "    <button onclick=\"location.href='/'\">Back</button>\n" +
                                    "</div>\n" +
                                    "</body>\n" +
                                    "</html>";
                        } else {
                            response.status = AppHttpStatus.Unauthorized;
                            response.content = new String(Files.readAllBytes(Paths.get(".\\src\\pages\\failAuth.html")));
                        }
                        writer.write(response.toString());
                        break;
                    default:
                        response = new AppHttpResponse();
                        response.status  =AppHttpStatus.NotFound;
                        response.content = new String(Files.readAllBytes(Paths.get(".\\src\\pages\\404.html")));
                        writer.write(response.toString());
                        break;

                }
                writer.flush();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}