package data;

import java.util.Objects;

/**
 * A class that represents a packet sent between the client and the server that contains some kind of message.
 */
public class MessageClackData extends ClackData {

	private String message;

	/**
	 * Full constructor of MessageClackData.
	 * @param userName name of the user who sent the packet
	 * @param message name of the contained message
	 * @param type the type of the packet
	 */
	public MessageClackData(String userName, String message, int type) {
		super(userName, type);
		this.message = message;
	}
	public MessageClackData(String userName, String message,String key, int type) {
		super(userName, type);
		this.message = encrypt (message, key);
	}
	/**
	 * Default constructor of MessageClackData. Default values:
	 * userName: "Anon"
	 * message: null
	 * type: 0 (LISTUSERS)
	 */
	public MessageClackData() {
		super();
		this.message = null;
	}

	/**
	 * @return the contained message
	 */
	public String getData() {
		return message;
	}

	/**
	 * @param key encryption key
	 * @return the contained message, decrypted
	 */
	public String getData(String key) {
		return decrypt(message, key);
	}

	/**
	 * Returns an integer that will be the same as that of another MessageClackData considered equal (see
	 * MessageClackData#equals), but different from that of another MessageClackData considered not equal. Implemented by
	 * constructing a string that has these properties itself, using information from each instance variable and a
	 * separator that cannot be sent in messages, and then taking the hash code of that string.
	 * @return the hash code of this instance of MessageClackData
	 */
	public int hashCode() {
		String uniqueString = message + '`' + userName + '`' + type + '`' + date;
		return uniqueString.hashCode();
	}

	/**
	 * @param otherMsg MessageClackData to be compared
	 * @return true if the passed MessageClackData is considered equal to this one (I.E. has the same values for all of
	 * the instance variables.)
	 */
	public boolean equals(Object otherMsg) {
		MessageClackData other = (MessageClackData) otherMsg;
		return Objects.equals(this.message, other.message)
				&& this.userName.equals(other.userName)
				&& this.type == other.type
				&& this.date.equals(other.date);
	}

	/**
	 * @return a string displaying the values all of the instance variables of this MessageClackData
	 */
	public String toString() {
		return "MessageClackData Instance:" + '\n'
				+ "Message: " + message + '\n'
				+ "Sender: " + userName + '\n'
				+ "Type: " + type + '\n'
				+ "Time sent: " + date + '\n';
	}
}
