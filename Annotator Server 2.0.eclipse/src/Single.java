import java.io.*;  
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Single implements Runnable{
	

	DataOutputStream out;
	DataInputStream in;
	Database db = new Database();
	Socket s;
	String usr,pwd;
	int id;
	int loggedin = 0;
	String s_path, t_path;
	File sourceFolder, targetFolder;
	File[] sourceFiles, targetFiles;
	int s_n, t_n;
	boolean targetFileAnnotated[];
	String tfa = "";
	
	public Single(Socket s){
		
				this.s = s;
				s_path = "/Users/RaghuRRB/Documents/annotator/event/source";
				t_path = "/Users/RaghuRRB/Documents/annotator/event/target";
				sourceFolder = new File(s_path);
				sourceFiles = sourceFolder.listFiles((dir, name) -> !name.equals(".DS_Store"));
				s_n = sourceFiles.length;
				targetFolder = new File(t_path);
				targetFiles = targetFolder.listFiles((dir, name) -> !name.equals(".DS_Store"));
				t_n = targetFiles.length;
				tfa = "";
				//targetFileAnnotated = new boolean[t_n];

				
	}

///////////////////
//method to login//
///////////////////

				public void loginMethod() throws Exception{
					
					usr = in.readUTF();
					pwd = in.readUTF();
					if(db.checkLogin(usr,pwd)){
						loggedin = 1;
					}
					setAnnotatedList();
					//out.writeUTF()
				}


///////////////////////////////////////////////
///method to set annotation for target files///
///////////////////////////////////////////////

				public void setAnnotatedList(){
					tfa = "";
					for(int i=0; i<targetFiles.length; i++)
						if(db.checkAnnotatedList(usr,String.valueOf(targetFiles[i]))) tfa = tfa+"1";
						else tfa = tfa+"0";
				}


//////////////////////////////////////
///method to fetch a file to String///
//////////////////////////////////////

				public String fetchFile(String folder, int x) throws Exception{
						FileReader fr = new FileReader("Users");
						if(folder.equals(String.valueOf(sourceFolder))){
							fr = new FileReader(folder+"/"+String.valueOf(sourceFiles[x]));
						}
						else if(folder.equals(String.valueOf(targetFolder))){
							fr = new FileReader(folder+"/"+String.valueOf(targetFiles[x]));
						}
						else{
							return "----error-409----";
						}
						
						BufferedReader br = new BufferedReader(fr);
						String line = "",text = "";
						
						while ((line = br.readLine()) != null) {
					        	text = text+line;
					    }
					    return text;
				}



///////////////////
///method to run///
///////////////////

	public void run(){

		try{
				out = new DataOutputStream(s.getOutputStream());
				in = new DataInputStream(s.getInputStream());
				String input;
				input = in.readUTF();

	
	//////////input parsing////////
				
				while(!input.equals("---end-program---")){
				
				//case for login
					if(input.equals("--login--")){
						while(loggedin != 0) loginMethod();
						if(loggedin != 0){
						 	out.writeUTF("login-done");
						 	out.writeUTF("--tfa--"+tfa);
						}
						else out.writeUTF("login-failed");
					}

				//case for sending source files names
					else if(input.equals("--names-sources--")){
						out.writeUTF(String.valueOf(s_n));
						for(int i=0; i<s_n; i++){
							out.writeUTF(String.valueOf(sourceFiles[i]));
						}
					}

				//case for sending target files names
					else if(input.equals("--names-targets--")){
						out.writeUTF(String.valueOf(t_n));
						for(int i=0; i<t_n; i++){
							out.writeUTF(String.valueOf(targetFiles[i]));
						}
					}

				//case for fetching particular source file
					else if(input.startsWith("--fetch-source-file--")){
						out.writeUTF(fetchFile(s_path, Integer.parseInt(input.substring(21))));
					}

				//case for sending files list unannotated
					else if(input.startsWith("--fetch-target-files--")){
						input = in.readUTF();
						ArrayList<SDJ> sdj = db.getSentences(t_path+"/"+input.substring(22));
						out.writeUTF(String.valueOf(sdj.size()));
						for(int j=0; j<sdj.size(); j++){
							//out.writeUTF(String.valueOf(j));
							out.writeUTF(sdj.get(j).s_id);
							out.writeUTF(sdj.get(j).f_id);
							out.writeUTF(sdj.get(j).statement);
						}
					}

				//case for updating incoming annotated files as SDJ arraylist
					else if(input.equals("--store-file-data--")){
						input = in.readUTF();
						ArrayList<MDJ> mdj = new ArrayList<MDJ>();
						for(int i=0; i<input.length(); i++){
							mdj.add(new MDJ(in.readUTF(), in.readUTF(), in.readUTF(), Integer.parseInt(in.readUTF())));
						}
						if(db.markSentence(mdj))
							out.writeUTF("---successfull-updated---");
						else
							out.writeUTF("----error----");					}


				}
		}

		catch (Exception e){
			System.out.println(e);
		}
	}
}