package ProjectJavaExam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Library {

	public ArrayList<Book> listBook = new ArrayList<>();
	public ArrayList<Author> listAuthor = new ArrayList<>();
	public ArrayList<Reader> listReader = new ArrayList<>();
	public ArrayList<Loan> listLoan = new ArrayList<>();
	// public ArrayList<Book> listBookBorrow = new ArrayList<>();
	// private Object Loan;

	public void ReadData() throws IOException
	{
		String filePathBook = "E:\\Desktop\\DataFileJava\\book.txt";
		String filePathReader = "E:\\Desktop\\DataFileJava\\reader.txt";
		String filePathLoan = "E:\\Desktop\\DataFileJava\\loan.txt";
		String filePathAuthor = "E:\\Desktop\\DataFileJava\\author.txt";
		ReadDataBookINFileTxt(filePathBook);
		ReadDataAuthorInTxtFile(filePathAuthor);
		ReadReaderInTXTfile(filePathReader);
		ReadListLoanInTxtFile(filePathLoan);
		
	}
	private void ReadListLoanInTxtFile(String filePathLoan) throws IOException {
		List<String> mangLoan = readFileMaster(filePathLoan);
		for (int i = 0; i < mangLoan.size(); i++) {
			String[]tempLoan = mangLoan.get(i).split("[,]");
			String codeReader = tempLoan[0];
			int anotherID = Integer.parseInt(tempLoan[1]);
			String[] arrayDayTime= tempLoan[3].split("[/]");
			Date dayBorrow = new Date(Integer.parseInt(arrayDayTime[0]), Integer.parseInt(arrayDayTime[1]), Integer.parseInt(arrayDayTime[2]));
			String[] arrayDayTimeReturn= tempLoan[3].split("[/]");
			Date dayReturn = new Date(Integer.parseInt(arrayDayTimeReturn[0]), Integer.parseInt(arrayDayTimeReturn[1]), Integer.parseInt(arrayDayTimeReturn[2]));
			Boolean statusBorrow = Boolean.valueOf(tempLoan[5]);
			int nbcopies = Integer.parseInt(tempLoan[2]);
			Loan oldLoan = new Loan(codeReader, anotherID, dayBorrow, dayReturn, statusBorrow, nbcopies);
			addLoanToListLoan(oldLoan);
		}
	}
	private void ReadReaderInTXTfile(String filePathReader) throws IOException {
		List<String> mangReader = readFileMaster(filePathReader);
		for (int i = 0; i < mangReader.size(); i++) {
			String[]tempReader = mangReader.get(i).split("[,]");
			String name =tempReader[0];
			String code = tempReader[1];
			String email = tempReader[2];
			char gender = tempReader[3].charAt(0);
			Reader oldReader = new Reader(name, code, email, gender);
			addReaderToLibrary(oldReader);
		}
	}
	private void ReadDataAuthorInTxtFile(String filePathAuthor) throws IOException {
		List<String> mangAuthor = readFileMaster(filePathAuthor);
		for (int i = 0; i < mangAuthor.size(); i++) {
			String[]tempITEM = mangAuthor.get(i).split("[,]");
			int id = Integer.parseInt(tempITEM[0]);
			String name = tempITEM[1];
			String email = tempITEM[2];
			char gender = tempITEM[3].charAt(0);
			Author oldAuthor = new Author(id,name,email,gender);
			addAuthorToLibrary(oldAuthor);
		}
	}
	private void ReadDataBookINFileTxt(String filePathBook) throws IOException {
		List<String> mang = readFileMaster(filePathBook);
		for (int i = 0; i < mang.size(); i++) {
			String[] tempItem = mang.get(i).split("[,]");
			String listAuthorID = tempItem[3].substring(1, tempItem[3].length()-1);
			String[] tempListAuthorID = listAuthorID.split("[.]");
			int[]listID = new int[tempListAuthorID.length];
			for (int j = 0; j < tempListAuthorID.length; j++) {
				listID[j] = Integer.parseInt(tempListAuthorID[j]);
			}
			int anotherID = Integer.valueOf(tempItem[0]);
			BookType typeB = Enum.valueOf(BookType.class, tempItem[2]);
			int year = Integer.valueOf(tempItem[4]);
			String title = tempItem[1];
			int nbcopies = Integer.parseInt(tempItem[5]);
			Book dataBook = new Book(anotherID, title, typeB, nbcopies, listID, year);
			addBookItemToLibrary(dataBook);
		}
	}
	// DEFINE DATA
	public void defineData() throws IOException {
		int[] id1 = { 0, 1, 3, 4, 5 };
		int[] id2 = { 3, 4 };
		int[] id3 = { 1, 4 };
		Book hvcx = new Book(0, "Toi da thay hoa vang tren co xanh", BookType.VANHOC, 5, id1, 2015);
		Book thtt = new Book(1, "Tieu thuyet trinh tham", BookType.TIEUTHUYET, 3, id3, 2012);
		Book txd = new Book(2, "Tho Xuan Dieu", BookType.THOCA, 4, id2, 2013);
		Book hh = new Book(3, "Ky thuat ve tranh son dau", BookType.HOIHOA, 1, id2, 2010);
		Book khvt = new Book(4, "Bi an Mat Trang", BookType.KHOAHOC, 3, id3, 2011);

		Author nxa = new Author(0, "Nguyen Nhat Anh", "anhng@tuoitre.com", 'm');
		Author nhn = new Author(1, "Nguyen Hoai Nam", "namnh@tuoitre.com", 'm');
		Author tnq = new Author(5, "Trinh Nhat Quyen", "quyen@nxba.com", 'f');
		Author nhx = new Author(2, "Nguyen Huu Duong", "duongnh@nxba.com", 'm');
		Author lvl = new Author(3, "Le Van Linh", "linhlv@chimchi.com", 'm');
		Author phtn = new Author(4, "Phan Thi Thanh Nga", "ngaptt@book.com", 'f');

		Reader dmk = new Reader("Doan Minh Khue", "DMK001", "khuedm@string.com", 'm');
		Reader ntb = new Reader("Nguyen Thanh Binh", "NTB001", "binhnt@float.com", 'm');
		Reader ttpl = new Reader("Tran Thi Phuong Linh", "TTPL0364", "linhttp@int.com", 'f');
		Reader tt = new Reader("Tran Thong", "TT0456", "thongt@dr.com", 'm');
		Reader ttn = new Reader("Tran Thi Nhung", "TTN0345", "nhungtt@ping.com", 'f');

		Date dateBorrow = new Date(2015, 3, 10);
		Date dateReturn = setExpireDay(dateBorrow);

		Loan lo01 = new Loan(dmk.getCode(), hvcx.getAnotherID(), dateBorrow, dateReturn, true, 2);
		System.out.println("XUAT LOAN : " + lo01);
		// Loan lo02 = new Loan(dmk.getCode(), thtt.getAnotherID(), dateBorrow,
		// dateReturn, dmk.isStatusBorrow(),2);

		addBookItemToLibrary(hvcx);
		addBookItemToLibrary(thtt);
		addBookItemToLibrary(txd);
		addBookItemToLibrary(hh);
		addBookItemToLibrary(khvt);

		addAuthorToLibrary(nxa);
		addAuthorToLibrary(nhn);
		addAuthorToLibrary(tnq);
		addAuthorToLibrary(nhx);
		addAuthorToLibrary(lvl);
		addAuthorToLibrary(phtn);

		addReaderToLibrary(ntb);
		addReaderToLibrary(ttpl);
		addReaderToLibrary(tt);
		addReaderToLibrary(ttn);

		Date dateBorrow1 = new Date(2015,10,25);
		loanAnewBook("NTB001",2,dateBorrow1,2,listBook,listLoan);
		loanAnewBook("NTB001",3,dateBorrow1,1,listBook,listLoan);
		
		writeFileMaster(getListBook(listBook), "E:\\Desktop\\DataFileJava\\book.txt");
		writeFileMaster(getListAuthor(listAuthor), "E:\\Desktop\\DataFileJava\\author.txt");
		writeFileMaster(getListReader(listReader), "E:\\Desktop\\DataFileJava\\reader.txt");
		writeFileMaster(getListLoan(listLoan), "E:\\Desktop\\DataFileJava\\loan.txt");
	}

	// CODE PROCESS BOOK
	public void addBookItemToLibrary(Book book) {
		listBook.add(book);
	}

	public List<String> getListBook(ArrayList<Book> listBook) {
		List<String> ls = new ArrayList<>();
		for (int i = 0; i < listBook.size(); i++) {
			ls.add(listBook.get(i).toString());
		}
		return ls;
	}

	public Book searchBookByTilte(String title) {
		// BookType relustBookType = Enum.valueOf(BookType.class, title);
		for (Book book : this.listBook) {
			if (book.getTitle().equals(title))// ||book.getType().equals(relustBookType))
				return book;
		}
		return null;
	}

	public BookType searchBookByBookType(BookType bookType) {
		// BookType relustBookType = Enum.valueOf(BookType.class, bookType);
		for (Book book : listBook) {
			if (book.getType().equals(bookType))
				return book.getType();
		}
		return null;
	}
	public Book searchBookByAnotherID(int anotherID)
	{
		for (Book book : listBook) {
			if(book.getAnotherID()==anotherID)
				return book;
		}
		return null;
	}

	public ArrayList<Book> getListBookWritenByAuthor(ArrayList<Book> lsBook, Author author) {
		ArrayList<Book> tempList = new ArrayList<>();
		for (Book book : lsBook) {
			for (int id : book.lsID) {
				if (author.getId() == id)
					tempList.add(book);
			}
		}
		return tempList;
	}

	public boolean checkBookInList(Book book) {
		if (listBook.contains(book))
			return true;
		return false;
	}
	// check nbcopies , check availble book in listbook to loan for Reader

	public Book getBookByAnotheID(int anotherID, ArrayList<Book> lcBook) {
		for (Book book : lcBook) {
			if (book.getAnotherID() == anotherID)
				return book;
		}
		return null;
	}

	public void updateNbcopiesBookByAnotherID(int anotherID, int newNBcopyes, ArrayList<Book> lcBook)
			throws IOException {
		for (int i = 0; i < lcBook.size(); i++) {
			if (lcBook.get(i).getAnotherID() == anotherID) {
				Book nb = lcBook.get(i);
				nb.setNbcopies(newNBcopyes);
				// nb.setBorrow(status);
				lcBook.set(i, nb);
			}
		}
		writeFileMaster(getListBook(lcBook), "E:\\Desktop\\DataFileJava\\book.txt");
	}

	public void displayListBookInLibrary() {
		System.out.println("---------- LIST BOOK IN THE LIBRARY---------");
		for (Book book : listBook)
			System.out.println(book);
	}
	public void displayAuthorInLibrary() {
		System.out.println("---------- LIST AUTHOR IN THE LIBRARY---------");
		for (Author author : listAuthor)
			System.out.println(author);
	}

	public void displayDetailOfABook(String title, BookType type, ArrayList<Author> listAuthor) {
		System.out.println("--------------------------------------------------");
		Book detailBook = new Book();
		detailBook.setTitle(searchBookByTilte(title).getTitle());
		detailBook.setType(searchBookByBookType(type));

		System.out.println("INFORMATION DETAIL OF A BOOK : ");
		System.out.println("BOOK NAME: " + detailBook.getTitle());
		System.out.println("BOOK TYPE: " + detailBook.getType().toString());
		System.out.println("AUTHOR NAME'S: ");
		int[] x = searchBookByTilte(title).lsID;
		for (int i : x) {
			System.out.println(getAuthorsByID(i, listAuthor));
		}
		System.out.println("--------------------------------------------------");

	}

	// Code process Author
	public void addAuthorToLibrary(Author author) throws IOException {
		listAuthor.add(author);
		
		writeFileMaster(getListAuthor(listAuthor), "E:\\Desktop\\DataFileJava\\author.txt");
	}
	public Author searchAuthorByID(int id)
	{
		for (Author author : listAuthor) {
			if(author.getId()==id)
				return author;
		}
		return null;
	}
	public Author findAuthorinListAuthor(String authorName) {
		for (Author author : listAuthor) {
			if (author.getName().equals(authorName))
				return author;
		}
		return null;
	}

	public List<String> getListAuthor(ArrayList<Author> listAuthor) {
		List<String> ls = new ArrayList<>();
		for (int i = 0; i < listAuthor.size(); i++) {
			ls.add(listAuthor.get(i).toString());
		}
		return ls;
	}

	public Reader getReaderByCodeName(String codeReader, ArrayList<Reader> lsReader) {
		for (Reader reader : lsReader) {
			if (reader.getCode().equals(codeReader))
				return reader;
		}
		return null;
	}

	public Author searchAuthorByName(String name) {
		for (Author author : listAuthor) {
			if (author.getName().equals(name))
				return author;
		}
		return null;
	}

	public ArrayList<Author> getAuthorsByID(int id, ArrayList<Author> listAuthor) {
		ArrayList<Author> listA = new ArrayList<>();
		for (Author author : listAuthor) {
			if (author.getId() == id)
				listA.add(author);
		}
		return listA;

	}

	public void displayDetailanAuthor(String name, String email, char gender, ArrayList<Book> lsBook) {
		System.out.println("--------------------------------------------------");
		Author detailAuthor = new Author();
		detailAuthor.setName(searchAuthorByName(name).getName());
		detailAuthor.setEmail(searchAuthorByName(name).getEmail());
		detailAuthor.setGender(searchAuthorByName(name).getGender());
		System.out.println("INFORMATION DETAIL AN AUTHOR : ");
		System.out.println("AUTHOR NAME: " + detailAuthor.getName());
		System.out.println("Email: " + detailAuthor.getEmail());
		System.out.println("Gender: " + detailAuthor.getGender());
		System.out.println("WRITTEN BOOK's: ");
		for (Book book : getListBookWritenByAuthor(lsBook, detailAuthor)) {
			System.out.println(book);
		}
		// int[]x = searchBookByTilte(title).lsID;
		// for (int i : x) {
		// System.out.println(getAuthorsByID(i, listAuthor));
		// }
		System.out.println("--------------------------------------------------");
	}

	// Code process Reader
	public void addReaderToLibrary(Reader reader) {
		listReader.add(reader);
	}

	public ArrayList<String> getListReader(ArrayList<Reader> listReader) {
		ArrayList<String> ls = new ArrayList<>();
		for (int i = 0; i < listReader.size(); i++) {
			ls.add(listReader.get(i).toString());
		}
		return ls;
	}

	public void dislayListReaderInLibrary() {
		System.out.println("---------- LIST READER IN THE LIBRARY---------");
		for (Reader reader : listReader) {
			System.out.println(reader);
		}
	}

	public Reader searchReaderByNameorCode(String objectSearch, ArrayList<Reader> lsReader) {
		for (Reader reader : lsReader) {
			if (reader.getName().equals(objectSearch) || reader.getCode().equals(objectSearch))
				return reader;
		}

		return null;
	}

	// PROCESS CODE FOR LOAN CLASS
	public void addBookandReaderToLoanList(Loan lo) {
		this.listLoan.add(lo);
	}

	public void displayListLoan() {
		System.out.println("---------- LIST READER and BOOK IN THE LOAN LIST---------");
		for (Loan lo : this.listLoan) {
			System.out.println(searchReaderByNameorCode(lo.getCodeReader(), listReader));
			System.out.println("Ma sach muon: " + lo.getAnotherID());
			//System.out.println(lo);
		}
	}

	public boolean checkLoanExpire(Date returnDay, Loan lo) {
		if (returnDay.before(lo.getDayBorrow()) || returnDay.equals(lo.getDayBorrow()))
			return true;
		return false;

	}

	// nhap ten nguoi muon, ten sach, ngay muon , so luong muon
	// Check Book nbcopies in Library
	public boolean checkNbCopies(int nbcopyofBook, int nbcopyBorrow) {
		if (nbcopyBorrow > nbcopyofBook)
			return false;
		return true;
	}

	public int countBookLoanByReader(String codeReader) {
		int count = 0;
		for (Loan loan : this.listLoan) {
			if (loan.getCodeReader().equals(codeReader))
				count++;
		}
		return count;
	}

	public boolean checkBookIsLoan(String codeReader, int anotherID, ArrayList<Loan> lsLoan) {
		for (Loan loan : lsLoan) {
			if (loan.getCodeReader().equals(codeReader) && loan.getAnotherID() == anotherID)
				return true; // this book is loaned
		}
		return false; // this book isn't loan
	}
	public void displayDetailOfReader(String codeReader)
	{
		Reader reader = getReaderByCodeName(codeReader, this.listReader);
		ArrayList<Book> tempList = new ArrayList<>();
		for (Loan loan : listLoan) {
			Book book = new Book();
			book = getBookByAnotheID(loan.getAnotherID(), this.listBook);
			tempList.add(book);
		}
		System.out.println("Print list book loan of reader: ");
		for (Book book : tempList) {
			System.out.println(book);
		}
	}
public ArrayList<String> getListLoan(ArrayList<Loan> listLoan) {
		ArrayList<String> ls = new ArrayList<>();
		for (int i = 0; i < listLoan.size(); i++) {
			ls.add(listLoan.get(i).toString());
		}
		return ls;
	}
	
	public void loanAnewBook(String codeReader, int anotherIDBook, Date dateBorrow, int numberBorrow,
			ArrayList<Book> lsBook, ArrayList<Loan> lsLoan) throws IOException {
		Book oldBook = getBookByAnotheID(anotherIDBook, lsBook);
		int oldNumberCopies = oldBook.getNbcopies();
//		System.out.println("ten sach muon: " + oldBook.getTitle());
//		System.out.println("So luong sach trong thu vien: " + oldBook.getNbcopies());

		if (checkNbCopies(oldBook.getNbcopies(), numberBorrow) && !checkBookIsLoan(codeReader, anotherIDBook, lsLoan)&& numberBorrow<=3) 
			{
				//System.out.println("TEST");
				Loan newL = new Loan(codeReader, anotherIDBook, dateBorrow, setExpireDay(dateBorrow), true, numberBorrow);
				addLoanToListLoan(newL);
				updateNbcopiesBookByAnotherID(anotherIDBook, oldNumberCopies - numberBorrow, lsBook);
				writeFileMaster(getListLoan(this.listLoan), "E:\\Desktop\\DataFileJava\\loan.txt");
			
		} else
			System.out.println("Sorry, You can't borrow new Book");
	}

	public void ReturnABook(String codeReader, int anotherIDBook, Date dateReturn, int numberBorrow,
			ArrayList<Book> lsBook, ArrayList<Loan> lsLoan) throws IOException {
		Book newBook = getBookByAnotheID(anotherIDBook, lsBook);
		int numberCopies = newBook.getNbcopies();// get number copy of Book in
													// data
		// Reader newReader = getReaderByCodeName(codeReader, this.listReader);
		for (int i = 0; i < lsLoan.size(); i++) {
			if (lsLoan.get(i).getCodeReader().equals(codeReader)) {
				Loan newLoan = new Loan(codeReader, anotherIDBook, lsLoan.get(i).getDayBorrow(), dateReturn, false,
						numberBorrow);
				lsLoan.set(i, newLoan);
			}
		}
		updateNbcopiesBookByAnotherID(anotherIDBook, numberCopies + numberBorrow, lsBook);// update
																							// number
																							// copy
																							// int
																							// txt
																							// Book
																							// file
		// newBook.
	}

	public void removeLoan(Loan lo, ArrayList<Loan> lsLoan) {
		lsLoan.remove(lo);
	}

	public void addLoanToListLoan(Loan lo) {
		this.listLoan.add(lo);

	}

	public void ShowAllListLoan() {
		for (Loan loan : listLoan) {
			System.out.println(loan);
		}
	}

	@SuppressWarnings("deprecation")
	public Date setExpireDay(Date borrowDay) {
		int day;
		int year;
		int month;
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(borrowDay);
		day = borrowDay.getDay();
		month = borrowDay.getMonth();
		year = borrowDay.getYear();
		if (month == 12) {
			month = 1;
			year++;
		} else
			month++;

		return new Date(year - 1900, month, day);

	}

	// Code Write file and read file
	public static void writeFileMaster(List<String> contenList, String filePath) throws IOException {
		File file = new File(filePath);

		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < contenList.size(); i++) {
			bw.write(contenList.get(i));
			bw.newLine();
		}
		bw.close();

		System.out.println("Writen to file " + filePath);
	}

	public List<String> readFileMaster(String filePath) throws IOException {
		List<String> res = new ArrayList<>();
		File file = new File(filePath);

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;

		while ((line = reader.readLine()) != null) {
			if (line != null && !line.isEmpty())
				res.add(line);
		}
		reader.close();
		return res;
	}

	// frome str [1,2] to list<Author> cat mang dung substring
	// String str = temp[4].substring(1,tem[4].length(0-1);)

}