package as;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import as.error.AbortException;
import as.tokens.Allign;
import as.tokens.Call;
import as.tokens.CallPtr;
import as.tokens.DIn;
import as.tokens.DOut;
import as.tokens.ExtensionCheck;
import as.tokens.Halt;
import as.tokens.HaltErr;
import as.tokens.In;
import as.tokens.Interrupt;
import as.tokens.InterruptReturn;
import as.tokens.Out;
import as.tokens.Return;
import as.tokens.SetupInterrupt;
import as.tokens.SetupStack;
import as.tokens.Token;
import as.tokens.float32.AddF;
import as.tokens.float32.CastFloat;
import as.tokens.float32.DivideF;
import as.tokens.float32.ModF;
import as.tokens.float32.MultiplyF;
import as.tokens.float32.SubF;
import as.tokens.float64.ACosF64;
import as.tokens.float64.ASinF64;
import as.tokens.float64.ATanF64;
import as.tokens.float64.AddF64;
import as.tokens.float64.CastDouble;
import as.tokens.float64.CbrtF64;
import as.tokens.float64.CosF64;
import as.tokens.float64.DivideF64;
import as.tokens.float64.InF64;
import as.tokens.float64.LogF64;
import as.tokens.float64.ModF64;
import as.tokens.float64.MultiplyF64;
import as.tokens.float64.PowerF64;
import as.tokens.float64.SinF64;
import as.tokens.float64.SqrtF64;
import as.tokens.float64.SubF64;
import as.tokens.float64.TanF64;
import as.tokens.jump.Jump;
import as.tokens.jump.JumpEquPtr;
import as.tokens.jump.JumpEqual;
import as.tokens.jump.JumpLess;
import as.tokens.jump.JumpLessEqual;
import as.tokens.jump.JumpLessEqualPtr;
import as.tokens.jump.JumpLessPtr;
import as.tokens.jump.JumpMore;
import as.tokens.jump.JumpMoreEqual;
import as.tokens.jump.JumpMoreEqualPtr;
import as.tokens.jump.JumpMorePtr;
import as.tokens.jump.JumpNaN;
import as.tokens.jump.JumpNaNPtr;
import as.tokens.jump.JumpNeg;
import as.tokens.jump.JumpNegPtr;
import as.tokens.jump.JumpNotEqual;
import as.tokens.jump.JumpNotEqualPtr;
import as.tokens.jump.JumpPos;
import as.tokens.jump.JumpPosPtr;
import as.tokens.jump.JumpPtr;
import as.tokens.jump.JumpTrue;
import as.tokens.jump.JumpZero;
import as.tokens.jump.JumpZeroPtr;
import as.tokens.pre.Pointer;
import as.tokens.pre.PopStack;
import as.tokens.pre.PushStack;
import as.tokens.pre.RegisterMove;
import as.tokens.pre.Skip;
import as.tokens.pre.TByte;
import as.tokens.pre.TChar;
import as.tokens.pre.TDouble;
import as.tokens.pre.TFloat;
import as.tokens.pre.TInteger;
import as.tokens.pre.TLong;
import as.tokens.pre.TShort;
import as.tokens.pre.TString;
import as.tokens.pre.ZeroFilled;
import as.tokens.x16.Cast16;
import as.tokens.x16.CastShort;
import as.tokens.x16.mem.Load16Offset;
import as.tokens.x16.mem.LoadPtr16;
import as.tokens.x16.mem.LoadPtr16Offset;
import as.tokens.x16.mem.Store16;
import as.tokens.x16.mem.Store16Offset;
import as.tokens.x16.mem.StorePtr16;
import as.tokens.x16.mem.StorePtr16Offset;
import as.tokens.x32.Cast32;
import as.tokens.x32.CastInt;
import as.tokens.x32.mem.Load32Offset;
import as.tokens.x32.mem.LoadPtr32;
import as.tokens.x32.mem.LoadPtr32Offset;
import as.tokens.x32.mem.Store32;
import as.tokens.x32.mem.Store32Offset;
import as.tokens.x32.mem.StorePtr32;
import as.tokens.x32.mem.StorePtr32Offset;
import as.tokens.x64.Add64;
import as.tokens.x64.And64;
import as.tokens.x64.Cast64;
import as.tokens.x64.CastLong;
import as.tokens.x64.Dec64;
import as.tokens.x64.Divide64;
import as.tokens.x64.Inc64;
import as.tokens.x64.LShift64;
import as.tokens.x64.LShift64Safe;
import as.tokens.x64.Mod64;
import as.tokens.x64.Multiply64;
import as.tokens.x64.Nand64;
import as.tokens.x64.Nor64;
import as.tokens.x64.Not64;
import as.tokens.x64.Or64;
import as.tokens.x64.RShift64;
import as.tokens.x64.RShift64Safe;
import as.tokens.x64.Rnd64;
import as.tokens.x64.Sub64;
import as.tokens.x64.Unix64;
import as.tokens.x64.Xor64;
import as.tokens.x64.mem.Load64;
import as.tokens.x64.mem.Load64Offset;
import as.tokens.x64.mem.LoadPtr64;
import as.tokens.x64.mem.LoadPtr64Offset;
import as.tokens.x64.mem.Store64;
import as.tokens.x64.mem.Store64Offset;
import as.tokens.x64.mem.StorePtr64;
import as.tokens.x64.mem.StorePtr64Offset;
import as.tokens.x8.Cast8;
import as.tokens.x8.CastByte;
import as.tokens.x8.mem.Load8;
import as.tokens.x8.mem.Load8Offset;
import as.tokens.x8.mem.LoadPtr8;
import as.tokens.x8.mem.LoadPtr8Offset;
import as.tokens.x8.mem.Store8;
import as.tokens.x8.mem.Store8Offset;
import as.tokens.x8.mem.StorePtr8;
import as.tokens.x8.mem.StorePtr8Offset;

