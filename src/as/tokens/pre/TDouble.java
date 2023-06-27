package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class TDouble extends Token {
	private static final long serialVersionUID = -4080393292788827243L;
	private final String data;
	public TDouble(String label, String data) {
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
		return Conversion.doubleToBytes(Double.parseDouble(data));
	}
}
