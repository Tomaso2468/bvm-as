package as.tokens.x64.mem;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class LoadPtr64Offset extends Token {
	private static final long serialVersionUID = 1657823501566469907L;
	private final byte r;
	private final byte r2;
	private final byte offset;
	public LoadPtr64Offset(String label, byte r2, byte r, String offset) {
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
		
		data[0] = 0x16;
		data[1] = r;
		data[2] = r2;
		data[3] = offset;
		
		return data;
	}

}