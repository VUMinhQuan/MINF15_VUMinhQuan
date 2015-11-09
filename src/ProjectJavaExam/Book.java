package ProjectJavaExam;

import java.util.ArrayList;

public class Book {

	private String title; // title
	private BookType type;// type
	private int nbcopies; /// nbcopies
	public int[] lsID; // list ID of author /// them mot cai notherID lay thu
	public int anotherID; // id book in Loan

	private int year;// year
	private boolean borrow; // khong co

	public Book(int anotherID, String title, BookType type, int nbcopies, int[] lsID, int year) {
		this.anotherID = anotherID;
		this.title = title;
		this.type = type;
		this.nbcopies = nbcopies;
		this.lsID = lsID;
		this.year = year;
	}

	public int getAnotherID() {
		return anotherID;
	}

	public void setAnotherID(int anotherID) {
		this.anotherID = anotherID;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNbcopies() {
		return nbcopies;
	}

	public void setNbcopies(int nbcopies) {
		this.nbcopies = nbcopies;
	}

	private ArrayList<Author> authorName;// interface

	public boolean isBorrow() {
		return borrow;
	}

	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}

	public ArrayList<Book> listBook;

	public Book() {
	}

	// Getter Setter

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public ArrayList<Book> getListBook() {
		return listBook;
	}

	public void setListBook(ArrayList<Book> listBook) {
		this.listBook = listBook;
	}

	public String getListIDAuthor() {
		String s = "[";
		for (int i = 0; i < lsID.length; i++) {
			if (i < lsID.length - 1)
				s += lsID[i] + ".";
			else
				s += lsID[i];
		}
		return s + "]";
	}

	@Override
	public String toString() {
		return this.getAnotherID() + "," + this.getTitle() + "," + this.getType() + "," + getListIDAuthor() + ","
				+ this.getYear() + "," + this.nbcopies;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
