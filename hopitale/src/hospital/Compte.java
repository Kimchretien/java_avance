package hospital;

import java.io.IOException;

import javax.faces.context.FacesContext;

import db.ConnecDB;

public class Compte {
	private String nom, password, info;
	
	public Compte(){}

	public String getNom() {
		return nom;
	}
	
	public void register(){
		String req_login = "insert into login(name,password)values";
        req_login += "('" + nom + "','" + password + "')";
        int resultat = ConnecDB.modify_DB(req_login);
	    if(resultat>0)
	    	this.info="la personne a ete enregistrer!! ";
	    else this.info="Désolé, la personne n'a pas ete enregistrer!!!";
	}
	
	
	public void login(){
		FacesContext face = FacesContext.getCurrentInstance();
		try {
			face.getExternalContext().redirect("login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	

}