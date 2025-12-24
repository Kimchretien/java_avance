package db;

import java.sql.*;

public class ConnecDB {

	
	private static Connection con;
	
	private static String rapport;
	
	public static String getRapport() {
		return rapport;
	}
	public static int modify_DB(String requete){
		
		Statement st =null;
		   ResultSet resultat=null;
		   if(con==null)
			   {getInstance();
			      if(!rapport.equalsIgnoreCase("succes"))
			      {
			    	 rapport="Pas de connexion obtenue!!!";
			    	 return -1;
			      }
			   }
		try {
			st=con.createStatement();
			
			int nbenr=st.executeUpdate(requete);
			return nbenr;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return -1;
	}
	
   public static ResultSet Interroger_DB(String req){
	   
	   Statement st =null;
	   ResultSet resultat=null;
	   if(con==null)
		   {getInstance();
		      if(!rapport.equalsIgnoreCase("succes"))
		      {
		    	 rapport="Pas de connexion obtenue!!!";
		    	 return null;
		      }
		   }
	 //il ya de connexion
	   try {
		   
		st=con.createStatement();
		resultat=st.executeQuery(req);
		 
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   
	   return resultat;
   }
	

	private static void getInstance(){
		
		try {
			

			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque", "root", "");
			rapport="succes";
			System.out.println("Connexin est etablie");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//Base 'alimentationdbv' inconnue
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Operation faite avec successfully 3

	}
	
	
	public static void main(String[] args) {
		getInstance();
		
		
	}

	/*
	 * 1,Haricots,25,kg
		2,Petits Poids,50,kg
		3,Riz,100,kg


	 */
}