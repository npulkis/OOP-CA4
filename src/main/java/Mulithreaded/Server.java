package Mulithreaded;

import DAOs.FighterDaoInterface;
import DAOs.MySqlFighterDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Mulithreaded.Server: Mulithreaded.Server started. Listening for connections on port 8080...");

            FighterDaoInterface FighterDao = new MySqlFighterDao();
            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Mulithreaded.Server: Mulithreaded.Client " + clientNumber + " has connected.");

                System.out.println("Mulithreaded.Server: Port# of remote client: " + socket.getPort());
                System.out.println("Mulithreaded.Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber, FighterDao)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Mulithreaded.Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Mulithreaded.Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Mulithreaded.Server: IOException: " + e);
        }
        System.out.println("Mulithreaded.Server: Mulithreaded.Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Mulithreaded.Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;
        FighterDaoInterface FighterDao;

        public ClientHandler(Socket clientSocket, int clientNumber, FighterDaoInterface FighterDao)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

                this.FighterDao = FighterDao;

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            try {

                String message;
                int res;
                try {
                    while ((message = socketReader.readLine()) != null) {
                        System.out.println("Mulithreaded.Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);


                        if (message.startsWith("findAll")) {
                             socketWriter.println(FighterDao.findAllAsJSON());

                        } else if (message.startsWith("DisplayAll")) {

                        } else {
                            socketWriter.println("Invalid Input Entered");
                        }
                    }
                }catch (Exception e){
                    socketWriter.println("Invalid Command");
                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Mulithreaded.Server: (ClientHandler): Handler for Mulithreaded.Client " + clientNumber + " is terminating .....");
        }
    }

}
