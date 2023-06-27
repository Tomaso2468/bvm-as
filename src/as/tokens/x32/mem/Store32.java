package as.tokens.x32.mem;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class Store32 extends Token {
	private static final long serialVersionUID = 7475618586577631338L;
	private final String loc;
	private final byte r;
	public Store32(String label, byte r, String loc) {
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
		
		data[0] = 0x61;
		data[1] = r;
		
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		return data;
	}
}
