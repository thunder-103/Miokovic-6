package hr.vvg.java.vjezbe.glavna;

import hr.vvg.java.vjezbe.entitet.Casopis;
import hr.vvg.java.vjezbe.entitet.Clan;
import hr.vvg.java.vjezbe.entitet.Datoteke;
//import hr.vvg.java.vjezbe.entitet.Izdavac;
import hr.vvg.java.vjezbe.entitet.Knjiga;
import hr.vvg.java.vjezbe.entitet.Knjiznica;
import hr.vvg.java.vjezbe.entitet.Posudba;
import hr.vvg.java.vjezbe.entitet.Publikacija;
//import hr.vvg.java.vjezbe.enumeracija.Jezik;
//import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;
import hr.vvg.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
//import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;



public class Glavna {
	
	private static final Logger log = LoggerFactory.getLogger(Glavna.class);
	
	public static void main(String[] args) {
		
		 Scanner ulazni = new Scanner(System.in);
		 

		 
		 Knjiznica<Publikacija> knjiznica = new Knjiznica<>();
		
		 Clan clan;
		
		 Posudba<Publikacija> posudba = null;

		 Datoteke datoteka = new Datoteke();
		 
		 List<Knjiga> listaKnjiga = new ArrayList<>(); 
		 
		 List<Casopis> listaCasopisa = new ArrayList<>();
		 
		 List<String> listaClanova = new ArrayList<>();
		 
		

				try {
					
					File fileZaCitanje = new File("knjige.txt");
					
					listaKnjiga = datoteka.ucitajKnjigu(listaKnjiga, fileZaCitanje);
					
				
				}
				catch (NeisplativoObjavljivanjeException ek) {
					
					System.out.println(ek.getMessage());
					log.error("Nije isplativo za objavu", ek);

				}
				catch (DuplikatPublikacijeException exk) {
					
					System.out.println(exk.getMessage());
					log.error("Dupla publikacija!", exk);

				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
					log.error("Nije pronaðena datoteka!", e);
				} catch (ParseException e) {
					System.out.println(e.getMessage());
					log.error("Pogreška kod parsiranja!", e);
				}
			
				
				try {
					
					File fileZaCitanje = new File("casopisi.txt");
					
					listaCasopisa = datoteka.ucitajCasopis(listaCasopisa, fileZaCitanje);
					
				}
				catch (NeisplativoObjavljivanjeException ec) {
					
					System.out.println(ec.getMessage());
					log.error("Nije isplativo za objavu", ec);
					
				}
				catch (DuplikatPublikacijeException exc) {
					
					System.out.println(exc.getMessage());
					log.error("Dupla publikacija!", exc);

				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
					log.error("Nije pronaðena datoteka!", e);
				} catch (ParseException e) {
					System.out.println(e.getMessage());
					log.error("Pogreška kod parsiranja!", e);
				}
			
				
		for(Knjiga knjiga : listaKnjiga) {
			
			knjiznica.dodajPublikaciju(knjiga);
			
		}
		
		
		
		for(Casopis casopis : listaCasopisa) {
			
			knjiznica.dodajPublikaciju(casopis);
			
		}
			
		najskupljaPublikacija(knjiznica);
		
		najjeftinijaPublikacija(knjiznica);
		
		int brojClana = 1;
		File fileZaCitanje = new File("clanovi.txt");
		
		System.out.println("Uèitavanje èlana...");
		
		clan = datoteka.ucitajClana(listaClanova, fileZaCitanje, brojClana);
		log.info("Unos clana", clan);
		
		System.out.println("Èlan uèitan.");
		
		do {
		
		posudba = odaberiPublikaciju(knjiznica, clan, ulazni);
		log.info("Odabrana publikacija: ", posudba.getPublikacija().getNaziv());
		
		} while (posudba == null);
		
		try {
			ObjectOutputStream out= new ObjectOutputStream (new FileOutputStream("posudba.dat"));
			
			out.writeObject(posudba);
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stanjePosudbe(posudba);
		
		pretragaPublikacije(knjiznica, ulazni);
		
		ulazni.close();	
		
	}
	
	
	private static Posudba<Publikacija> odaberiPublikaciju(Knjiznica<Publikacija> knjiznica, Clan clan, 
			Scanner ulazni) {
		
		System.out.println("Molimo, odaberite publikaciju:");
		
		String nazivPublikacije = ulazni.nextLine();
		
		//List<Publikacija> listaPublikacija = new ArrayList<>();
		
		Publikacija publikacija = knjiznica.dohvatiSvePublikacije().stream()
				.filter(p -> p.getNaziv().equals(nazivPublikacije)).findFirst().get();
		
//			for (int i = 0; i < listaPublikacija.size(); i++) {
//		
//				System.out.println((i+1) + ")" + listaPublikacija.get(i).getNaziv());
//				
//			}
//			
			LocalDateTime datumPosudbe = LocalDateTime.now();
			
			
			return new Posudba<Publikacija>(clan, publikacija, datumPosudbe);
	}

	
	private static void stanjePosudbe(Posudba<Publikacija> posudba) {
		
		System.out.println("Stanje posudbe:");
		
		System.out.println("Naziv publikacije: " + posudba.getPublikacija().getNaziv());
		
		System.out.println("Vrsta publikacije: " + posudba.getPublikacija().getVrstaPublikacije());
		
		System.out.println("Broj stranica: " + posudba.getPublikacija().getBrojStranica());
		
		System.out.println("Cijena: " + posudba.getPublikacija().getCijena());
		
		Publikacija publikacija = posudba.getPublikacija();
		
		if (publikacija instanceof Knjiga){
			
			Knjiga knjiga = (Knjiga) publikacija;
		
			System.out.println("Jezik: " + knjiga.getJezikKnjige());
			
			System.out.println("Izdavaè: " + knjiga.getIzdavacKnjige().getNazivIzdavaca());
			
			System.out.println("Država izdavaèa: " + knjiga.getIzdavacKnjige().getDrzavaIzdavaca());
			
			boolean raspolozivost = knjiga.provjeriRaspolozivost();
			String raspolozivostKaoString = new Boolean(raspolozivost).toString();
			
			raspolozivostKaoString = raspolozivostKaoString.replaceAll("false", "NE");
			raspolozivostKaoString = raspolozivostKaoString.replaceAll("true", "DA");
			
			System.out.println("Raspoloživo za posudbu: " + raspolozivostKaoString);
		}
		else {
			
			Casopis casopis = (Casopis) publikacija;
			
			System.out.println("Mjesec izdanja: " + casopis.getMjesecIzdavanjaCasopisa());
		}
		
		System.out.println("Podaci korisnika: ");
		
		System.out.println("Prezime: " + posudba.getNekiClan().getPrezimeClana());
		
		System.out.println("Ime: " + posudba.getNekiClan().getImeClana());
		
		System.out.println("OIB: " + posudba.getNekiClan().getOibClana());
		
		DateTimeFormatter formaterDatuma = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		
		System.out.println("Datum posudbe: " + 
							posudba.getDatumPosudbe().format(formaterDatuma));
			
	}
	
	private static void najjeftinijaPublikacija(Knjiznica<Publikacija> knjiznica) {
		
		Optional<Publikacija> najjeftinijaPublikacija = 
				knjiznica.dohvatiSvePublikacije().stream()
				.sorted((p1, p2) -> p1.getCijena().compareTo(p2.getCijena())).findFirst();

		//System.out.println(najjeftinijaPublikacija.get());
		
		System.out.println("Najjeftinija publikacija:");
		
		System.out.println("Naziv publikacije: " + najjeftinijaPublikacija.get().getNaziv());
		
		System.out.println("Vrsta publikacije: " + najjeftinijaPublikacija.get().getVrstaPublikacije());
		
		System.out.println("Broj stranica: " + najjeftinijaPublikacija.get().getBrojStranica());
		
		System.out.println("Cijena: " + najjeftinijaPublikacija.get().getCijena());
		
		if (najjeftinijaPublikacija.get() instanceof Knjiga){
			
			Knjiga knjiga = (Knjiga) najjeftinijaPublikacija.get();
		
			System.out.println("Jezik: " + knjiga.getJezikKnjige());
			
			System.out.println("Izdavaè: " + knjiga.getIzdavacKnjige().getNazivIzdavaca());
			
			System.out.println("Država izdavaèa: " + knjiga.getIzdavacKnjige().getDrzavaIzdavaca());
			
			boolean raspolozivost = knjiga.provjeriRaspolozivost();
			String raspolozivostKaoString = new Boolean(raspolozivost).toString();
			
			raspolozivostKaoString = raspolozivostKaoString.replaceAll("false", "NE");
			raspolozivostKaoString = raspolozivostKaoString.replaceAll("true", "DA");
			
			System.out.println("Raspoloživo za posudbu: " + raspolozivostKaoString);
		}
		
		else {
			
			Casopis casopis = (Casopis) najjeftinijaPublikacija.get();
			
			System.out.println("Mjesec izdanja: " + casopis.getMjesecIzdavanjaCasopisa());
		}
	}
	
	private static void najskupljaPublikacija(Knjiznica<Publikacija> knjiznica) {
		
		Optional<Publikacija> najskupljaPublikacija = 
				knjiznica.dohvatiSvePublikacije().stream()
				.sorted((p2, p1) -> p1.getCijena().compareTo(p2.getCijena())).findFirst();

		//System.out.println(najskupljaPublikacija.get());
		
		System.out.println("Najskuplja publikacija:");
		
		System.out.println("Naziv publikacije: " + najskupljaPublikacija.get().getNaziv());
		
		System.out.println("Vrsta publikacije: " + najskupljaPublikacija.get().getVrstaPublikacije());
		
		System.out.println("Broj stranica: " + najskupljaPublikacija.get().getBrojStranica());
		
		System.out.println("Cijena: " + najskupljaPublikacija.get().getCijena());
		
		if (najskupljaPublikacija.get() instanceof Knjiga){
			
			Knjiga knjiga = (Knjiga) najskupljaPublikacija.get();
		
			System.out.println("Jezik: " + knjiga.getJezikKnjige());
			
			System.out.println("Izdavaè: " + knjiga.getIzdavacKnjige().getNazivIzdavaca());
			
			System.out.println("Država izdavaèa: " + knjiga.getIzdavacKnjige().getDrzavaIzdavaca());
			
			boolean raspolozivost = knjiga.provjeriRaspolozivost();
			String raspolozivostKaoString = new Boolean(raspolozivost).toString();
			
			raspolozivostKaoString = raspolozivostKaoString.replaceAll("false", "NE");
			raspolozivostKaoString = raspolozivostKaoString.replaceAll("true", "DA");
			
			System.out.println("Raspoloživo za posudbu: " + raspolozivostKaoString);
		}
		
		else {
			
			Casopis casopis = (Casopis) najskupljaPublikacija.get();
			
			System.out.println("Mjesec izdanja: " + casopis.getMjesecIzdavanjaCasopisa());
		}
	}
	
	private static void pretragaPublikacije(Knjiznica<Publikacija> knjiznica , Scanner ulazni) {
		
		System.out.println("Pretražite publikacije:");
		
		String rijecPretrage = ulazni.nextLine();
		
		List<Publikacija> publikacija = knjiznica.dohvatiSvePublikacije().stream().filter(p -> p.getNaziv()
				.contains(rijecPretrage)).collect(Collectors.toList());
		
		for (Publikacija trenutnaPublikacija: publikacija) {
		
		System.out.println("Naziv publikacije: " + trenutnaPublikacija.getNaziv());
		
		System.out.println("Vrsta publikacije: " + trenutnaPublikacija.getVrstaPublikacije());
		
		System.out.println("Broj stranica: " + trenutnaPublikacija.getBrojStranica());
		
		System.out.println("Cijena: " + trenutnaPublikacija.getCijena());
		
		if (trenutnaPublikacija instanceof Knjiga){
			
			Knjiga knjiga = (Knjiga) trenutnaPublikacija;
		
			System.out.println("Jezik: " + knjiga.getJezikKnjige());
			
			System.out.println("Izdavaè: " + knjiga.getIzdavacKnjige().getNazivIzdavaca());
			
			System.out.println("Država izdavaèa: " + knjiga.getIzdavacKnjige().getDrzavaIzdavaca());
			
			boolean raspolozivost = knjiga.provjeriRaspolozivost();
			String raspolozivostKaoString = new Boolean(raspolozivost).toString();
			
			raspolozivostKaoString = raspolozivostKaoString.replaceAll("false", "NE");
			raspolozivostKaoString = raspolozivostKaoString.replaceAll("true", "DA");
			
			System.out.println("Raspoloživo za posudbu: " + raspolozivostKaoString);
		}
		else {
			
			Casopis casopis = (Casopis) trenutnaPublikacija;
			
			System.out.println("Mjesec izdanja: " + casopis.getMjesecIzdavanjaCasopisa());
		}
		
		}
		
	}
	
}
