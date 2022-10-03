

public class MessageClackData extends ClackData {

	private String message;

	public MessageClackData(String userName, String message, int type) {
		super(userName, type);
		this.message = message;
	}

	public MessageClackData() {
		super();
		this.message = "";
	}

	public ClackData getData() {
		return this;
	}

	public boolean equals(Object otherMsg) {
		MessageClackData other = (MessageClackData)otherMsg;
		return this.message == other.message && this.userName == other.userName && this.date == other.date && this.type == other.type;
	}

	public int hashCode() {
		//TODO: Implement this!
	}

	public String toString() {
		System.out.println("MessageClackData:\nMessage: " + message + "\nType: " + type + "\nSender: " + userName + "\nTime: " + date.toString());
	}
