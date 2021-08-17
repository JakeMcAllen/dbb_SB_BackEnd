package dto;

import java.util.Arrays;

public class addNewImgs {

	// id dell'articolo
	private int id;
	
	// immagini extra	
	private String[] lstimg;
	private int val;
	
	private int cop;
	
	// user 
	private String username;
	private String password;
	
	
	
	public addNewImgs() {}	
	public addNewImgs(int id, String[] lstimg, int val, int cop, String username, String password) {
		super();
		this.id = id;
		this.lstimg = lstimg;
		this.val = val;
		this.cop = cop;
		this.username = username;
		this.password = password;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getLstimg() {
		return lstimg;
	}
	public void setLstimg(String[] lstimg) {
		this.lstimg = lstimg;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public int getCop() {
		return cop;
	}
	public void setCop(int cop) {
		this.cop = cop;
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
		return "addNewImgs [id=" + id + ", val=" + val + ", cop=" + cop
				+ ", username=" + username + ", password=" + password + "]";
	}
	
}
