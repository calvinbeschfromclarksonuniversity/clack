package test;

import java.io.*;
import java.util.Objects;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

public class TestClackData {

    public static void main(String[] args) {

        MessageClackData messageA = new MessageClackData("Bob", "greetings my fellow earthlings", ClackData.CONSTANT_SENDMESSAGE);
        ClackData messageB = new MessageClackData("Bob", "greetings my fellow earthlings", ClackData.CONSTANT_SENDMESSAGE);
        MessageClackData messageC = new MessageClackData("Bob", "how do you fare?", ClackData.CONSTANT_SENDMESSAGE);
        MessageClackData messageD = new MessageClackData( null, null, ClackData.CONSTANT_LISTUSERS);

        System.out.println("Testing MessageClackData: " + "\n"
                + "getData(): " + messageA.getData() + "\n"
                + "equals(): on equal messages -> " + messageA.equals(messageB) + " on unequal messages -> " + messageA.equals(messageC) + "\n"
                + "hashcode(): of two separate but equal messages -> " + messageA.hashCode() + " , " + messageB.hashCode() +
                    " of a message not equal to them -> " + messageC.hashCode() + "\n"
                + "toString(): " + messageB.toString() + "\n"
                + "getType(): " + messageC.getType() + "\n"
                + "getUserName(): " + messageB.getUserName() + "\n"
                + "getDate(): " + messageA.getDate().toString() + "\n"
        );

        FileClackData fileA = new FileClackData("Megasteve", "loss.png", ClackData.CONSTANT_SENDFILE);
        ClackData fileB = new FileClackData("Megasteve", "loss.png", ClackData.CONSTANT_SENDFILE);
        FileClackData fileC = new FileClackData("LessCoolSteve27", "uncool-stuff.pdf", ClackData.CONSTANT_SENDFILE);
        FileClackData fileD = new FileClackData();

        System.out.println("Testing MessageClackData: " + "\n"
                + "getData(): " + fileA.getData() + "\n"
                + "hashcode(): of two separate but equal messages -> " + fileA.hashCode() + " , " + fileB.hashCode() +
                " of a message not equal to them -> " + fileC.hashCode() + "\n"
                + "toString(): " + fileB.toString() + "\n"
                + "getType(): " + fileC.getType() + "\n"
                + "getUserName(): " + fileB.getUserName() + "\n"
                + "getDate(): " + fileA.getDate().toString() + "\n"
                + "getFileName(): " + fileA.getFileName() + "\n"
        );

        System.out.println("equals(): on equal messages -> " + fileA.equals(fileB) + " on unequal messages -> " + fileA.equals(fileC));

        fileA.setFileName("icon.txt");
        System.out.println("setFileName(): " + fileA.getFileName());

        System.out.println(messageD.getUserName());
        System.out.println(fileD.getFileName());
        System.out.println(messageD.getType());
        System.out.println(fileD.getType());
        System.out.println(fileD.getData());
        System.out.println(fileD.equals(fileA));
        System.out.println(messageD.equals(messageA));
   }
}
