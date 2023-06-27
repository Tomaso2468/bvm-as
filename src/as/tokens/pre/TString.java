package as.tokens.pre;

import as.Labels;
import as.tokens.Token;

public class TString extends Token {
	private static final long serialVersionUID = 6908153879682641240L;
	private final String data;
	public TString(String label, String data) {
		super(label);
		this.data = data;
	}

	@Override
	public int getLength() {
		return data.getBytes().length;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		return data.getBytes();
	}

	public String getData() {
		return data;
	}

}
