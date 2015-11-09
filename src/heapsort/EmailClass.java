package heapsort;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class EmailClass {	
	private String title;
	private Date timeSpan;
	private int color;

	public EmailClass(String title, int color) {
		this.title = title;
		this.color = color;
		timeSpan = new Date();
	}
	
	public EmailClass(String title, Date timespan, int color) {		
		this.title = title;
		this.color = color;
		this.timeSpan = timespan;
	}
	
	public EmailClass(String title, String colorText) {		
		this.title = title;
		timeSpan = new Date();
		this.color = getColorByText(colorText);		
	}
	
	public EmailClass(String title, Date timespan, String colorText) {		
		this.title = title;
		this.color = getColorByText(colorText);
		this.timeSpan = timespan;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getTimeSpan() {
		return timeSpan;
	}
	
	public String getTimeSpanWithFormat(){
		SimpleDateFormat dt = new SimpleDateFormat("M/d/yyyy hh:mm:ss");
		return dt.format(this.timeSpan);
	}

	public void setTimeSpan(Date timeSpan) {
		this.timeSpan = timeSpan;
	}
	
	public int getColor() {
		return color;
	}
	
	public String getColorText(){
		String result = "";
		
		switch (color) {
		case 0:
			result = "RED";
			break;
		case 1:
			result = "GREEN";
			break;
		case 2:
			result = "BLUE";
			break;
		case 3:
			result = "YELLOW";
			break;
		case 4:
			result = "WHITE";
			break;
		case 5:
			result = "BLACK";
			break;
		case 6:
			result = "UNCATEGORY";
			break;			

		default:
			result = "UNCATEGORY";
			break;
		}
		
		return result;
	}
	
	public int getColorByText(String text){
		int result = 6;
		
		switch (text) {
		case "RED":
			result = 0;
			break;
		case "GREEN":
			result = 1;
			break;
		case "BLUE":
			result = 2;
			break;
		case "YELLOW":
			result = 3;
			break;
		case "WHITE":
			result = 4;
			break;
		case "BLACK":
			result = 5;
			break;
		case "UNCATEGORY":
			result = 6;
			break;			

		default:
			result = 6;
			break;
		}
		
		return result;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		SimpleDateFormat dt = new SimpleDateFormat("M/d/yyyy hh:mm:ss");
		return "Email: " + this.title + " create at "
				+ dt.format(this.timeSpan);
	}
}
