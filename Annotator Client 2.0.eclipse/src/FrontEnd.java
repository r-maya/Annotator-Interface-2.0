import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.net.*;

public class FrontEnd{
	
	DataInputStream in; DataOutputStream out;
	Socket s; public String tfa;
	int index, max_index;
	String usr, pwd;

	ArrayList<String> tnames, snames;
	ArrayList<SDJ> sdj; 
	ArrayList<MDJ> mdj;


	FrontEnd(Socket ss) throws Exception{
		this.s = ss;
		in=new DataInputStream(s.getInputStream());
		out=new DataOutputStream(s.getOutputStream());
		index = 0; max_index = 0;
	}


	public boolean Login(String usr,String pwd) throws Exception{
		this.usr = usr;
		this.pwd = pwd;

		out.writeUTF("--login--");
		out.writeUTF(usr);
		out.writeUTF(pwd);

		if(in.readUTF().equals("login-done")){
			tfa = in.readUTF().substring(7);
			return true;
		}
		else return false;

	}

	public ArrayList<String> get_source_names()throws Exception{

		out.writeUTF("--names-sources--");
		int n = Integer.parseInt(in.readUTF());
		snames = new ArrayList<String>();
		for(int i=0; i<n ; i++){
			snames.add(in.readUTF());
		}
		return snames;
	}


	public ArrayList<String> get_target_names() throws Exception{

		out.writeUTF("--names-targets--");
		int n = Integer.parseInt(in.readUTF());
		tnames = new ArrayList<String>();
		for(int i=0; i<n ; i++){
			tnames.add(in.readUTF());
		}
		return tnames;
	}

	public void getSourceFile(int x) throws Exception{
		
		out.writeUTF("--fetch-source-file--"+x);
		String inn = in.readUTF();
		new Thread(new interfetcher(inn)).start();
	}

	//public String get

	public boolean getSentencesforFile(int x) throws Exception{
		sdj = new ArrayList<SDJ>();
		int n = Integer.parseInt(in.readUTF());
		for(int i=0; i<n ; i++){
			sdj.add(new SDJ(in.readUTF(),in.readUTF(),in.readUTF()));
		}
		index = 0; max_index = n-1;
		mdj = new ArrayList<MDJ> ();

		return true;
	}

	public String getNextQuestion() throws Exception{
		if(index <= max_index)
			return sdj.get(index).statement;
		else
			return "File came to an end";
	}

	public boolean setPresent(String s_id, String f_id, String usr, int marks){
		mdj.add(new MDJ(s_id, f_id, usr, marks));
		return true;
	}

	public boolean submitButton() throws Exception{
		out.writeUTF("--store-file-data--");
		out.writeUTF(String.valueOf(mdj.size()));
		for(int i=0; i<mdj.size(); i++){

			out.writeUTF(mdj.get(i).s_id);
			out.writeUTF(mdj.get(i).f_id);
			out.writeUTF(mdj.get(i).usr);
			out.writeUTF(String.valueOf(mdj.get(i).marks));
		}
		String inn = in.readUTF();
		if(inn.equals("updated-submit-button")) return true;
		else return false;
	}


}