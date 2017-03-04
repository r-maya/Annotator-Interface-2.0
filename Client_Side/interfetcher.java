

public class interfetcher implements Runnable{
	
	String s;

	interfetcher(String ss){
		this.s = ss;
	}

	public void run(){
		System.out.println(s+"interfetcher");
		filefetcher f1 = new filefetcher(s);
		f1.main(new String[1]);
	}
}