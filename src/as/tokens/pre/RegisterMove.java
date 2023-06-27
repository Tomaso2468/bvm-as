package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class RegisterMove extends Token {
	private static final long serialVersionUID = 299870350741829569L;
	private final byte r0;
	private final byte r1;
	public RegisterMove(String label, byte r0, byte r1) {
		super(label);
		this.r0 = r0;
		this.r1 = r1;
	}

	@Override
	public int getLength() {
		return 56;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[56];
		
		data[0] = 0x11;
		data[1] = r0;
		System.arraycopy(Conversion.longToBytes(cl + 48), 0, data, 8, 8);
		
		data[16] = 0x10;
		data[17] = r1;
		System.arraycopy(Conversion.longToBytes(cl + 48), 0, data, 24, 8);
		
		data[32] = (byte) 0xA0;
		System.arraycopy(Conversion.longToBytes(cl + 56), 0, data, 40, 8);
		
		System.arraycopy(Conversion.longToBytes(0), 0, data, 48, 8);
		
		return data;
	}

}
