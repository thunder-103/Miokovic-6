package hr.vvg.java.vjezbe.enumeracija;

public enum Jezik {
	
	HRVATSKI (1, "HRVATSKI"), 
	ENGLESKI (2, "ENGLESKI"), 
	NJEMACKI (3, "NJEMACKI"), 
	FRANCUSKI (4, "FRANCUSKI"), 
	TALIJANSKI (5, "TALIJANSKI"), 
	RUSKI (6, "RUSKI"), 
	KINESKI (7, "KINESKI");
	
	private Integer brojJezika;
	private String nazivJezika;
	
	private Jezik(Integer brojJezika, String nazivJezika) {
		
	this.brojJezika= brojJezika;
	this.nazivJezika= nazivJezika;
	}

	public Integer getBrojJezika() {
		return brojJezika;
	}

	public String getNazivJezika() {
		return nazivJezika;
	}

}
