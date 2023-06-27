package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class TFloat extends Token {
	private static final long serialVersionUID = -2908759596458502110L;
	private final String data;
	public TFloat(String label, String data) {
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
		return Conversion.floatToBytes(Float.parseFloat(data));
	}
}
