package pharmacie;

import db.ConnecDB;

import java.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.faces.context.FacesContext;


public class Agent {
	
	private String nom,adresse,info;
	private int id,phone,age;
	
	private List agents = new ArrayList<Agent>();
	
	public Agent(){
		
	}

	public Agent(String nom, String adresse, String info, int id, int phone,
			int age) {
		this.nom = nom;
		this.adresse = adresse;
		this.info = info;
		this.id = id;
		this.phone = phone;
		this.age = age;
	}

	
	public void enregistrer(){
		
		String requete = "insert into agent(nom,age,phone,adresse)values";
        requete += "('" + nom + "','" + age + "','" + phone + "','" + adresse
                + "')";
        int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
        
	}
	
	public void supprimer() {

		String req_sup = " delete from agent where id_agent='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	public void modifier() {
		String requete = "update agent set age='" + age
        + "', phone='" + phone
        + "', adresse='" + adresse + "', nom='" + this.nom + "' where id_agent='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	public List getAgents() {
		
		String req = "select * from agent";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			agents.clear();

			try {
				while (res.next()) {
					Agent agen = new Agent();
					agen.setId(res.getInt("id_agent"));
					agen.setNom(res.getString("nom"));
					agen.setAge(res.getInt("age"));
					agen.setPhone(res.getInt("phone"));
					agen.setAdresse(res.getString("adresse"));
					

					agents.add(agen);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return agents;
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


	public void setAgents(List agents) {
		this.agents = agents;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}