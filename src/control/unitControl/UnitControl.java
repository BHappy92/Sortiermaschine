package control.unitControl;

import java.util.Vector;

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
			tempUnit = new Rectangle(width, height);
			tempUnit.setFill(Color.WHITE);
			units.add(tempUnit);	
		}	
		return units;
	}
}
