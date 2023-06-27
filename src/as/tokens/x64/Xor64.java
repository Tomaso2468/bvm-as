package as.tokens.x64;

import as.Labels;
import as.tokens.Token;

public class Xor64 extends Token {
	private static final long serialVersionUID = -1151227810641700994L;
	private final byte r;
	private final byte r2;
	private final byte r3;
	public Xor64(String label, byte r, byte r2, byte r3) {
		super(label);
		this.r = r;
		this.r2 = r2;
		this.r3 = r3;
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = 0x37;
		data[1] = r;
		data[2] = r2;
		data[3] = r3;
		
		return data;
	}

}
