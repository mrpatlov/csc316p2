/**
 * Exception class for project
 * @author Jeremy
 *
 */
@SuppressWarnings("serial")
public class Warnings extends Exception {

	private String message;
	/**
	 * constructor
	 * @param message error message for user
	 */
	public Warnings(String message){
		this.message = message;
	}
	
	/**
	 * get method for message
	 * @return error message
	 */
	public String getMessage(){
		return message;
	}

}
