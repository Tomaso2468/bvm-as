package as.tokens.pre;

import as.Conversion;
import as.Labels;
import as.tokens.Token;

public class Pointer extends Token {
	private static final long serialVersionUID = 3535905668062590883L;
	private final String loc;
	public Pointer(String label, String loc) {
		super(label);
		this.loc = loc;
	}
	public String getLocation() {
		return loc;
	}
	@Override
	public int getLength() {
		return 8;
	}
	@Override
	public byte[] getData(Labels labels, long cl) {
		return Conversion.longToBytes(labels.get(loc));
	}
}
