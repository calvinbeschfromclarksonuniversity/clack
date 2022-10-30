package data;

import java.util.Objects;

/**
 * The FileClackData class is a class that represents a packet sent between the client and server that has some type of
 * file in it.
 * @author Calvin Besch
 * @author Trevor Savage
 */
public class FileClackData extends ClackData {
	private String fileName;
	private String fileContents;

	/**
	 * Full constructor of the FileClackData class.
	 * @param userName the name of the user who sent the packet
	 * @param fileName the name of the contained file
	 * @param type the type of packet (as of now, should always be 3)
	 */
	public FileClackData(String userName, String fileName, int type) {
		super(userName, type);
		this.fileName = fileName;
		fileContents = null;
	}

	/**
	 * Default constructor of the FileClackData class. Default values:
	 * userName: "Anon"
	 * fileName: "unnamed"
	 * type: 3 (SENDFILE)
	 */
	public FileClackData() {
		this("Anon", "unnamed", 3);
	}

	/**
	 * @param fileName the desired name of the contained file
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the name of the contained file
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the contents of the contained file
	 */
	public String getData() {
		return fileContents;
	}

	/**
	 * @param key key to decrypt with
	 * @return the held fileContents, decrypted
	 */
	public String getData(String key) {
		return decrypt(fileContents, key);
	}

	public void readFileContents() { } //To be implemented later
	
	public void writeFileContents() { } //To be implemented later

	/**
	 * Returns an integer that will be the same as that of another FileClackData considered equal (see
	 * FileClackData#equals), but different from that of another FileClackData considered not equal. Implemented by
	 * constructing a string that has these properties itself, using information from each instance variable and a
	 * separator that cannot be sent in messages, and then taking the hash code of that string.
	 * NOTE: If ` must be able to be sent in messages, a different seperator may be chosen.
	 * @return the hash code of this instance of FileClackData
	 */
	public int hashCode() {
		String uniqueString = fileName + '`' + fileContents + '`' + userName + '`' + type + '`' + date;
		return uniqueString.hashCode();
	}

	/**
	 * @param otherFile FileClackData to be compared
	 * @return true if the passed FileClackData is considered equal to this one (I.E. has the same values for all of the
	 * instance variables.)
	 */
	public boolean equals(Object otherFile) {
		FileClackData other = (FileClackData)otherFile;

		return this.fileName.equals(other.fileName)
				&& Objects.equals(this.fileContents, other.fileContents) //Objects#equals used because it works on nulls
				&& this.userName.equals(other.userName)
				&& this.date.equals(other.date)

				&& this.type == other.type; //Just in case FileClackData must support more types in the future
	}

	/**
	 * @return a string displaying the values all of the instance variables of this FileClackData
	 */
	public String toString() {
		return "FileClackData Instance:" + '\n'
				+ "File: " + fileName + '\n'
				+ "File Contents: " + fileContents + '\n'
				+ "Sender: " + userName + '\n'
				+ "Type: " + type + '\n'
				+ "Time sent: " + date + '\n';
	}
}
