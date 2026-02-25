package inscriptionetudiant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


import db.ConnecDB;


public class Cours {
	
	private String intitule, code, credit, info;
	
	int id;
	private List cours = new ArrayList<Cours>();
	
	public Cours(){}
	
	public void Inserer() {
		 String requete = "insert into cours(intitule,code_cours,credit)values";
	        requete += "('" + intitule + "','" + code + "','" + credit + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
		String req_sup = " delete from cours where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE cours SET intitule='" + intitule + "', code_cours='" + code + "', credit='" + credit + "' WHERE id='" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getCours() {
		
		String req = "select * from cours";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			cours.clear();

			try {
				while (res.next()) {
					Cours vis = new Cours();
					vis.setId(res.getInt("id"));
					vis.setIntitule(res.getString("intitule"));
					vis.setCode(res.getString("code_cours"));
					vis.setCredit(res.getString("credit"));

					cours.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return cours;
	}

	public void menu() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("menu.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void setCours(List cours) {
		this.cours = cours;
	}



	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
