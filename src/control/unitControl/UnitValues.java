package control.unitControl;

public class UnitValues {
	private double xPos;
	private double height;
	
	public UnitValues(double xPos, double height) {
		setxPos(xPos);
		setHeight(height);
	}
	
	public double getxPos() {
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
}
