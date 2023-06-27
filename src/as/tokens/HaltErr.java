package as.tokens;

import as.Conversion;
import as.Labels;

public class HaltErr extends Token {
	private static final long serialVersionUID = -7585252183458948176L;
	private final byte code;
	public HaltErr(String label, String code) {
		super(label);
		this.code = Conversion.parseByte(code);
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		data[0] = 0x01;
		data[1] = code;
		return data;
	}

}
