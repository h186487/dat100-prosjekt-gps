package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	// TODO - objektvariable
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
	
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - konstruktur
		this.time= time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		
//		throw new UnsupportedOperationException(TODO.construtor("GPSPoint"));
		
	}
	
	public static void main (String[] args) {
		GPSPoint point = new GPSPoint(10, 5.5, 4.5, 12);
		
		System.out.println("Tid: " + point.time + " sekunder");
		System.out.println("Høydegrad: " + point.latitude);
		System.out.println("Lengdegrad: " + point.longitude);
		System.out.println("Høyde: " + point.elevation + " meter");

		
	}

	// TODO - get/set metoder
	public int getTime() {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setTime(int time) {
				
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getLatitude() {
		
		throw new UnsupportedOperationException(TODO.method());
		
		
	}

	public void setLatitude(double latitude) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getLongitude() {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setLongitude(double longitude) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getElevation() {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setElevation(double elevation) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	public String toString() {
		
		String str;
		
		throw new UnsupportedOperationException(TODO.method());

		// TODO
		
	}
}
