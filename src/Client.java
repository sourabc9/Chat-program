import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.net.Socket;

public class Client {
    private     GUI            ch      = null;
    private     Socket          s       = null;
    private     BufferedReader  in      = null;
    private     BufferedWriter  out     = null;

    public Client() {
        try {
            s = new Socket("localHost", 1792);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            ch = new GUI("Client", out);
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