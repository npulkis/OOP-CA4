package Mulithreaded;

/** CLIENT                                                  March 2021
 *
 * This Mulithreaded.Client program asks the user to input commands to be sent to the server.
 *
 * There are only two valid commands in the protocol: "Time" and "Echo"
 *
 * If user types "Time" the server should reply with the current server time.
 *
 * If the user types "Echo" followed by a message, the server will echo back the message.
 * e.g. "Echo Nice to meet you"
 *
 * If the user enters any other input, the server will not understand, and
 * will send back a message to the effect.
 *
 * NOte: You must run the server before running this the client.
 * (Both the server and the client will be running together on this computer)
 */


import DTOs.Fighter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Mulithreaded.Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Mulithreaded.Client: Port# of Mulithreaded.Server :" + socket.getPort() );

            System.out.println("Mulithreaded.Client message: The Mulithreaded.Client is running and has connected to the server");
//
//            System.out.println("Please enter a command:  (\"Time\" to get time, or \"Echo message\" to get echo) \n>");
//            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers
            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply



            final String MENU_ITEMS = "\n*** Main Menu ***\n"
                    + "1. Find all as JSON\n"
                    + "2. Exit\n"
                    + "Enter Option [1-2]\n";


            final int FIND_ALL_JSON = 1;
            final int EXIT = 2;

            Scanner keyboard = new Scanner(System.in);
            int option = 0;

            do {
                System.out.println("\n" + MENU_ITEMS);
                try {
                    String userInput = keyboard.nextLine();
                    option = Integer.parseInt(userInput);

                    switch (option) {
                        case FIND_ALL_JSON:
                            System.out.println("");
                            socketWriter.println("findAll");
                            String allFighters = socketReader.nextLine();
                            Gson gsonParser= new Gson();
                            Fighter[] fighters = gsonParser.fromJson(allFighters,Fighter[].class);
                            System.out.println("Mulithreaded.Client message: All fighters in JSON format ");

                            for (Fighter fighter : fighters){
                                System.out.println(fighter);
                            }

                            promptEnterKey();
                            break;

                        case EXIT:
                            System.out.println("Exit menu option chosen");
                            break;
                        default:
                            System.out.println("Invalid Option - please enter number in range");
                            break;
                    }

                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.print("Invalid option - please enter number in range");
                }
            } while (option != EXIT);

            System.out.println("\nExiting Main Menu, goodbye.");
//
//            if(command.startsWith("Time"))   //we expect the server to return a time
//            {
//                String timeString = socketReader.nextLine();
//                System.out.println("Mulithreaded.Client message: Response from server Time: " + timeString);
//            }
//            else                            // the user has entered the Echo command or an invalid command
//            {
//                String input = socketReader.nextLine();
//                System.out.println("Mulithreaded.Client message: Response from server: \"" + input + "\"");
//            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Mulithreaded.Client message: IOException: "+e);
        }
    }

    public void promptEnterKey() {
        System.out.println("\nPress \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}


//  LocalTime time = LocalTime.parse(timeString); // Parse timeString -> convert to LocalTime object if required
