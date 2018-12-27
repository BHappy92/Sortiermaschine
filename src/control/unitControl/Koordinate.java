package control.unitControl;

public class Koordinate {
	private int left;
	private int right;
	
	public Koordinate(int left, int right) {
		setLeft(left);
		setRight(right);
	}
	
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	
}
