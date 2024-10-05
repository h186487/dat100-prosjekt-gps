package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;
 
	public GPSData(int antall) {

		this.gpspoints = new GPSPoint[antall];  //starter tabellen med en gitt størrelse
		this.antall = 0; 						//setter antall til 0
		
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;					//henter referansen til gpspoint-tabellen
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;
		
		if (antall < gpspoints.length) {		//sjekker om det er plass
			gpspoints[antall] = gpspoint;		//legger til gpspoints
			antall++;							//øker antall
			inserted = true;					//intsetting velykket
		}
		return inserted;						//returnerer insettingsstatus
	
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint; //deklarerer objektet

		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation); //konverterer
		return insertGPS(gpspoint);  //setter inn gpspoint i tabellen
		
	}

	public void print() {
		System.out.println("====== GPS Data - START ======");
		for (int i = 0; i < antall; i++) {
			System.out.print(gpspoints[i].toString());
		}
		System.out.println("====== GPS Data - SLUTT ======");
	}
}
