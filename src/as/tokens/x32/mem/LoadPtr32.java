package as.tokens.x32.mem;

import as.Labels;
import as.tokens.Token;

public class LoadPtr32 extends Token {
	private static final long serialVersionUID = -4076021374694500913L;
	private final byte r;
	private final byte r2;
	public LoadPtr32(String label, byte r2, byte r) {
		super(label);
		this.r2 = r2;
		this.r = r;
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = 0x64;
		data[1] = r;
		data[2] = r2;
		
		return data;
	}

}