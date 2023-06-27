package as.tokens.x64;

import as.Labels;
import as.tokens.Token;

public class Unix64 extends Token {
	private static final long serialVersionUID = 4393358167128944537L;
	private final byte r;
	public Unix64(String label, byte r) {
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
		
		data[0] = 0x3C;
		data[1] = r;
		
		return data;
	}
}
