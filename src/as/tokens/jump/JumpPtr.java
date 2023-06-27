package as.tokens.jump;

import as.Labels;
import as.tokens.Token;

public class JumpPtr extends Token {
	private static final long serialVersionUID = 1L;
	private final byte loc;
	
	public JumpPtr(String label, byte loc) {
		super(label);
		this.loc = loc;
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = (byte) 0xA8;
		data[3] = loc;
		
		return data;
	}

}
