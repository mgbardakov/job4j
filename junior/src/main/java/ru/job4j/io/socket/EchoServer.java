package ru.job4j.io.socket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

/**
 * simple server
 * @author mbardakov
 * @since 06.05.2020
 */
public class EchoServer {
    public static final String END = "Bye";
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            var run = true;
            Pattern delimiter = Pattern.compile("[ =]");
            while (run) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    if (END.equals(parseMessage(str, delimiter))) {
                        run = false;
                    }
                    while (!(str.isEmpty())) {
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }

    /**
     * parsing message from incoming data
     * @param line - incoming line
     * @param delimiter - delimiter to the line
     * @return parsed message
     */
    public static String parseMessage(String line, Pattern delimiter) {
        return delimiter.split(line)[2];
    }
}
