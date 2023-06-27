package as.tokens.x64;

import as.Labels;
import as.tokens.Token;

public class Rnd64 extends Token {
	private static final long serialVersionUID = -5283929028440092273L;
	private final byte r;
	public Rnd64(String label, byte r) {
		super(label);
		this.r = r;
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = 0x3B;
		data[1] = r;
		
		return data;
	}
}
