package hr.vvg.java.vjezbe.entitet;

import hr.vvg.java.vjezbe.enumeracija.Jezik;
import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

import java.math.BigDecimal;

public class Knjiga extends Publikacija implements ZaPosudbu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean dostupnaZaPosudbu;
	private Jezik jezikKnjige;
	private Izdavac izdavacKnjige;
	private final static BigDecimal USPOREDNA_CIJENA_KNJIGE = new BigDecimal("100.00");
	
	private static final BigDecimal CIJENA_PO_STRANICI_HR = new BigDecimal("0.50");
	private static final BigDecimal CIJENA_PO_STRANICI_FOREIGN = new BigDecimal("0.70");

	public Knjiga(String naziv, Jezik jezikKnjige, Izdavac izdavacKnjige, int godinaIzdanja,
			VrstaPublikacije vrstaPublikacije, BigDecimal cijenaPoStranici, int brojStranica, 
			Boolean dostupnaZaPosudbu) {
		
		super(naziv, godinaIzdanja, brojStranica, vrstaPublikacije, cijenaPoStranici);
		this.jezikKnjige = jezikKnjige;
		this.izdavacKnjige = izdavacKnjige;
		this.dostupnaZaPosudbu = true;
		
		if (this.getCijena().compareTo(USPOREDNA_CIJENA_KNJIGE) <= 0) {
			
			throw new NeisplativoObjavljivanjeException("Pogreška! Unijeli ste neisplativu "
					+ "knjigu za objavljivanje, molimo ponovite upis!");

		}
		
	}
	
	public void posudba() {
		
		this.dostupnaZaPosudbu = false;
	}
	
	public void vracanje() {
		
		this.dostupnaZaPosudbu = true;
	}
	
	public Boolean provjeriRaspolozivost() {
		
		return this.dostupnaZaPosudbu;
	}

	public Jezik getJezikKnjige() {
		return jezikKnjige;
	}

	public Izdavac getIzdavacKnjige() {
		return izdavacKnjige;
	}

	public static BigDecimal getCijenaPoStraniciHr() {
		return CIJENA_PO_STRANICI_HR;
	}

	public static BigDecimal getCijenaPoStraniciForeign() {
		return CIJENA_PO_STRANICI_FOREIGN;
	}
	
	@Override
	public boolean equals(Object objekt) {
		
		boolean provjeraKnjige = false;
		
		if (objekt instanceof Knjiga) {
			
			Knjiga knjiga = (Knjiga)objekt;
			
		    if (getNaziv().equals(knjiga.getNaziv()) && 
		    		getGodinaIzdanja() == knjiga.getGodinaIzdanja() &&
		    		getBrojStranica() == knjiga.getBrojStranica() &&
		    		getVrstaPublikacije().equals(knjiga.getVrstaPublikacije()) && 
		    		getJezikKnjige().equals(knjiga.getJezikKnjige()) &&
		    		getIzdavacKnjige().getNazivIzdavaca().equals(knjiga.getIzdavacKnjige().getDrzavaIzdavaca()) &&
		    		getIzdavacKnjige().getDrzavaIzdavaca().equals(knjiga.getIzdavacKnjige().getDrzavaIzdavaca())) {
		    	
		    		provjeraKnjige = true;
		    }		    
		}
		else {
			
			provjeraKnjige = false;
		}
		return provjeraKnjige;
	}
	
}