package hospital;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;;

public class Pharmacie {
	
private String nom,dose,date_creation,date_expiration,info;
	
	int id,paquet;
	private List medicaments = new ArrayList<Pharmacie>();
	
	public Pharmacie(){
			
		}
	
	public Pharmacie(String nom, String dose, String date_creation,
			String date_expiration, int id, int paquet) {
		this.nom = nom;
		this.dose = dose;
		this.date_creation = date_creation;
		this.date_expiration = date_expiration;
		this.id = id;
		this.paquet = paquet;
	}
	
	
	public void acheter(){
		String requete = "insert into pharmacie(nom,dose,date_creation,date_expiration,paquet)values";
		requete += "('" + this.nom + "','" + this.dose + "','" + this.date_creation + "','" + this.date_expiration
				+ "','" + this.paquet + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";;
		} else {
			this.info = "echouer";
		}
	}
	
	public void annuler() {
		String req_sup = " delete from pharmacie where id_pharmacie='" + this.id+ "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	public void changer() {
		String requete = "update pharmacie set paquet='" + this.paquet +
				"',nom='" + this.nom +
				"',dose='" + this.dose +
				"',date_creation='" + this.date_creation +
				"',date_expiration='" + this.date_expiration +
				"'where id_pharmacie='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	

	public List getMedicaments() {
		
		String req = "select * from pharmacie";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			medicaments.clear();
			
			try {
				while(res.next()){
					Pharmacie vis = new Pharmacie();
					vis.setId(res.getInt("id_pharmacie"));
					vis.setNom(res.getString("nom"));
					vis.setDose(res.getString("dose"));
					vis.setDate_creation(res.getString("date_creation"));
					vis.setDate_expiration(res.getString("date_expiration"));
					vis.setPaquet(res.getInt("paquet"));
					
					medicaments.add(vis);
					
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return medicaments;
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

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public String getDate_expiration() {
		return date_expiration;
	}

	public void setDate_expiration(String date_expiration) {
		this.date_expiration = date_expiration;
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

	public int getPaquet() {
		return paquet;
	}

	public void setPaquet(int paquet) {
		this.paquet = paquet;
	}


	public void setMedicaments(List medicaments) {
		this.medicaments = medicaments;
	}

	
}
