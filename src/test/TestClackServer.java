package test;

import main.ClackServer;

public class TestClackServer {

    public static void main(String[] args) {
        ClackServer serverA = new ClackServer(69);
        ClackServer serverB = new ClackServer();
        ClackServer serverC = new ClackServer(69);
        ClackServer serverD = new ClackServer(-69);

        System.out.println(serverA);
        System.out.println(serverB);

        System.out.println(serverA.getPort());
        System.out.println(serverA.equals(serverB));
        System.out.println(serverA.equals(serverC));
        System.out.println(serverA.hashCode());
        System.out.println(serverB.hashCode());
        System.out.println(serverC.hashCode());
        System.out.println(serverD.hashCode()); //commetn
    }
}
