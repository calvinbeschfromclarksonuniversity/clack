# clack Part 4 report
Authors: Trevor Bartholomew Savage and Calvin Prothificus Besch

Brief Overview:
In the Fourth part of this project, we implemented the following:
	
ClientSideServerListener:
	- a new class which creates a runnable object in ClackClient, and a thread wrapped around the runnable object
	 - the public constructor, the private ClackClient client variable, and the overridedn run method
	
ServerSideClientIO 
	- a new class that handles sending and receiving data.
	-this replaces the send and recieve data methods within ClackServer
	
In ClackClient:
	- getCloseConnection a method which returns the boolean value of the closeConnection variable
	- an updated version of start() that utilizes the ClientSideServerListener
	
In ClackServer: 
	-Removed outdated functions that are now implemented in ServerSideClientIO
	-updated the start method to include the multithreading functionality using ServerSideClientIO
	-Broadcast, a method which takes a message from one client to all client
	
	
	
WRITEUP QUESTIONS
	
Explain why there should be a separate class to receive data from the
server and print it, and the client only gets data from the user and sends it to the server.
Also, why is the class called a ‘listener’?

The client sending and recieving messages must happen at the same time which requires multiple threads, this class is a listener because it waits for a message, or listens so to speak and prints it for the user, whereas the user is the one who speaks by sending messages to the server. 

Explain why you need a separate thread for each client, and why you
cannot handle all clients in the main server thread. Conceptually, why is the listener
class ‘ClientSideServerListener’ different from the class ‘ServerSideClientIO’?

With many clients sending messages potentially simultaneoulsly, threads are needed in order to process each client's messages at the same time. Conceptually, the serverSideClientIO is different from the ClientSideServerListener becuase it can send and recieve data whereas the ServerListener cannot. 

Explain why the broadcast() and remove() methods are synchronized.
You may find it easier to answer this question after completing all programming.

While both broadcast and remove are a part of MessageClackData, they are called from ServerSideClientIO which each have their own thread. If the interleaving of broadcast is incorrect, it could end up sending the same data twice instead of each individual data. If the interleaving of remove is incorrect, and the second instance verifies the data is still there before the first has deleted it, and then attempts to delete nothing could potentially throw errors. 

Discuss all new methods and new code in existing
methods that you wrote to handle LISTUSERS.

We created a string variable in ServerSideClientIO that represents the client's username, which is automatically sent form the client upon creation. When the client sends LISTUSERS we use a for loop across all of the active ServerSideClientIO's that takes the username variable and puts it into a string that is then broadcast to all clients.

TESTING:
Test 1: Calvin has server and 1 client, Trevor has 1 client

Server Output:
copperthief@boopbox ~/t/d/a/c/c/o/a/clackserver (main)> java -jar clackser
ver.jar 8000
Starting ClackServer ...
^C⏎ 

Calvin's Client Output:
copperthief@boopbox ~/t/d/a/c/c/o/a/clackclient (main)> java -jar clackcli
ent.jar trevvywevy@localhost:8000
<trevorator> Testing 123
it didnt work 
just kidding!
<trevorator> trevorator left the room.
The server closed. Terminating . . .

Trevor's Client Output:
C:\Users\trevo\Downloads>java -jar clackclient.jar trevorator@128.153.203.155:8000                                  
Testing 123                                                                                                          
<trevvywevy> it didnt work                                                                                          
<trevvywevy> just kidding!                                                                                         
LISTUSERS                                                                                                           
<trevorator> trevorator, trevvywevy,                                                                                
DONE


copperthief@boopbox ~/t/d/a/c/c/o/a/clackclient (main)> java -jar clackclient.jar obamnasoda@128.153.1
68.51:6500
<trevorator> Hello
I love JAVA!
<trevorator> JAVA PATRAYYYYYYY
lame im leaving
DONE









Brief Overview:
  In the third Part of this project, we implemented the following:
  
  In ClackData:
  Made ClackData Serializable

  In ClackClient:
  - new instance variables for ObjectInputStream 'inFromServer' & ObjectOutputStream 'outToServer'
  - start, a method which opens a new socket to connects to the server using the saved port variable then reads client data    standard input, sends it to the server, recieves an echo back, and prints it to the user using the readClientData(),        sendData() receiveData() and printData() methods. 
  - sendData method writes the ClackData object in 'dataToSendToServer into ObjectOutpuStream 'outToServer
  - recieveData method reads a ClackData object into 'dataToReceiveFromServer' from ObjectInputStream 'inFromServer'
  - main method in ClackClient parses arguments and starts the client.
  
  In ClackServer:
  - new instance variables for ObjectInputStream 'inFromClient' & ObjectOutputStream 'outToClient'
  - illegal argument exception now thrown when port is set to less than 1024
  - start method creates a Server Socket and a socket for the client, then recieves data from the client and echoes it back    with the receiveData() and sendData() methods.
  - receiveData() reads in a ClackData object from the ObjectInputStream ‘inFromClient’ into ‘dataToReceiveFromClient’
  - sendData() writes out the ClackData object ‘dataToSendToClient’ to the ObjectOutputStream ‘outToClient’.
  - main method in ClackServer parses arguments and starts the Server.
  
  Created JAR files for ClackClient and ClackServer
  
  Command line output:
  
  With java -jar clackserver.jar:
  
  java -jar clackclient.jar
  test       
MessageClackData Instance:
Message: test
Sender: Anonymous
Type: 2
Time sent: Sun Nov 13 16:42:16 EST 2022

  java -jar clackclient.jar trevvywevvy
