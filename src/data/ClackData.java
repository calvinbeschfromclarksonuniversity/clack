package data;

import java.util.Date;

public abstract class ClackData {

	//TODO: Are these supposed to be public?
	public static final int CONSTANT_LISTUSERS = 0;
	public static final int CONSTANT_LOGOUT = 1;
	public static final int CONSTANT_SENDMESSAGE = 2;
	public static final int CONSTANT_SENDFILE = 3;

	protected String userName;
	protected int type;
	protected Date date;

	public ClackData(String userName, int type) {
		this.userName = userName;
		this.type = type;
		date = new Date();
	}

	public ClackData(int type) {
		this("Anon", type);
	}

	public ClackData() {
		this("Anon", 0);
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

	public abstract String getData();

}
