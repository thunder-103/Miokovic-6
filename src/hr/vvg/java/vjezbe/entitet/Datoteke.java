package hr.vvg.java.vjezbe.entitet;

import hr.vvg.java.vjezbe.enumeracija.Jezik;
import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Datoteke implements ZaProdaju{
	
	public List<Knjiga> ucitajKnjigu(List<Knjiga> listaKnjiga,File fileZaCitanje)
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		List<String> listaLinija = new ArrayList<>();
		Jezik jezikKnjige = null;
		VrstaPublikacije vrstaPublikacije = null;
		Knjiga knjiga = null;
		BigDecimal cijenaPoStranici;
		
		try(BufferedReader in = new BufferedReader(new FileReader(fileZaCitanje))) {
			
			String line;
			
			while((line = in.readLine()) != null) {
				
				listaLinija.add(line);
				
				//System.out.println(line);
			}
		}
		catch(IOException e) {
				
			System.err.println(e);
		}
		
		for(int i = 0; i < listaLinija.size(); i += 7) {
			
			System.out.println("Uèitavam " + ((i / 7) + 1) + ". knjigu.");
			
			//System.out.println(listaKnjiga.get(i));
			
		String naziv = listaLinija.get(i);
		int brojJezikaKnjige = Integer.parseInt(listaLinija.get(i + 1));
		
		for(Jezik jezik : Jezik.values()) {
			
			if (brojJezikaKnjige == jezik.getBrojJezika()) {
				
				jezikKnjige = Jezik.values()[brojJezikaKnjige - 1];
				
			}
		}
		String nazivIzdavaca = listaLinija.get(i + 2);
		
		String drzavaIzdavaca = listaLinija.get(i + 3);
		
		if(drzavaIzdavaca.equals("Hrvatska")) {
			
			cijenaPoStranici = Knjiga.getCijenaPoStraniciHr();
		}
		
		else {
			
			cijenaPoStranici = Knjiga.getCijenaPoStraniciForeign();
		}
		
		Izdavac izdavacKnjige = new Izdavac(nazivIzdavaca, drzavaIzdavaca);
		
		int godinaIzdanja = Integer.parseInt(listaLinija.get(i + 4));
		
		int brojVrstePublikacije = Integer.parseInt(listaLinija.get(i + 5));
		
		for(VrstaPublikacije vrstaPublikacije1 : VrstaPublikacije.values()) {
			
			if (brojVrstePublikacije == vrstaPublikacije1.getBrojVrste()) {
				
				vrstaPublikacije = VrstaPublikacije.values()[brojVrstePublikacije - 1];
			}
		}
		
		int brojStranica = Integer.parseInt(listaLinija.get(i + 6));
		
		knjiga = new Knjiga(naziv, jezikKnjige, izdavacKnjige, godinaIzdanja, vrstaPublikacije,
				cijenaPoStranici, brojStranica, null);
		
		System.out.println("Uèitavanje " + ((i / 7) + 1) + ". knjige završeno...");
		
		listaKnjiga.add(knjiga);
		
		}
		return listaKnjiga;

	}
	
	public List<Casopis> ucitajCasopis (List<Casopis> listaCasopisa, File fileZaCitanje) 
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		List<String> listaLinija = new ArrayList<>();
		
		VrstaPublikacije vrstaPublikacije = null;
		
		Casopis casopis = null;
		
		try(BufferedReader in = new BufferedReader(new FileReader(fileZaCitanje))) {
			
			String line;
			
			while((line = in.readLine()) != null) {
				
				listaLinija.add(line);
				
				//System.out.println(line);
			}
		}
		catch(IOException e) {
				
			System.err.println(e);
		}
		
		for(int i = 0; i < listaLinija.size(); i += 5) {
			
			System.out.println("Uèitavam " + ((i / 5) + 1) + ". èasopis.");
			
			String nazivCasopisa = listaLinija.get(i);
			
			int godinaIzdanja = Integer.parseInt(listaLinija.get(i + 1));
			
			int mjesecIzdavanja = Integer.parseInt(listaLinija.get(i + 2));
			
			int brojVrstePublikacije = Integer.parseInt(listaLinija.get(i + 3));
			
			for(VrstaPublikacije vrstaPublikacije1 : VrstaPublikacije.values()) {
				
				if (brojVrstePublikacije == vrstaPublikacije1.getBrojVrste()) {
					
					vrstaPublikacije = VrstaPublikacije.values()[brojVrstePublikacije - 1];
				}
			}
			
			int brojStranica = Integer.parseInt(listaLinija.get(i + 4));
			
			
		casopis = new Casopis(nazivCasopisa, godinaIzdanja, brojStranica, vrstaPublikacije, mjesecIzdavanja);
		
		System.out.println("Uèitavanje " + ((i / 5) + 1) + ". èasopisa završeno...");
		
		listaCasopisa.add(casopis);
		}
		
		return listaCasopisa;
	}
	
	public Clan ucitajClana (List<String> listaClanova, File fileZaCitanje, int brojClana) {
		
		Clan clan = null;
		
		
		try(BufferedReader in = new BufferedReader(new FileReader(fileZaCitanje))) {
			
			String line;
			
			while((line = in.readLine()) != null) {
				
				listaClanova.add(line);
				
				//System.out.println(line);
			}
		}
		catch(IOException e) {
				
			System.err.println(e);
		}
		
		for(int i = 0; i < listaClanova.size(); i += 3) {
			
			String oib = listaClanova.get(i);
			
			String prezime = listaClanova.get(i + 1);
			
			String ime = listaClanova.get(i + 2);
			
			clan = new Clan(oib, prezime, ime);
		}
		
		return clan;
		
		
	}
	
}
	

	

