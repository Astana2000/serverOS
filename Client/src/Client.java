import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Client {
    static Random generator = new Random();
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7000);
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Connected to server");
            int processingTime = 5 + generator.nextInt(115);
            int toAnswer = (generator.nextInt() % 2 + 2) %2;
            int priority = generator.nextInt(5);
            writer.write(processingTime);
            writer.write(toAnswer);
            writer.write(priority);

            writer.newLine();
            writer.flush();

            String response = reader.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
