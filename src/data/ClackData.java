import java.util.*;

public class ClackData {

	//TODO: Are these supposed to be public?
	public static final int CONSTANT_LISTUSERS = 0;
	public static final int CONSTANT_LOGOUT = 1;
	public static final int CONSTANT_SENDMESSAGE = 2;
	public static final int CONSTANT_SENDFILE = 3;

	private String userName;
	private int type;
	private Date date;

	public ClackData(String userName, int type) {
		this.userName = userName;
		this.type = type;
		date = new Date();
	}

	public ClackData(int type) {
		ClackData("Anon", type);
	}

	public ClackData() {
		ClackData("Anon", 0);
	}
	
	public int getType() {
		return type;
	}

	public String getUserName() {
		return userName;
	}

	public Date getDate() {
		return date;
	}

	public ClackData abstract getData();

}
