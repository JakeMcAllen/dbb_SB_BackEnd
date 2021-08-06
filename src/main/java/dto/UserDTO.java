package dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "user")
@JsonRootName("user")
public class UserDTO {

	private int id;
	private String nome;
	private String cognome;
	
	private String username;
	private String password;
	
	private int permessi;
	
	public UserDTO() {}

	public UserDTO(int id, String nome, String cognome, String username, String password, int permessi) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.permessi = permessi;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	public int getPermessi() {
		return permessi;
	}

	public void setPermessi(int permessi) {
		this.permessi = permessi;
	}


	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + ", permessi=" + permessi + "]";
	}

}
