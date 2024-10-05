package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		GPSPoint gpsPoint1 = new GPSPoint(1, 1.1, 2.2, 3.3); //opprette GPSPoint objekter
		GPSPoint gpsPoint2 = new GPSPoint(2, 4.4, 5.5, 6.6);
		
		GPSData gpsData = new GPSData(2); //opprette et GPSData objekt med plass til to GPSPoint objekter
		
		gpsData.insertGPS(gpsPoint1); //sette inn objektene
		gpsData.insertGPS(gpsPoint2);
		
		gpsData.print();
	}
}
