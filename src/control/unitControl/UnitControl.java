package control.unitControl;

import java.util.Collections;
import java.util.Vector;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.mainwindow.MainWindow;
import view.units.Unit;


public class UnitControl {
	
	/*
	 * n steht für die Anzahl der Units
	 * */
	public static Vector<Unit> generateUnits(int n) {
		/*Vector<Rectangle> units = new Vector<>(n);	
		double minHeight = 50;
		double maxHeight = MainWindow.mainWindowHeight-100;
		double delta = (maxHeight - minHeight) / n;
		for(int i = 0; i < n; i++) {
			Rectangle tempUnit;
			
			double width = ((MainWindow.mainWindowWidth - 40) / n) - 1;
			double height = minHeight + i*delta;
		
			tempUnit = new Rectangle(width, height);
			tempUnit.setFill(Color.WHITE);
			units.add(tempUnit);	
		}	
		return units;*/
		Vector<Unit> units = new Vector<Unit>(n);
		double minHeight = 50;
		double maxHeight = MainWindow.mainWindowHeight-100;
		double delta = (maxHeight - minHeight) / n;
		for(int i = 0; i < n; i++) {
			double width = ((MainWindow.mainWindowWidth - 40) / n) - 1;
			double height = minHeight + i*delta;
			units.add(new Unit(width, height));
		}
		return units;
	}
	
	//Ab hier muss weiter gearbeitet werden!!!!!!!!!
	//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	
	public static void posNormal(Vector<Unit> units) {
		//Damit die Units von Links nach Rechts der Größe nach geordnet werden
		//reverseOrder(units);
		
		for(int i = 0; i < units.size(); i++) {
			double margin = i;
			double x = (20 + margin) + i * (units.get(i).getWidth());
			double y = MainWindow.mainWindowHeight - units.get(i).getHeight();
			//units.get(i).relocate(xPos, yPos);
			units.get(i).relocateUnit(x, y);
		}	
	}
	public static void reverseOrder(Vector<Unit> units) {
		Collections.reverse(units);		
	}	
	
	public static void posRandom(Vector<Rectangle> units) {
		//Hilfsvektor um die Positionen zu speichern
		Vector<Double> xPositios = new Vector<>();
		posNormal(units);
		
		for(int i = 0; i < units.size(); i++) {
			double tempPos = units.get(i).getLayoutX();
			xPositios.add(tempPos);
		}

		Collections.shuffle(units);
		
		for (int i = 0; i < units.size(); i++) {
			units.get(i).setLayoutX(xPositios.get(i));;
		}
		
	}	
	
	
	public static Vector<UnitValues> initUnitValues(Vector<Rectangle> units) {
		Vector<UnitValues> unitValues = new Vector<>();
		for(int i = 0; i < units.size(); i++) {
			double xPos = units.get(i).getLayoutX();
			double height = units.get(i).getHeight();
			unitValues.add(new UnitValues(xPos, height));
		}
		return unitValues;
	}
	
	
	public static void swapUnit(Vector<Rectangle> units, int left, int right) {	
		double leftXPos = units.get(left).getLayoutX();
		double rightXPos = units.get(right).getLayoutX();
		Collections.swap(units, left, right);
		units.get(left).setLayoutX(leftXPos);
		units.get(right).setLayoutX(rightXPos);
		
	}
	
	
	public static void addUnits(Pane background, Vector<Rectangle> units) {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().add(units.get(i));
			
		}
		
	}
	
	public static void removeUnits(Pane background, Vector<Rectangle> units) {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().remove(units.get(i));
		}
	}	
	

	
}
