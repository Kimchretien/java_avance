package pharmacie;

import java.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.faces.context.FacesContext;

import db.ConnecDB;

public class Company {

	private String nom, experience, adresse, info;
	private int id, phone;

	private List companys = new ArrayList<Company>();

	public Company() {

	}

	public Company(String nom, String experience, String adresse, String info,
			int id, int phone) {
		this.nom = nom;
		this.experience = experience;
		this.adresse = adresse;
		this.info = info;
		this.id = id;
		this.phone = phone;
	}

	public void ajouter() {

		String requete = "insert into company(nom,experience,adresse,phone)values";
		requete += "('" + nom + "','" + experience + "','" + adresse + "','"
				+ phone + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}

	}

	public void modifier() {
		String requete = "update company set experience='" + experience
				+ "', adresse='" + adresse + "', phone='" + phone
				+ "', nom='" + this.nom + "' where id_comp='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void annuler() {

		String req_sup = " delete from company where id_comp='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getCompanys() {

		String req = "select * from company";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			companys.clear();

			try {
				while (res.next()) {
					Company md = new Company();
					md.setId(res.getInt("id_comp"));
					md.setNom(res.getString("nom"));
					md.setExperience(res.getString("experience"));
					md.setAdresse(res.getString("adresse"));
					md.setPhone(res.getInt("phone"));

					companys.add(md);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return companys;
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

	public void setCompanys(List companys) {
		this.companys = companys;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

}

