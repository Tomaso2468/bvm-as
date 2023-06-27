package as.error;

public class CompileException extends RuntimeException {
	private static final long serialVersionUID = -7108655597967590603L;
	
	public CompileException() {
	}

	public CompileException(String message) {
		super(message);
	}

	public CompileException(Throwable cause) {
		super(cause);
	}

	public CompileException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
