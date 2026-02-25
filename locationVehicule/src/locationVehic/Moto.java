package locationVehic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnecDB;

public class Moto {
	
	private String marque, model, immatricule, info;
	
	int id;
	private List motos = new ArrayList<Moto>();

	public Moto() {}
	
	
	public void Inserer() {
		String requete = "insert into moto(marque,modele,immatriculation)values";
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
		String req_sup = " delete from moto where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE moto SET marque = '" + marque + "', modele = '" + model + "', immatriculation = '" + immatricule + "' WHERE id = '" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}
	
	

	public List getMotos() {
		
		String req = "select * from moto";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			motos.clear();

			try {
				while (res.next()) {
					Moto vis = new Moto();
					vis.setId(res.getInt("id"));
					vis.setMarque(res.getString("marque"));
					vis.setModel(res.getString("modele"));
					vis.setImmatricule(res.getString("immatriculation"));

					motos.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return motos;
	}



	public void setMotos(List motos) {
		this.motos = motos;
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
