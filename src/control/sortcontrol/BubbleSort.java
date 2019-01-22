package control.sortcontrol;

import java.util.Collections;
import java.util.Vector;

import control.unitControl.Swap;
import control.unitControl.UnitControl;
import control.unitControl.UnitValues;
import javafx.scene.paint.Color;
import view.units.Unit;

public class BubbleSort extends Thread {
	private Vector<Swap> swaps;
	private Vector<Unit> units;
	private Vector<UnitValues> unitValues;


	public BubbleSort(Vector<Unit> units, Vector<UnitValues> unitValues, int initialDelay) {
		this.units = units;
		this.unitValues = unitValues;
		this.swaps = initSwaps();
		
		Sorts.delay = initialDelay;
	}

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
			try {
				Thread.sleep(Sorts.delay / 3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private Vector<Swap> initSwaps() {
		Vector<Swap> swaps = new Vector<>();

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
		
		return swaps;
	}	
}
