package as.tokens.pre;

import as.Labels;
import as.tokens.Token;

public class DataToken extends Token {
	private static final long serialVersionUID = -747516820688422932L;
	private final byte[] data;
	public DataToken(String label, byte[] data) {
		super(label);
		this.data = data;
	}

	@Override
	public int getLength() {
		return data.length;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		return data;
	}

}
