package inscriptionetudiant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;

public class Etudiant {
	
	private String nom, prenom, date, tel, email, info;
	
	int id;
	private List etudiants = new ArrayList<Etudiant>();

	public Etudiant(){}
	
	public void Inserer() {
		String requete = "insert into etudiants(nom,prenom,date_naissance,email,telephone)values";
        requete += "('" + nom + "','" + prenom + "','" + date + "','" + email + "','" + tel + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
		String req_sup = " delete from etudiants where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE etudiants SET nom='" + nom + "', prenom='" + prenom + "', date_naissance='" + date + "', email='" + email + "', telephone='" + tel + "' WHERE id='" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getEtudiants() {
		
		String req = "select * from etudiants";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			etudiants.clear();

			try {
				while (res.next()) {
					Etudiant vis = new Etudiant();
					vis.setId(res.getInt("id"));
					vis.setNom(res.getString("nom"));
					vis.setPrenom(res.getString("prenom"));
					vis.setDate(res.getString("date_naissance"));
					vis.setEmail(res.getString("email"));
					vis.setTel(res.getString("telephone"));

					etudiants.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return etudiants;
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

	public void setEtudiants(List etudiants) {
		this.etudiants = etudiants;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
