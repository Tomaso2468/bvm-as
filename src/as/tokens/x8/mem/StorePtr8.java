package as.tokens.x8.mem;

import as.Labels;
import as.tokens.Token;

public class StorePtr8 extends Token {
	private static final long serialVersionUID = 6640182690101138029L;
	private final byte r;
	private final byte r2;
	public StorePtr8(String label, byte r, byte r2) {
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
		
		data[0] = 0x45;
		data[1] = r;
		data[2] = r2;
		
		return data;
	}

}