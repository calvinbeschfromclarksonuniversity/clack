package main;

import data.ClackData;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Objects;
import java.net.*;

/**
 * Represents the Clack server, the central entity that stores data and connects clients.
 */
public class ClackServer {

    private static final int DEFAULT_PORT = 7000;

    int port;
    boolean closeConnection;
    ArrayList<ServerSideClientIO> serverSideClientIOList;

    /**
     * Full constructor for ClackServer.
     * @param port the networking port for clients to connect to
     */
    public ClackServer(int port){
        if (port >= 1024){
            this.port = port;
        }
        else {
            throw new IllegalArgumentException("Port must be greater than 1024");

        }
        serverSideClientIOList = new ArrayList<ServerSideClientIO>();
    }

    /**
     * Default constructor for ClackServer. Default values:
     * port: 7000
     */
    public ClackServer(){
        this(DEFAULT_PORT);
    }

    /**
     * Creates and starts an instance of ClackServer with the information provided in args.
     * @param args either a port number or nothing, in which case the default will be used
     */
    public static void main(String[] args) {
        ClackServer server;
        System.out.println("Starting ClackServer ...");

        if (args.length == 0) {
            server = new ClackServer();
        } else if (args.length == 1) {
            try {
                server = new ClackServer(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Argument was not a port number.");
            }
        } else {
            throw new IllegalArgumentException("ClackServer takes either a port number or no arguments.");
        }

        server.start();
    }

    void start(){
        try{
            ServerSocket sskt = new ServerSocket(port);

            while (!closeConnection) {
                Socket clientSkt = sskt.accept();
                serverSideClientIOList.add(new ServerSideClientIO(this, clientSkt));
                Thread serverClientIOThread = new Thread(serverSideClientIOList.get(0));
                serverClientIOThread.start();
            }

            sskt.close();

        }catch(IOException ioe){
            System.out.println("IO error occurred while starting: " + ioe);
        }
    }

    synchronized void broadcast (ClackData dataToBroadcastToClients) {
        for (ServerSideClientIO connection : serverSideClientIOList) {
           connection.setDataToSendToClient(dataToBroadcastToClients);
           connection.sendData();
        }
    }

    synchronized void remove (ServerSideClientIO connection) {
       serverSideClientIOList.remove(connection);
    }

    /**
     * @return the networking port for clients to connect to
     */
    public int getPort(){
        return this.port;
    }

    /**
     * @param other ClackServer to be compared
     * @return true if the passed ClackServer is considered equal to this one (I.E. has the same values for all of the
     * instance variables.)
     */
    public boolean equals(Object other){
        ClackServer server = (ClackServer)other;
        return ((this.port == server.port) && (this.closeConnection == server.closeConnection) && (this.serverSideClientIOList.equals(((ClackServer) other).serverSideClientIOList)));

    }

    /**
     * @return a string displaying the values all of the instance variables of this ClackServer
     */
    public String toString() {
        return  "The port of the client is: " + this.port + "\n" +
                "Is the server open? :" + this.closeConnection + "\n" +
                "Number of connections open: " + this.serverSideClientIOList.size() + "\n";
    }

    /**
     * Returns an integer that will be the same as that of another ClackServer considered equal (see
     * ClackServer#equals), but different from that of another ClackServer considered not equal. Implemented by
     * constructing a string that has these properties itself, using information from each instance variable and a
     * separator that cannot be sent in messages, and then taking the hash code of that string.
     * NOTE: If ` must be able to be sent in messages, a different separator may be chosen.
     * @return the hash code of this instance of ClackServer
     */
    public int hashCode(){
        return Objects.hash(this.port, this.closeConnection);
    }
}

