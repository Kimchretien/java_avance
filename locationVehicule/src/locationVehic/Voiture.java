package locationVehic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnecDB;

public class Voiture {
	
	private String marque, model, immatricule, info;
	
	int id;
	private List voitures = new ArrayList<Voiture>();
	
	public Voiture(){}
	
	
	public void Inserer() {
		String requete = "insert into voiture(marque,modele,immatriculation)values";
        requete += "('" + marque + "','" + model + "','" + immatricule + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
        String req_sup = " delete from voiture where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE voiture SET marque = '" + marque + "', modele = '" + model + "', immatriculation = '" + immatricule + "' WHERE id = '" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getVoitures() {
		String req = "select * from voiture";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			voitures.clear();

			try {
				while (res.next()) {
					Moto vis = new Moto();
					vis.setId(res.getInt("id"));
					vis.setMarque(res.getString("marque"));
					vis.setModel(res.getString("modele"));
					vis.setImmatricule(res.getString("immatriculation"));

					voitures.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return voitures;
	}




	public void setVoitures(List voitures) {
		this.voitures = voitures;
	}




	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImmatricule() {
		return immatricule;
	}

	public void setImmatricule(String immatricule) {
		this.immatricule = immatricule;
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
