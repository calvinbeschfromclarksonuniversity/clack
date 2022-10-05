package main;


import data.ClackData;

/**
* Represents the clack client entity that sends data to the server 
*/
public class ClackClient {

    String userName;
    String hostName;
    int port;
    boolean closeConnection;
    ClackData dataToSendToServer;
    ClackData dataToRecieveFromServer;
    
    /**
    * Full constructor for clack client
    * @param userName, the name of the client using clack
    * @param hostName, the name of the device being used by the client
    * @param port the netwoeking poet used to connect to the server
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
            new ClackClient(userName, hostName, 7000);


    }
    
    /**
    * Partial constructor for clack client
    * @param userName, the name of the client using clack
    * hostName: localhost
    * port: 7000
    */
    
   public ClackClient(String userName){
            new ClackClient(userName, "localhost");
    }

    /**
    * Partial constructor for clack client
    * userName: Anonymous
    * hostName: localhost
    * port: 7000
    */
    ClackClient(){
        new ClackClient("Anonymous");
    }
    
    void start(){}
    void readClientData(){}
    void sendData(){}
    void recieveData(){}
    void printData(){}
    
      /**
    * @return userName, the name of the client using clack
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
    * @param port the netwoeking poet used to connect to the server
    */
    public int getPort(){
        return this.port;
    }
    public boolean equals(Object other){
      ClackClient client = (ClackClient)other;
      return ((this.userName == client.userName) && (this.hostName == client.hostName) && (this.port == client.port) && (this.dataToRecieveFromServer == client.dataToRecieveFromServer) && (this.dataToSendToServer == client.dataToSendToServer));

    }
    public String toString() {
        return "The username of the client is " + this.userName + "\n" +
                "The host name of the client is " + this.hostName + "\n" +
                "The port of the client is: " + this.port + "\n" +
                "The data to send to the server is : " + this.dataToSendToServer + "\n +" +
                "The data to send to the server is : " + this.dataToSendToServer + "\n\n";
    }

    public int hashCode(){
      String uniqueString = userName + "`" + hostName + "`" + port + "`" + closeConnection + "`" + dataToSendToServer.hashCode() + "`" + dataToRecieveFromServer.hashCode();
        return uniquestring.hashCode();
    }
}
