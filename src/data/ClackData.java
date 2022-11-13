package data;

import java.util.Date;

/**
 * The ClackData class is a superclass representing data packets to be sent between the Clack client and server.
 * @author Calvin Besch
 * @author Trevor Savage
 */
public abstract class ClackData implements java.io.Serializable{

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
	 * Encrypts the given message using the Vignere cipher with the given key. Returns the result.
	 * @param inputStringToEncrypt message to encrypt
	 * @param key key to encrypt with
	 * @return encrypted message
	 */
	protected String encrypt(String inputStringToEncrypt, String key) {
		String encrypted = "";
		int keyIndex = 0;

		for (int i = 0; i < inputStringToEncrypt.length(); i++) {
			if(65 <= inputStringToEncrypt.charAt(i) && inputStringToEncrypt.charAt(i) <= 90) {
				encrypted += (char) (((inputStringToEncrypt.charAt(i) - 65 + Character.toUpperCase(key.charAt(keyIndex)) - 65) % 26) + 65);
				keyIndex = (keyIndex + 1) % key.length();
			}
			else if (97 <= inputStringToEncrypt.charAt(i) && inputStringToEncrypt.charAt(i) <= 122) {
				encrypted += (char) (((inputStringToEncrypt.charAt(i) - 97 + Character.toUpperCase(key.charAt(keyIndex)) - 65) % 26) + 97);
				keyIndex = (keyIndex + 1) % key.length();
			}
			else encrypted += inputStringToEncrypt.charAt(i);
		}
		return encrypted;
	}

	/**
	 * Decrypts the given (encrypted) message using the given key. Returns the result.
	 * @param inputStringToDecrypt encrypted message to decrypt
	 * @param key key to decrypt with
	 * @return decrypted message
	 */
	protected String decrypt(String inputStringToDecrypt, String key) {
		String decrypted = "";
		int keyIndex = 0;

		for(int i = 0; i < inputStringToDecrypt.length(); i++) {
			if (65 <= inputStringToDecrypt.charAt(i) && inputStringToDecrypt.charAt(i) <= 90) {
				decrypted += (char) (((inputStringToDecrypt.charAt(i) - 65 - (Character.toUpperCase(key.charAt(keyIndex)) - 65) + 26) % 26) + 65);
				keyIndex = (keyIndex + 1) % key.length();
			} else if (97 <= inputStringToDecrypt.charAt(i) && inputStringToDecrypt.charAt(i) <= 122) {
				decrypted += (char) (((inputStringToDecrypt.charAt(i) - 97 - (Character.toUpperCase(key.charAt(keyIndex)) - 65) + 26) % 26) + 97);
				keyIndex = (keyIndex + 1) % key.length();
			} else decrypted += inputStringToDecrypt.charAt(i);
		}
		return decrypted;
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

