package as.tokens.x32.mem;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class StorePtr32Offset extends Token {
	private static final long serialVersionUID = 6585913930356983647L;
	private final byte r2;
	private final byte r;
	private final byte offset;
	public StorePtr32Offset(String label, byte r, byte r2, String offset) {
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
		
		data[0] = 0x67;
		data[1] = r;
		data[2] = r2;
		data[3] = offset;
		
		return data;
	}

}
