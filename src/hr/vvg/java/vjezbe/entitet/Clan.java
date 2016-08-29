package hr.vvg.java.vjezbe.entitet;

import java.io.Serializable;

public class Clan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imeClana;
	private String prezimeClana;
	private String oibClana;
	
	public Clan(String oibClana, String prezimeClana, String imeClana) {
		this.oibClana = oibClana;
		this.prezimeClana = prezimeClana;
		this.imeClana = imeClana;
	}

	public String getImeClana() {
		return imeClana;
	}

	public String getPrezimeClana() {
		return prezimeClana;
	}

	public String getOibClana() {
		return oibClana;
	}
}
