package as.tokens;

import as.Labels;

public class Return extends Token {
	private static final long serialVersionUID = -1226142500494066790L;

	public Return(String label) {
		super(label);
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = (byte) 0x91;
		
		return data;
	}

}
