package as.tokens.x8.mem;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class Store8 extends Token {
	private static final long serialVersionUID = 8013868280402073591L;
	private final String loc;
	private final byte r;
	public Store8(String label, byte r, String loc) {
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
		
		data[0] = 0x41;
		data[1] = r;
		
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		return data;
	}
}
