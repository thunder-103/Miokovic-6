package hr.vvg.java.vjezbe.iznimke;

public class DuplikatPublikacijeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DuplikatPublikacijeException(String poruka) {
		
		super(poruka);
	}
	
	public  DuplikatPublikacijeException(Throwable uzrok) {
		
		super(uzrok);
	}

	public DuplikatPublikacijeException(String poruka, Throwable uzrok) {
		
		super(poruka, uzrok);
	}
}
