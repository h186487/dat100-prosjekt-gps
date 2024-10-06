package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

	 	String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
		
		int x = MARGIN,y;
	
		double [] speeds = gpscomputer.speeds(); 
		
		double maxSpeed = 0;
		
		for(double speed : speeds) { //finn maks hastighet
			if (speed > maxSpeed) {
				maxSpeed = speed;
			}
		}
		//tege stolpene for hastigheter
		for(int i = 0; i < speeds.length; i++) { 
			//beregn høyden på baren
			y = ybase - (int)(BARHEIGHT * (speeds[i] / maxSpeed));
			
			setColor(0, 0, 255);
			fillRectangle(x, y, 2, ybase - y); 
			
			x += 2; //flytt x for neste bar
		}
		
		//tegne gjennomstinttshastighet som grønn linje
		double avgSpeed = gpscomputer.averageSpeed();
		int avgY = ybase - (int)(BARHEIGHT * (avgSpeed / maxSpeed));
		setColor(0, 255, 0);
		drawLine(MARGIN, avgY, MARGIN + 2 * speeds.length, avgY);
		
		//tegn aksene
		setColor(0, 0, 0);
		drawLine(MARGIN, ybase, MARGIN + 2 * speeds.length, ybase); //x-akse
		drawLine(MARGIN, ybase, MARGIN, ybase - BARHEIGHT); //y-akse
		
		for(int j = 0; j <= 10; j++) { //10 etiketter, juster etter behov
			int labelY = ybase - (BARHEIGHT * j) / 10;
			drawString(String.valueOf((int)(j * (maxSpeed / 10))), MARGIN - 30, labelY + 5); //etikett til venstre for grafen			
		}
		 //teng gjennomsnittshastighet på aksene
		drawString("Gj. hastighet: " + String.format("%.1f", avgSpeed) + " km/t", MARGIN + 5, avgY - 5);
		
		//usikker på om jeg må ha med de to siste drawString.
	}
}
