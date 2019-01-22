package control.unitControl;

import java.util.Collections;
import java.util.Vector;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.mainwindow.MainWindow;


public class UnitControl {
	
	/*
	 * Als Parameter wird die gewünschte Anzahl an zu generierenden
	 * Units übergeben
	 * */
	public static Vector<Rectangle> generateUnits(int n) {
		Vector<Rectangle> units = new Vector<>(n);	
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
		return units;
	}
	/**
	 * Positioniert die Units der Größe nach aufsteigend von 
	 * links nach rechts
	 */
	public static void posNormal(Vector<Rectangle> units) {
	
		for(int i = 0; i < units.size(); i++) {
			double margin = i;
			double xPos = (20 + margin) + i * (units.get(i).getWidth());
			double yPos = MainWindow.mainWindowHeight - units.get(i).getHeight();
			units.get(i).relocate(xPos, yPos);
		}	
	}

	/**
	 * Kehrt die Reihenfolge aller Units um
	 * 
	 */
	public static void reverseOrder(Vector<Rectangle> units) {
		Collections.reverse(units);		
	}	
	
	/**
	 * Positioniert alle Units an zufällige Positionen
	 * 
	 */
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
	
	/**
	 * Die Werte der Units werden bei den Sortieralgorithmen benötigt
	 * damit nicht mit den Units selbst hin und her geschoben wird
	 */
	public static Vector<UnitValues> initUnitValues(Vector<Rectangle> units) {
		Vector<UnitValues> unitValues = new Vector<>();
		for(int i = 0; i < units.size(); i++) {
			double xPos = units.get(i).getLayoutX();
			double height = units.get(i).getHeight();
			unitValues.add(new UnitValues(xPos, height));
		}
		return unitValues;
	}
	
	/**
	 * Methode zum Postionswechsel zweier Units
	 */
	public static void swapUnit(Vector<Rectangle> units, int left, int right) {	
		double leftXPos = units.get(left).getLayoutX();
		double rightXPos = units.get(right).getLayoutX();
		Collections.swap(units, left, right);
		units.get(left).setLayoutX(leftXPos);
		units.get(right).setLayoutX(rightXPos);
		
	}
	
	/**
	 * Fügt die Units zum MainWindow hinzu
	 * @param background
	 * Bekommt den Hintergrund vom MainWindow als Parameter
	 * @param units
	 */
	public static void addUnits(Pane background, Vector<Rectangle> units) {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().add(units.get(i));
			
		}
		
	}

	/**
	 * Fügt eine einzige Unit zum MainWindow hinzu
	 */
	public static void addUnitToWindow(Pane background, Vector<Rectangle> units, int index) {
		background.getChildren().add(units.get(index));
	}

	/**
	 * Entfernt alle Units vom MainWindow
	 * @param background
	 * Bekommt den Hintergrund vom MainWindow als Parameter
	 * @param units
	 */
	public static void removeUnits(Pane background, Vector<Rectangle> units) {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().remove(units.get(i));
		}
	}
	
	/**
	 * Entfernt eine einzige Unit vom MainWindow
	 */
	public static void removeUnitFromWindow(Pane background, Vector<Rectangle> units, int index) {
		background.getChildren().remove(units.get(index));
	}
	

	
}