public class AS {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(new File(args[0]).toPath());
		
		List<Token> tokens = new LinkedList<Token>();
		List<String> global = new LinkedList<String>();
		
		boolean multiline_comment = false;
		String label = null;
		for(String line : lines) {
			if (line.trim().equals("")) {
				continue;
			}
			if (line.trim().startsWith("/*")) {
				multiline_comment = true;
			}
			if (line.trim().endsWith("*/")) {
				multiline_comment = false;
				continue;
			}
			if (!multiline_comment) {
				String[] parts = line.replace("->", "").replace("\t", "    ").replace("(", " ").replace(")", " ").replaceAll(" +", " ").split(" ");
				for(String part : parts) {
					System.out.print(part + " ");
				}
				System.out.println();
				label = parts[0];
				String ins = parts[1].toLowerCase();
				
				switch(ins) {
				case ".point":
					tokens.add(new Pointer(label, parts[2]));
					break;
				case ".size":
					tokens.add(new TLong(label, parts[2]));
					break;
				case ".long":
					tokens.add(new TLong(label, parts[2]));
					break;
				case ".int":
					tokens.add(new TInteger(label, parts[2]));
					break;
				case ".short":
					tokens.add(new TShort(label, parts[2]));
					break;
				case ".byte":
					tokens.add(new TByte(label, parts[2]));
					break;
				case ".char":
					tokens.add(new TChar(label, parts[2]));
					break;
				case ".float":
					tokens.add(new TFloat(label, parts[2]));
					break;
				case ".double":
					tokens.add(new TDouble(label, parts[2]));
					break;
				case ".string":
					StringBuilder string_sb = new StringBuilder();
					for(int i = 2; i < parts.length; i++) {
						string_sb.append(parts[i]);
						if(i != parts.length - 1) {
							string_sb.append("\n");
						}
					}
					String string_s = string_sb.toString()
							.replace("\\0", "\u0000")
							.replace("\\n", "\n")
							.replace("\\r", "\r")
							.replace("\\t", "\t")
							.replace("\\b", "\b")
							.replace("\\a", "\u0007")
							.replace("\\f", "\f")
							.replace("\\v", "\u000B")
							.replace("\\'", "\'")
							.replace("\\\"", "\"");
					for(int i = 0; i <= Short.MAX_VALUE; i++) {
						string_s.replace("\\u" + String.format("%04X", i), ((char) i) + "");
					}
					tokens.add(new TString(label, string_s));
					break;
				case ".ascii":
					StringBuilder ascii_sb = new StringBuilder();
					for(int i = 2; i < parts.length; i++) {
						ascii_sb.append(parts[i]);
						if(i != parts.length - 1) {
							ascii_sb.append(" ");
						}
					}
					String ascii_s = ascii_sb.toString()
							.replace("\\0", "\u0000")
							.replace("\\n", "\n")
							.replace("\\r", "\r")
							.replace("\\t", "\t")
							.replace("\\b", "\b")
							.replace("\\a", "\u0007")
							.replace("\\f", "\f")
							.replace("\\v", "\u000B")
							.replace("\\'", "\'")
							.replace("\\\"", "\"");
					tokens.add(new TString(label, ascii_s));
					break;
				case ".asciz":
					StringBuilder asciz_sb = new StringBuilder();
					for(int i = 2; i < parts.length; i++) {
						asciz_sb.append(parts[i]);
						if(i != parts.length - 1) {
							asciz_sb.append(" ");
						}
					}
					String asciz_s = asciz_sb.toString()
							.replace("\\0", "\u0000")
							.replace("\\n", "\n")
							.replace("\\r", "\r")
							.replace("\\t", "\t")
							.replace("\\b", "\b")
							.replace("\\a", "\u0007")
							.replace("\\f", "\f")
							.replace("\\v", "\u000B")
							.replace("\\'", "\'")
							.replace("\\\"", "\"");
					asciz_s += "\u0000";
					tokens.add(new TString(label, asciz_s));
					break;
				case ".push":
					tokens.add(new PushStack(label, getRegister(parts[2])));
					break;
				case ".pop":
					tokens.add(new PopStack(label));
					break;
				case ".zero":
					tokens.add(new ZeroFilled(label, Long.parseLong(parts[2])));
					break;
				case ".mov":
					boolean mov_r0 = parts[2].startsWith("$");
					boolean mov_r1 = parts[3].startsWith("$");
					
					if (mov_r0 && mov_r1) {
						tokens.add(new RegisterMove(label, getRegister(parts[2]), getRegister(parts[3])));
					}
					if (!mov_r0 && mov_r1) {
						tokens.add(new Load64(label, parts[2], getRegister(parts[3])));
					}
					if (mov_r0 && !mov_r1) {
						tokens.add(new Store64(label, getRegister(parts[3]), parts[2]));
					}
					break;
				case ".allign":
					if(parts.length > 3) {
						tokens.add(new Allign(label, parts[2], parts[3]));
					} else {
						tokens.add(new Allign(label, parts[2]));
					}
					break;
				case ".skip":
					if(parts.length > 3) {
						tokens.add(new Skip(label, parts[2], parts[3]));
					} else {
						tokens.add(new Skip(label, parts[2]));
					}
					break;
				case ".abort":
					throw new AbortException(".abort instruction was issued.");
				case ".data":
					tokens.add(new Skip(label, "8"));
					tokens.add(new Allign(label, "16"));
					break;
				case ".text":
					tokens.add(new Allign(label, "16"));
					break;
				case ".global":
					global.add(parts[2]);
					break;
				case "halt":
					tokens.add(new Halt(label));
					break;
				case "halterr":
					tokens.add(new HaltErr(label, parts[2]));
					break;
				case "load":
					tokens.add(new Load64(label, parts[2], getRegister(parts[3])));
					break;
				case "loadq":
					tokens.add(new Load64(label, parts[2], getRegister(parts[3])));
					break;
				case "store":
					tokens.add(new Store64(label, getRegister(parts[2]), parts[3]));
					break;
				case "storeq":
					tokens.add(new Store64(label, getRegister(parts[2]), parts[3]));
					break;
				case "store_offset":
					tokens.add(new Store64Offset(label, getRegister(parts[2]), parts[4], parts[3]));
					break;
				case "storeq_offset":
					tokens.add(new Store64Offset(label, getRegister(parts[2]), parts[4], parts[3]));
					break;
				case "load_offset":
					tokens.add(new Load64Offset(label, getRegister(parts[4]), parts[3], parts[2]));
					break;
				case "loadq_offset":
					tokens.add(new Load64Offset(label, getRegister(parts[4]), parts[3], parts[2]));
					break;
				case "load_ptr":
					tokens.add(new LoadPtr64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "store_ptr":
					tokens.add(new StorePtr64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "loadq_ptr":
					tokens.add(new LoadPtr64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "storeq_ptr":
					tokens.add(new StorePtr64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "load_ptr_offset":
					tokens.add(new LoadPtr64Offset(label, getRegister(parts[3]), getRegister(parts[4]), parts[2]));
					break;
				case "loadq_ptr_offset":
					tokens.add(new LoadPtr64Offset(label, getRegister(parts[3]), getRegister(parts[4]), parts[2]));
					break;
				case "store_ptr_offset":
					tokens.add(new StorePtr64Offset(label, getRegister(parts[2]), getRegister(parts[4]), parts[3]));
					break;
				case "storeq_ptr_offset":
					tokens.add(new StorePtr64Offset(label, getRegister(parts[2]), getRegister(parts[4]), parts[3]));
					break;
				case "add":
					tokens.add(new Add64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "addq":
					tokens.add(new Add64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "sub":
					tokens.add(new Sub64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "subq":
					tokens.add(new Sub64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "mul":
					tokens.add(new Multiply64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "mulq":
					tokens.add(new Multiply64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "div":
					tokens.add(new Divide64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "divq":
					tokens.add(new Divide64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "mod":
					tokens.add(new Mod64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "modq":
					tokens.add(new Mod64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "and":
					tokens.add(new And64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "andq":
					tokens.add(new And64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "or":
					tokens.add(new Or64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "orq":
					tokens.add(new Or64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "xor":
					tokens.add(new Xor64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "xorq":
					tokens.add(new Xor64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "not":
					tokens.add(new Not64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "notq":
					tokens.add(new Not64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "nor":
					tokens.add(new Nor64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "norq":
					tokens.add(new Nor64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "nand":
					tokens.add(new Nand64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "nandq":
					tokens.add(new Nand64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "random":
					tokens.add(new Rnd64(label, getRegister(parts[2])));
					break;
				case "randomq":
					tokens.add(new Rnd64(label, getRegister(parts[2])));
					break;
				case "unix":
					tokens.add(new Rnd64(label, getRegister(parts[2])));
					break;
				case "unixq":
					tokens.add(new Unix64(label, getRegister(parts[2])));
					break;
				case "inc":
					tokens.add(new Inc64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "incq":
					tokens.add(new Inc64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "dec":
					tokens.add(new Dec64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "decq":
					tokens.add(new Dec64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "call":
					tokens.add(new Call(label, parts[2]));
					break;
				case "call_ptr":
					tokens.add(new CallPtr(label, getRegister(parts[2])));
					break;
				case "int_return":
					tokens.add(new InterruptReturn(label));
					break;
				case "return":
					tokens.add(new Return(label));
					break;
				case "int":
					tokens.add(new Interrupt(label, parts[2]));
					break;
				case "dout":
					tokens.add(new DOut(label, getRegister(parts[2])));
					break;
				case "jump":
					tokens.add(new Jump(label, parts[2]));
					break;
				case "jump_equ":
					tokens.add(new JumpEqual(label, getRegister(parts[2]), getRegister(parts[3]), parts[4]));
					break;
				case "jump_nequ":
					tokens.add(new JumpNotEqual(label, getRegister(parts[2]), getRegister(parts[3]), parts[4]));
					break;
				case "jump_pos":
					tokens.add(new JumpPos(label, getRegister(parts[2]), parts[3]));
					break;
				case "jump_neg":
					tokens.add(new JumpNeg(label, getRegister(parts[2]), parts[3]));
					break;
				case "jump_zero":
					tokens.add(new JumpZero(label, getRegister(parts[2]), parts[3]));
					break;
				case "jump_true":
					tokens.add(new JumpTrue(label, getRegister(parts[2]), parts[3]));
					break;
				case "jump_equ_ptr":
					tokens.add(new JumpEquPtr(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "jump_nequ_ptr":
					tokens.add(new JumpNotEqualPtr(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "jump_pos_ptr":
					tokens.add(new JumpPosPtr(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "jump_neg_ptr":
					tokens.add(new JumpNegPtr(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "jump_zero_ptr":
					tokens.add(new JumpZeroPtr(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "jump_nan":
					tokens.add(new JumpNaN(label, getRegister(parts[2]), parts[4]));
					break;
				case "jump_nan_ptr":
					tokens.add(new JumpNaNPtr(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "jump_ptr":
					tokens.add(new JumpPtr(label, getRegister(parts[2])));
					break;
				case "check_ext":
					tokens.add(new ExtensionCheck(label, getRegister(parts[2]), parts[3]));
					break;
				case "setup_stack":
					tokens.add(new SetupStack(label, parts[2]));
					break;
				case "setup_int":
					tokens.add(new SetupInterrupt(label, parts[2]));
					break;
				case "din":
					tokens.add(new DIn(label, getRegister(parts[2])));
					break;
				case "cast_long":
					tokens.add(new Cast64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "loadd":
					tokens.add(new Load8(label, parts[2], getRegister(parts[3])));
					break;
				case "storeb":
					tokens.add(new Store8(label, getRegister(parts[2]), parts[3]));
					break;
				case "storeb_offset":
					tokens.add(new Store8Offset(label, getRegister(parts[2]), parts[4], parts[3]));
					break;
				case "loadb_offset":
					tokens.add(new Load8Offset(label, getRegister(parts[4]), parts[3], parts[2]));
					break;
				case "loadb_ptr":
					tokens.add(new LoadPtr8(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "storeb_ptr":
					tokens.add(new StorePtr8(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "loadb_ptr_offset":
					tokens.add(new LoadPtr8Offset(label, getRegister(parts[3]), getRegister(parts[4]), parts[2]));
					break;
				case "storeb_ptr_offset":
					tokens.add(new StorePtr8Offset(label, getRegister(parts[2]), getRegister(parts[4]), parts[3]));
					break;
				case "cast_byte":
					tokens.add(new Cast8(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "storew":
					tokens.add(new Store16(label, getRegister(parts[2]), parts[3]));
					break;
				case "storew_offset":
					tokens.add(new Store16Offset(label, getRegister(parts[2]), parts[4], parts[3]));
					break;
				case "loadw_offset":
					tokens.add(new Load16Offset(label, getRegister(parts[4]), parts[3], parts[2]));
					break;
				case "loadw_ptr":
					tokens.add(new LoadPtr16(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "storew_ptr":
					tokens.add(new StorePtr16(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "loadw_ptr_offset":
					tokens.add(new LoadPtr16Offset(label, getRegister(parts[3]), getRegister(parts[4]), parts[2]));
					break;
				case "storew_ptr_offset":
					tokens.add(new StorePtr16Offset(label, getRegister(parts[2]), getRegister(parts[4]), parts[3]));
					break;
				case "cast_short":
					tokens.add(new Cast16(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "stored":
					tokens.add(new Store32(label, getRegister(parts[2]), parts[3]));
					break;
				case "stored_offset":
					tokens.add(new Store32Offset(label, getRegister(parts[2]), parts[4], parts[3]));
					break;
				case "loadd_offset":
					tokens.add(new Load32Offset(label, getRegister(parts[4]), parts[3], parts[2]));
					break;
				case "loadd_ptr":
					tokens.add(new LoadPtr32(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "stored_ptr":
					tokens.add(new StorePtr32(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "loadd_ptr_offset":
					tokens.add(new LoadPtr32Offset(label, getRegister(parts[3]), getRegister(parts[4]), parts[2]));
					break;
				case "stored_ptr_offset":
					tokens.add(new StorePtr32Offset(label, getRegister(parts[2]), getRegister(parts[4]), parts[3]));
					break;
				case "cast_int":
					tokens.add(new Cast32(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "add_f32":
					tokens.add(new AddF(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "sub_f32":
					tokens.add(new SubF(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "mul_f32":
					tokens.add(new MultiplyF(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "div_f32":
					tokens.add(new DivideF(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "mod_f32":
					tokens.add(new ModF(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "add_f64":
					tokens.add(new AddF64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "sub_f64":
					tokens.add(new SubF64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "mul_f64":
					tokens.add(new MultiplyF64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "div_f64":
					tokens.add(new DivideF64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "mod_f64":
					tokens.add(new ModF64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "pow_f64":
					tokens.add(new PowerF64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "sin_f64":
					tokens.add(new SinF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "cos_f64":
					tokens.add(new CosF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "tan_f64":
					tokens.add(new TanF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "sqrt_f64":
					tokens.add(new SqrtF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "cbrt_f64":
					tokens.add(new CbrtF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "asin_f64":
					tokens.add(new ASinF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "acos_f64":
					tokens.add(new ACosF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "atan_f64":
					tokens.add(new ATanF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "log_f64":
					tokens.add(new LogF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "in_f64":
					tokens.add(new InF64(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "out":
					tokens.add(new Out(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "in":
					tokens.add(new In(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "acast_long":
					tokens.add(new CastLong(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "acast_int":
					tokens.add(new CastInt(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "acast_short":
					tokens.add(new CastShort(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "acast_byte":
					tokens.add(new CastByte(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "acast_float":
					tokens.add(new CastFloat(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "acast_double":
					tokens.add(new CastDouble(label, getRegister(parts[2]), getRegister(parts[3])));
					break;
				case "jump_less":
					tokens.add(new JumpLess(label, getRegister(parts[2]), getRegister(parts[3]), parts[4]));
					break;
				case "jump_more":
					tokens.add(new JumpMore(label, getRegister(parts[2]), getRegister(parts[3]), parts[4]));
					break;
				case "jump_less_equ":
					tokens.add(new JumpLessEqual(label, getRegister(parts[2]), getRegister(parts[3]), parts[4]));
					break;
				case "jump_more_equ":
					tokens.add(new JumpMoreEqual(label, getRegister(parts[2]), getRegister(parts[3]), parts[4]));
					break;
				case "jump_less_ptr":
					tokens.add(new JumpLessPtr(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "jump_more_ptr":
					tokens.add(new JumpMorePtr(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "jump_less_equal_ptr":
					tokens.add(new JumpLessEqualPtr(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "jump_more_equal_ptr":
					tokens.add(new JumpMoreEqualPtr(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "lshift":
					tokens.add(new LShift64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "rshift":
					tokens.add(new RShift64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "lshiftq":
					tokens.add(new LShift64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "rshiftq":
					tokens.add(new RShift64(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "lshift_safe":
					tokens.add(new LShift64Safe(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "rshift_safe":
					tokens.add(new RShift64Safe(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "lshift_safeq":
					tokens.add(new LShift64Safe(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				case "rshift_safeq":
					tokens.add(new RShift64Safe(label, getRegister(parts[2]), getRegister(parts[3]), getRegister(parts[4])));
					break;
				}
				
				label = null;
			}
		}
		
		for(String l : global) {
			System.out.println("Exporting global: " + l);
		}
		
		System.out.println("Exporting tokens");
		write(tokens, global, new File(args[1]));
	}
	
	public static void write(List<Token> tokens, List<String> globals, File f) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		out.writeObject(tokens);
		out.flush();
		
		out.writeObject(globals);
		out.flush();
		
		out.close();
	}
	
	public static byte getRegister(String id) {
		switch(id.toLowerCase()) {
		case "$pc":
			return 0;
		case "$thread":
			return 1;
		case "$zero":
			return 2;
		case "$one":
			return 3;
		case "$negative_one":
			return 4;
		case "$sp":
			return 5;
		case "$int_table":
			return 6;
		case "$ins":
			return 7;
		case "$ip":
			return 8;
		case "$temp":
			return 9;
		case "$a":
			return 12;
		case "$b":
			return 13;
		case "$c":
			return 14;
		case "$d":
			return 15;
		case "$e":
			return 16;
		case "$f":
			return 17;
		case "$g":
			return 18;
		case "$h":
			return 19;
		case "$i":
			return 20;
		case "$j":
			return 21;
		case "$k":
			return 22;
		case "$l":
			return 23;
		case "$m":
			return 24;
		case "$n":
			return 25;
		case "$o":
			return 26;
		case "$p":
			return 27;
		case "$q":
			return 28;
		case "$r":
			return 29;
		case "$s":
			return 30;
		case "$t":
			return 31;
		case "$u":
			return 32;
		case "$v":
			return 33;
		case "$w":
			return 34;
		case "$x":
			return 35;
		case "$y":
			return 36;
		case "$z":
			return 37;
		case "$":
			return 0;
		case "$eax":
			return 10;
		case "$ebx":
			return 12;
		case "$ecx":
			return 13;
		case "$edx":
			return 14;
		case "$esi":
			return 15;
		case "$edi":
			return 16;
		case "$esp":
			return 5;
		case "$rsp":
			return 5;
		case "$rbp":
			return 11;
		case "$0":
			return 12;
		case "$1":
			return 13;
		case "$2":
			return 14;
		case "$3":
			return 15;
		case "$4":
			return 16;
		case "$5":
			return 17;
		case "$6":
			return 18;
		case "$7":
			return 19;
		case "$8":
			return 20;
		case "$9":
			return 21;
		case "$return":
			return 10;
		default:
			return Byte.parseByte(id);
		}
	}
}
