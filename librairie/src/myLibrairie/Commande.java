package myLibrairie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import db.ConnecDB;

public class Commande {
	
	private String livre, client, date,info;
	
	int id;
	private List commandes = new ArrayList<Commande>();
	private List<SelectItem> livres;
	private List<SelectItem> clients;
	
	public Commande() {
		livres = new ArrayList<SelectItem>();
		clients = new ArrayList<SelectItem>();
	}
	
	public void Inserer() {
		String requete = "insert into commandes(nom_livre,nom_client,date_commande)values";
        requete += "('" + livre + "','" + client + "','" + date + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
        String req_sup = " delete from commandes where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE commandes SET nom_livre='" + livre + "', nom_client='" + client + "', date_commande='" + date + "' WHERE id='" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	

	public List getCommandes() {
		
		String req = "select * from commandes";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			commandes.clear();

			try {
				while (res.next()) {
					Commande vis = new Commande();
					vis.setId(res.getInt("id"));
					vis.setClient(res.getString("nom_client"));
					vis.setLivre(res.getString("nom_livre"));
					vis.setDate(res.getString("date_commande"));

					commandes.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return commandes;
	}


	

	public List<SelectItem> getClients() {
		
		if (clients.isEmpty()) { // Charger uniquement si la liste est vide
            String req = "SELECT id, nom FROM client";
            ResultSet res = ConnecDB.Interroger_DB(req);
            try {
//                stations.add(new SelectItem("", "-- Sélectionnez --"));
                while (res.next()) {
                    clients.add(new SelectItem(res.getInt("id"), res.getString("nom")));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		return clients;
	}


	

	public List<SelectItem> getLivres() {
		
		if (livres.isEmpty()) { // Charger uniquement si la liste est vide
            String req = "SELECT id, nom FROM livres";
            ResultSet res = ConnecDB.Interroger_DB(req);
            try {
//                stations.add(new SelectItem("", "-- Sélectionnez --"));
                while (res.next()) {
                    livres.add(new SelectItem(res.getInt("id"), res.getString("nom")));
                }
                res.close();
            } catch (SQLException e) {
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

	public void setLivres(List<SelectItem> livres) {
		this.livres = livres;
	}



	public void setClients(List<SelectItem> clients) {
		this.clients = clients;
	}



	public void setCommandes(List commandes) {
		this.commandes = commandes;
	}


	

	public String getLivre() {
		return livre;
	}

	public void setLivre(String livre) {
		this.livre = livre;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
