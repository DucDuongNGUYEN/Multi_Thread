import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServerSPool {
    private int port;
    private int nbThreads;

    public EchoServerSPool(int port, int nbThreads) {
        this.port = port;
        this.nbThreads = nbThreads;
    }

    public void start() throws Exception {
        ServerSocket server = new ServerSocket(port);
        ExecutorService executor = Executors.newFixedThreadPool(nbThreads);

        while (true) {
            Socket client = server.accept();
            executor.submit(() -> {
                try {
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        out.println(inputLine);
                    }
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[1]);
        int nbThreads = Integer.parseInt(args[2]);
        EchoServerSPool echoServer = new EchoServerSPool(port, nbThreads);
        try {
            echoServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
