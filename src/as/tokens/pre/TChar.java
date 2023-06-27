package as.tokens.pre;

public class TChar extends TByte {
	private static final long serialVersionUID = -8070199394141624424L;
	public TChar(String label, String data) {
		super(label, ((byte) data.charAt(0)) + "");
	}
}