hehheehe
MessageClackData Instance:
Message: hehheehe
Sender: trevvywevvy
Type: 2
Time sent: Sun Nov 13 16:43:31 EST 2022

java -jar clackclient.jar trevvywevvy@localhost:7000
hahahahha
MessageClackData Instance:
Message: hahahahha
Sender: trevvywevvy
Type: 2
Time sent: Sun Nov 13 16:44:33 EST 2022

java -jar clackclient.jar trevvywevvy@localhost:100
Exception in thread "main" java.lang.IllegalArgumentException: The port must be an integer greater than 1024
	at main.ClackClient.<init>(ClackClient.java:51)
	at main.ClackClient.main(ClackClient.java:131)
  
  java -jar clackclient.jar trevvywevvy@localhost
weeee
MessageClackData Instance:
Message: weeee
Sender: trevvywevvy
Type: 2
Time sent: Sun Nov 13 16:46:01 EST 2022

copperthief@boopbox ~/t/d/a/c/c/o/a/clackclient (main) [SIGINT]> java -jar clackcli
ent.jar trevvywevvy@notlocalhost
Unknown host: java.net.UnknownHostException: notlocalhost

now, with java -jar clackserver.jar 8000:

java -jar clackclient.jar
Connection refused: java.net.ConnectException: Connection refused

java -jar clackclient.jar trevvywevvy@localhost:8000
message
MessageClackData Instance:
Message: message
Sender: trevvywevvy
Type: 2
Time sent: Sun Nov 13 16:55:33 EST 2022
  
  
  
  
  
  
  Brief Overview:
  In the Second Part of this project, we implemented the following:
  
  In ClackData:
  - encrypt, a method which encrypts strings using the Vignere cipher
  - decrypt,a method which decripts encrypted strings
  
  In MessageClackData
  - implemented constructor that takes a key and immediately encrypts the data
  - Overloaded getData to allow it to retrieve encrypted data
  
  In FileClackData:
  - readFileContents(), this  reads unsecured files
  - readFileContents(String Key) this implements decrypt to read secured files
  - writeFileContents(), this writes unsecured files
  - writeFileContents(String key), this implements encrypt to write secured files
  - Overloaded getData to allow it to retrieve encrypted data

  In ClackClient:
  - readClientData, this uses a scanner to take input from the user and initializes ClackData accordingly
  - printData, this prints out the contents of the ClackData object dataToRecieveFromServer to the client
  - start, this starts the client- server interface by initializing the scanner that will be used in readClientData
  - Updated constructors to throw IllegalArgumentExceptions when necessary
  
  The terminal output when running testClackClient is:
  
  The username of the client is Billy
The host name of the client is Bob
The port of the client is: 1024
Is connection open?: false
The data to send to the server is : null
The data to send to the server is : null


The username of the client is Billy
The host name of the client is Bob
The port of the client is: 7000
Is connection open?: false
The data to send to the server is : null
The data to send to the server is : null


The username of the client is Billy
The host name of the client is localhost
The port of the client is: 7000
Is connection open?: false
The data to send to the server is : null
The data to send to the server is : null


The username of the client is Anonymous
The host name of the client is localhost
The port of the client is: 7000
Is connection open?: false
The data to send to the server is : null
The data to send to the server is : null


Billy
Bob
1024
false
true
1519325177
-1885299100
-1885299100
The username of the client is N
The host name of the client is Nxcellent
The port of the client is: 1024
Is connection open?: false
The data to send to the server is : null
The data to send to the server is : null


DONE
null
SENDFILE quote.txt
FileClackData Instance:
File: quote.txt
File Contents: null
Sender: Billy
Type: 3
Time sent: Mon Oct 31 18:55:55 EDT 2022

This is a message
MessageClackData Instance:
Message: This is a message
Sender: Billy
Type: 2
Time sent: Mon Oct 31 18:56:11 EDT 2022

This is another message
MessageClackData Instance:
Message: This is another message
Sender: Anonymous
Type: 2
Time sent: Mon Oct 31 18:56:17 EDT 2022


Process finished with exit code 0


  
  
  
  In the first part of this project, we implemented:
  - ClackServer, a class representing the server that stores data and connectes the clients.
  - ClackClient, a class representing an individual user and what they can do.
  - ClackData, an abstract superclass representing data packets to be sent between the clients and server.
  - MessageClackData, a subcalss of ClackData representing packets containing messages to be sent between the clients and server.
  -FileClackData, a subclass of ClackData representing packets containing files to be sent between the clients and server.
  
  Among functions implemented are:
  - Constructors
  - Accessors and Mutators
  - Overloaded toString, equals, and hashCode methods
  - Empty methods to be implemented later
  - And more!
  
  We also implimented test cases for all the above.
  
  If we provide a negative value for a port number, currently ClackServer and Client just stores it as is. In later iterations, we can throw errors to prevent users from entering negative port numbers.
  If a null value is entered for a user, the equal method may fail as it may try to run a comparison on a null object. Other methods may show strange behavior, but will not crash. In future iterations we should prevent user from being instantiated with a null value.
  In order to handle the various equals methods in our classes, we had to use Objects#equals for any necessary comparisons that could possibly include a null. This is because if the object from which the comparison is called is actually instead a null reference, an exception will be thrown as null references aren't objects and don't have methods.
  Currently, FileClackData instances can be initalized with types 0-2, MessageClackData types, and MessageClackData can be initialized with type 3, the FileClackData type. This is because the type system doesn't actually do anything yet - the project document says it will be further developed in future iterations of Clack.
