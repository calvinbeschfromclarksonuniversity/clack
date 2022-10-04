import data.ClackData;

public class ClackServer {
    //import main.ClackData
    int port;
    boolean closeConnection;
    ClackData dataToReceiveFromClient;
    ClackData dataToSendToClient;
    public ClackServer(int port){
        this.port = port;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
    }

    ClackServer(){
        new ClackServer(7000);
    }
    void start(){}
    void receiveData(){}
    void sendData(){}


    public int getPort(){
        return this.port;
    }
    public boolean equals(Object other){
        ClackServer server = (ClackServer)other;
        return ((this.port == server.port) && (this.dataToSendToClient == server.dataToSendToClient) && (this.dataToReceiveFromClient == server.dataToReceiveFromClient));

    }
    public String toString() {
        return  "The port of the client is: " + this.port + "\n" +
                "The data to send to the client is : " + this.dataToSendToClient + "\n +" +
                "The data to receive from the client : " + this.dataToReceiveFromClient + "\n\n";
    }

    public int hashCode(){
        //TODO: IMPLEMENT THIS!
        return 0;
    }
}

