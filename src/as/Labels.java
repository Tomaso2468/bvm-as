package as;

import java.util.HashMap;
import java.util.Map;

public class Labels {
	private final Map<String, Long> pointers = new HashMap<String, Long>();
	public long get(String label) {
		try {
			if(label.startsWith("0x")) {
				return Long.parseLong(label.replace("0x", ""), 16);
			}
			if(label.startsWith("0b")) {
				return Long.parseLong(label.replace("0b", ""), 2);
			}
			return Long.parseLong(label);
		} catch (NumberFormatException e) {
			return pointers.get(label);
		}
	}
	public void add(String label, Long pointer) {
		pointers.put(label, pointer);
	}
	public Labels merge(Labels l) {
		Labels labels = new Labels();
		
		for(Map.Entry<String, Long> e : pointers.entrySet()) {
			labels.add(e.getKey(), e.getValue());
		}
		
		for(Map.Entry<String, Long> e : l.pointers.entrySet()) {
			labels.add(e.getKey(), e.getValue());
		}
		
		return labels;
	}
}
