package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class TShort extends Token {
	private static final long serialVersionUID = -4800914644490108049L;
	private final String data;
	public TShort(String label, String data) {
		super(label);
		this.data = data;
	}
	public String getData() {
		return data;
	}
	@Override
	public int getLength() {
		return 2;
	}
	@Override
	public byte[] getData(Labels labels, long cl) {
		return Conversion.shortToBytes(Conversion.parseShort(data));
	}
}
