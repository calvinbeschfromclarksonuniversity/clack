

public class FileClackData {
	private String fileName;
	private String fileContents;

	public FileClackData(userName, fileName, type) {
		super(userName, type);
		this.fileName = fileName;
		fileContents = null;
	}

	public FileClackData() {
		FileClackData("Anon", "unnamed", 3);
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

	public String readFileContents() { }
	
	public void writeFileContents() { }

	public int hashCode() { //TODO: implement
	}

	public boolean equals(Object otherFile) {
		FileClackData other = (FileClackData)otherFile
		return this.fileName == other.fileName && this.fileContents == other.fileContents && this.userName == other.userName && this.date.equals(other.date);
	}

	public void toString() { //TODO: implement }
}
