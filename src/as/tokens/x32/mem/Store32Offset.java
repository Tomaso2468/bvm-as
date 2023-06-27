package as.tokens.x32.mem;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class Store32Offset extends Token {
	private static final long serialVersionUID = -6953963204796225918L;
	private final String loc;
	private final byte r;
	private final byte offset;
	public Store32Offset(String label, byte r, String loc, String offset) {
		super(label);
		this.loc = loc;
		this.r = r;
		this.offset = Conversion.parseByte(offset);
	}

	@Override
	public int getLength() {
		return 16;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[16];
		
		data[0] = 0x63;
		data[1] = r;
		data[2] = offset;
		
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		return data;
	}

}
