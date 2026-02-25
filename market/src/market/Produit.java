package market;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;

public class Produit {
	
	private String nom,date, info;
	private int id, prix, quantite;
	
	private List produits = new ArrayList<Produit>();
	
	public Produit(){}
	
	
	public void Inserer() {
		String requete = "insert into produit(nom,date,prix,quantite)values";
        requete += "('" + nom + "','" + date + "','" + prix + "','" + quantite + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
		String req_sup = " delete from produit where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "update produit set date='" + date
        + "', prix='" + prix
        + "', quantite='" + quantite + "', nom ='" + nom
        + "' where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	

	public List getProduits() {
		
		String req = "select * from produit";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			produits.clear();

			try {
				while (res.next()) {
					Produit vis = new Produit();
					vis.setId(res.getInt("id"));
					vis.setNom(res.getString("nom"));
					vis.setDate(res.getString("date"));
					vis.setPrix(res.getInt("prix"));
					vis.setQuantite(res.getInt("quantite"));

					produits.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return produits;
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

	public void setProduits(List produits) {
		this.produits = produits;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	
	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	
	

}
