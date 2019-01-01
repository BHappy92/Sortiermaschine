package view.mainwindow;

import java.util.Collections;
import java.util.Vector;

import control.unitControl.Swap;
import control.unitControl.UnitControl;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainWindow {
	
	private static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	
	//public static double mainWindowHeight = primaryScreenBounds.getHeight();
	//public static double mainWindowWidth  = primaryScreenBounds.getWidth();
	public static double mainWindowHeight = 600;
	public static double mainWindowWidth  = 1200;
	
	private Stage primaryStage;
	private BorderPane root;
	private Scene scene;
	private HBox menuBox;
	private Pane background;	
	private Button startBtn;
	private Button backwardBtn;
	private Button pauseBtn;
	private Button forwardBtn;
	private ToggleButton stepByStepToggle;
	private TextField txtfSampleSize;
	private ComboBox<String> algorithmsCB;
	private ComboBox<String> ordersCB;
	private Button testBtn;
	private Label speedLbl;	
	private int speed;
	private Vector<Rectangle> units;
	private Vector<Swap> swaps;
	private boolean unitsAreGenerated = false;
	
	
	
	public MainWindow() {
		
		initComponents();	
		posComponents();
		styleComponents();
		addFunctionality();
		//testComponents();
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		
		
		

        //set Stage boundaries to visible bounds of the main screen
       // primaryStage.setX(primaryScreenBounds.getMinX());
      //  primaryStage.setY(primaryScreenBounds.getMinY());
       // primaryStage.setWidth(primaryScreenBounds.getWidth());
      //  primaryStage.setHeight(primaryScreenBounds.getHeight());
        
		primaryStage.show();
		
	}
	private void initComponents() {
		//Initialisierung der Oberflächenkomponenten
		//Oberfläche wird erzeugt
		primaryStage 		= new Stage();
		root			= new BorderPane();
		menuBox				= new HBox();
		background 			= new Pane();
		//scene 				= new Scene(background, mainWindowWidth, mainWindowHeight);
		scene 				= new Scene(root, mainWindowWidth, mainWindowHeight);
		startBtn			= new Button("START");
		backwardBtn			= new Button();
		pauseBtn 			= new Button();
		forwardBtn			= new Button();
		stepByStepToggle	= new ToggleButton("Step-By-Step");
		txtfSampleSize		= new TextField();
		algorithmsCB		= new ComboBox<>();
		
		new Vector<Swap>();
			algorithmsCB.getItems().addAll(
							"BubbleSort",
							"Quicksort",
							"Countingsort" );
		
		ordersCB 			= new ComboBox<>();
			ordersCB.getItems().addAll(
							"Ordered",
							"Random"
					);
			ordersCB.getSelectionModel().select(1);;
		testBtn				= new Button("Test");
		
		speedLbl			= new Label("Speed: "+speed);
		//Hier neue Elemente zum Hauptfenster hinzufügen
		//vvvvvvvvvvvvvvvvvvvvvvv
		background.getChildren().addAll(startBtn, backwardBtn, pauseBtn, forwardBtn, stepByStepToggle, txtfSampleSize, algorithmsCB, ordersCB, testBtn, speedLbl);
		
		//Sonstige Initialisierungen
		root.setTop(menuBox);
		
		// vvvvvvvvv Wenn ich die Elemente in die HBox einfüge fehlt mir immer das kleinste Unit-Element im Fenster
		//menuBox.getChildren().addAll(startBtn, backwardBtn, pauseBtn, forwardBtn, stepByStepToggle, txtfSampleSize, algorithmsCB, ordersCB, testBtn);
		menuBox.setPrefSize(mainWindowWidth, (1/5)*mainWindowHeight);
		
		root.setCenter(background);
		background.setPrefSize(mainWindowWidth, (4/5)*mainWindowHeight);

		txtfSampleSize.setPromptText("Anzahl Elemente");
		txtfSampleSize.setPrefWidth(55);
		
		algorithmsCB.setPromptText("Algorithmus");
		ordersCB.setPromptText("Anordnung");
		
		
	}
	private void posComponents() {
		startBtn.relocate(5, 5);
		backwardBtn.relocate(700, 5);
		pauseBtn.relocate(760, 5);
		forwardBtn.relocate(820, 5);
		stepByStepToggle.relocate(900, 5);
		txtfSampleSize.relocate(500, 5);
		algorithmsCB.relocate(250, 5);
		ordersCB.relocate(100, 5);
		testBtn.relocate(400, 5);
		speedLbl.relocate(580, 5);
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
		
		menuBox.setStyle("-fx-background-color: black");
		menuBox.setPadding(new Insets(5, 10, 5, 10));
		
		speedLbl.setTextFill(Color.WHITE);
	}
	private void testComponents() {		
		UnitControl.swapUnit(units, 1, 3);	
	}
	public static void delayms(int delay) {
		long now = 0;
		long later = System.currentTimeMillis()+delay;
		while (now < later) {
			now = System.currentTimeMillis();
			
		}
		
		
	}
	
	public void bubbleSort() {	
		int count = 0;
		boolean swapped = false; //Vermerkt ob Vertauschung im Durchlauf
		do { //Beginn des Durchlaufs
			swapped = false;
			for(int i = 0; i < units.size() - 1; i++) {
				if(units.get(i).getHeight() > units.get(i+1).getHeight()) {
					UnitControl.swapUnit(units, i, i+1);
					swapped = true;
					
					
				}
				
			}
			
		} while(swapped);
	}
	
	Service<Void> sort = new Service<Void>() {

		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					
					for(int i = 0; i < swaps.size(); i++) {
						int left = swaps.get(i).getLeft();
						int right = swaps.get(i).getRight();
						units.get(left).setFill(Color.RED);
						units.get(right).setFill(Color.GREEN);
						Thread.sleep(speed/3);
						UnitControl.swapUnit(units, left, right);
						units.get(left).setFill(Color.GREEN);
						units.get(right).setFill(Color.RED);
						Thread.sleep(speed/3);
						units.get(left).setFill(Color.WHITE);
						units.get(right).setFill(Color.WHITE);
						Thread.sleep(speed/3);
					}
					return null;	
					
				}							
			};
		}					
	}; 
	public void initOrder() {
		switch(ordersCB.getValue()) {
		case "Ordered":
			UnitControl.posNormal(units); 
			break;
			
		case "Random":
			UnitControl.posRandom(units); 
			swaps = UnitControl.initSwaps(units);
			break;
			
		default: 
			if(ordersCB.getValue() == null)
				UnitControl.posRandom(units);
			break;
		}
	}
	
	
	private void addFunctionality() {	
		startBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(unitsAreGenerated) {		
					UnitControl.removeUnitsFromWindow(background, units);
				}
				int sampleSize = Integer.parseInt(txtfSampleSize.getText());
				units = UnitControl.generateUnits(sampleSize);
				
				UnitControl.addUnitsToWindow(background, units);
				
				initOrder();
				
				txtfSampleSize.setText("");
				txtfSampleSize.setPromptText(Integer.toString(sampleSize));
				unitsAreGenerated = true;
				speed = 250;
				speedLbl.setText("Speed: "+speed);
				//bubbleSort();
						
			}				
		});
		
		testBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//bubbleSort();
				
				if(sort.isRunning()) {
					sort.cancel();
				}
				sort.restart();
				
			}
		});	
		
		forwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				speed -= 10;
				speedLbl.setText("Speed: "+speed);
			}
		});
		
		backwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				speed += 10;
				speedLbl.setText("Speed: "+speed);
			}
		});
	}	
}
