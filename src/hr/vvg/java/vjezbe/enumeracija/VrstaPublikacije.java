package hr.vvg.java.vjezbe.enumeracija;

public enum VrstaPublikacije {
	
	ELEKTRONICKA (1, "ELEKTRONICKA"), 
	PAPIRNATA (2, "PAPIRNATA");
	
	private Integer brojVrste;
	private String nazivVrste;
	
	private VrstaPublikacije(Integer brojVrste, String nazivVrste) {
		
	this.brojVrste= brojVrste;
	this.nazivVrste= nazivVrste;
	}

	public Integer getBrojVrste() {
		return brojVrste;
	}

	public String getNazivVrste() {
		return nazivVrste;
	}
	
	

}
