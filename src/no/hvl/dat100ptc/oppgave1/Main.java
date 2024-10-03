package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		
		GPSPoint point = new GPSPoint(0, 0, 0, 0);
		
        point.setTime(20);
        point.setLatitude(60.385390);
        point.setLongitude(5.332200);
        point.setElevation(75.0);
		
		System.out.println("Tid: " + point.getTime() + " sekunder");
		System.out.println("Høydegrad: " + point.getLatitude());
		System.out.println("Lengdegrad: " + point.getLongitude());
		System.out.println("Høyde: " + point.getElevation() + " meter");
		
		
	}

}
