package foot;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;



import db.ConnecDB;

public class Player {
	
	private String nom, prenom, info;
	private int id, age, poids;
	private List list_player = new ArrayList<Player>();

	public Player() {

	}

	public void enregistrer() {
		String req = "insert into player(nom,prenom,age,poids)values";
		req += "('" + this.nom + "','" + prenom + "','" + age + "','" + poids + "')";
		int resultat = ConnecDB.modify_DB(req);
		if (resultat != 0) {
			this.info = "Vous avez inserer";
		} else {
			this.info = "Perduuuuuuuuuuuuuuuuuuuuuuuuu.";
		}
	}

	public void supprimer() {
		String req_sup = " delete from player where idPlayer='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "Vous avez supprimer";
		} else {
			this.info = "Perduu.";
		}
	}

	public void modifier() {
		String req_mod = "update player set nom='" + this.nom + "',poids='"
				+ this.poids + "',prenom='" + this.prenom + "',age='" + this.age + "' where idPlayer='" + this.id + "'";
		int resultat = ConnecDB.modify_DB(req_mod);
		if (resultat > 0) {
			this.info = "Vous avez modifier";
		} else {
			this.info = "Perduuu.";
		}
	}

	public void menu() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("mercato.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public List getList_player() {
		String req = "select * from player";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			list_player.clear();
			
			try {
				while(res.next()){
					Player vis = new Player();
					vis.setId(res.getInt("idPlayer"));
					vis.setNom(res.getString("nom"));
					vis.setPrenom(res.getString("prenom"));
					vis.setAge(res.getInt("age"));
					vis.setPoids(res.getInt("poids"));
					
					list_player.add(vis);
					
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list_player;
	}

	public void setList_player(List list_player) {
		this.list_player = list_player;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	

	public String getInfo() {
		return info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}
	
	
}
