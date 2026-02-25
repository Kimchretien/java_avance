package market;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;

public class Client {

	private String nom, prenom, identite, info;

	int id, tel;
	private List clients = new ArrayList<Client>();

	public Client() {

	}

	public void Inserer() {
		String requete = "insert into client(nom,prenom,identite,telephone)values";
		requete += "('" + nom + "','" + prenom + "','" + identite + "','" + tel
				+ "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
		String req_sup = " delete from client where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE client SET " + "nom='" + nom + "', "
				+ "prenom='" + prenom + "', " + "identite='" + identite + "', "
				+ "telephone='" + tel + "' " + "WHERE id='" + id + "'";

		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getClients() {

		String req = "select * from client";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			clients.clear();

			try {
				while (res.next()) {
					Client vis = new Client();
					vis.setId(res.getInt("id"));
					vis.setNom(res.getString("nom"));
					vis.setPrenom(res.getString("prenom"));
					vis.setIdentite(res.getString("identite"));
					vis.setTel(res.getInt("telephone"));

					clients.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return clients;
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

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}


	public void setClients(List clients) {
		this.clients = clients;
	}

}
