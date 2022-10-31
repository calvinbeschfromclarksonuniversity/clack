package main;


import data.ClackData;

import java.util.Objects;

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

    /**
    * Full constructor for clack client
    * @param userName, the name of the client using clack
    * @param hostName, the name of the device being used by the client
    * @param port the networking poet used to connect to the server
    */
    
   public ClackClient(String userName, String hostName, int port){
            this.userName = userName;
            this.hostName = hostName;
            this.port = port;
            this.closeConnection = false;
            dataToSendToServer = null;
            dataToRecieveFromServer = null;
    }
    
    /**
    * Partial constructor for clack client
    * @param userName, the name of the client using clack
    * @param hostName, the name of the device being used by the client
    * port = 7000
    */
    
   public ClackClient(String userName, String hostName) {
            this(userName, hostName, DEFAULT_PORT);
    }
    
    /**
    * Partial constructor for clack client
    * @param userName, the name of the client using clack
    * hostName: localhost
    * port: 7000
    */
    
   public ClackClient(String userName){
            this(userName, "localhost");
    }

    /**
    * Partial constructor for clack client
    * userName: Anonymous
    * hostName: localhost
    * port: 7000
    */
    public ClackClient(){
        this("Anonymous");
    }
    /**
     * @return userName, the name of the client using clack
     */
    void start(){}
    void readClientData(){
        if (inFromStd == DONE) {
            closeConnection = true;
        }
         /* else if (inFromStd == (SENDFILE x)){
             if x is readable {
         dataToSendToServer = FileClackData(x,3)
          }
          else {
          dataToSendToServer = null;
          system.err.println("File x is unreadable");
          }
          TODO
          need to implement this in a way that works
*/
         else if (inFromStd == LISTUSERS) {

        }
        else{
            dataToSendToServer = MessageClackData (2)
            }
    }
    /**
     * Initializes a ClackData obkect 'dataToSendToServer based upon a given input from the user
     */
    void sendData(){}
    void recieveData(){}
    void printData(){
         System.out.println("Type: " + dataToSendToServer.getType() + '\n' );
         System.out.println("Username: " + dataToSendToServer.getUserName() + '\n' );
         System.out.println("Date: " + dataToSendToServer.getDate() + '\n' );
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
}
