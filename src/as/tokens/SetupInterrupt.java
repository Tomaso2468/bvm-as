package as.tokens;

import as.Conversion;
import as.Labels;

public class SetupInterrupt extends Token {
	private static final long serialVersionUID = -6011352427832693328L;
	private final String loc;
	public	SetupInterrupt(String label, String loc) {
		super(label);
		this.loc = loc;
	}

	@Override
	public int getLength() {
		return 16;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[16];
		
		data[0] = (byte) 0xD1;
		
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		return data;
	}

}