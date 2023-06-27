package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class TByte extends Token {
	private static final long serialVersionUID = -530949365611367298L;
	private final String data;
	public TByte(String label, String data) {
		super(label);
		this.data = data;
	}
	public String getData() {
		return data;
	}
	@Override
	public int getLength() {
		return 1;
	}
	@Override
	public byte[] getData(Labels labels, long cl) {
		return new byte[] {Conversion.parseByte(data)};
	}
}