package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		
		GPSPoint point = new GPSPoint(0, 0, 0, 0);
		
        point.setTime(1);
        point.setLatitude(2);
        point.setLongitude(3);
        point.setElevation(5);
		
		System.out.println("Tid: " + point.getTime() + " sekunder");
		System.out.println("Høydegrad: " + point.getLatitude());
		System.out.println("Lengdegrad: " + point.getLongitude());
		System.out.println("Høyde: " + point.getElevation() + " meter");
		
		System.out.println(point.toString());
	}

}
