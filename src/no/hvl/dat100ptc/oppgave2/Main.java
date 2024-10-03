package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		//a
		GPSPoint gpsPoint = GPSDataConverter.convert("2017-08-13T08:52:26.000Z","60.385390","5.217217","61.9");
		
		System.out.println(gpsPoint);
	}
}
