package as.tokens.x16.mem;

import as.Labels;
import as.tokens.Token;

public class StorePtr16 extends Token {
	private static final long serialVersionUID = 2562533634618582273L;
	private final byte r;
	private final byte r2;
	public StorePtr16(String label, byte r, byte r2) {
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
		
		data[0] = 0x55;
		data[1] = r;
		data[2] = r2;
		
		return data;
	}

}