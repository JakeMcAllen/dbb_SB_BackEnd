package dto;

public class addFaq {
	
	
	private String titolo;
	private String risposta;
	private String username;
	private String password;
	
	public addFaq() {}

	public addFaq(String titolo, String risposta, String username, String password) {
		this.titolo = titolo;
		this.risposta = risposta;
		this.username = username;
		this.password = password;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getRisposta() {
		return risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	@Override
	public String toString() {
		return "addFaq [titolo=" + titolo + ", risposta=" + risposta + ", username=" + username + ", password="
				+ password + "]";
	}
	
	
}
