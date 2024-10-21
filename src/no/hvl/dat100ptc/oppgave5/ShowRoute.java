package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
 	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {
		setColor(0, 255, 0); 
		
		int x = 0;
		int y = 0;
		
		int [] lastPoint = null;

		for(GPSPoint point : gpspoints) {
			double longitude = point.getLongitude();
			double latitude = point.getLatitude();
			
			x = (int)(Math.abs(longitude - minlon) * xstep);
			y = (int)(Math.abs(latitude - minlat) * ystep);
			
			if (lastPoint != null) {
				drawLine(lastPoint[0], ybase - lastPoint[1], x, ybase - y);
			}
			
			lastPoint = new int [2];
			lastPoint[0]= x;
			lastPoint[1] = y;
			
			fillCircle(x, ybase - y, 5);
		
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		double totalTime = gpscomputer.totalTime();
		double totalDistance = gpscomputer.totalDistance();
		double totalElevation = gpscomputer.totalElevation();
		double maxSpeed = gpscomputer.maxSpeed();
		double averageSpeed = gpscomputer.averageSpeed();
		double energy = gpscomputer.totalKcal(80); //80kg
		
		drawString("Total time: " + String.format("%.2f", totalTime) + " timer", 10, TEXTDISTANCE);
		drawString("Total distance: " + String.format("%.2f", totalDistance) + " km", 10, TEXTDISTANCE + 20);
		drawString("Total elevation: " + String.format("%.2f", totalElevation) + " m", 10, TEXTDISTANCE + 40);
		drawString("Max speed: " + String.format("%.2f", maxSpeed) + " km/t", 10, TEXTDISTANCE + 60);
		drawString("Average speed: " + String.format("%.2f", averageSpeed) + " km/t", 10, TEXTDISTANCE + 80);
		drawString("Energy: " + String.format("%.2f", energy)+ " kcal", 10, TEXTDISTANCE + 100);
		
		
	}

	public void replayRoute(int ybase) {
		setColor(0, 0, 255);
		int x = (int)(Math.abs(gpspoints[0].getLongitude() - minlon) * xstep);
		int y = (int)(Math.abs(gpspoints[0].getLatitude() - minlat) * ystep); 
		
		int circle = fillCircle(x, ybase - y, 8);
		
		setSpeed(5);
		for(GPSPoint point : gpspoints) {
			double longitude = point.getLongitude();
			double latitude = point.getLatitude();
			
			x = (int)(Math.abs(longitude - minlon) * xstep);
			y = (int)(Math.abs(latitude - minlat) * ystep);
			
			moveCircle(circle, x, ybase - y);
		}
	}

}
