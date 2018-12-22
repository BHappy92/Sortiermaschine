package control.unitControl;

import javafx.scene.shape.Rectangle;

public class UnitControl {
	private Rectangle[] units;
	
	//Maximale Höhe einer Unit: 400px
	//Minimale Höhe einer Unit: 20px
	
	
	public Rectangle[] posInOrder(Rectangle[] units) {
		reverseOrder(units); //Damit die Units von Links nach Recht der Größe nach geordnet werden
		for(int i = 0; i < units.length; i++) {
			double xPos = 1000-(980/(i+1));
			double yPos = 500 - units[i].getHeight();
			units[i].relocate(xPos, yPos);
		}
		return units;
	}
	
	public Rectangle[] reverseOrder(Rectangle[] units) {
		for(int i = 0; i < units.length / 2; i++) {
			Rectangle temp = units[i];
			units[i] = units[units.length - i - 1];
			units[units.length - i - 1] = temp;
		}
		return units;
	}
}
