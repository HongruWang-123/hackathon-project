package lassonde_hackaton;


public class InvalidLoginException extends RuntimeException {
	public InvalidLoginException(String message) {
	        super(message);
	    }
}