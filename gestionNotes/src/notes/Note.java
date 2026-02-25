package notes;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import db.ConnecDB;


public class Note {

	
	private String etudiant, matiere, info;
	
	int id;
	double note;
	private List lesNotes = new ArrayList<Note>();
	private List<SelectItem> etudiants;
	
	public Note(){
		etudiants = new ArrayList<SelectItem>();
	}
	
	public void Inserer() {
		String requete = "insert into notes(nom_etudiant,matiere,note)values";
        requete += "('" + etudiant + "','" + matiere + "','" + note + "')";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat != 0) {
			this.info = "reussi";
			;
		} else {
			this.info = "echouer";
		}
	}

	public void annuler() {
		String req_sup = " delete from notes where id='" + id + "'";
		int resultat = ConnecDB.modify_DB(req_sup);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public void modifier() {
		String requete = "UPDATE notes SET nom_etudiant='" + etudiant + "', matiere='" + matiere + "', note='" + note + "'WHERE id='" + id + "'";
		int resultat = ConnecDB.modify_DB(requete);
		if (resultat > 0) {
			this.info = "reussi";
		} else {
			this.info = "echouer.";
		}
	}

	public List getLesNotes() {
		
		String req = "select * from notes";
		ResultSet res = ConnecDB.Interroger_DB(req);
		if (res != null) {
			lesNotes.clear();

			try {
				while (res.next()) {
					Note vis = new Note();
					vis.setId(res.getInt("id"));
					vis.setEtudiant(res.getString("nom_etudiant"));
					vis.setMatiere(res.getString("matiere"));
					vis.setNote(res.getDouble("note"));

					lesNotes.add(vis);

				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lesNotes;
	}

	

	public List<SelectItem> getEtudiants() {
		
		if (etudiants.isEmpty()) { // Charger uniquement si la liste est vide
            String req = "SELECT nom FROM etudiants";
            ResultSet res = ConnecDB.Interroger_DB(req);
            try {
//                stations.add(new SelectItem("", "-- Sélectionnez --"));
                while (res.next()) {
                    etudiants.add(new SelectItem(res.getString("nom")));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		return etudiants;
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

	public void setEtudiants(List<SelectItem> etudiants) {
		this.etudiants = etudiants;
	}



	public void setLesNotes(List lesNotes) {
		this.lesNotes = lesNotes;
	}



	public String getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
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

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}
	
	

	
}
