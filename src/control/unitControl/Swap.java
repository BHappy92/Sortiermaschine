package control.unitControl;

public class Swap {
	private int left;
	private int right;
	private double leftHeight;
	private double rightHeight;
	private double leftXPos;
	private double rightXPos;
	
	public Swap(int left, int right, double leftHeight, double rightHeight, double leftXPos, double rightXPos) {
		setLeft(left);
		setRight(right);
		setLeftHeight(leftHeight);
		setRightHeight(rightHeight);
		setLeftXPos(leftXPos);
		setRightXPos(rightXPos);
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

	public double getLeftHeight() {
		return leftHeight;
	}

	public void setLeftHeight(double leftHeight) {
		this.leftHeight = leftHeight;
	}

	public double getRightHeight() {
		return rightHeight;
	}

	public void setRightHeight(double rightHeight) {
		this.rightHeight = rightHeight;
	}

	public double getRightXPos() {
		return rightXPos;
	}

	public void setRightXPos(double rightXPos) {
		this.rightXPos = rightXPos;
	}

	public double getLeftXPos() {
		return leftXPos;
	}

	public void setLeftXPos(double leftXPos) {
		this.leftXPos = leftXPos;
	}

	
}
