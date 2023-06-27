package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class TInteger extends Token {
	private static final long serialVersionUID = -2749321906903186470L;
	private final String data;
	public TInteger(String label, String data) {
		super(label);
		this.data = data;
	}
	public String getData() {
		return data;
	}
	@Override
	public int getLength() {
		return 4;
	}
	@Override
	public byte[] getData(Labels labels, long cl) {
		return Conversion.intToBytes(Conversion.parseInteger(data));
	}
}
