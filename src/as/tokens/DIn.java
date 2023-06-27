package as.tokens;

import as.Labels;

public class DIn extends Token {
	private static final long serialVersionUID = -6774508432431590384L;
	private final byte r;
	public DIn(String label, byte r) {
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
		
		data[0] = 0x21;
		data[1] = r;
		
		return data;
	}

}
