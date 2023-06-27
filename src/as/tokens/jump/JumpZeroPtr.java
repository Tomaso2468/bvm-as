package as.tokens.jump;

import as.Labels;
import as.tokens.Token;

public class JumpZeroPtr extends Token {
	private static final long serialVersionUID = 1882237545794144112L;
	private final byte loc;
	private final byte r;
	
	public JumpZeroPtr(String label, byte r, byte loc) {
		super(label);
		this.loc = loc;
		this.r = r;
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = (byte) 0xAB;
		data[1] = r;
		data[2] = loc;
		
		return data;
	}
}
