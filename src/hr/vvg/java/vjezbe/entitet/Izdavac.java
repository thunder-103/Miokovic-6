package hr.vvg.java.vjezbe.entitet;

import java.io.Serializable;

public class Izdavac implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nazivIzdavaca;
	private String drzavaIzdavaca;
	
	public Izdavac(String nazivIzdavaca, String drzavaIzdavaca) {
		this.nazivIzdavaca = nazivIzdavaca;
		this.drzavaIzdavaca = drzavaIzdavaca;
	}

	public String getNazivIzdavaca() {
		return nazivIzdavaca;
	}

	public String getDrzavaIzdavaca() {
		return drzavaIzdavaca;
	}
}
