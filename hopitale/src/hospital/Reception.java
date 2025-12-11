package hospital;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;

;

public class Reception {

	private String nom, adresse, fonction, info;
	private int numero, id;
	private List employes = new ArrayList<Reception>();

	public Reception() {

	}

	public void ajouter() {
		String requete = "insert into employe(nom,fonction,adresse,tel)values";
		requete += "('" + this.nom + "','" + this.fonction + "','"
				+ this.adresse + "','" + this.numero + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}

	}

	public void enlever() {
		String req_sup = " delete from employe where id_employe='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void changer() {
		String requete = "update employe set fonction='" + this.fonction
				+ "', nom='" + this.nom +
				"', fonction='" + this.fonction +
				"' where id_employe='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getEmployes() {

		String req = "select * from employe";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			employes.clear();

			try {
				while (res.next()) {
					Reception vis = new Reception();
					vis.setId(res.getInt("id_employe"));
					vis.setNom(res.getString("nom"));
					vis.setFonction(res.getString("fonction"));
					vis.setAdresse(res.getString("adresse"));
					vis.setNumero(res.getInt("tel"));

					employes.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return employes;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmployes(List employes) {
		this.employes = employes;
	}

}
