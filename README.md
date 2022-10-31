# clack Part 1 report
Authors: Trevor Bartholomew Savage and Calvin Prothificus Besch
  
  Brief Overview:
  In the Second Part of this project, we implemented the following:
  
  In ClackData:
  -encrypt, a method which encrypts strings using the Vignere cipher
  -decrypt,a method which decripts encrypted strings
  
  In MessageClackData
  -implemented constructor that takes a key and immediately encrypts the data
  -Overloaded getData to allow it to retrieve encrypted data
  
  In FileClackData:
  -readFileContents(), this  reads unsecured files
  -readFileContents(String Key) this implements decrypt to read secured files
  -writeFileContents(), this writes unsecured files
  -writeFileContents(String key), this implements encrypt to write secured files
  -Overloaded getData to allow it to retrieve encrypted data

  In ClackClient:
  -readClientData, this uses a scanner to take input from the user and initializes ClackData accordingly
  -printData, this prints out the contents of the ClackData object dataToRecieveFromServer to the client
  -start, this starts the client- server interface by initializing the scanner that will be used in readClientData
  -Updated constructors to throw IllegalArgumentExceptions when necessary
  
  
  
  
  
  In the first part of this project, we implemented:
  -ClackServer, a class representing the server that stores data and connectes the clients.
  -ClackClient, a class representing an individual user and what they can do.
  -ClackData, an abstract superclass representing data packets to be sent between the clients and server.
  -MessageClackData, a subcalss of ClackData representing packets containing messages to be sent between the clients and server.
  -FileClackData, a subclass of ClackData representing packets containing files to be sent between the clients and server.
  
  Among functions implemented are:
  -Constructors
  -Accessors and Mutators
  -Overloaded toString, equals, and hashCode methods
  -Empty methods to be implemented later
  -And more!
  
  We also implimented test cases for all the above.
  
  If we provide a negative value for a port number, currently ClackServer and Client just stores it as is. In later iterations, we can throw errors to prevent users from entering negative port numbers.
  If a null value is entered for a user, the equal method may fail as it may try to run a comparison on a null object. Other methods may show strange behavior, but will not crash. In future iterations we should prevent user from being instantiated with a null value.
  In order to handle the various equals methods in our classes, we had to use Objects#equals for any necessary comparisons that could possibly include a null. This is because if the object from which the comparison is called is actually instead a null reference, an exception will be thrown as null references aren't objects and don't have methods.
  Currently, FileClackData instances can be initalized with types 0-2, MessageClackData types, and MessageClackData can be initialized with type 3, the FileClackData type. This is because the type system doesn't actually do anything yet - the project document says it will be further developed in future iterations of Clack.
