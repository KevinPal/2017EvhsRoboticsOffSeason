package org.usfirst.frc.team2854.map;

import java.util.ArrayList;

public class DualSensor {

	private ArrayList<Double> data1, data2;
	private double variance1, variance2, combinedVariance;
	private String name;
	
	public DualSensor(String name) {
		this.name = name;
		data1 = new ArrayList<Double>();
		data2 = new ArrayList<Double>();
	}
	
	public void addValue(double v1, double v2) {
		data1.add(v1);
		data2.add(v2);
	}
	
	public void calibrate() {
		System.out.println("Starting calibration of " + name);
		double m1 = 0, m2 = 0;
		for(int i = 0; i < data1.size(); i++) {
			m1 += data1.get(i);
			m2 += data2.get(i);
		}
		m1 /= data1.size();
		m2 /= data2.size();
		
		for(int i = 0; i < data1.size(); i++) {
			variance1 += Math.pow(m1 - data1.get(i), 2);
			variance2 += Math.pow(m2 - data2.get(i), 2);
		}
		
		m1 /= (data1.size()-1);
		m2 /= (data2.size()-1);
		
		combinedVariance = 1d/(1d/variance1 + 1d/variance2);
		System.out.println("Sensor 1 averages at " + m1 + " with standard devation " + Math.sqrt(variance1) );
		System.out.println("Sensor 2 averages at " + m2 + " with standard devation " + Math.sqrt(variance2) );
		System.out.println("Combined standard devation: " + Math.sqrt(combinedVariance));
		
	}
	
	public double calculateValue(double measurement1, double measurement2) {
		return combinedVariance * (measurement1/variance1 + measurement2/variance2);
	}

	public double getError() {
		return Math.sqrt(combinedVariance);
	}
	
}
