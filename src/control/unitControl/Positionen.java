package control.unitControl;


import java.util.Vector;
import javafx.scene.shape.Rectangle;

public class Positionen {
	private double[] x;
	private double[] y;
	
	public Positionen(Vector<Rectangle> units) {
		x = new double[units.size()];
		y = new double[units.size()];
		for(int i = 0; i < units.size(); i++) {
			
			x[i] = units.get(i).getLayoutX();
			y[i] = units.get(i).getHeight();
		}
	}
	
	public double[] getAllX() {
		return x;
	}
	
	public double[] getAllY() {
		return y;
	}
	
	public double getX(int index) {
		return x[index];
	}
	
	public double getY(int index) {
		return y[index];
	}
	
	public int size() {
		return x.length;
	}
	
	public static void swapX(double[] x, int left, int right) {
		double tempX = x[left];
		x[left] = x[right];
		x[right] = tempX;
	}
	
	public static void swapY(double[] y, int left, int right) {
		double tempY = y[left];
		y[left] = y[right];
		y[right] = tempY;
	}
}
