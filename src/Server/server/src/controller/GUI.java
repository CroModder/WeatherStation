package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import socket.XtrinsicDemoApp;

public class GUI extends Application  implements Initializable{
	private SensorData sensorData;
	private static Timer segmentTimer = new Timer();
	private static XtrinsicDemoApp app = new XtrinsicDemoApp();
	
	@FXML
	private Pane tempPane;
	@FXML
	private Pane airPane;
	@FXML
	private Pane movePane;
	@FXML
	private Pane magPane;
	@FXML
	private Pane titlePane;
	@FXML
	private ImageView imageView;

	
	@Override
	public void start(Stage stage) throws Exception {
	    AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Display.fxml"));
		stage.setTitle("Weather station");
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                segmentTimer.cancel();
                segmentTimer.purge();
            }
        });  
	}

	
	public static void main(String[] args) {
	    Application.launch(args);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titlePane.setStyle(
    			"-fx-background-color: #3f51b5; " +
                "-fx-effect: dropshadow(three-pass-box, #8d8d8d, 10, 0, 0, 0);");
		sensorData = new SensorData(Color.RED, Color.rgb(236,223,223),0);
		sensorData.setLayoutX(60);
		sensorData.setLayoutY(105);
		sensorData.getTransforms().add(new Scale(0.53f, 0.53f, 0, 0));
        System.out.println("init " + tempPane);
        tempPane.getChildren().add(sensorData);
        sensorData = new SensorData(Color.RED, Color.rgb(236,223,223),1);
        sensorData.setLayoutX(60);
        sensorData.setLayoutY(105);
        sensorData.getTransforms().add(new Scale(0.53f, 0.53f, 0, 0));
        airPane.getChildren().add(sensorData);
        sensorData = new SensorData(Color.RED, Color.rgb(236,223,223),2);
        sensorData.setLayoutX(60);
        sensorData.setLayoutY(105);
        sensorData.getTransforms().add(new Scale(0.53f, 0.53f, 0, 0));
        movePane.getChildren().add(sensorData);
        sensorData = new SensorData(Color.RED, Color.rgb(236,223,223),3);
        sensorData.setLayoutX(60);
        sensorData.setLayoutY(105);
        sensorData.getTransforms().add(new Scale(0.53f, 0.53f, 0, 0));
        magPane.getChildren().add(sensorData);
        
       // app.startInit();
	}
	
	/*private static void log(String aMessage){
		System.out.println(aMessage);
	}*/
	  
	public static int getNthDigit(int number, int base, int n) {    
		return (int) ((number / Math.pow(base, n - 1)) % base);
	}
	
	public static class SensorData extends Parent {
		private static Digit[] digitsTemp;
		private static Digit[] digitsAir;
		private static Digit[] digitsMove;
		private static Digit[] digitsMag;

		public SensorData(Color onColor, Color offColor,Integer id) {
			InnerShadow offEffect = new InnerShadow();
			// create digits
			if(id == 0){
				digitsTemp = new Digit[3];
				for (int i = 0; i < 3; i++) {
					Digit digit = new Digit(onColor, offColor, offEffect);
					digit.setLayoutX(i * 60 + 20);
					System.out.println(digit.getLayoutX());
					digitsTemp[i] = digit;
					getChildren().add(digit);
				}
				refreshTemp(0);
			}else if(id == 1){
				digitsAir = new Digit[6];
				for (int i = 0; i < 6; i++) {
					Digit digit = new Digit(onColor, offColor, offEffect);
					digit.setLayoutX(i * 60 + 20);
					System.out.println(digit.getLayoutX());
					digitsAir[i] = digit;
					getChildren().add(digit);
				}
				refreshAir(0);
			}else if (id == 2){
            	digitsMove = new Digit[4];
                for (int i = 0; i < 4; i++) {
                    Digit digit = new Digit(onColor, offColor, offEffect);
                    digit.setLayoutX(i * 60 + 20);
                    System.out.println(digit.getLayoutX());
                    digitsMove[i] = digit;
                    getChildren().add(digit);
                }
                refreshMove(0);
            }else{
            	digitsMag = new Digit[12];
                for (int i = 0; i < 12; i++) {
                    Digit digit = new Digit(onColor, offColor, offEffect);
                    if(i >= 4 && i < 8){
                    	System.out.println(i+ "prvi if");
                    	digit.setLayoutX(i * 60 + 600);
                    }else if(i >= 8 && i < 12){
                    	System.out.println(i+ "drugi if");
                    	digit.setLayoutX(i * 60 + 1000);
                    }else{
                    	System.out.println(i+ "treci if");
                    	digit.setLayoutX(i * 60 + 150);
                    }
                    System.out.println(digit.getLayoutX());
                    digitsMag[i] = digit;
                    getChildren().add(digit);
                }
                refreshMag(0,0,0);
            }
			play();
		}
		
		private void refreshMag(int first,int second, int third){
			Integer signFirst = -1;//nothing || +
			Integer signSecond = -1;//nothing || +
			Integer signThird = -1;//nothing || +
			if(first < 0){//mx is negative number
				signFirst = -2;
				if(first < -99 && first > -1000){//mx has three digits
					digitsMag[0].showNumber(signFirst);
					digitsMag[1].showNumber(getNthDigit(Math.abs(first), 10, 3));
					digitsMag[2].showNumber(getNthDigit(Math.abs(first), 10, 2));
					digitsMag[3].showNumber(getNthDigit(Math.abs(first), 10, 1));
				}else if(first < -9 && first > -100){//mx has two digits
					digitsMag[0].showNumber(signFirst);
					digitsMag[1].showNumber(0);
					digitsMag[2].showNumber(getNthDigit(Math.abs(first), 10, 2));
					digitsMag[3].showNumber(getNthDigit(Math.abs(first), 10, 1));
				}else if(first <= 0 && first > -10){//mx has one digit
					digitsMag[0].showNumber(signFirst);
					digitsMag[1].showNumber(0);
					digitsMag[2].showNumber(0);
					digitsMag[3].showNumber(Math.abs(first));
				}
			}else{//mx is positive
				if(first > 99 && first < 1000){//mx has three digits
					digitsMag[0].showNumber(signFirst);
					digitsMag[1].showNumber(getNthDigit(first, 10, 3));
					digitsMag[2].showNumber( getNthDigit(first, 10, 2));
					digitsMag[3].showNumber( getNthDigit(first, 10, 1));
				}else if(first > 9 && first < 100){//mx has two digits
					digitsMag[0].showNumber(signFirst);
					digitsMag[1].showNumber(0);
					digitsMag[2].showNumber(getNthDigit(first, 10, 2));
					digitsMag[3].showNumber(getNthDigit(first, 10, 1));
				}else if(first >= 0 && first < 10){//mx has one digit
					digitsMag[0].showNumber(signFirst);
					digitsMag[1].showNumber(0);
					digitsMag[2].showNumber(0);
					digitsMag[3].showNumber(first);
				}
			}
			if(second < 0){
				signSecond = -2;
				if(second < -99 && second > -1000){//my has three digits
					digitsMag[4].showNumber(signSecond);
					digitsMag[5].showNumber(getNthDigit(Math.abs(second), 10, 3));
					digitsMag[6].showNumber(getNthDigit(Math.abs(second), 10, 2));
					digitsMag[7].showNumber(getNthDigit(Math.abs(second), 10, 1));
				}else if(second < -9 && second > -100){//my has two digits
					digitsMag[4].showNumber(signSecond);
					digitsMag[5].showNumber(0);
					digitsMag[6].showNumber(getNthDigit(Math.abs(second), 10, 2));
					digitsMag[7].showNumber(getNthDigit(Math.abs(second), 10, 1));
				}else if(second <= 0 && second > -10){//my has one digit
					digitsMag[4].showNumber(signSecond);
					digitsMag[5].showNumber(0);
					digitsMag[6].showNumber(0);
					digitsMag[7].showNumber(Math.abs(second));
				}
			}else{
				if(second > 99 && second < 1000){//my has three digits
					digitsMag[4].showNumber(signSecond);
					digitsMag[5].showNumber(getNthDigit(second, 10, 3));
					digitsMag[6].showNumber(getNthDigit(second, 10, 2));
					digitsMag[7].showNumber(getNthDigit(second, 10, 1));
				}else if(second > 9 && second < 100){//my has two digits
					digitsMag[4].showNumber(signSecond);
					digitsMag[5].showNumber(0);
					digitsMag[6].showNumber(getNthDigit(second, 10, 2));
					digitsMag[7].showNumber(getNthDigit(second, 10, 1));
				}else if(second >= 0 && second < 10){//my has one digit
					digitsMag[4].showNumber(signSecond);
					digitsMag[5].showNumber(0);
					digitsMag[6].showNumber(0);
					digitsMag[7].showNumber(second);
				}
			}
			if(third < 0){
				signThird = -2;
				if(third < -99 && third > -1000){//my has three digits
					digitsMag[8].showNumber(signThird);
					digitsMag[9].showNumber(getNthDigit(Math.abs(third), 10, 3));
					digitsMag[10].showNumber(getNthDigit(Math.abs(third), 10, 2));
					digitsMag[11].showNumber(getNthDigit(Math.abs(third), 10, 1));
				}else if(third < -9 && third > -100){//my has two digits
					digitsMag[8].showNumber(signThird);
					digitsMag[9].showNumber(0);
					digitsMag[10].showNumber(getNthDigit(Math.abs(third), 10, 2));
					digitsMag[11].showNumber(getNthDigit(Math.abs(third), 10, 1));
				}else if(third <= 0 && third > -10){//my has one digit
					digitsMag[8].showNumber(signThird);
					digitsMag[9].showNumber(0);
					digitsMag[10].showNumber(0);
					digitsMag[11].showNumber(Math.abs(third));
				}
			}else{
				if(third > 99 && third < 1000){//my has three digits
					digitsMag[8].showNumber(signThird);
					digitsMag[9].showNumber(getNthDigit(third, 10, 3));
					digitsMag[10].showNumber(getNthDigit(third, 10, 2));
					digitsMag[11].showNumber(getNthDigit(third, 10, 1));
				}else if(third > 9 && third < 100){//my has two digits
					digitsMag[8].showNumber(signThird);
					digitsMag[9].showNumber(0);
					digitsMag[10].showNumber(getNthDigit(third, 10, 2));
					digitsMag[11].showNumber(getNthDigit(third, 10, 1));
				}else if(third >= 0 && third < 10){//my has one digit
					digitsMag[8].showNumber(signThird);
					digitsMag[9].showNumber(0);
					digitsMag[10].showNumber(0);
					digitsMag[11].showNumber(third);
				}
			}
		}
		
		private void refreshMove(int first){
			int signMove = -1;
			if(first < 0){
				signMove = -2;
				if(first < -99 && first > -1000){//move has three digits
					digitsMove[0].showNumber(signMove);
					digitsMove[1].showNumber(getNthDigit(Math.abs(first), 10, 3));
					digitsMove[2].showNumber(getNthDigit(Math.abs(first), 10, 2));
					digitsMove[3].showNumber(getNthDigit(Math.abs(first), 10, 1));
				}else if(first < -9 && first > -100){//move has two digits
					digitsMove[0].showNumber(signMove);
					digitsMove[1].showNumber(0);
					digitsMove[2].showNumber(getNthDigit(Math.abs(first), 10, 2));
					digitsMove[3].showNumber(getNthDigit(Math.abs(first), 10, 1));
				}else if(first <= 0 && first > -10){//move has one digit
					digitsMove[0].showNumber(signMove);
					digitsMove[1].showNumber(0);
					digitsMove[2].showNumber(0);
					digitsMove[3].showNumber(Math.abs(first));
				}
			}else{
				if(first > 99 && first < 1000){//move has three digits
					digitsMove[0].showNumber(signMove);
					digitsMove[1].showNumber(getNthDigit(first, 10, 3));
					digitsMove[2].showNumber(getNthDigit(first, 10, 2));
					digitsMove[3].showNumber(getNthDigit(first, 10, 1));
				}else if(first > 9 && first < 100){//move has two digits
					digitsMove[0].showNumber(signMove);
					digitsMove[1].showNumber(0);
					digitsMove[2].showNumber(getNthDigit(first, 10, 2));
					digitsMove[3].showNumber(getNthDigit(first, 10, 1));
				}else if(first >= 0 && first < 10){//move has one digit
					digitsMove[0].showNumber(signMove);
					digitsMove[1].showNumber(0);
					digitsMove[2].showNumber(0);
					digitsMove[3].showNumber(first);
				}
			}

		}
		
		private void refreshAir(int first) {
			digitsAir[0].showNumber(getNthDigit(first, 10, 4));
			digitsAir[1].showNumber(getNthDigit(first, 10, 3));
			digitsAir[2].showNumber(getNthDigit(first, 10, 2));
			digitsAir[3].showNumber(getNthDigit(first, 10, 1));
			digitsAir[4].showNumber(getNthDigit(first, 10, 1));
			digitsAir[5].showNumber(getNthDigit(first, 10, 1));
		}

		private void refreshTemp(int first) {
			int signTemp = -1;
			if(first < 0){
				signTemp = -2;
				digitsTemp[0].showNumber(signTemp);
				digitsTemp[1].showNumber(getNthDigit(Math.abs(first), 10, 2));
				digitsTemp[2].showNumber(getNthDigit(Math.abs(first), 10, 1));
			}else{
				digitsTemp[0].showNumber(signTemp);
				digitsTemp[1].showNumber(getNthDigit(first, 10, 2));
				digitsTemp[2].showNumber(getNthDigit(first, 10, 1));
			}
		}


		public void play() {
		    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
		    	/***********RANDOM NUM GEN -10 <-> +99***********/
		    /*	int START = -999;
				int END = 999;
				Random random = new Random();
				if (START > END) {
					throw new IllegalArgumentException("Start cannot exceed End.");
				}
				long range = (long)END - (long)START + 1;
				long fraction = (long)(range * random.nextDouble());
				int randomNumber =  (int)(fraction + START);  */
				/***********************/
				//log("Generated : " + randomNumber);
			//	refreshMag(randomNumber,randomNumber,randomNumber);
			//	refreshMove(randomNumber);
			//	refreshAir(1024);
			//	refreshTemp(-5);
				/*System.out.println("Temp : " + (int) app.getTempData().doubleValue());
				System.out.println("Air :" + (int) app.getAirData().doubleValue());
    			System.out.println("Move : " + app.getZ());
    			System.out.println("Magx :" + app.getX());
    			System.out.println("Magy :" + app.getY());
    			System.out.println("Magz :" + app.getZ());*/
    			
		    }));
		    timeline.setCycleCount(Animation.INDEFINITE);
		    timeline.play();
		}
    }

    public static final class Digit extends Parent {
		static Integer numBer;
        private static final boolean[][] DIGIT_COMBINATIONS = new boolean[][]{
                new boolean[]{true, false, true, true, true, true, true},//0
                new boolean[]{false, false, false, false, true, false, true},//1
                new boolean[]{true, true, true, false, true, true, false},//2
                new boolean[]{true, true, true, false, true, false, true},//3
                new boolean[]{false, true, false, true, true, false, true},//4
                new boolean[]{true, true, true, true, false, false, true},//5
                new boolean[]{true, true, true, true, false, true, true},//6
                new boolean[]{true, false, false, false, true, false, true},//7
                new boolean[]{true, true, true, true, true, true, true},//8
                new boolean[]{true, true, true, true, true, false, true},//9
                new boolean[]{false, false, false, false, false, false, false},//+
                new boolean[]{false, true, false, false, false, false, false}};//-
        private final Polygon[] polygons = new Polygon[]{
                new Polygon(2, 0, 52, 0, 42, 10, 12, 10),
                new Polygon(12, 49, 42, 49, 52, 54, 42, 59, 12f, 59f, 2f, 54f),
                new Polygon(12, 98, 42, 98, 52, 108, 2, 108),
                new Polygon(0, 2, 10, 12, 10, 47, 0, 52),
                new Polygon(44, 12, 54, 2, 54, 52, 44, 47),
                new Polygon(0, 56, 10, 61, 10, 96, 0, 106),
                new Polygon(44, 61, 54, 56, 54, 106, 44, 96)};
        private final Color onColor;
        private final Color offColor;


        public Digit(Color onColor, Color offColor,Effect offEffect) {
            this.onColor = onColor;
            this.offColor = offColor;
            getChildren().addAll(polygons);
            getTransforms().add(new Shear(-0.1,0));
            showNumber(0);
        }

		public void showNumber(Integer num) {
			//TODO LATER
			numBer = num;
	    	 if (numBer < 0 || numBer > 9){
	         	if(numBer == -1){
	         		numBer = 10;
	         	}else if(numBer == -2){
	         		numBer = 11;
	         	}else{
	         		numBer = 0;
	         	} // default to 0 for non-valid numbers
	         }
	         for (int i = 0; i < 7; i++) {
	        	 if(DIGIT_COMBINATIONS[numBer][i] == true){
	        		 polygons[i].setFill(onColor);
	        	 }else{
	        		 polygons[i].setFill(offColor);
	        	 }
	         }
        }
    }
}