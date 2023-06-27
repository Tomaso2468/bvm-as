package as.tokens;

import as.Labels;

public class InterruptReturn extends Token {
	private static final long serialVersionUID = 6640138882263851581L;
	public InterruptReturn(String label) {
		super(label);
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = (byte) 0x93;
		
		return data;
	}

}
