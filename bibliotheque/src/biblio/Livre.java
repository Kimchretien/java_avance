package biblio;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;

public class Livre {
	

	private String nom, auteur, genre, disponible, info;
	
	int id;
	private List livres = new ArrayList<Livre>();
	
	public Livre() {}
	
	public void Inserer() {
		String requete = "insert into livre(nom,auteur,genre,disponible)values";
        requete += "('" + nom + "','" + auteur + "','" + genre + "','" + disponible + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
		String req_sup = " delete from livre where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE livre SET " +
        "nom='" + nom + "', " +
        "auteur='" + auteur + "', " +
        "genre='" + genre + "', " +
        "disponible='" + disponible + "' " +
        "WHERE id='" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getLivres() {
		
		String req = "select * from livre";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			livres.clear();

			try {
				while (res.next()) {
					Livre vis = new Livre();
					vis.setId(res.getInt("id"));
					vis.setNom(res.getString("nom"));
					vis.setAuteur(res.getString("auteur"));
					vis.setGenre(res.getString("genre"));
					vis.setDisponible(res.getString("disponible"));

					livres.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return livres;
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

	public void setLivres(List livres) {
		this.livres = livres;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
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
