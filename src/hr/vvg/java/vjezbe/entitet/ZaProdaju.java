package hr.vvg.java.vjezbe.entitet;

import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface ZaProdaju {
	
	default public BigDecimal cijenaPublikacije (int brojStranica, VrstaPublikacije vrstaPublikacije, 
			BigDecimal cijenaPoStranici) {
		
		BigDecimal cijena = cijenaPoStranici.multiply(new BigDecimal(brojStranica));
		
		BigDecimal cijenaUmanjena = cijena.divide(new BigDecimal("1.1"), 
				2, RoundingMode.HALF_UP);
		
		if (vrstaPublikacije.equals(VrstaPublikacije.PAPIRNATA)) {
			
			return cijena;
		}
		else {
			
			return cijenaUmanjena;
		}
		
	}

}
