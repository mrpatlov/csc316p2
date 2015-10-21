
public class Warnings extends Exception {

	private String message;
	
	public Warnings(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}

}
