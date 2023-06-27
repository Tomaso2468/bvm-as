package as.tokens;

import as.Conversion;
import as.Labels;

public class SetupStack extends Token {
	private static final long serialVersionUID = 3572880470156973620L;
	private final String loc;
	public	SetupStack(String label, String loc) {
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
		
		data[0] = (byte) 0xD0;
		
		System.arraycopy(Conversion.longToBytes(labels.get(loc)), 0, data, 8, 8);
		
		return data;
	}

}
