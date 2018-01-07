package com.team2854.mapauto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.team2854.fieldAuto.FieldAuto;

import edu.wpi.first.wpilibj.command.Command;

public class MapBasedAuto {

	private FieldAuto field;
	
	public static void main(String[] args) {
		new MapBasedAuto("C:\\Users\\kevin\\Desktop\\mapFile");
	}
	
	public MapBasedAuto(String filePath) {
		File f = null;
		try {
			f = new File(filePath);
			if(!f.exists()) {throw new FileNotFoundException();}
			FileInputStream input = new FileInputStream(f);
			try(ObjectInputStream oStream = new ObjectInputStream(input)) {
				Object o = oStream.readObject();
				field = (FieldAuto) (o);
			}
			
		} catch (FileNotFoundException e1) {
			System.err.println("Could not find file at " + f.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Command generateAuto() {
		return null;
		
	}
	
}
