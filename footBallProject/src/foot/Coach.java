package foot;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


import db.ConnecDB;

public class Coach {

	private String nom, prenom, licence, info;
	
	private int age, id;
	private List list_coach = new ArrayList<Coach>();

	public Coach() {

	}
	
	public void enregistrer() {
		String req = "insert into coach(nom,prenom,age,licence)values";
		req += "('" + this.nom + "','" + prenom + "','" + age + "','" + licence + "')";
		int resultat = ConnecDB.modify_DB(req);
		if (resultat != 0) {
			this.info = "Vous avez inserer un coach";
		} else {
			this.info = "Perduuuuuuuuuuuuuuuuuuuuuuuuu.";
		}
	}
	
	public void supprimer() {
		String req_sup = " delete from coach where idCoach='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "Vous avez supprimer un Coach";
		} else {
			this.info = "Perduuuuuuuuuuuuuuuuuuuuuuuuu.";
		}
	}
	
	public void modifier() {
		String req_mod = "update coach set licence='" + this.licence + "',nom='"
				+ this.nom + "',prenom='" + this.prenom + "',age='" + this.age + "' where idCoach='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_mod);
		if (resultat > 0) {
			this.info = "Vous avez modifier un Coach";
		} else {
			this.info = "Perduuuuuuuuuuuuuuuuuuuuuuuuu.";
		}
	}
	
public List getList_coach() {
		
		String req = "select * from coach";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			list_coach.clear();
			
			try {
				while(res.next()){
					Coach vis = new Coach();
					vis.setId(res.getInt("idCoach"));
					vis.setNom(res.getString("nom"));
					vis.setPrenom(res.getString("prenom"));
					vis.setAge(res.getInt("age"));
					vis.setLicence(res.getString("licence"));
					
					list_coach.add(vis);
					
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list_coach;
	}

	public void setList_coach(List list_coach) {
		this.list_coach = list_coach;
	}
	
	public void menu() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("mercato.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
