package as.tokens;

import as.Labels;

public class CallPtr extends Token {
	private static final long serialVersionUID = 7857099432377863089L;
	private final byte r;
	public CallPtr(String label, byte r) {
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
		
		data[0] = (byte) 0x90;
		data[1] = r;
		
		return data;
	}

}
