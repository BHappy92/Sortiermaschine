package view.mainwindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import control.unitControl.UnitControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.units.Unit;

public class MainWindow {
	
	public static double mainWindowHeight = 500;
	public static double mainWindowWidth  = 1000;
	
	private Stage primaryStage;
	private Pane background;
	private Scene scene;
	private Button start;
	private Button backwardBtn;
	private Button pauseBtn;
	private Button forwardBtn;
	private ToggleButton stepByStep;
	private TextField txtfSampleSize;
	private ComboBox<String> algorithmus;
	private ComboBox<String> orders;
	
	private ArrayList<Rectangle> units;
	
	
	private boolean unitsAreGenerated = false;
	
	public MainWindow() {
		
		initComponents();	
		posComponents();
		styleComponents();
		addFunctionality();
		//testComponents();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	private void initComponents() {
		//Initialisierung der Oberfl�chenkomponenten
		//Oberfl�che wird erzeugt
		primaryStage 	= new Stage();
		background 		= new Pane();
		scene 			= new Scene(background, mainWindowWidth, mainWindowHeight);
		start			= new Button("START");
		backwardBtn		= new Button();
		pauseBtn 		= new Button();
		forwardBtn		= new Button();
		stepByStep 		= new ToggleButton("Step-By-Step");
		txtfSampleSize	= new TextField();
		algorithmus		= new ComboBox<>();
			algorithmus.getItems().addAll(
							"BubbleSort",
							"Quicksort",
							"Countingsort" );
		
		orders 			= new ComboBox<>();
			orders.getItems().addAll(
							"Ordered",
							"Totally Random"
					);
		
		//Hier neue Elemente zum Hauptfenster hinzuf�gen
		//vvvvvvvvvvvvvvvvvvvvvvv
		background.getChildren().addAll(start, backwardBtn, pauseBtn, forwardBtn, stepByStep, txtfSampleSize, algorithmus, orders);
		
	}
	
	private void posComponents() {
		start.relocate(5, 5);
		backwardBtn.relocate(700, 5);
		pauseBtn.relocate(760, 5);
		forwardBtn.relocate(820, 5);
		stepByStep.relocate(900, 5);
		txtfSampleSize.relocate(500, 5);
		algorithmus.relocate(250, 5);
		orders.relocate(100, 5);
	}
	
	private void styleComponents(){
		scene.getStylesheets().add("util/style.css");
		background.getStyleClass().add("pane");
		//Style pausebutton
		Image pause=new Image("util/images/pause.png");
		ImageView iv1=new ImageView(pause);
		iv1.setFitHeight(30);
		iv1.setFitWidth(30);
		pauseBtn.setGraphic(iv1);
		
		//Style playbutton
		Image play=new Image("util/images/play.png");
		ImageView iv2=new ImageView(play);
		iv2.setFitHeight(30);
		iv2.setFitWidth(30);
		forwardBtn.setGraphic(iv2);
		
		//Style backbutton
		Image back=new Image("util/images/back.png");
		ImageView iv3=new ImageView(back);
		iv3.setFitHeight(30);
		iv3.setFitWidth(30);
		backwardBtn.setGraphic(iv3);
		
		//Stylen des Eingabefeldes f�r die Samplesize
		txtfSampleSize.setPromptText("Anzahl Elemente");
		txtfSampleSize.setPrefWidth(110);
		
		algorithmus.setPromptText("Algorithmus");
		orders.setPromptText("Anordnung");
	}
	
	private void testComponents() {
		Rectangle rect1 = new Rectangle(300, 300, 100, 100);
		Rectangle rect2 = new Rectangle(320, 300, 100, 100);
		rect1.setFill(Color.RED);
		rect2.setFill(Color.GREEN);
		
		background.getChildren().addAll(rect1,rect2);
		
		if (rect1.intersects(rect2.getBoundsInLocal())) {
			rect2.setWidth(10);
		}
	}
	
	public ArrayList<Rectangle> generateUnits(int amount) {
		units = new ArrayList<>(amount);	
		for(int i = 0; i < amount; i++) {
			Rectangle tempUnit;
			
			//(...-40) damit man links und rechts einen Abstand vom mainWindow hat
			double width = ((mainWindowWidth - 40)/amount)-1;		
			double height = 400-((380/(amount-1))*i);
			tempUnit = new Rectangle(width, height);
			tempUnit.setFill(Color.WHITE);
			units.add(tempUnit);	
		}
		
		
		return units;
	}
	
	private void addUnitsToWindow() {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().add(units.get(i));
		}
		
	}
	
	public ArrayList<Rectangle> posRandom(ArrayList<Rectangle> units) {
		
		//Allgemeine initialisierung der Positionen n�tig da sonst keine bekannt
		posInOrder(units);
		
		
		/*Random rnd = new Random();
		
		//Damit die Units von Links nach Rechts der Gr��e nach geordnet werden
		reverseOrder(units); 
		
		for(int i = 0; i < units.length; i++) {
			double margin = i;
			double xPos = (20 + margin) + i * (units[i].getWidth());
			double yPos = 500 - units[i].getHeight();
			units[i].relocate(xPos, yPos);
		}*/
		return units;
	}
	
	public ArrayList<Rectangle> posInOrder(ArrayList<Rectangle> units) {
		//Damit die Units von Links nach Rechts der Gr��e nach geordnet werden
		reverseOrder(units);
		for(int i = 0; i < units.size(); i++) {
			double margin = i;
			double xPos = (20 + margin) + i * (units.get(i).getWidth());
			double yPos = 500 - units.get(i).getHeight();
			units.get(i).relocate(xPos, yPos);
		}
		return units;
	}
	
	public void reverseOrder(ArrayList<Rectangle> units) {
		Collections.reverse(units);		
	}
	
	
	
	private void addFunctionality() {
		
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(unitsAreGenerated) {		
					for(int i = 0; i < units.size(); i++) {
						background.getChildren().remove(units.get(i));
					}
				}
				int sampleSize = Integer.parseInt(txtfSampleSize.getText());
				
				generateUnits(sampleSize);
				addUnitsToWindow();
				//posInOrder(units);
				posRandom(units);
				unitsAreGenerated = true;
				
				txtfSampleSize.setText("");
				txtfSampleSize.setPromptText(Integer.toString(sampleSize));			
			}				
		});
	}
	
	
}
