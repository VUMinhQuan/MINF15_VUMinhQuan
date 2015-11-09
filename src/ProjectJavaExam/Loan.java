package ProjectJavaExam;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;

public class Loan {
	
	private String codeReader;// code of Reader
	private int anotherID;// id of Book in the Library
	private Date dayBorrow;
	private Date dayReturn;
	private boolean statusBorrow; // status of reader borrow or return book
	private int nbcopies;// quantities of book borrow
	
	public int getNbcopies() {
		return nbcopies;
	}
	public void setNbcopies(int nbcopies) {
		this.nbcopies = nbcopies;
	}
	public Loan(){}
	public Loan(String codeReader, int anotherID, Date dayBorrow, Date dayReturn, boolean statusBorrow,int nbcopies) {
		this.codeReader = codeReader;
		this.anotherID = anotherID;
		this.dayBorrow = dayBorrow;
		this.dayReturn = dayReturn;
		this.statusBorrow = statusBorrow;
		this.nbcopies = nbcopies;
	}
	
	 public String getCodeReader() {
		return codeReader;
	}
	public void setCodeReader(String codeReader) {
		this.codeReader = codeReader;
	}
	public int getAnotherID() {
		return anotherID;
	}
	public void setAnotherID(int anotherID) {
		this.anotherID = anotherID;
	}
	public Date getDayBorrow() {
		return dayBorrow;
	}
	public void setDayBorrow(Date dayBorrow) {
		this.dayBorrow = dayBorrow;
	}
	public Date getDayReturn() {
		return dayReturn;
	}
	public void setDayReturn(Date dayReturn) {
		this.dayReturn = dayReturn;
	}
	public boolean isStatusBorrow() {
		return statusBorrow;
	}
	public void setStatusBorrow(boolean statusBorrow) {
		this.statusBorrow = statusBorrow;
	}

	@Override
	public String toString()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return this.getCodeReader()+","+this.getAnotherID()+","+this.getNbcopies()+","+dateFormat.format(this.getDayBorrow())+","+ dateFormat.format(this.getDayReturn())+","+this.isStatusBorrow();
	}
	
	// private String 
}
