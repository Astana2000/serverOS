import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Server started!");
            while (true) {
                try (
                        Socket socket = server.accept();
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream()));
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()))) {
                    System.out.println("Client connected");
                    String request = reader.readLine();
                    String response = "Hello from Server " + request.length();
                    System.out.println(response);
                    writer.write(response);
                    writer.newLine();
                    writer.flush();

                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }


    }
}
