package control.sortcontrol;

import java.util.Collections;
import java.util.Vector;

import control.unitControl.Swap;
import control.unitControl.UnitControl;
import control.unitControl.UnitValues;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BubbleSort extends Thread {
	private Vector<Swap> swaps;
	private Vector<UnitValues> unitValues;
	private Vector<Rectangle> units;

	public BubbleSort(Vector<Rectangle> units, Vector<UnitValues> unitValues, int initialDelay) {
		this.units = units;
		this.unitValues = unitValues;
		initSwaps();
		Sorts.delay = initialDelay;
	}

	/**
	 * Die run() Methode nimmt den Swap Vector und arbeitet jeden Swap einen 
	 * nach dem anderen ab. Diese wurden in verherigen Methoden bereits
	 * initialisiert.
	 * 
	 * Außerdem werden hier die zu swappenden Units farblich gekennzeichnet
	 * und ein delay zwischen den Farbwechseln eingefügt
	 */
	public void run() {
		
		for (int i = 0; i < swaps.size(); i++) {
			int left = swaps.get(i).getLeft();
			int right = swaps.get(i).getRight();
			units.get(left).setFill(Color.RED);
			units.get(right).setFill(Color.GREEN);
			try {
				Thread.sleep(Sorts.delay / 2);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			UnitControl.swapUnit(units, left, right);
			units.get(left).setFill(Color.GREEN);
			units.get(right).setFill(Color.RED);
			try {
				Thread.sleep(Sorts.delay / 2);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			units.get(left).setFill(Color.WHITE);
			units.get(right).setFill(Color.WHITE);
		}
		
	}
	/**
	 * Der Sortieralgorithmus nimmt die UnitValues und sortiert mit ihnen
	 * Jeder Swap wird in Swaps gespeichert und kann dann so in der 
	 * run() Methode abgearbeitet werden
	 * @param unitValues
	 * @param swaps
	 */
	private void sort(Vector<UnitValues> unitValues, Vector<Swap> swaps) {
		boolean swapped = false; 
		do { 
			swapped = false;
			for(int i = 0; i < unitValues.size() - 1; i++) {
				int left = i;
				int right = i+1;
				double leftHeight = unitValues.get(left).getHeight();
				double rightHeight = unitValues.get(right).getHeight();
				if(leftHeight > rightHeight) {
					swaps.add(new Swap(left, right));
					Collections.swap(unitValues, left, right);
					swapped = true;
				}				
			}		
		} while(swapped);
		
	}

	/**
	 * Initialisiert die Swaps mit dem Sortieralgorithmus
	 */
	private void initSwaps() {
		swaps = new Vector<Swap>();	
		sort(unitValues, swaps);
	}
	
}
