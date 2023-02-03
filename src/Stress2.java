import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class Stress2 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        try (FileWriter writer = new FileWriter("response_times.csv")) {
            writer.write("id,first_response_time,second_response_time\n");
            for (int i = 0; i < n; i++) {
                new StressClient(i, writer).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class StressClient extends Thread {
    private int id;
    private FileWriter writer;

    public StressClient(int id, FileWriter writer) {
        this.id = id;
        this.writer = writer;
    }

    public void run() {
        try {
            Socket socket = new Socket("localhost", 12345);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            long startTime = System.nanoTime();
            output.writeUTF("client stress1 n°" + id);
            long firstResponseTime = System.nanoTime() - startTime;
            output.writeUTF("client stress2 n°" + id);
            long secondResponseTime = System.nanoTime() - startTime;
            System.out.println(id + "," + firstResponseTime + "," + secondResponseTime);
            // NE PAS FERMER LA CONNEXION
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
