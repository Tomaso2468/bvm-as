package as.tokens;

import as.Labels;

public class In extends Token {
	private static final long serialVersionUID = 5241813521643545558L;
	private final byte r;
	private final byte port;
	public In(String label, byte port, byte r) {
		super(label);
		this.r = r;
		this.port = port;
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = 0x23;
		data[1] = port;
		data[2] = r;
		
		return data;
	}
}
