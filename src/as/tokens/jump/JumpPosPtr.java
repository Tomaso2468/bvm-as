package as.tokens.jump;

import as.Labels;
import as.tokens.Token;

public class JumpPosPtr extends Token {
	private static final long serialVersionUID = 3644180339767460075L;
	private final byte loc;
	private final byte r;
	
	public JumpPosPtr(String label, byte r, byte loc) {
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
		
		data[0] = (byte) 0xAD;
		data[1] = r;
		data[2] = loc;
		
		return data;
	}

}
