package congestion.calculator.custom.exception;

import org.springframework.stereotype.Component;

@Component
public class ValidationException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	

	public ValidationException() {
		super();
	}
	
	public ValidationException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
