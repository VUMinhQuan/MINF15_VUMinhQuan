package ProjectJavaExam;

//import java.util.ArrayList;

public class Reader {

	
	private String name;
	private String code;
	private String email;
	private char gender;
	//private boolean statusBorrow;
	//private ArrayList<Book> listBookBorrow;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	//Define Contructor
	public Reader(){}
	public Reader(String name, String code, String email, char gender) {
		
		this.name = name;
		this.code = code;
		this.email = email;
		this.gender = gender;
	
	}
	
	@Override
	public String toString()
	{
		return this.getName()+","+this.getCode()+","+this.getEmail()+","+this.getGender();
	}
}
