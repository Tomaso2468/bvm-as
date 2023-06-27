package as.tokens.pre;

import as.Labels;
import as.tokens.Token;

public class ZeroFilled extends Token {
	private static final long serialVersionUID = 1028705729443869037L;
	private final long length;
	public ZeroFilled(String label, long length) {
		super(label);
		this.length = length;
	}

	@Override
	public int getLength() {
		return (int) length;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		return new byte[getLength()];
	}

}
