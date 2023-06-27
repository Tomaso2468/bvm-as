package as.tokens.float64;

import as.Labels;
import as.tokens.Token;

public class SinF64 extends Token {
	private static final long serialVersionUID = 8575835367465075772L;
	private final byte r;
	private final byte r2;
	public SinF64(String label, byte r, byte r2) {
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
		
		data[0] = (byte) 0x86;
		data[1] = r;
		data[2] = r2;
		
		return data;
	}
}
