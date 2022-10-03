import java.util.*;

public class ClackData {

	public static final CONSTANT_LISTUSERS = 0;
	public static final CONSTANT_LOGOUT = 1;
	public static final CONSTANT_SENDMESSAGE = 2;
	public static final CONSTANT_SENDFILE = 3;

	String userName;
	int type;
	Date date;

	public ClackData(userName, type) {
		this.userName = userName;
		this.type = type;
		//TODO: initialize date here
	}

}
