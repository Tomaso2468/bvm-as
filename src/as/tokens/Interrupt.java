package as.tokens;

import as.Conversion;
import as.Labels;

public class Interrupt extends Token {
	private static final long serialVersionUID = -4052323536338807622L;
	private final byte id;
	public Interrupt(String label, String loc) {
		super(label);
		this.id = Conversion.parseByte(loc);
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = (byte) 0x92;
		data[1] = id;
		
		return data;
	}

}

