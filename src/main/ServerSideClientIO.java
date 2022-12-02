package main;

import data.ClackData;

import java.io.*;
import java.net.Socket;

/**
 * Handles the IO between the ClackServer instance and a single one of its connected clients.
 */
public class ServerSideClientIO implements Runnable {

    boolean closeConnection;
    ClackData dataToReceiveFromClient;
    ClackData dataToSendToClient;
    ObjectInputStream inFromClient;
    ObjectOutputStream outToClient;
    ClackServer server;
    Socket clientSocket;

    /**
     * Full constructor  for ServersideClientIO.
     * @param server ClackServer that accepted this connection
     * @param clientSocket ClackClient that requested this connection
     */
    public ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        closeConnection = false;

        dataToReceiveFromClient = null;
        dataToSendToClient = null;
        inFromClient = null;
        outToClient = null;
    }

    /**
     * Runs the thread for this connection between client and server. Uses the socket's data streams to send and
     * receive messages to client in communication with server.
     */
    @Override
    public void run() {
        try {
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

            while (!closeConnection) {
                receiveData();
                server.broadcast(dataToReceiveFromClient);
            }
        } catch (IOException e) {
            System.out.println("IO Error when establishing single client connection:" + e);
        }
    }

    void sendData() {
        try{
            outToClient.writeObject(dataToSendToClient);
        }catch (IOException ioe){
            System.out.println("IO error occurred while sending data");
        }
    }

    void setDataToSendToClient(ClackData data) {
        this.dataToSendToClient = data;
    }

    void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();

            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                this.closeConnection = true;
                server.remove(this);
            }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFoundException thrown in receiveData(): " + cnfe.getMessage());

        } catch (InvalidClassException ice) {
            System.err.println("InvalidClassException thrown in receiveData(): " + ice.getMessage());

        } catch (StreamCorruptedException sce) {
            System.err.println("StreamCorruptedException thrown in receiveData(): " + sce.getMessage());

        } catch (OptionalDataException ode) {
            System.err.println("OptionalDataException thrown in receiveData(): " + ode.getMessage());

        } catch (IOException ioe) {
            System.err.println("IOException thrown in receiveData(): " + ioe.getMessage());
        }
    }
}
