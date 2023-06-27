package as;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import as.tokens.Token;
import as.tokens.pre.DataToken;

public class LD {
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<ObjectFile> objects = new LinkedList<ObjectFile>();
		System.out.println("Loading files");
		for(int i = 1; i < args.length; i++) {
			String arg = args[i];
			System.out.println("Loading file: " + arg);
			if(arg.endsWith(".bo")) {
				objects.add(new ObjectFile(new File(arg)));
			} else {
				objects.add(new DataFile(new File(arg)));
			}
		}
		
		System.out.println("Generating label data");
		
		Labels globals = new Labels();
		
		long pointer = 0;
		for(ObjectFile f : objects) {
			System.out.println("Generating label data for " + f.f);
			for(Token token : f.tokens) {
				System.out.println("Generating label data for token: " + token);
				if(token.getLabel() != null) {
					f.labels.add(token.getLabel(), pointer);
					System.out.println("Label: " + token.getLabel() + " " + Long.toHexString(pointer).toUpperCase());
				}
				if(f.globals.contains(token.getLabel())) {
					globals.add(token.getLabel(), pointer);
					System.out.println("Found global: " + token.getLabel() + " " + Long.toHexString(pointer).toUpperCase());
				}
				pointer += token.getLength(pointer);
			}
		}
		
		System.out.println("Generating byte code");
		
		List<Byte> bytes = new ArrayList<Byte>((int) pointer);
		pointer = 0;
		for(ObjectFile f : objects) {
			System.out.println("Generating byte code for " + f.f);
			for(Token token : f.tokens) {
				System.out.println("Generating byte code for token: " + token + " @ " + pointer + " " + Long.toHexString(pointer) + "\t" + token.getLabel());
				byte[] data = token.getData(f.labels.merge(globals), pointer);
				for(byte b : data) {
					bytes.add(b);
				}
				pointer += data.length;
			}
		}
		
		write(bytes.toArray(new Byte[bytes.size()]), new File(args[0]));
	}
	
	public static void write(Byte[] data, File f) throws IOException {
		OutputStream fos = new BufferedOutputStream(new FileOutputStream(f));
		for(byte b : data) {
			fos.write(b);
			if(System.currentTimeMillis() % 200 == 0) {
				fos.flush();
			}
		}
		fos.flush();
		fos.close();
	}

	public static class DataFile extends ObjectFile {
		public DataFile(File f) throws IOException {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
			byte[] data = new byte[(int) f.length()];
			int i = 0;
			while(in.available() > 0) {
				data[i] = (byte) in.read();
				i += 1;
			}
			tokens.add(new DataToken(null, data));
			in.close();
		}
	}
	
	public static class ObjectFile {
		List<Token> tokens;
		List<String> globals;
		Labels labels = new Labels();
		File f;
		
		protected ObjectFile() {
			tokens = new LinkedList<Token>();
			globals = new LinkedList<String>();
		}
		
		@SuppressWarnings("unchecked")
		public ObjectFile(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
			this.f = f;
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			tokens = (List<Token>) ois.readObject();
			globals = (List<String>) ois.readObject();
			ois.close();
		}
	}
}
