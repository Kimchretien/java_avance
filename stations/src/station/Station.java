package station;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import db.ConnecDB;


public class Station {
	
	private String nom, emplacement, info;
	
	int id;
	private List stations = new ArrayList<Station>();
	
	public Station(){}

	
	public void Inserer() {
		String requete = "insert into stations(nom,emplacement )values";
        requete += "('" + nom + "','" + emplacement + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
		String req_sup = " delete from stations where id_station='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE stations SET nom='" + nom + "', emplacement='" + emplacement + "' WHERE id_station=" + id;
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}


	public List getStations() {
		
		String req = "select * from stations";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			stations.clear();

			try {
				while (res.next()) {
					Station vis = new Station();
					vis.setId(res.getInt("id_station"));
					vis.setNom(res.getString("nom"));
					vis.setEmplacement(res.getString("emplacement"));

					stations.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stations;
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


	public void setStations(List stations) {
		this.stations = stations;
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
