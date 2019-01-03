package control.sortcontrol;

import java.util.Collections;
import java.util.Vector;

import control.unitControl.Swap;
import control.unitControl.UnitControl;
import control.unitControl.UnitValues;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class QuickSort extends Thread{
	private Vector<Swap> swaps;
	private Vector<UnitValues> unitValues;
	private Vector<Rectangle> units;
	
	
	public QuickSort(Vector<Rectangle> units, Vector<UnitValues> unitValues, int initialDelay) {
		this.units 		= units;
		this.unitValues = unitValues;
		initSwaps();
		Sorts.delay = initialDelay;
	}
	
	
	
	public void run() {
		for(int i = 0; i < swaps.size(); i++) {
			int left = swaps.get(i).getLeft();
			int right = swaps.get(i).getRight();
			//units.get(left).setFill(Color.RED);
			//units.get(right).setFill(Color.GREEN);
			try {
				Thread.sleep(Sorts.delay/2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UnitControl.swapUnit(units, left, right);
			//units.get(left).setFill(Color.GREEN);
			//units.get(right).setFill(Color.RED);
			try {
				Thread.sleep(Sorts.delay/2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//units.get(left).setFill(Color.WHITE);
			//units.get(right).setFill(Color.WHITE);
			//Thread.sleep(delay/3);
		}
	}
	
	private void initSwaps() {
		swaps = new Vector<Swap>();
		sort(unitValues, swaps, 0, unitValues.size()-1);		
	}
	
	private void sort(Vector<UnitValues> unitValues, Vector<Swap> swaps, int L, int R) {
		if(R - L > 0) {
			int p = partition(unitValues, L, R);
			sort(unitValues, swaps, L, p - 1);
			sort(unitValues, swaps, p + 1, R);
		}
	}
	
	private int partition(Vector<UnitValues> unitValues, int L, int R) {
		
		double p = unitValues.get(R).getHeight();
		
		int i = L - 1;
		for(int j = L; j < R; j++) {
			if(unitValues.get(j).getHeight() <= p) {
				i++;
				swaps.add(new Swap(i, j));
				
				Collections.swap(unitValues, i, j);
			}
		}
		swaps.add(new Swap(i + 1, R));
		
		Collections.swap(unitValues, i + 1 , R);
		return i+1;
	}
	
	
	
}
