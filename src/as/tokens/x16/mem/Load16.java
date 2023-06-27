package as.tokens.x16.mem;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class Load16 extends Token {
	private static final long serialVersionUID = 2875271540268147725L;
	private final String loc;
	private final byte r;
	public Load16(String label, String loc, byte r) {
		super(label);
		this.loc = loc;
		this.r = r;
	}

	@Override
	public int getLength() {
		return 16;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[16];
		
		data[0] = 0x50;
		data[1] = r;
		
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		return data;
	}

}
