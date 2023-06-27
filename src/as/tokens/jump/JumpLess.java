package as.tokens.jump;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class JumpLess extends Token {
	private static final long serialVersionUID = -1857879536785364193L;
	private final String loc;
	private final byte r;
	private final byte r2;
	
	public JumpLess(String label, byte r, byte r2, String loc) {
		super(label);
		this.loc = loc;
		this.r = r;
		this.r2 = r2;
	}

	@Override
	public int getLength() {
		return 16;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[16];
		
		data[0] = (byte) 0xE0;
		data[1] = r;
		data[2] = r2;
		
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		return data;
	}

}
