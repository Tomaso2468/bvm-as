package as.tokens.jump;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class Jump extends Token {
	private static final long serialVersionUID = -5718153858881254698L;
	private final String loc;
	
	public Jump(String label, String loc) {
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
		
		data[0] = (byte) 0xA0;
		
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		return data;
	}

}
