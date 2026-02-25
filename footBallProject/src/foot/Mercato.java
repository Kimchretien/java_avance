package foot;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class Mercato {
	
	
	public Mercato() {}

	public void EnvoyerVersPlayers() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("player.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void EnvoyerVersCoach() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("coach.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deconnection() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
