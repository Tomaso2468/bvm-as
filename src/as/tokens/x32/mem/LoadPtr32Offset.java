package as.tokens.x32.mem;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class LoadPtr32Offset extends Token {
	private static final long serialVersionUID = -9135768397474965616L;
	private final byte r;
	private final byte r2;
	private final byte offset;
	public LoadPtr32Offset(String label, byte r2, byte r, String offset) {
		super(label);
		this.r2 = r2;
		this.r = r;
		this.offset = Conversion.parseByte(offset);
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = 0x66;
		data[1] = r;
		data[2] = r2;
		data[3] = offset;
		
		return data;
	}

}