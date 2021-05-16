import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Server {
    public static ArrayList<Integer> getMessage() {
        return queue.poll();
    }
    static Queue<ArrayList<Integer>> queueFromHandler;
    static Queue<ArrayList<Integer>> queue;
    static Comparator<ArrayList<Integer>> comparator = Comparator.comparingInt(c -> c.get(2));

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(7000)) {
            queue = new PriorityQueue(comparator);
            System.out.println("Server started!");
            while (true) {
                try (
                        Socket socket = server.accept();
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream()));
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()))) {
                    System.out.println("Client connected");
                    ArrayList<Integer> parameters = new ArrayList<>();
                    parameters.add(reader.read()); //time
                    parameters.add(reader.read()); //toAnswer
                    parameters.add(reader.read()); //priority
                    queue.add(parameters);

                    String response = "Time:  " + parameters.get(0)
                            + "sec . To answer? " + (parameters.get(1)==1) +". Priority number:"+ parameters.get(2);
                    System.out.println(response);

                    System.out.println(queue.toString());

                    if (queueFromHandler != null){
                        System.out.println(queueFromHandler.toString());
                    }
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

