package ProjectJavaExam;

import java.awt.im.spi.InputMethod;
import java.io.IOException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class LibraryMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		Menu();

	}

	

	public static void Menu() throws IOException {
		Library lib = new Library();
		lib.ReadData();
		
		//lib.defineData();

		System.out.println("1: Add an author into the Library");
		System.out.println("2: Add a reader into the Library");
		System.out.println("3: List authors in the library");
		System.out.println("4: List book in the library");
		System.out.println("5: List reader int the library");
		System.out.println("6: Search book by title or booktype");
		System.out.println("7: Search a reader by name or reader code");
		System.out.println("8: Search an author by name");
		System.out.println("9: Display detail of book");
		System.out.println("10: Display detail of author");
		System.out.println("11: Loan a new Book");
		System.out.println("12: Return a book ");
		System.out.println("13: Display detail Reader ");
		System.out.print("Please choose feature for each question in Assessment: ");
		Scanner input = new Scanner(System.in);
		Scanner data = new Scanner(System.in);
		int feature = input.nextInt();
		switch (feature) {
		case 1:
			System.out.println("Add author to the library");
			System.out.println("Please input Author name : ");
			data = new Scanner(System.in);
			String name = data.next();
			System.out.println("Please input Author email : ");
			data = new Scanner(System.in);
			String email = data.next();
			System.out.println("Please input Author gender : ");
			data = new Scanner(System.in);
			char gender = data.next().charAt(0);
			Author author = new Author(name, email, gender);
			lib.addAuthorToLibrary(author);
			break;
		case 2:
			System.out.println("Add reader to the library");
			System.out.println("Please input Reader name : ");
			data = new Scanner(System.in);
			name = data.next();
			System.out.println("Please input Reader code : ");
			data = new Scanner(System.in);
			String code = data.next();
			System.out.println("Please input Reader email : ");
			data = new Scanner(System.in);
			email = data.next();
			System.out.println("Please input Reader gender : ");
			data = new Scanner(System.in);
			gender = data.next().charAt(0);
			Reader reader = new Reader(name, code, email, gender);
			lib.addReaderToLibrary(reader);
			break;
		case 3:
			System.out.println("Show list author in the library");
			lib.displayAuthorInLibrary();
			break;
		case 4:
			System.out.println("Show list book in the library");
			lib.displayListBookInLibrary();
			break;
		case 5:
			System.out.println("Show list reader in the library");
			lib.dislayListReaderInLibrary();
			break;
		case 6:
			System.out.println("Seach book by titel or book type");
			System.out.println("press 1 to search book by title");
			System.out.println("press 2 to search book by booktype");
			int choose = new Scanner(System.in).nextInt();
			switch (choose) {
			case 1:
				System.out.print("please enter name to search: ");
				String title = new Scanner(System.in).next();
				if (lib.searchBookByTilte(title) != null)
					System.out.println(lib.searchBookByTilte(title));
				else
					System.out.println("Sorry, progame can't find your book name");
				break;

			case 2:
				System.out.print("please enter bootype to search: ");
				Scanner temp = new Scanner(System.in);
				BookType type = Enum.valueOf(BookType.class, temp.nextLine());
				if (lib.searchBookByBookType(type) != null)
					System.out.println("Data have " + lib.searchBookByBookType(type) + " in the library");
				else
					System.out.println("Sorry, progame can't find your book type");
				break;
			}
		case 7:
			System.out.print("please enter reader name or reader code to search: ");

			String inputOption = new Scanner(System.in).next();
			if (lib.searchReaderByNameorCode(inputOption, lib.listReader) != null)
				System.out.println(lib.searchReaderByNameorCode(inputOption, lib.listReader));
			else
				System.out.println("Sorry, progame can't find your book name");
		case 8:
			System.out.print("please enter author name to search: ");

			inputOption = new Scanner(System.in).next();
			if (lib.searchAuthorByName(inputOption) != null)
				System.out.println(lib.searchAuthorByName(inputOption));
			else
				System.out.println("Sorry, progame can't find your author name");
		case 9:
			System.out.println("Display detail of book");
			lib.displayListBookInLibrary();
			System.out.print("Press ID of Book to see detail: ");
			int anotherID = new Scanner(System.in).nextInt();
			Book detailBook = lib.searchBookByAnotherID(anotherID);
			lib.displayDetailOfABook(detailBook.getTitle(), detailBook.getType(), lib.listAuthor);
			break;
		case 10:
			System.out.println("Display detail of an author");
			lib.displayAuthorInLibrary();
			System.out.print("Press ID of an Author to see detail: ");
			int id = new Scanner(System.in).nextInt();
			Author detailAu = lib.searchAuthorByID(id);
			lib.displayDetailanAuthor(detailAu.getName(), detailAu.getEmail(), detailAu.getGender(), lib.listBook);
			break;
		case 11:
			System.out.println("Borrow 3 book in the library");
			System.out.print("Please input your code to loan a book: ");
			String ncode = new Scanner(System.in).next();
			if (lib.searchReaderByNameorCode(ncode, lib.listReader) == null) {
				System.out.print("Please input your name: ");
				String nname = new Scanner(System.in).nextLine();
				System.out.print("Please input your email: ");
				String nemail = new Scanner(System.in).nextLine();
				System.out.print("Please input your gender: ");
				char ngender = new Scanner(System.in).nextLine().charAt(0);
				Reader nreader = new Reader(nname, ncode, nemail, ngender);
				lib.addReaderToLibrary(nreader);
				System.out.println("List BOOK int the library");
				lib.displayListBookInLibrary();
				Date dayBorrow = new Date(2015, 11, 2);
				int count = 0;
				while (count < 3) {

					System.out.print("Choose ID book so you want to borrow: ");
					int idBook = new Scanner(System.in).nextInt();
					System.out.print("What do you want to number : ");
					int ncopies = new Scanner(System.in).nextInt();
					if (ncopies > 3) {
						System.out.println("Sorry you can't not borrow larger than 3 book");
						break;
					}
					lib.loanAnewBook(nreader.getCode(), idBook, dayBorrow, ncopies, lib.listBook, lib.listLoan);

					System.out.println("ncopy : " + count);
					count++;
				}

			}
			break;
		case 12:
			System.out.println("Return book in the library");
			System.out.print("Please input code reader: ");
			String codeReader = new Scanner(System.in).nextLine();
			System.out.print("Please input ID of book so you want to borrow: ");
			int anotherIDBook =new Scanner(System.in).nextInt();
			System.out.print("Please input you number book so you want to borrow: ");
			int numberBorrow =new Scanner(System.in).nextInt();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			//System.out.println(cal.getTime());
			Date nowDate = (Date) cal.getTime();
			lib.ReturnABook(codeReader, anotherIDBook, nowDate, numberBorrow, lib.listBook, lib.listLoan);
			break;
		case 13:
			System.out.print("Please input code to see detail information of Reader");
			String oldCodeReader = new Scanner(System.in).nextLine();
			lib.displayDetailOfReader(oldCodeReader);
			break;
		default:
			break;
		}
	}
}
