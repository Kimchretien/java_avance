package biblio;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;

public class Employe {
	private String nom, prenom, identite, adresse, fonction, info;
	
	int id, tel;
	private List employees = new ArrayList<Employe>();

	public Employe() {

	}

	public void Inserer() {
		String requete = "insert into employe(nom,prenom,identite,telephone,adresse,fonction)values";
		requete += "('" + nom + "','" + prenom + "','" + identite + "','" + tel
				+ "','" + adresse + "','" + fonction + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
		String req_sup = " delete from employe where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE employe SET " + "nom='" + nom + "', "
				+ "prenom='" + prenom + "', " + "identite='" + identite + "', "
				+ "telephone='" + tel + "', " + "adresse='" + adresse + "', "
				+ "fonction='" + fonction + "' " + "WHERE id='" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getEmployees() {

		String req = "select * from employe";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			employees.clear();

			try {
				while (res.next()) {
					Employe vis = new Employe();
					vis.setId(res.getInt("id"));
					vis.setTel(res.getInt("telephone"));
					vis.setNom(res.getString("nom"));
					vis.setPrenom(res.getString("prenom"));
					vis.setAdresse(res.getString("adresse"));
					vis.setFonction(res.getString("fonction"));
					vis.setIdentite(res.getString("identite"));

					employees.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return employees;
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

	public String getIdentite() {
		return identite;
	}

	public void setIdentite(String identite) {
		this.identite = identite;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
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

	public void setEmployees(List employees) {
		this.employees = employees;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

}
