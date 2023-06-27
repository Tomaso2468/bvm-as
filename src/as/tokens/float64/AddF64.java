package as.tokens.float64;

import as.Labels;
import as.tokens.Token;

public class AddF64 extends Token {
	private static final long serialVersionUID = 521835413990328691L;
	private final byte r;
	private final byte r2;
	private final byte r3;
	public AddF64(String label, byte r, byte r2, byte r3) {
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
		
		data[0] = (byte) 0x80;
		data[1] = r;
		data[2] = r2;
		data[3] = r3;
		
		return data;
	}

}
