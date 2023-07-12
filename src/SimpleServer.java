import java.io.*;
import java.net.*;


public class SimpleServer {


    public static void main(String[] args) {

        //args length checking and correct usage outputs
        if (args.length != 1) {
            System.out.println("Wrong number of arguments");
            System.out.println("Correct Usage: java SimpleServer <port>");
            System.exit(0);
        }


        //variables to hold the response message and the recieved message, and a boolean one that tells the while loop
        //if the program is finished
        boolean finished = false;
        String response = "HELLO";
        String received;


        try {
            //creating a socket
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            Socket socket = serverSocket.accept();

            //creating an input and a reader
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //creating an output and writer
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            //receiving the client's correct IP address
            String clientAddress = reader.readLine();


            //untilt the program is finished
            while (!finished) {

                //recieve the clients input
                received = reader.readLine();

                //check it
                if (received.equals(clientAddress)) {
                    response = "ADDRESS: " + socket.getLocalAddress();
                } else if (received.equals("BYE")) {
                    finished = true;
                    response = "BYE";
                }

                //send a response
                writer.println(response);

            }

            //close the socket
            socket.close();


            //catch an IO exception thrown by creating a bad socket
        } catch (IOException e) {
            System.out.println("I/O error" + e);
        }
    }
}