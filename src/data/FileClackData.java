

public class FileClackData extends ClackData {
	private String fileName;
	private String fileContents;

	public FileClackData(String userName, String fileName, int type) {
		super(userName, type);
		this.fileName = fileName;
		fileContents = null;
	}

	public FileClackData() {
		this("Anon", "unnamed", 3);
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getData() {
		return fileContents;
	}

	public String readFileContents() { return null; } //To be implemented later
	
	public void writeFileContents() { } //To be implemented later

	public int hashCode() {
		String uniqueString = fileName + '`' + fileContents + '`' + userName + '`' + type + '`' + date;
		return uniqueString.hashCode();
	}

	public boolean equals(Object otherFile) {
		FileClackData other = (FileClackData)otherFile;
		return this.fileName.equals(other.fileName)
				&& this.fileContents.equals(other.fileContents)
				&& this.userName.equals(other.userName)
				&& this.date.equals(other.date)

				&& this.type == other.type; //Just in case FileClackData must support more types in the future
	}

	public String toString() {
		return "FileClackData Instance:" + '\n'
				+ "File: " + fileName + '\n'
				+ "File Contents: " + fileContents + '\n'
				+ "Sender: " + userName + '\n'
				+ "Type: " + type + '\n'
				+ "Time sent: " + date + '\n';
	}
}
