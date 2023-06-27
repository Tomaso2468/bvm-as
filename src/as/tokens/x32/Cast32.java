package as.tokens.x32;

import as.Labels;
import as.tokens.Token;

public class Cast32 extends Token {
	private static final long serialVersionUID = -2487920092083038652L;
	private final byte r;
	private final byte r2;
	public Cast32(String label, byte r, byte r2) {
		super(label);
		this.r = r;
		this.r2 = r2;
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = 0x6F;
		data[1] = r;
		data[2] = r2;
		
		return data;
	}
}
