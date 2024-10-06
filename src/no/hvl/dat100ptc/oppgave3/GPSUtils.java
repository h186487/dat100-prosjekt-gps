package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
	 		if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
//		initialiser array variabel som lagrer breddegrad verdier for gps-punkter
		double[] latitudes = new double[gpspoints.length];
//		gå gjennom gps-punkt og hent breddegrad, lagre breddegrad verdier i latitudes arrayen
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}

		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
//		initialiser array variabel som lagrer lengdegrad verdier for gps-punkter
		double[] longitudes = new double[gpspoints.length];
//		gå gjennom gps-punkt og hent ut lengdegrad, lagre lengdegrad verdier i longitudes arrayen
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
//		definer variabler og omregn til radianer
		latitude1 = toRadians(gpspoint1.getLatitude());
		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());
		longitude2 = toRadians(gpspoint2.getLongitude());

		double deltaphi = latitude2 - latitude1;
		double deltadelta = longitude2 - longitude1;
//		definer og hent variabler fra compute_a og b metoder
		double a = compute_a(latitude1, latitude2, deltaphi, deltadelta);
		double c = compute_c(a);
		d = R * c;

		return d;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
//		definer variabler f og g som inneholder del av mattestykket som skal opphøyes i 2
		double f = (sin(deltaphi / 2));
		double g = (sin(deltadelta / 2));
		
		double a = pow(f, 2) + cos(phi1) * cos(phi2) * pow(g, 2);
		return a;

	}

	private static double compute_c(double a) {

		double c = 2 * atan2(sqrt(a), sqrt(1 - a));
		return c;

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double speed;
//		definer variabler som henter tid fra gpspoint
		int time1 = gpspoint1.getTime();
		int time2 = gpspoint2.getTime();
		int secs = time2 - time1;

		double distance = distance(gpspoint1, gpspoint2);

		speed = distance / secs;
		return speed;
		
		

	}

	public static String formatTime(int secs) {


		String TIMESEP = ":";

		int timer = secs / 3600; //1 time = 3600 sekunder
		int minutter = (secs % 3600) / 60; //1 minutt = 60 sekunder
		int sekunder = secs % 60; //overskudd minutter
		
		String timestr = String.format("%02d%s%02d%s%02d", timer, TIMESEP, minutter, TIMESEP, sekunder);
		
		timestr = "  " + timestr;
		
		return timestr;
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
//		definer string til å omgjøre til to desimaler for double d
		String str = String.format("%.2f", d);
//		bestemmer antall mellomrom til å være 10 - lengden på strengen
		int antallMellomrom = TEXTWIDTH - str.length();
//		går gjennom antall mellomrom og lager mellomrom for hvert antall mellomrom
		for (int i = 0; i < antallMellomrom; i++) {
			str = " " + str;
		}

		return str;

	}
}
