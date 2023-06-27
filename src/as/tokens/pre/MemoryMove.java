package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class MemoryMove extends Token {
	private static final long serialVersionUID = 3157504836521599852L;
	private final String loc;
	private final String loc2;
	public MemoryMove(String label, String loc, String loc2) {
		super(label);
		this.loc = loc;
		this.loc2 = loc2;
	}

	@Override
	public int getLength() {
		return 32;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[32];
		
		data[0] = 0x10;
		data[1] = 9;
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		data[16] = 0x11;
		data[17] = 9;
		System.arraycopy(Conversion.longToBytes(labels.get(loc2)), 0, data, 24, 8);
		
		return data;
	}

}
