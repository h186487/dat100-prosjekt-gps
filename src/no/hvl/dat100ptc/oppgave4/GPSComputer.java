package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;

	}

	public double totalElevation() {

		double elevation = 0;
//		gå gjennom tabellen med gps punkter
		for (int i = 0; i < gpspoints.length - 1; i++) {
//			lagre høyden fra nåværende og neste gps punkt
			double elevationNå = gpspoints[i].getElevation();
			double elevationNeste = gpspoints[i + 1].getElevation();
//			kalkulerer høyde hvis neste punkt er høyere
			if (elevationNeste > elevationNå) {
				elevation += (elevationNeste - elevationNå);
			}
		}
		return elevation;
		
	}

	public int totalTime() {
//		lagrer tid fra første og siste gps punkt
		int startTime = gpspoints[0].getTime();
		int sluttTime = gpspoints[gpspoints.length - 1].getTime();
//		returnerer total tid
		return sluttTime - startTime;
		
	}
		
	public double[] speeds() {

		double[] speeds = new double[gpspoints.length - 1];
		
//		gå gjennom tabellen med gps punkter
		for (int i = 0; i < gpspoints.length - 1; i++) {
//			kalkuler og lagre gjennomsnittsfart gjennom punkt i og i + 1
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}
		return speeds;
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		double[] speedArray = speeds();
		
		maxspeed = GPSUtils.findMax(speedArray);
		
		return maxspeed;
	
	}

	public double averageSpeed() {

		double average = 0;
		
		average = totalDistance() / totalTime();
		
		return average;
			
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
		
		double speedmph = speed * MS;
		
		double met = 0;	
		
		double t = secs / 3600.0;
		
		double metArray[][] = {
				{10, 4},
				{12, 6},
				{14, 8},
				{16, 10},
				{20, 12},
				{Double.MAX_VALUE, 16}
		};
		
		for (int i = 0; i < metArray.length; i++) {
			if (speedmph < metArray[i][0]) {
				met = metArray[i][1];
				break;
			}
		}
		
//		double kcal = met * weight * t;
		
		return met * weight * t;
		
	}

	public double totalKcal(double weight) {
		
		double totalkcal = 0;

		for (int i = 1; i < gpspoints.length; i++) {
			double speed = GPSUtils.speed(gpspoints[i - 1], gpspoints[i]);
			
			int secs = gpspoints[i].getTime() - gpspoints[i - 1].getTime();
			
			double kcalsegment = kcal(weight, secs, speed);
			
			totalkcal += kcalsegment;
		}
		return totalkcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		

		double totalEnergyKcal = totalKcal(WEIGHT);

		System.out.println("==============================================");
		System.out.printf("Total Time     :   %s%n", GPSUtils.formatTime(totalTime()));
		System.out.printf("Total distance :      %.2f km%n", totalDistance());
		System.out.printf("Total elevation:     %.2f m%n", totalElevation());
		System.out.printf("Max speed      :      %.2f km/t%n", maxSpeed());
		System.out.printf("Average speed  :      %.2f km/t%n", averageSpeed());
		System.out.printf("Energy         :     %.2f kcal%n", totalEnergyKcal);
		System.out.println("==============================================");
		
	}

}
