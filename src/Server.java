import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    private     GUI           ch      = null;
    private     ServerSocket    server  = null;
    private     Socket          s       = null;
    private     BufferedReader  in      = null;
    private     BufferedWriter  out     = null;

    public Server() {
        try {
            server = new ServerSocket(1792);
            s = server.accept();
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            ch = new GUI("Server", out);
        } catch (IOException ioe) {

        }

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        String line = in.readLine();
                        ch.showString(line);
                    } catch (IOException e) {
                        System.exit(0);     // exit program when connection is lost
                        return;
                    }
                }
            }
        }).start();
    }
}