package control.sortcontrol;

import java.util.Collections;
import java.util.Vector;

import control.unitControl.Swap;
import control.unitControl.UnitControl;
import control.unitControl.UnitValues;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;

public class BubbleSort {
	private Vector<Swap> swaps;
	
	//Der untere Teil hier muss noch erledigt werden
	
	/*Service<Void> sort = new Service<Void>() {

		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					System.out.println(swaps.size());
					for(Swap temp : swaps) {
						String ausgabe = "Left: " +temp.getLeft() + " Right: " + temp.getRight();
						System.out.println(ausgabe);
					}
					for(int i = 0; i < swaps.size(); i++) {
						int left = swaps.get(i).getLeft();
						int right = swaps.get(i).getRight();
						units.get(left).setFill(Color.RED);
						units.get(right).setFill(Color.GREEN);
						Thread.sleep(delay/3);
						UnitControl.swapUnit(units, left, right);
						units.get(left).setFill(Color.GREEN);
						units.get(right).setFill(Color.RED);
						Thread.sleep(delay/3);
						units.get(left).setFill(Color.WHITE);
						units.get(right).setFill(Color.WHITE);
						//Thread.sleep(delay/3);
					}
					return null;	
					
				}							
			};
		}					
	}; */
	
	public static Vector<Swap> initSwaps(Vector<UnitValues> unitValues) {
		//reverseOrder(units);
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
