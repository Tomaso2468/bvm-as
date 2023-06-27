package as.tokens;

import as.Conversion;
import as.Labels;

public class Allign extends Token {
	private static final long serialVersionUID = 353366449824010631L;
	private long length = 0;
	private byte value;
	public Allign(String label, String length, String value) {
		super(label);
		this.length = Conversion.parseLong(length);
		this.value = Conversion.parseByte(value);
	}
	public Allign(String label, String loc) {
		this(label, loc, "0");
	}

	@Override
	public int getLength() {
		throw new UnsupportedOperationException("getLength() must be called with long arg");
	}
	
	@Override
	public int getLength(long loc) {
		if (loc % length == 0) {
			return 0;
		}
		return (int) (length - (loc % length));
	}

	@Override
	public byte[] getData(Labels labels, long cl) {
		byte[] data = new byte[getLength(cl)];
		for(int i = 0; i < data.length; i++) {
			data[i] = value;
		}
		return data;
	}

}
