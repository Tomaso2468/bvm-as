package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class Skip extends Token {
	private static final long serialVersionUID = 1447070417629390260L;
	private final long length;
	private final byte value;
	public Skip(String label, String length, String value) {
		super(label);
		this.length = Conversion.parseLong(length);
		this.value = Conversion.parseByte(value);
	}
	public Skip(String label, String length) {
		this(label, length, "0");
	}

	@Override
	public int getLength() {
		return (int) length;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[getLength(cl)];
		for(int i = 0; i < data.length; i++) {
			data[i] = value;
		}
		return data;
	}

}
