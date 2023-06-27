package as.tokens.float32;

import as.Labels;
import as.tokens.Token;

public class SubF extends Token {
	private static final long serialVersionUID = -3970783327022597264L;
	private final byte r;
	private final byte r2;
	private final byte r3;
	public SubF(String label, byte r, byte r2, byte r3) {
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
		
		data[0] = 0x71;
		data[1] = r;
		data[2] = r2;
		data[3] = r3;
		
		return data;
	}

}
