package data;

import java.util.Date;

/**
 * The ClackData class is a superclass representing data packets to be sent between the Clack client and server.
 * @author Calvin Besch
 * @author Trevor Savage
 */
public abstract class ClackData {

	/**
	 * Integer constant representing the type of packet requesting a list of users.
	 * Instances of this type are instantiated as MessageClackData.
	 */
	public static final int CONSTANT_LISTUSERS = 0;

	/**
	 * Integer constant representing the type of packet notifying the server of a client's logout.
	 * Instances of this type are instantiated as MessageClackData.
	 */
	public static final int CONSTANT_LOGOUT = 1;

	/**
	 * Integer constant representing the type of packet containing a message.
	 * Instances of this type are instantiated as MessageClackData.
	 */
	public static final int CONSTANT_SENDMESSAGE = 2;

	/**
	 * Integer constant representing the type of packet containing a file.
	 * Instances of this type are instantiated as FileClackData.
	 */
	public static final int CONSTANT_SENDFILE = 3;

	protected String userName;
	protected int type;
	protected Date date;

	/**
	 * Full constructor of the ClackData class.
	 * @param userName the name of the user sending the packet
	 * @param type the type of packet
	 */
	public ClackData(String userName, int type) {
		this.userName = userName;
		this.type = type;
		date = new Date();
	}

	/**
	 * Partial constructor of the ClackData class. Sets userName to "Anon".
	 * @param type the type of packet
	 */
	public ClackData(int type) {
		this("Anon", type);
	}

	/**
	 * Default constructor of the ClackData class. Sets userName to "Anon" and type to 0 (LISTUSERS.)
	 */
	public ClackData() {
		this("Anon", 0);
	}

	/**
	 * @return the type of packet
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the name of the user who sent the packet
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the time at which the packet was sent
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return whatever data this packet was meant to hold; specific type depends on subclass
	 */
	public abstract String getData();

}
