package pharmacie;

import db.ConnecDB;
import java.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.faces.context.FacesContext;

public class Medicament {

	private String nom, dfab, dexp, info;

	int id, prix, quantite;
	private List medis = new ArrayList<Medicament>();

	public Medicament() {

	}

	public Medicament(String nom, String dfab, String dexp, String info,
			int id, int prix, int quantite) {
		this.nom = nom;
		this.dfab = dfab;
		this.dexp = dexp;
		this.info = info;
		this.id = id;
		this.prix = prix;
		this.quantite = quantite;
	}

	public void ajouter() {
		String requete = "insert into medicament(nom,prix,quantite,d_fab,d_exp)values";
		requete += "('" + nom + "','" + prix + "','" + quantite + "','" + dfab
				+ "','" + dexp + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void supprimer() {

		String req_sup = " delete from medicament where id_med='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "update medicament set d_exp='" + dexp + "', prix='"
				+ prix + "', quantite='" + quantite + "', d_fab ='" + dfab
				+ "', nom='" + this.nom + "' where id_med='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getMedis() {

		String req = "select * from medicament";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			medis.clear();

			try {
				while (res.next()) {
					Medicament md = new Medicament();
					md.setId(res.getInt("id_med"));
					md.setNom(res.getString("nom"));
					md.setQuantite(res.getInt("quantite"));
					md.setDfab(res.getString("d_fab"));
					md.setDexp(res.getString("d_exp"));
					md.setPrix(res.getInt("prix"));

					medis.add(md);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return medis;
	}
	
	public void menu() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("liste.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setMedis(List medis) {
		this.medis = medis;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDfab() {
		return dfab;
	}

	public void setDfab(String dfab) {
		this.dfab = dfab;
	}

	public String getDexp() {
		return dexp;
	}

	public void setDexp(String dexp) {
		this.dexp = dexp;
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

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}