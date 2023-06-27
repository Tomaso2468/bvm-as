package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class PushStack extends Token {
	private static final long serialVersionUID = 4932079686234109727L;
	private int register;
	public PushStack(String label, int register) {
		super(label);
		this.register = register;
	}

	@Override
	public int getLength() {
		return 56;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[56];
		data[0] = 0x15;
		data[1] = (byte) register;
		data[2] = 5;
		
		data[8] = 0x10;
		data[9] = 9;
		System.arraycopy(Conversion.longToBytes(cl + 16), 0, data, 16, 8);
		
		data[24] = 0x30;
		data[25] = 5;
		data[26] = 9;
		data[27] = 5;
		
		data[32] = (byte) 0xA0;
		System.arraycopy(Conversion.longToBytes(cl + 56), 0, data, 40, 8); //A cl + 48
		
		System.arraycopy(Conversion.longToBytes(8), 0, data, 48, 8);
		
		return data;
	}

}
