package ProjectJavaExam;

public class Author {

	public Author() {
	}

	public Author(String name, String email, char gender) {

		this.name = name;
		this.email = email;
		this.gender = gender;
	} // fix

	public Author(int id , String name, String email, char gender) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
	}

	private String name;
	private String email;
	private char gender;
	public void setGender(char gender) {
		this.gender = gender;
	}

	private int id;
	/// them mot cai ID

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getGender() {
		return gender;
	}

	

	@Override
	public String toString() {
		return this.getId() + "," + this.getName() + "," + this.getEmail() + "," + this.getGender();

	}
}
