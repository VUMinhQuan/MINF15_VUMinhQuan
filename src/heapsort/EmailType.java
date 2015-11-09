package heapsort;

public enum EmailType {
	RED(0), 
	GREEN(1), 
	BLUE(2), 
	YELLOW(3),
	WHITE(4),
	BLACK(5),
	UNCATEGORY(6);
	
	private int value;
	
	EmailType(int value){
		this.value = value;
	}
	
	public int getEmailTypeValue(){
		return this.value;
	}
	
}
