package test;

import main.ClackClient;

public class TestClackClient {

    public static void main(String[] args) {
        ClackClient clientA = new ClackClient("Billy", "Bob", 1024);
        ClackClient clientB = new ClackClient("Billy", "Bob");
        ClackClient clientC = new ClackClient("Billy");
        ClackClient clientD = new ClackClient();
        ClackClient clientE = new ClackClient();

        System.out.println(clientA);
        System.out.println(clientB);
        System.out.println(clientC);
        System.out.println(clientD);

        System.out.println(clientA.getUserName());
        System.out.println(clientA.getHostName());
        System.out.println(clientA.getPort());
        System.out.println(clientA.equals(clientB));
        System.out.println(clientD.equals(clientE));
        System.out.println(clientA.hashCode());
        System.out.println(clientD.hashCode());
        System.out.println(clientE.hashCode());

        ClackClient clientN = new ClackClient("N", "Nxcellent", 1024);
        System.out.println(clientN);

        clientA.start();
    }
}
