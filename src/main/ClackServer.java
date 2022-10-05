package main;

import data.ClackData;

/**
 * Represents the Clack server, the central entity that stores data and connects clients.
 */
public class ClackServer {

    int port;
    boolean closeConnection;
    ClackData dataToReceiveFromClient;
    ClackData dataToSendToClient;

    /**
     * Full constructor for ClackServer.
     * @param port the networking port for clients to connect to
     */
    public ClackServer(int port){
        this.port = port;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
    }

    /**
     * Default constructor for ClackServer. Default values:
     * port: 7000
     */
    ClackServer(){
        new ClackServer(7000);
    }
    void start(){}
    void receiveData(){}
    void sendData(){}

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
        return ((this.port == server.port) && (this.dataToSendToClient == server.dataToSendToClient) && (this.dataToReceiveFromClient == server.dataToReceiveFromClient));

    }

    /**
     * @return a string displaying the values all of the instance variables of this ClackServer
     */
    public String toString() {
        return  "The port of the client is: " + this.port + "\n" +
                "The data to send to the client is : " + this.dataToSendToClient + "\n +" +
                "The data to receive from the client : " + this.dataToReceiveFromClient + "\n\n";
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
        String uniqueString = port + "`" + closeConnection + "`" + dataToReceiveFromClient.hashCode() + "`" + dataToSendToClient.hashCode();
        return uniqueString.hashCode();
    }
}

