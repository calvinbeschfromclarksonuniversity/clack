# clack Part 1 report
Authors: Trevor Bartholomew Savage and Calvin Prothificus Besch

  In order to handle the various equals methods in our classes, we had to use Objects#equals for any necessary comparisons that could possibly include a null. This is because if the object from which the comparison is called is actually instead a null reference, an exception will be thrown as null references aren't objects and don't have methods.
  Currently, FileClackData instances can be initalized with types 0-2, MessageClackData types, and MessageClackData can be initialized with type 3, the FileClackData type. This is because the type system doesn't actually do anything yet - the project document says it will be further developed in future iterations of Clack.
