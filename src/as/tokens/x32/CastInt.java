package as.tokens.x32;

import as.Labels;
import as.tokens.Token;

public class CastInt extends Token {
	private static final long serialVersionUID = 3270856423941349642L;
	private final byte r;
	private final byte r2;
	public CastInt(String label, byte r, byte r2) {
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
		
		data[0] = (byte) 0xB2;
		data[1] = r;
		data[2] = r2;
		
		return data;
	}
}
