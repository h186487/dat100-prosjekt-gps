package no.hvl.dat100ptc.oppgave5;

import no.hvl.dat100ptc.TODO;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50;		// margin on the sides 
	
	private static final int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn (uten .csv): "); //eks. skriv medium
		GPSComputer gpscomputer =  new GPSComputer(filename);

	 	gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {
		
		int x = MARGIN; // første høyde skal tegnes ved MARGIN
		int y;
		
		for(int i = 0; i < gpspoints.length; i++) {
			int height = (int) Math.max(0, gpspoints[i].getElevation()); //ikke ta med negative høyder
				
			y = ybase - height; //beregner y-posisjon basert på høyden
		
			//tegne linjen for høydepprofilen
			if (i < 0) {
				int previousHeight = (int) Math.max(0, gpspoints[i - 1].getElevation());
				int previousY = ybase - previousHeight;
				drawLine(x -1, previousY, x - 1, y); //tegn fra forjrrige til høyde nå
			}
			//tegn linjen for høyden np
			drawLine(x, ybase, x, y); //tegne høydebar
			x++; //flytter til neste x-posisjon
		}
	}

}
