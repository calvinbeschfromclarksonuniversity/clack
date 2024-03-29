package main;


import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.Scanner;

/**
* Represents the clack client entity that sends data to the server 
*/
public class ClackClient {

    private static final String KEY = "PONTIFICUS";
    private static final int DEFAULT_PORT = 7000;

    String userName;
    String hostName;
    int port;
    boolean closeConnection;
    ClackData dataToSendToServer;
    ClackData dataToRecieveFromServer;
    java.util.Scanner inFromStd;

    ObjectInputStream inFromServer;
    ObjectOutputStream outToServer;

    /**
    * Full constructor for clack client
    * @param userName, the name of the client using clack
    * @param hostName, the name of the device being used by the client
    * @param port the networking poet used to connect to the server
     * @throws IllegalArgumentException
    */

    public ClackClient(String userName, String hostName, int port){
        if (userName == null)
            throw new IllegalArgumentException("The username cannot be null");
        else
            this.userName = userName;
        if (hostName == null)
            throw new IllegalArgumentException("The hostname cannot be null");
        else
            this.hostName = hostName;

        if (port < 1024)
            throw new IllegalArgumentException("The port must be an integer greater than 1024");
        else
            this.port = port;

        this.closeConnection = false;
        dataToSendToServer = null;
        dataToRecieveFromServer = null;

        inFromServer = null;
        outToServer = null;
    }
    
    /**
    * Partial constructor for clack client
    * @param userName, the name of the client using clack
    * @param hostName, the name of the device being used by the client
    * port = 7000
     * @throws IllegalArgumentException
    */
    
   public ClackClient(String userName, String hostName) {
       this(userName, hostName, DEFAULT_PORT);

       if (userName == null)
           throw new IllegalArgumentException("The username cannot be null");
       else
           this.userName = userName;
       if (hostName == null)
           throw new IllegalArgumentException("The hostname cannot be null");
       else
           this.hostName = hostName;
   }
    /**
    * Partial constructor for clack client
    * @param userName, the name of the client using clack
    * hostName: localhost
    * port: 7000
     * @throws IllegalArgumentException
    */
    
   public ClackClient(String userName){
            this(userName, "localhost");
       if (userName == null)
           throw new IllegalArgumentException("The username cannot be null");
       else
           this.userName = userName;
    }

    /**
    * Partial constructor for clack client
    * userName: Anonymous
    * hostName: localhost
    * port: 7000
     * @throws IllegalArgumentException
    */
    public ClackClient(){
        this("Anonymous");
    }
    /**
     * @return userName, the name of the client using clack
     */

    /**
     * Starts an instance of ClackClient and attemps to connect to a ClackServer using the information provided in args
      * @param args String in format username@hostname:port, or username@hostname, or just username
     */
    public static void main(String[] args) {

        ClackClient client;

        if (args.length == 0) {
            client = new ClackClient();
        } else if (args.length == 1) {
            String[] splitArgs;

            if (args[0].contains("@")) {
                splitArgs = args[0].split("@");
                if (args[0].contains(":")) {
                    String[] splitArgs2 = splitArgs[1].split(":");
                    try {
                        client = new ClackClient(splitArgs[0], splitArgs2[0], Integer.parseInt(splitArgs2[1]));
                    } catch ( NumberFormatException e ) {
                        throw new IllegalArgumentException("Text after ':' was not a port number");
                    }
                } else {
                    client = new ClackClient(splitArgs[0], splitArgs[1]);
                }
            } else {
                client = new ClackClient(args[0]);
            }
        } else {
            throw new IllegalArgumentException("ClackClient takes either 0 or 1 arguments.");
        }

        client.start();
    }

