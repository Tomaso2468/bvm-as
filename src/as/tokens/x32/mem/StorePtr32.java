package as.tokens.x32.mem;

import as.Labels;
import as.tokens.Token;

public class StorePtr32 extends Token {
	private static final long serialVersionUID = 7903412516066960503L;
	private final byte r;
	private final byte r2;
	public StorePtr32(String label, byte r, byte r2) {
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
		
		data[0] = 0x65;
		data[1] = r;
		data[2] = r2;
		
		return data;
	}

}