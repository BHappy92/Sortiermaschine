package control.unitControl;

import java.util.Collections;
import java.util.Vector;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.mainwindow.MainWindow;

public class UnitControl {
	public static Vector<Rectangle> generateUnits(int amount) {
		Vector<Rectangle> units = new Vector<>(amount);	
		for(int i = 0; i < amount; i++) {
			Rectangle tempUnit;
			
			//(...-40) damit man links und rechts einen Abstand vom mainWindow hat
			double width = ((MainWindow.mainWindowWidth - 40) / amount) - 1;		
			double height = (MainWindow.mainWindowHeight-100) - ((380/(amount-1))*i);
			//double height = ((8/10)*MainWindow.mainWindowHeight) - ((380/(amount-1))*i);
			tempUnit = new Rectangle(width, height);
			tempUnit.setFill(Color.WHITE);
			units.add(tempUnit);	
		}	
		return units;
	}
	
	public static void posNormal(Vector<Rectangle> units) {
		//Damit die Units von Links nach Rechts der Größe nach geordnet werden
		reverseOrder(units);
		
		for(int i = 0; i < units.size(); i++) {
			double margin = i;
			double xPos = (20 + margin) + i * (units.get(i).getWidth());
			double yPos = MainWindow.mainWindowHeight - units.get(i).getHeight();
			units.get(i).relocate(xPos, yPos);
		}	
	}
	public static void reverseOrder(Vector<Rectangle> units) {
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
		for(Rectangle unit : units)
			System.out.println("NEW: "+ unit);
	}	
	
	public static void swapUnit(Vector<Rectangle> units, int left, int right) {	
		double leftXPos = units.get(left).getLayoutX();
		double rightXPos = units.get(right).getLayoutX();
		Collections.swap(units, left, right);
		units.get(left).setLayoutX(leftXPos);
		units.get(right).setLayoutX(rightXPos);
		
	}
	
	private static void swapUnitInternally(Vector<Swap> swaps, int left,int right) {
		
	}
	
	public static void addUnitsToWindow(Pane background, Vector<Rectangle> units) {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().add(units.get(i));
			
		}
		
	}
	public static void addUnitToWindow(Pane background, Vector<Rectangle> units, int index) {
		background.getChildren().add(units.get(index));
	}
	public static void removeUnitsFromWindow(Pane background, Vector<Rectangle> units) {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().remove(units.get(i));
		}
	}	
	public static void removeUnitFromWindow(Pane background, Vector<Rectangle> units, int index) {
		background.getChildren().remove(units.get(index));
	}
	
	public static Vector<Swap> initSwaps(Vector<Rectangle> units) {
		//reverseOrder(units);
		Vector<Swap> swaps = new Vector<>();
		
		
		boolean swapped = false; //Vermerkt ob Vertauschung im Durchlauf
		do { //Beginn des Durchlaufs
			swapped = false;
			for(int i = 0; i < units.size() - 1; i++) {
				int left = i;
				int right = i+1;
				double leftHeight = units.get(left).getHeight();
				double rightHeight = units.get(right).getHeight();
				double leftXPos = units.get(left).getLayoutX();
				double rightXPos = units.get(right).getLayoutX();
				if(leftHeight > rightHeight) {
					swaps.add(new Swap(left, right, leftHeight, rightHeight, leftXPos, rightXPos));
					//Hier werden die Units tatsächlich geswappt und das WILL ICH NICHT!
					swapUnit(units, left, right);
					swapped = true;
				}	
				
			}
			
		} while(swapped);
		
		return swaps;
	}
	
	
}
