package station;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import db.ConnecDB;


public class Carburant {
	
	private String carb, cap, quant,stat, nom, emplacement, info;
	
	private List<SelectItem> stations;
	
	
	int id;
	private List carburants = new ArrayList<Carburant>();
	
	public Carburant(){
		stations = new ArrayList<SelectItem>();
	}
	
	public void Inserer() {
		String requete = "insert into carburants(type_carburant,capacite,quantite_stockee,station_nom)values";
        requete += "('" + carb + "','" + cap + "','" + quant + "','" + stat
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
		String req_sup = " delete from carburants where id_carburant ='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	
	public void modifier() {
		String requete = "update carburants set type_carburant='" + carb
        + "', capacite='" + cap
        + "', quantite_stockee='" + quant + "', station_nom='" + stat + "' where id_carburant='" + id + "'";

		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	
	
	
	
	

	public List<SelectItem> getStations() {
        if (stations.isEmpty()) { // Charger uniquement si la liste est vide
            String req = "SELECT id_station, nom FROM stations";
            ResultSet res = ConnecDB.Interroger_DB(req);
            try {
//                stations.add(new SelectItem("", "-- Sélectionnez --"));
                while (res.next()) {
                    stations.add(new SelectItem(res.getInt("id_station"), res.getString("nom")));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stations;
    }

	public void setStations(List<SelectItem> stations) {
		this.stations = stations;
	}

	public List getCarburants() {
		
		String req = "select * from carburants";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			carburants.clear();

			try {
				while (res.next()) {
					Carburant vis = new Carburant();
					vis.setId(res.getInt("id_carburant"));
					vis.setCarb(res.getString("type_carburant"));
					vis.setCap(res.getString("capacite"));
					vis.setQuant(res.getString("quantite_stockee"));
					vis.setStat(res.getString("station_nom"));

					carburants.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return carburants;
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

	public void setCarburants(List carburants) {
		this.carburants = carburants;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public String getCarb() {
		return carb;
	}

	public void setCarb(String carb) {
		this.carb = carb;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getQuant() {
		return quant;
	}

	public void setQuant(String quant) {
		this.quant = quant;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
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
