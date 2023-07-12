import java.io.*;
import java.net.*;
import java.util.Scanner;


public class SimpleClient {


    public static void main(String[] args) {

        //args length checking and correct usage outputs
        if (args.length != 2) {
            System.out.println("Wrong number of arguments");
            System.out.println("Correct Usage: java SimpleClient <ip address> <port>");
            System.exit(0);
        }

        //variables to hold the response message and the recieved message, and a boolean one that tells the while loop
        //if the program is finished
        //and a scanner to read the user's input
        boolean finished = false;
        Scanner scanner = new Scanner(System.in);
        String response, received;

        try {
            //creating a socket
            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));

            //creating an input and a reader
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //creating an output and writer
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            //send the server the correct IP address
            writer.println("ADDRESS: " + socket.getLocalAddress());

            //until the program is finished
            while (!finished) {

                //read the user's input and send it to the server
                response = scanner.nextLine();
                writer.println(response);

                //get the server's response and print it
                received = reader.readLine();
                System.out.println("SERVER: " + received);

                //if its bye then end the loop
                if (received.equals("BYE"))
                    finished = true;

            }

            //close the socket
            socket.close();

        //catch an IO exception thrown by creating a bad socket
        } catch (IOException e) {
            System.out.println("I/O error" + e);
        }


    }
}