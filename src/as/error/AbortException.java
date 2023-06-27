package as.error;

public class AbortException extends CompileException {
	private static final long serialVersionUID = -4858698917969157607L;

	public AbortException() {
	}

	public AbortException(String message) {
		super(message);
	}

	public AbortException(Throwable cause) {
		super(cause);
	}

	public AbortException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
