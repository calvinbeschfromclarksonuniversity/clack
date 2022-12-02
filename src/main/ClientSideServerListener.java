package main;

/**
 * Creates a Runnable object in ClackClient, and a thread wrapped around the Runnable Object
 */
public class ClientSideServerListener implements Runnable{

    private ClackClient client;

    /**
     * Constructor for the ClientSideServerListener
     * @param client the ClackClient Object that the runnable object will be created in
     */
    public ClientSideServerListener(ClackClient client){
        this.client = client;
    }

    /**
     * While the connection to the server is open, receives and prints the data
     */
    @Override
   public void run(){
        while (!client.getCloseConnection()) {
            client.recieveData();
            client.printData();
        }
    }
}
