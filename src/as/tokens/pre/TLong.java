package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class TLong extends Token {
	private static final long serialVersionUID = 7617739511432351148L;
	private final String data;
	public TLong(String label, String data) {
		super(label);
		this.data = data;
	}
	public String getData() {
		return data;
	}
	@Override
	public int getLength() {
		return 8;
	}
	@Override
	public byte[] getData(Labels labels, long cl) {
		return Conversion.longToBytes(Conversion.parseLong(data));
	}
}
