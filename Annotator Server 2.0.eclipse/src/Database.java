import java.sql.*;
import java.util.ArrayList;
import java.io.File;

public class Database {
	Connection con;
	Connection con1;
	PreparedStatement pst;
	ResultSet rs,rs1;

	Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:8889/annotator_sample", "root", "rootroot");
			pst = con.prepareStatement("select * from users where username=? and password=?");
			//con1 = DriverManager.getConnection("jdbc:mysql://localhost:8889/annotator_sample", "root", "root");
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// ip:username,password
	// return boolean

	/*
	 * public boolean checkLogin (String uname, String pwd){
	 * System.out.println(uname+"======"+pwd); return true; }
	 */



////////////////////////////
/////set Annotation list////
////////////////////////////
	public boolean setAnnotatedList(String usr, File file){

		try{	
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/annotator_sample", "root", "root");
				String f = String.valueOf(file);
				PreparedStatement pp = con.prepareStatement("select * from sentences_data where id = ? and usr = ?");
				pp.setString(1, f);
				pp.setString(2, usr);
				rs = pp.executeQuery();
				if(rs.next())
					return true;
				else return false;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return false;
	}


////////////////////////////////
/////check login credentials////
////////////////////////////////

	public Boolean checkLogin(String uname, String pwd) {

		try {

			pst.setString(1, uname); // this replaces the 1st "?" in the query
										// for username
			pst.setString(2, pwd); // this replaces the 2st "?" in the query for
									// password
			// executes the prepared statement
			rs = pst.executeQuery();
			if (rs.next()) {
				// TRUE iff the query founds any corresponding data
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error while validating" + e);
			return false;
		}
	}

	public boolean checkTargetFile(String f_id){

		try{
				PreparedStatement pf_id =  con.prepareStatement("select * from sentences_data where fid = ?");
				rs = pf_id.executeQuery();
				if(rs.next())
					return true;
				else return false;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	public boolean addTargetSentence(String f_id, String s_id, String sentence){

		try{
			PreparedStatement pf_id =  con.prepareStatement("insert into sentences_data (fid, sid, statement) values (?, ?, ?)");
			pf_id.setString(1,f_id);
			pf_id.setString(2,s_id);
			pf_id.setString(3,sentence);
			int flag = pf_id.executeUpdate();
			return true;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	public boolean checkAnnotatedList(String usr, String t_file){
		try{

			PreparedStatement pf_id =  con.prepareStatement("select * from marked_data where usr = ? and f_id = ?");
			pf_id.setString(1,usr);
			pf_id.setString(2,t_file);
			rs = pf_id.executeQuery();
			return true;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	public ArrayList<SDJ> getSentences(String fid){
		ArrayList<SDJ>sdj = new ArrayList<SDJ>();
		try{
			PreparedStatement ss = con.prepareStatement("select * from sentences_data where fid = ?");
			ss.setString(1,fid);
			rs = ss.executeQuery();
			
			while(rs.next()){
				sdj.add(new SDJ(rs.getString(1),rs.getString(2),rs.getString(3)));
			}
			return sdj;
		}
		catch(Exception e){
			System.out.println();
		}
		return sdj;
	}

////////////////////////////////
/////////mark a statement///////
////////////////////////////////
	public boolean markSentence(ArrayList<MDJ> mdj){
		try{
			PreparedStatement pmarks =  con.prepareStatement("INSERT INTO sentences_data (sid, fid, usr, marks) VALUES (?,?,?,?)");
			
			for(int i=0; i<mdj.size(); i++){
				pmarks.setString(1,mdj.get(i).s_id);
				pmarks.setString(2,mdj.get(i).f_id);
				pmarks.setString(3,mdj.get(i).usr);
				pmarks.setString(4,String.valueOf(mdj.get(i).marks));
				int flag = pmarks.executeUpdate();
			}
			
		}
		catch (Exception e){
			System.out.println(e);
		}
		return true;
	}


///////check Username/////
	public boolean checkUsernameExists(String usr){
		try{
			PreparedStatement p =  con.prepareStatement("select * from users where username=?");
			p.setString(1, usr);
			rs = p.executeQuery();
			if(rs.next())
				return true;
			else return false;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

///////set password/////
	public boolean setPassword(String usr, String pwd){
		try{
			if(checkUsernameExists(usr)){
				PreparedStatement p =  con.prepareStatement("update users set password =? where username = ?");
				p.setString(1, pwd);
				p.setString(2, usr);
				rs = p.executeQuery();
				return true;
			}
			else
				return false;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	
	/*
	 * public static void main(String [] args){ Database db = new Database();
	 * System.out.println(db.checkLogin("raghu", "ram")); }
	 */
}