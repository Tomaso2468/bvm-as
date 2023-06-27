package as.tokens;

import as.Labels;

public class DOut extends Token {
	private static final long serialVersionUID = -6833257199362703319L;
	private final byte r;
	public DOut(String label, byte r) {
		super(label);
		this.r = r;
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = 0x20;
		data[1] = r;
		
		return data;
	}

}
