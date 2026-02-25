package station;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class Menu {

	public Menu() {

	}

	public void station() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("station.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carburant() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("carburant.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void login() {
		FacesContext face = FacesContext.getCurrentInstance();

		try {
			face.getExternalContext().redirect("login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
