package hr.vvg.java.vjezbe.iznimke;

public class NeisplativoObjavljivanjeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NeisplativoObjavljivanjeException(String poruka) {
		
		super(poruka);
	}

	public NeisplativoObjavljivanjeException(Throwable uzrok) {
	
	super(uzrok);
}
	
	public NeisplativoObjavljivanjeException(String poruka, Throwable uzrok) {
		
		super(poruka, uzrok);
	}

}
