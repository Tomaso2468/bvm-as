package as.tokens.pre;

public class TBoolean extends TLong {
	private static final long serialVersionUID = -1481259790058110550L;
	public TBoolean(String label, String data) {
		super(label, data.toLowerCase().equals("true") ? ((~0L) + "") : ((0L) + ""));
	}
}
