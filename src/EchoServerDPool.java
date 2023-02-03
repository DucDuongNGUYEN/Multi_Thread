import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServerDPool {
    private static final int DEFAULT_THREAD_POOL_SIZE = 5;

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(args[0]);
        int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;

        if (args.length > 1) {
            threadPoolSize = Integer.parseInt(args[1]);
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(() -> {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            out.println("Echo: " + inputLine);
                        }
                    } catch (IOException e) {
                        System.out.println("Error handling client connection: " + e);
                    }
                });
            }
        }
    }
}
