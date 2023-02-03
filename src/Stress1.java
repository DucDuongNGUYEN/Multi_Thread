import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Stress1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (int i = 0; i < n; i++) {
            new StressClient1(i).start();
        }
    }
}

class StressClient1 extends Thread {
    private int id;

    public StressClient1(int id) {
        this.id = id;
    }

    public void run() {
        try {
            Socket socket = new Socket("localhost", 12345);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF("client stress1 n°" + id);
            //FERMER LA CONNEXION
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
