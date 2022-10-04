package main;
//import main.ClackData

import data.ClackData;

public class ClackClient {

    String userName;
    String hostName;
    int port;
    boolean closeConnection;
    ClackData dataToSendToServer;
    ClackData dataToRecieveFromServer;
   public ClackClient(String userName, String hostName, int port){
            this.userName = userName;
            this.hostName = hostName;
            this.port = port;
            this.closeConnection = false;
            dataToSendToServer = null;
            dataToRecieveFromServer = null;
    }
   public ClackClient(String userName, String hostName) {
            new ClackClient(userName, hostName, 7000);


    }
   public ClackClient(String userName){
            new ClackClient(userName, "localhost");
    }

    ClackClient(){
        new ClackClient("Anonymous");
    }
    void start(){}
    void readClientData(){}
    void sendData(){}
    void recieveData(){}
    void printData(){}

   public String getUserName(){
        return this.userName;
    }
   public String getHostName(){
        return this.hostName;
    }
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
       //TODO: IMPLEMENT THIS!
        return 0;
    }
}
