package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class PopStack extends Token {
	private static final long serialVersionUID = 3650673128314703746L;
	public PopStack(String label) {
		super(label);
	}

	@Override
	public int getLength() {
		return 48;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[48];
		
		data[0] = 0x10;
		data[1] = 9;
		System.arraycopy(Conversion.longToBytes(cl + 16), 0, data, 8, 8);
		
		data[16] = 0x31;
		data[17] = 0x5;
		data[18] = 0x9;
		data[19] = 0x5;
		
		data[24] = (byte) 0xA0;
		System.arraycopy(Conversion.longToBytes(cl + 48), 0, data, 32, 8);
		
		System.arraycopy(Conversion.longToBytes(8), 0, data, 40, 8);
		
		return data;
	}

}
