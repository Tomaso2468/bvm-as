package as.tokens;

import java.io.Serializable;

import as.Labels;

public abstract class Token implements Serializable {
	private static final long serialVersionUID = -2235439726689885407L;
	private final String label;
	public Token(String label) {
		if(label != null) {
			this.label = label.replace(":", "");
		} else {
			this.label = null;
		}
	}
	public String getLabel() {
		return label;
	}
	public abstract int getLength();
	public int getLength(long loc) {
		return getLength();
	}
	public abstract byte[] getData(Labels labels, long cl);
}
