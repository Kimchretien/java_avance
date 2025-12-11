package hospital;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;

public class RendezVous {
	private String nom_docteur,nom_malade,specialist,date,chambre,info;
	int id;
	
	private List les_rendez_vous = new ArrayList<RendezVous>();
	
	public RendezVous(){
		
	}

	
	
	public RendezVous(String nom_docteur, String nom_malade, String specialist,
			String date, String chambre, String info, int id) {
		this.nom_docteur = nom_docteur;
		this.nom_malade = nom_malade;
		this.specialist = specialist;
		this.date = date;
		this.chambre = chambre;
		this.info = info;
		this.id = id;
	}



	public void demander(){
		String requete = "insert into rendez_vous(nom_docteur,specialist,nom_malade,chambre,date)values";
		requete += "('" + this.nom_docteur + "','" + this.specialist + "','" + this.nom_malade + "','" + this.chambre
				+ "','" + this.date + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";;
		} else {
			this.info = "echouer";
		}
	}

	
	public void annuler() {
		String req_sup = " delete from rendez_vous where id_rendez_vous='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	public void changer() {
		String requete = "update rendez_vous set nom_docteur='" + this.nom_docteur +
				"', specialist='" + this.specialist +
				"', chambre='" + this.chambre +"', date='" + this.date +
				"' where id_rendez_vous='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	
	
	
	public List getLes_rendez_vous() {
		
		String req = "select * from rendez_vous";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			les_rendez_vous.clear();
			
			try {
				while(res.next()){
					RendezVous vis = new RendezVous();
					vis.setId(res.getInt("id_rendez_vous"));
					vis.setNom_docteur(res.getString("nom_docteur"));
					vis.setSpecialist(res.getString("specialist"));
					vis.setNom_malade(res.getString("nom_malade"));
					vis.setChambre(res.getString("chambre"));
					vis.setDate(res.getString("date"));
					
					les_rendez_vous.add(vis);
					
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return les_rendez_vous;
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


	public void setLes_rendez_vous(List les_rendez_vous) {
		this.les_rendez_vous = les_rendez_vous;
	}



	public String getNom_docteur() {
		return nom_docteur;
	}

	public void setNom_docteur(String nom_docteur) {
		this.nom_docteur = nom_docteur;
	}

	public String getNom_malade() {
		return nom_malade;
	}

	public void setNom_malade(String nom_malade) {
		this.nom_malade = nom_malade;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChambre() {
		return chambre;
	}

	public void setChambre(String chambre) {
		this.chambre = chambre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
