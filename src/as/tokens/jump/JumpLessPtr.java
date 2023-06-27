package as.tokens.jump;

import as.Labels;
import as.tokens.Token;

public class JumpLessPtr extends Token {
	private static final long serialVersionUID = 1L;
	private final byte loc;
	private final byte r;
	private final byte r2;
	
	public JumpLessPtr(String label, byte r, byte r2, byte loc) {
		super(label);
		this.loc = loc;
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
		
		data[0] = (byte) 0xE2;
		data[1] = r;
		data[2] = r2;
		data[3] = loc;
		
		return data;
	}

}

