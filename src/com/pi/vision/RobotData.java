package com.pi.vision;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class RobotData implements Serializable {

	/**
	 * Version 1 (Please increment whenever this class's instance variables change
	 * ty
	 */
	private static final long serialVersionUID = 1L;

	private transient Mat image;
	private double[] imgData;
	private int width, height;
	private int type;

	private long timeStamp;
	
	public RobotData() {
		this(new Mat());
	}

	public RobotData(Mat img) {
		this.image = img;
		width = img.cols();
		height = img.rows();
		type = image.type();
		updateImgData();
	}

	public void updateImgData() {
		imgData = new double[image.channels() * image.rows() * image.cols()];
		int counter = 0;
		for (int i = 0; i < image.rows(); i++) {
			for (int j = 0; j < image.cols(); j++) {
				double[] data = image.get(j, i);
				for (double d : data) {
					imgData[counter++] = d;
				}
			}
		}
		width = image.rows();
		height = image.cols();
		type = image.type();
	}

	public void updateImage() {

		int channels = (imgData.length / (width * height));
		if (!(channels == 1 || channels == 3)) {
			throw new RuntimeException("Unsupported number of channels: " + channels);
		}
		image = new Mat(width, height, type);
		if (channels == 1) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					image.put(j, i, imgData[i * width + j]);
				}
			}
		} else {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					image.put(j, i, imgData[3 * (i * width + j) + 0], imgData[3 * (i * width + j) + 1],
							imgData[3 * (i * width + j) + 2]);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "RobotData [image=" + image.dump() + "]";
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Mat getImage() {
		return image;
	}

}
