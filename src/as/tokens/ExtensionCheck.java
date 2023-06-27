package as.tokens;

import as.Conversion;
import as.Labels;

public class ExtensionCheck extends Token {
	private static final long serialVersionUID = -620800067627954510L;
	private final byte r;
	private final byte feature;
	
	public ExtensionCheck(String label, byte r, String f) {
		super(label);
		this.r = r;
		switch(f.toLowerCase()) {
		case "power":
			feature = 0x0;
			break;
		case "64bit":
			feature = 0x1;
			break;
		case "io":
			feature = 0x2;
			break;
		case "math":
			feature = 0x3;
			break;
		case "8bit":
			feature = 0x4;
			break;
		case "16bit":
			feature = 0x5;
			break;
		case "32bit":
			feature = 0x6;
			break;
		case "float":
			feature = 0x7;
			break;
		case "double":
			feature = 0x8;
			break;
		case "call":
			feature = 0x9;
			break;
		case "jump":
			feature = 0xA;
			break;
		case "cast":
			feature = 0xB;
			break;
		case "array":
			feature = 0xC;
			break;
		case "stack":
			feature = 0xD;
			break;
		case "additional":
			feature = 0xE;
			break;
		default:
			feature = Conversion.parseByte(f);
			break;
		}
	}

	@Override
	public int getLength() {
		return 8;
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[8];
		
		data[0] = (byte) (0xF0 | feature);
		data[1] = r;
		
		return data;
	}

}
