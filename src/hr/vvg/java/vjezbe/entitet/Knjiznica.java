package hr.vvg.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Knjiznica<T extends Publikacija> {

	private List<T> listaPublikacija = new ArrayList<>();


	public void dodajPublikaciju(T publikacija) {
		
		listaPublikacija.add(publikacija);
		
	}
	
	public List<T> dohvatiSvePublikacije() {
		
		return this.listaPublikacija;
	}
	
	public List<T> mogucePosuditi(List<T> listaPublikacija) {
		
		List<T> listaMogucePosuditi = listaPublikacija.stream().filter(p -> p instanceof ZaPosudbu)
				.collect(Collectors.toList()).stream().filter(p -> ((ZaPosudbu) p).provjeriRaspolozivost())
				.collect(Collectors.toList());
		
		return listaMogucePosuditi;
		
	}

}
