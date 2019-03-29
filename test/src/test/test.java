package test;

public class test {
	public static void main(String[]args){
		int [][][]x = new int[12][5][2];
		System.out.println(x.length + " " + x[0].length + " " + x[0][0].length);
//		F f = new F();
//		System.out.println(f.i ); 
//		System.out .println(f.s);
//		f.imethod(); 
//		f.smethod(); 
//		System.out.println(F.i);
//		System.out.println(F.s); 
//		F.imethod(); 
//		F.smethod();
	}
	

}

class F { 
	int i ; 
	static String s = "!";
	void imethod() { }
	static void smethod() { }
}
