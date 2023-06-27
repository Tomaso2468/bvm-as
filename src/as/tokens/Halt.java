package as.tokens;

import as.Labels;

public class Halt extends Token {
	private static final long serialVersionUID = 2086259316668005510L;

	public Halt(String label) {
		super(label);
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		data[0] = 0x00;
		return data;
	}

}
