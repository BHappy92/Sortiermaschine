package view.mainwindow;

import java.util.Collections;
import java.util.Vector;

import control.unitControl.Koordinate;
import control.unitControl.Positionen;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;





public class MainWindow {
	
	public static double mainWindowHeight = 800;
	public static double mainWindowWidth  = 1500;
	
	private Stage primaryStage;
	private BorderPane mainArea;
	private HBox menuBox;
	private Pane background;	
	private Scene scene;
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
	private Vector<Koordinate> swaps;
	//private Positionen positionen;
	private boolean unitsAreGenerated = false;
	
	public MainWindow() {
		
		initComponents();	
		posComponents();
		styleComponents();
		addFunctionality();
		//testComponents();
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	private void initComponents() {
		//Initialisierung der Oberflächenkomponenten
		//Oberfläche wird erzeugt
		primaryStage 		= new Stage();
		mainArea			= new BorderPane();
		menuBox				= new HBox();
		background 			= new Pane();
		//scene 				= new Scene(background, mainWindowWidth, mainWindowHeight);
		scene 				= new Scene(mainArea, mainWindowWidth, mainWindowHeight);
		startBtn			= new Button("START");
		backwardBtn			= new Button();
		pauseBtn 			= new Button();
		forwardBtn			= new Button();
		stepByStepToggle	= new ToggleButton("Step-By-Step");
		txtfSampleSize		= new TextField();
		algorithmsCB		= new ComboBox<>();
		swaps 				= new Vector<Koordinate>();
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
		mainArea.setTop(menuBox);
		
		// vvvvvvvvv Wenn ich die Elemente in die HBox einfüge fehlt mir immer das kleinste Unit-Element im Fenster
		//menuBox.getChildren().addAll(startBtn, backwardBtn, pauseBtn, forwardBtn, stepByStepToggle, txtfSampleSize, algorithmsCB, ordersCB, testBtn);
		menuBox.setPrefSize(mainWindowWidth, (1/5)*mainWindowHeight);
		
		mainArea.setCenter(background);
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
		swapUnit(1, 3);	
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
					swapUnit(i, i+1);
					swapped = true;
					
					
				}
				
			}
			
		} while(swapped);
	}
	public void generateUnits(int amount) {
		units = new Vector<>(amount);	
		for(int i = 0; i < amount; i++) {
			Rectangle tempUnit;
			
			//(...-40) damit man links und rechts einen Abstand vom mainWindow hat
			double width = ((mainWindowWidth - 40)/amount)-1;		
			double height = 400-((380/(amount-1))*i);
			tempUnit = new Rectangle(width, height);
			tempUnit.setFill(Color.WHITE);
			units.add(tempUnit);	
		}	
	}
	public void posRandom() {
		//Hilfsvektor um die Positionen zu speichern
		Vector<Double> xPositios = new Vector<>();
		posNormal();
		
		for(int i = 0; i < units.size(); i++) {
			double tempPos = units.get(i).getLayoutX();
			xPositios.add(tempPos);
		}

		Collections.shuffle(units);
		
		for (int i = 0; i < units.size(); i++) {
			units.get(i).setLayoutX(xPositios.get(i));;
		}
		
	}	
	public void posNormal() {
		//Damit die Units von Links nach Rechts der Größe nach geordnet werden
		reverseOrder();
		
		for(int i = 0; i < units.size(); i++) {
			double margin = i;
			double xPos = (20 + margin) + i * (units.get(i).getWidth());
			double yPos = mainWindowHeight - units.get(i).getHeight();
			units.get(i).relocate(xPos, yPos);
		}	
	}	
	public void swapUnit(int left, int right) {	
		double leftXPos = units.get(left).getLayoutX();
		double rightXPos = units.get(right).getLayoutX();
		Collections.swap(units, left, right);
		units.get(left).setLayoutX(leftXPos);
		units.get(right).setLayoutX(rightXPos);
		
	}	
	
	public void swapUnit(Vector<Rectangle> units, int left, int right) {
		double leftXPos = units.get(left).getLayoutX();
		double rightXPos = units.get(right).getLayoutX();
		Collections.swap(units, left, right);
		units.get(left).setLayoutX(leftXPos);
		units.get(right).setLayoutX(rightXPos);
	}
	public void reverseOrder() {
		Collections.reverse(units);		
	}	
	public void addUnitsToWindow() {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().add(units.get(i));
		}
		
	}
	public void addUnitToWindow(int index) {
		background.getChildren().add(units.get(index));
	}
	public void removeUnitsFromWindow() {
		for(int i = 0; i < units.size(); i++) {
			background.getChildren().remove(units.get(i));
		}
	}	
	public void removeUnitFromWindow(int index) {
		background.getChildren().remove(units.get(index));
	}
	//----------------------------Beustelle------------------------------
	public void initSwaps() {
		
		Positionen positionen = new Positionen(units);
	
		boolean swapped = false; //Vermerkt ob Vertauschung im Durchlauf
		do { //Beginn des Durchlaufs
			swapped = false;
			for(int i = 0; i < positionen.size() - 1; i++) {
				int left = i;
				int right = i+1;
				
				if(positionen.getY(left) > positionen.getY(right)) {
					swaps.add(new Koordinate(left, right));
					Positionen.swapX(positionen.getAllX(), left, right);
					Positionen.swapY(positionen.getAllY(), left, right);
					//System.out.println("Cunt:" + cunt);
					swapped = true;
				}	
			}
			
		} while(swapped);
	}
	//-------------------------------------------------------------------
	Service<Void> service = new Service<Void>() {

		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					for (int i = 0; i < swaps.size(); i++) {
						int left = swaps.get(i).getLeft();
						int right = swaps.get(i).getRight();
						swapUnit(left, right);
						Thread.sleep(speed);
					}
					return null;				
				}							
			};
		}					
	}; 
	public void initOrder() {
		switch(ordersCB.getValue()) {
		case "Ordered":
			posNormal(); break;
			
		case "Random":
			posRandom(); break;
			
		default: 
			if(ordersCB.getValue() == null)
				posNormal();
			break;
		}
	}
	
	
	private void addFunctionality() {	
		startBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(unitsAreGenerated) {		
					removeUnitsFromWindow();
				}
				int sampleSize = Integer.parseInt(txtfSampleSize.getText());
				generateUnits(sampleSize);
				addUnitsToWindow();
				initOrder();
				initSwaps();
				
				txtfSampleSize.setText("");
				txtfSampleSize.setPromptText(Integer.toString(sampleSize));
				unitsAreGenerated = true;
				speed = 500;
				speedLbl.setText("Speed: "+speed);
				//bubbleSort();
						
			}				
		});
		
		testBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//bubbleSort();
				
				if(service.isRunning()) {
					service.cancel();
				}
				service.restart();
				
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
