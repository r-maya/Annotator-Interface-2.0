import java.io.*;  
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ParseTargetFiles{
	public static void main(String args[]) throws Exception{

		Database db = new Database();
		//db.updateSID();
		
		File t_path = new File("/Users/RaghuRRB/Documents/annotator/event/target");
		File[] files = t_path.listFiles((dir, name) -> !name.equals(".DS_Store"));
		for(int i=0; i<files.length; i++){
			
			if(!db.checkTargetFile(String.valueOf(files[i]))){

						FileReader fr = new FileReader(files[i]);
						BufferedReader br = new BufferedReader(fr);
						String line = "",text = "";
						
						while ((line = br.readLine()) != null) {
					        	text = text+line;
					    }
					    BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
					    iterator.setText(text);
					    int j=0;
					    int start = iterator.first();
						for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
							j++;
						    db.addTargetSentence(String.valueOf(files[i])+j,String.valueOf(files[i]), text.substring(start,end));
						}
			}
		}

	}
}