    /**
     * Starts this client's communication with the server.
     */
    public void start(){

        try {
            Socket socket = new Socket(hostName, port);
            socket.setSoTimeout(10000);

            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());

            dataToSendToServer = new MessageClackData(this.userName, "greetings", ClackData.CONSTANT_SENDMESSAGE);
            sendData();

            inFromStd = new Scanner(System.in);

//hey idiots if it breaks its because of this this
            ClientSideServerListener listener = new ClientSideServerListener(this);
            Thread clientSideServerListenerThread = new Thread(listener);
            clientSideServerListenerThread.start();

            while (socket.isConnected() && !closeConnection) {
                readClientData();
                sendData();
            }

            closeConnection = true;
            clientSideServerListenerThread.join();
            inFromStd.close();
            outToServer.close();
            inFromServer.close();
            socket.close();

        } catch ( UnknownHostException e ) {
            System.err.println("Unknown host: " + e);
        } catch ( NoRouteToHostException e ) {
            System.err.println("Server unreachable: " + e);
        } catch ( ConnectException e ) {
            System.err.println("Connection refused: " + e);
        } catch (SocketException e) {
            System.err.println("Socket exception :" + e);
        } catch (IOException e) {
            System.err.println("PI/O Error : " + e);
        } catch (InterruptedException e) {
            System.err.println(e);
        }

    }

    /**
     * Read input from the client and act accordingly on it depending on what it is.
     */
    void readClientData(){
        String input = inFromStd.nextLine();
        if (input.equals("DONE")) {
            dataToSendToServer = new MessageClackData(userName, userName + " left the room.", ClackData.CONSTANT_LOGOUT);
            closeConnection = true;
        }
        else if (input.equals("LISTUSERS")) {
            dataToSendToServer = new MessageClackData(userName, "ONLINE:\n", ClackData.CONSTANT_LISTUSERS);
        }
        else if (input.length() >= 10 && input.substring(0, 8).equals("SENDFILE")) {
             dataToSendToServer = new FileClackData(userName, input.substring(9), 3);

             try {
                 FileReader reader = new FileReader(input.substring(9));
                 reader.read();
             } catch(FileNotFoundException e) {
                 System.err.println("Error! File not found: " + e);
                 dataToSendToServer = null;
             } catch(IOException e) {
                 System.err.println("Error! General I/O error: " + e);
                 dataToSendToServer = null;
             }
        }
        else {
            dataToSendToServer = new MessageClackData(userName, input, 2);
        }
    }
    /**
     * Initializes a ClackData obkect 'dataToSendToServer based upon a given input from the user
     */
    void sendData(){
        try {
            outToServer.writeObject(dataToSendToServer);
        } catch (IOException e) {
            System.err.println("GI/O Error: " + e);
        }
    }

    void recieveData(){
        try {
            if (!closeConnection) dataToRecieveFromServer = (ClackData) inFromServer.readObject();
        } catch (SocketTimeoutException e) {
            dataToRecieveFromServer = null;
        } catch (EOFException e) {
            System.err.println("The server closed. Terminating . . .");
            System.exit(0);
            closeConnection = true;
        } catch (IOException e) {
           System.err.println("DI/O Error : " + e);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e);
        }
    }

    void printData() {
        dataToSendToServer = dataToRecieveFromServer;
        if (dataToSendToServer != null) {
            System.out.println("<" + dataToSendToServer.getUserName() + "> " + dataToSendToServer.getData());
        }
    }
    /**
     * Prints the contents of dataToSendToServer to the client
     */
    


   public String getUserName(){
        return this.userName;
    }
    
      /**
    * @return hostName, the name of the device being used by the client
    */
   public String getHostName(){
        return this.hostName;
    }
    
      /**
    * @return port the networking port used to connect to the server
    */
    public int getPort(){
        return this.port;
    }

    /**
     * @param other ClackClient to be compared
     * @return true if the passed ClackClient is considered equal to this one (I.E. has the same values for all of the
     * instance variables.)
     */
    public boolean equals(Object other){
      ClackClient client = (ClackClient)other;
      return ((this.userName == client.userName) && (this.hostName == client.hostName) && (this.port == client.port) && (this.closeConnection == client.closeConnection) && (this.dataToRecieveFromServer == client.dataToRecieveFromServer) && (this.dataToSendToServer == client.dataToSendToServer));
    }

    /**
     * @return a string displaying the values all of the instance variables of this ClackClient
     */
    public String toString() {
        return "The username of the client is " + this.userName + "\n" +
                "The host name of the client is " + this.hostName + "\n" +
                "The port of the client is: " + this.port + "\n" +
                "Is connection open?: " + this.closeConnection + "\n" +
                "The data to send to the server is : " + this.dataToSendToServer + "\n" +
                "The data to send to the server is : " + this.dataToSendToServer + "\n\n";
    }

    /**
     * Returns an integer that will be the same as that of another ClackClient considered equal (see
     * ClackClient#equals), but different from that of another ClackClient considered not equal. Implemented by
     * constructing a string that has these properties itself, using information from each instance variable and a
     * separator that cannot be sent in messages, and then taking the hash code of that string.
     * NOTE: If ` must be able to be sent in messages, a different separator may be chosen.
     * @return the hash code of this instance of ClackClient
     */
    public int hashCode(){
        return Objects.hash(this.userName, this.hostName, this.port, this.closeConnection, this.dataToSendToServer, this.dataToRecieveFromServer);
    }

    /** Returns a Bool representing if the client's connection is closed
     */
    public boolean getCloseConnection() {
        return closeConnection;
    }
}
