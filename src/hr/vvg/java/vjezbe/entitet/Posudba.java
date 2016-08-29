package hr.vvg.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Posudba<T extends Publikacija> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Clan nekiClan;
	
	private T publikacija;
	
	private LocalDateTime datumPosudbe;

	public Posudba(Clan nekiClan, T publikacija, LocalDateTime datumPosudbe) {
		this.nekiClan = nekiClan;
		this.publikacija = publikacija;
		this.datumPosudbe = datumPosudbe;
	}

	public Clan getNekiClan() {
		return nekiClan;
	}

	public T getPublikacija() {
		return publikacija;
	}

	public LocalDateTime getDatumPosudbe() {
		return datumPosudbe;
	}
}
