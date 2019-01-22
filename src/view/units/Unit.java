package view.units;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Unit {
	private StackPane anchor;
	private Rectangle unit;
	private Label indexLbl;
	
	private double width;
	private double height;
	private double x;
	private double y;
	
	public Unit(double width, double height) {
		unit = new Rectangle(width, height);
		setFill(Color.WHITE);
		
		indexLbl = new Label();
		indexLbl.setTextFill(Color.BLACK);
		
		anchor = new StackPane(unit, indexLbl);
	}

	public StackPane getAnchor() {
		return anchor;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		unit.setWidth(width);
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		unit.setHeight(height);
	}

	public double getLayoutX() {
		return x;
	}

	public void setLayoutX(double x) {
		anchor.setLayoutX(x);
	}

	public double getLayoutY() {
		return y;
	}

	public void setLayoutY(double y) {
		anchor.setLayoutY(y);
	}
	
	public void relocateUnit(double x, double y) {
		anchor.relocate(x, y);
	}

	public void setIndexLabel(String index) {
		indexLbl.setText(index);
	}

	public String getIndexLabel(String index) {
		return indexLbl.getText();
	}

	public void setFill(Color color) {
		unit.setFill(color);
	}
}
