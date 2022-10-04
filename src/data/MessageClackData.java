

public class MessageClackData extends ClackData {

	private String message;

	public MessageClackData(String userName, String message, int type) {
		super(userName, type);
		this.message = message;
	}

	public MessageClackData() {
		super();
		this.message = null;
	}

	public String getData() {
		return message;
	}

	public boolean equals(Object otherMsg) {
		MessageClackData other = (MessageClackData) otherMsg;
		return this.message.equals(other.message)
				&& this.userName.equals(other.userName)
				&& this.type == other.type
				&& this.date.equals(other.date);
	}

	public int hashCode() {
		String uniqueString = message + '`' + userName + '`' + type + '`' + date;
		return uniqueString.hashCode();
	}

	public String toString() {
		return "MessageClackData Instance:" + '\n'
				+ "Message: " + message + '\n'
				+ "Sender: " + userName + '\n'
				+ "Type: " + type + '\n'
				+ "Time sent: " + date + '\n';
	}
}
