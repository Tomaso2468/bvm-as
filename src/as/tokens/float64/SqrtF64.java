package as.tokens.float64;

import as.Labels;
import as.tokens.Token;

public class SqrtF64 extends Token {
	private static final long serialVersionUID = -2248668001917209882L;
	private final byte r;
	private final byte r2;
	public SqrtF64(String label, byte r, byte r2) {
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
		
		data[0] = (byte) 0x89;
		data[1] = r;
		data[2] = r2;
		
		return data;
	}
}
