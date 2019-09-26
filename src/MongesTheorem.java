import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.Cursor;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MongesTheorem extends Application {
	private double deltaX;
	private double deltaY;
	private double lastX;
	private double lastY;
	private double lastR;
	
	private static final int width = (int) Screen.getPrimary().getVisualBounds().getWidth();
	private static final int height = (int) Screen.getPrimary().getVisualBounds().getHeight();
	
	private static Slider slider1 = new Slider(0.01, 0.5, 0.05);
	private static Slider slider2 = new Slider(0.01, 0.5, 0.03);
	private static Slider slider3 = new Slider(0.01, 0.5, 0.1);
	
	private static Circle circle1 = new Circle(width / 2.5, height / 3, slider1.getValue() * width);
	private static Circle circle2 = new Circle(width / 3, height / 2, slider2.getValue() * width);
	private static Circle circle3 = new Circle(width / 1.3, height / 1.8, slider3.getValue() * width);
	
	private static Line line1 = new Line();
	private static Line line2 = new Line();
	private static Line line3 = new Line();
	private static Line line4 = new Line();
	private static Line line5 = new Line();
	private static Line line6 = new Line();
	
	private static Circle point1 = new Circle(5);
	private static Circle point2 = new Circle(5);
	private static Circle point3 = new Circle(5);
	
	private static Line line7 = new Line();
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		circle1.setFill(Color.TRANSPARENT);
		circle1.setStroke(Color.BLACK);
		circle1.setStrokeWidth(5);
		
		circle2.setFill(Color.TRANSPARENT);
		circle2.setStroke(Color.BLACK);
		circle2.setStrokeWidth(5);
		
		circle3.setFill(Color.TRANSPARENT);
		circle3.setStroke(Color.BLACK);
		circle3.setStrokeWidth(5);
		
		Tooltip tooltip1 = new Tooltip("Circle 1");
		Tooltip tooltip2 = new Tooltip("Circle 2");
		Tooltip tooltip3 = new Tooltip("Circle 3");
		
		Tooltip.install(circle1, tooltip1);
		Tooltip.install(circle2, tooltip2);
		Tooltip.install(circle3, tooltip3);
		
		Tooltip.install(slider1, tooltip1);
		Tooltip.install(slider2, tooltip2);
		Tooltip.install(slider3, tooltip3);
		
		line1.setStroke(Color.BLUE);
		line2.setStroke(Color.BLUE);
		line3.setStroke(Color.BLUE);
		line4.setStroke(Color.BLUE);
		line5.setStroke(Color.BLUE);
		line6.setStroke(Color.BLUE);
		
		line7.setStroke(Color.RED);
		line7.setStrokeWidth(2);
		
		point1.setFill(Color.RED);
		point2.setFill(Color.RED);
		point3.setFill(Color.RED);
		
		draw();
		
		circle1.setOnMousePressed(e -> {
			deltaX = circle1.getCenterX() - e.getSceneX();
			deltaY = circle1.getCenterY() - e.getSceneY();
		});
		
		circle1.setOnMouseDragged(e -> {
			lastX = circle1.getCenterX();
			lastY = circle1.getCenterY();
			
			circle1.setCenterX(e.getSceneX() + deltaX);
			circle1.setCenterY(e.getSceneY() + deltaY);
			
			if(Math.sqrt(Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2)) <= circle1.getRadius() + circle2.getRadius() + 5 || Math.sqrt(Math.pow(circle3.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle1.getCenterY(), 2)) <= circle1.getRadius() + circle3.getRadius() + 5) {
				circle1.setCenterX(lastX);
				circle1.setCenterY(lastY);
			} else {
				draw();
			}
		});
		
		circle1.setOnMouseEntered(e -> circle1.setCursor(Cursor.HAND));
		
		circle2.setOnMousePressed(e -> {
			deltaX = circle2.getCenterX() - e.getSceneX();
			deltaY = circle2.getCenterY() - e.getSceneY();
		});
		
		circle2.setOnMouseDragged(e -> {
			lastX = circle2.getCenterX();
			lastY = circle2.getCenterY();
			
			circle2.setCenterX(e.getSceneX() + deltaX);
			circle2.setCenterY(e.getSceneY() + deltaY);
			
			if(Math.sqrt(Math.pow(circle1.getCenterX() - circle2.getCenterX(), 2) + Math.pow(circle1.getCenterY() - circle2.getCenterY(), 2)) <= circle2.getRadius() + circle1.getRadius() + 5 || Math.sqrt(Math.pow(circle3.getCenterX() - circle2.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle2.getCenterY(), 2)) <= circle2.getRadius() + circle3.getRadius() + 5) {
				circle2.setCenterX(lastX);
				circle2.setCenterY(lastY);
			} else {
				draw();
			}
		});
		
		circle2.setOnMouseEntered(e -> circle2.setCursor(Cursor.HAND));
		
		circle3.setOnMousePressed(e -> {
			deltaX = circle3.getCenterX() - e.getSceneX();
			deltaY = circle3.getCenterY() - e.getSceneY();
		});
		
		circle3.setOnMouseDragged(e -> {
			lastX = circle3.getCenterX();
			lastY = circle3.getCenterY();
			
			circle3.setCenterX(e.getSceneX() + deltaX);
			circle3.setCenterY(e.getSceneY() + deltaY);
			
			if(Math.sqrt(Math.pow(circle1.getCenterX() - circle3.getCenterX(), 2) + Math.pow(circle1.getCenterY() - circle3.getCenterY(), 2)) <= circle3.getRadius() + circle1.getRadius() + 5 || Math.sqrt(Math.pow(circle2.getCenterX() - circle3.getCenterX(), 2) + Math.pow(circle2.getCenterY() - circle3.getCenterY(), 2)) <= circle3.getRadius() + circle2.getRadius() + 5) {
				circle3.setCenterX(lastX);
				circle3.setCenterY(lastY);
			} else {
				draw();
			}
		});
		
		circle3.setOnMouseEntered(e -> circle3.setCursor(Cursor.HAND));
		
		slider1.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
            public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
				if(Math.sqrt(Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2)) > circle1.getRadius() + circle2.getRadius() + 5 || Math.sqrt(Math.pow(circle3.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle1.getCenterY(), 2)) > circle1.getRadius() + circle3.getRadius() + 5) {
					lastR = circle1.getRadius();
				}
				
				circle1.setRadius(((Double) newVal).doubleValue() * width);
				
				if(Math.sqrt(Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2)) <= circle1.getRadius() + circle2.getRadius() + 5 || Math.sqrt(Math.pow(circle3.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle1.getCenterY(), 2)) <= circle1.getRadius() + circle3.getRadius() + 5) {
					circle1.setRadius(lastR);
				} else {
					draw();
				}
			}
		});
		
		slider2.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
            public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
				if(Math.sqrt(Math.pow(circle1.getCenterX() - circle2.getCenterX(), 2) + Math.pow(circle1.getCenterY() - circle2.getCenterY(), 2)) > circle2.getRadius() + circle1.getRadius() + 5 || Math.sqrt(Math.pow(circle3.getCenterX() - circle2.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle2.getCenterY(), 2)) > circle2.getRadius() + circle3.getRadius() + 5) {
					lastR = circle2.getRadius();
				}
				
				circle2.setRadius(((Double) newVal).doubleValue() * width);
				
				if(Math.sqrt(Math.pow(circle1.getCenterX() - circle2.getCenterX(), 2) + Math.pow(circle1.getCenterY() - circle2.getCenterY(), 2)) <= circle2.getRadius() + circle1.getRadius() + 5 || Math.sqrt(Math.pow(circle3.getCenterX() - circle2.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle2.getCenterY(), 2)) <= circle2.getRadius() + circle3.getRadius() + 5) {
					circle2.setRadius(lastR);
				} else {
					draw();
				}
			}
		});
		
		slider3.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
            public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
				if(Math.sqrt(Math.pow(circle1.getCenterX() - circle3.getCenterX(), 2) + Math.pow(circle1.getCenterY() - circle3.getCenterY(), 2)) > circle3.getRadius() + circle1.getRadius() + 5 || Math.sqrt(Math.pow(circle2.getCenterX() - circle3.getCenterX(), 2) + Math.pow(circle2.getCenterY() - circle3.getCenterY(), 2)) > circle3.getRadius() + circle2.getRadius() + 5) {
					lastR = circle3.getRadius();
				}
				
				circle3.setRadius(((Double) newVal).doubleValue() * width);
				
				if(Math.sqrt(Math.pow(circle1.getCenterX() - circle3.getCenterX(), 2) + Math.pow(circle1.getCenterY() - circle3.getCenterY(), 2)) <= circle3.getRadius() + circle1.getRadius() + 5 || Math.sqrt(Math.pow(circle2.getCenterX() - circle3.getCenterX(), 2) + Math.pow(circle2.getCenterY() - circle3.getCenterY(), 2)) <= circle3.getRadius() + circle2.getRadius() + 5) {
					circle3.setRadius(lastR);
				} else {
					draw();
				}
			}
		});
		
		HBox hBoxSliders = new HBox(slider1, slider2, slider3);
		
		Label watermarkLabel = new Label("Created by Robert D. Rioja");
		watermarkLabel.setFont(Font.font("Verdana", 20));
		watermarkLabel.setLayoutX(5);
		watermarkLabel.setStyle("-fx-background-color: white;");
		
		Pane paneMain = new Pane(circle1, circle2, circle3, line1, line2, line3, line4, line5, line6, line7, point1, point2, point3, watermarkLabel);
		
		VBox vBoxMain = new VBox(hBoxSliders, paneMain);
		
		Rectangle rectangleClip = new Rectangle();
		
		rectangleClip.widthProperty().bind(primaryStage.widthProperty());
		rectangleClip.heightProperty().bind(vBoxMain.heightProperty().subtract(hBoxSliders.heightProperty()));
		
		paneMain.setClip(rectangleClip);
		
		Scene scene = new Scene(vBoxMain);
		
		watermarkLabel.layoutYProperty().bind(scene.heightProperty().subtract(40));
		
		primaryStage.setTitle("Monge's Theorem");
        primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		
		slider1.prefWidthProperty().bind(primaryStage.widthProperty().divide(3));
		slider2.prefWidthProperty().bind(primaryStage.widthProperty().divide(3));
		slider3.prefWidthProperty().bind(primaryStage.widthProperty().divide(3));
		
		primaryStage.show();
	}
	
	private static void draw() {
		double gamma, beta, a, x1, y1, x2, y2, m, b, m1, b1, m2, b2, m3, b3, m4, b4, point1M, point1B, point2M, point2B;
		
		//Line1
		gamma = -1 * Math.atan((circle2.getCenterY() - circle1.getCenterY()) / (circle2.getCenterX() - circle1.getCenterX()));
		beta = Math.asin((circle2.getRadius() - circle1.getRadius()) / Math.sqrt(Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2)));
		
		if(circle1.getCenterX() > circle2.getCenterX()) {
			a = gamma + beta;
		} else {
			a = gamma - beta;
		}
		
		x1 = circle1.getCenterX() + circle1.getRadius() * Math.cos((Math.PI / 2) - a);
		y1 = circle1.getCenterY() + circle1.getRadius() * Math.sin((Math.PI / 2) - a);
		x2 = circle2.getCenterX() + circle2.getRadius() * Math.cos((Math.PI / 2) - a);
		y2 = circle2.getCenterY() + circle2.getRadius() * Math.sin((Math.PI / 2) - a);
		m = (y2 - y1) / (x2 - x1);
		b = y1 - m * x1;
		
		point1M = m;
		point1B = b;
		
		if(m > 0) {
			line1.setStartX(0);
			line1.setStartY(b);
			line1.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line1.setEndY(m * Screen.getPrimary().getVisualBounds().getWidth() + b);
		} else if(m < 0) {
			line1.setStartX(0);
			line1.setStartY(b);
			line1.setEndX((-1 * b) / m);
			line1.setEndY(0);
		} else {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line7.setEndY(b);
		}
		
		//Line 2
		gamma = -1 * Math.atan((circle2.getCenterY() - circle1.getCenterY()) / (circle2.getCenterX() - circle1.getCenterX()));
		beta = Math.asin((circle2.getRadius() - circle1.getRadius()) / Math.sqrt(Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2)));
		
		if(circle1.getCenterX() > circle2.getCenterX()) {
			a = gamma - beta;
		} else {
			a = gamma + beta;
		}
		
		x1 = circle1.getCenterX() - circle1.getRadius() * Math.cos((Math.PI / 2) - a);
		y1 = circle1.getCenterY() - circle1.getRadius() * Math.sin((Math.PI / 2) - a);
		x2 = circle2.getCenterX() - circle2.getRadius() * Math.cos((Math.PI / 2) - a);
		y2 = circle2.getCenterY() - circle2.getRadius() * Math.sin((Math.PI / 2) - a);
		m = (y2 - y1) / (x2 - x1);
		b = y1 - m * x1;
		
		point2M = m;
		point2B = b;
		
		if(m > 0) {
			line2.setStartX(0);
			line2.setStartY(b);
			line2.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line2.setEndY(m * Screen.getPrimary().getVisualBounds().getWidth() + b);
		} else if(m < 0) {
			line2.setStartX(0);
			line2.setStartY(b);
			line2.setEndX((-1 * b) / m);
			line2.setEndY(0);
		} else {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line7.setEndY(b);
		}
		
		//Point 1
		x1 = (point1B - point2B) / (point2M - point1M);
		y1 = point1M * x1 + point1B;
		
		point1.setCenterX(x1);
		point1.setCenterY(y1);
		
		//Line 3
		gamma = -1 * Math.atan((circle3.getCenterY() - circle1.getCenterY()) / (circle3.getCenterX() - circle1.getCenterX()));
		beta = Math.asin((circle3.getRadius() - circle1.getRadius()) / Math.sqrt(Math.pow(circle3.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle1.getCenterY(), 2)));
		
		if(circle1.getCenterX() > circle3.getCenterX()) {
			a = gamma + beta;
		} else {
			a = gamma - beta;
		}
		
		x1 = circle1.getCenterX() + circle1.getRadius() * Math.cos((Math.PI / 2) - a);
		y1 = circle1.getCenterY() + circle1.getRadius() * Math.sin((Math.PI / 2) - a);
		x2 = circle3.getCenterX() + circle3.getRadius() * Math.cos((Math.PI / 2) - a);
		y2 = circle3.getCenterY() + circle3.getRadius() * Math.sin((Math.PI / 2) - a);
		m = (y2 - y1) / (x2 - x1);
		b = y1 - m * x1;
		
		point1M = m;
		point1B = b;
		
		if(m > 0) {
			line3.setStartX(0);
			line3.setStartY(b);
			line3.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line3.setEndY(m * Screen.getPrimary().getVisualBounds().getWidth() + b);
		} else if(m < 0) {
			line3.setStartX(0);
			line3.setStartY(b);
			line3.setEndX((-1 * b) / m);
			line3.setEndY(0);
		} else {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line7.setEndY(b);
		}
		
		//Line 4
		gamma = -1 * Math.atan((circle3.getCenterY() - circle1.getCenterY()) / (circle3.getCenterX() - circle1.getCenterX()));
		beta = Math.asin((circle3.getRadius() - circle1.getRadius()) / Math.sqrt(Math.pow(circle3.getCenterX() - circle1.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle1.getCenterY(), 2)));
		
		if(circle1.getCenterX() > circle3.getCenterX()) {
			a = gamma - beta;
		} else {
			a = gamma + beta;
		}
		
		x1 = circle1.getCenterX() - circle1.getRadius() * Math.cos((Math.PI / 2) - a);
		y1 = circle1.getCenterY() - circle1.getRadius() * Math.sin((Math.PI / 2) - a);
		x2 = circle3.getCenterX() - circle3.getRadius() * Math.cos((Math.PI / 2) - a);
		y2 = circle3.getCenterY() - circle3.getRadius() * Math.sin((Math.PI / 2) - a);
		m = (y2 - y1) / (x2 - x1);
		b = y1 - m * x1;
		
		point2M = m;
		point2B = b;
		
		if(m > 0) {
			line4.setStartX(0);
			line4.setStartY(b);
			line4.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line4.setEndY(m * Screen.getPrimary().getVisualBounds().getWidth() + b);
		} else if(m < 0) {
			line4.setStartX(0);
			line4.setStartY(b);
			line4.setEndX((-1 * b) / m);
			line4.setEndY(0);
		} else {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line7.setEndY(b);
		}
		
		//Point 2
		x1 = (point1B - point2B) / (point2M - point1M);
		y1 = point1M * x1 + point1B;
		
		point2.setCenterX(x1);
		point2.setCenterY(y1);
		
		//Line 5
		gamma = -1 * Math.atan((circle3.getCenterY() - circle2.getCenterY()) / (circle3.getCenterX() - circle2.getCenterX()));
		beta = Math.asin((circle3.getRadius() - circle2.getRadius()) / Math.sqrt(Math.pow(circle3.getCenterX() - circle2.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle2.getCenterY(), 2)));
		
		if(circle2.getCenterX() > circle3.getCenterX()) {
			a = gamma + beta;
		} else {
			a = gamma - beta;
		}
		
		x1 = circle2.getCenterX() + circle2.getRadius() * Math.cos((Math.PI / 2) - a);
		y1 = circle2.getCenterY() + circle2.getRadius() * Math.sin((Math.PI / 2) - a);
		x2 = circle3.getCenterX() + circle3.getRadius() * Math.cos((Math.PI / 2) - a);
		y2 = circle3.getCenterY() + circle3.getRadius() * Math.sin((Math.PI / 2) - a);
		m = (y2 - y1) / (x2 - x1);
		b = y1 - m * x1;
		
		point1M = m;
		point1B = b;
		
		if(m > 0) {
			line5.setStartX(0);
			line5.setStartY(b);
			line5.setEndX(Screen.getPrimary().getVisualBounds().getWidth());
			line5.setEndY(m * Screen.getPrimary().getVisualBounds().getWidth() + b);
		} else if(m < 0) {
			line5.setStartX(0);
			line5.setStartY(b);
			line5.setEndX((-1 * b) / m);
			line5.setEndY(0);
		} else {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX(Screen.getPrimary().getVisualBounds().getWidth());
			line7.setEndY(b);
		}
		
		//Line 6
		gamma = -1 * Math.atan((circle3.getCenterY() - circle2.getCenterY()) / (circle3.getCenterX() - circle2.getCenterX()));
		beta = Math.asin((circle3.getRadius() - circle2.getRadius()) / Math.sqrt(Math.pow(circle3.getCenterX() - circle2.getCenterX(), 2) + Math.pow(circle3.getCenterY() - circle2.getCenterY(), 2)));
		
		if(circle2.getCenterX() > circle3.getCenterX()) {
			a = gamma - beta;
		} else {
			a = gamma + beta;
		}
		
		x1 = circle2.getCenterX() - circle2.getRadius() * Math.cos((Math.PI / 2) - a);
		y1 = circle2.getCenterY() - circle2.getRadius() * Math.sin((Math.PI / 2) - a);
		x2 = circle3.getCenterX() - circle3.getRadius() * Math.cos((Math.PI / 2) - a);
		y2 = circle3.getCenterY() - circle3.getRadius() * Math.sin((Math.PI / 2) - a);
		m = (y2 - y1) / (x2 - x1);
		b = y1 - m * x1;
		
		point2M = m;
		point2B = b;
		
		if(m > 0) {
			line6.setStartX(0);
			line6.setStartY(b);
			line6.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line6.setEndY(m * Screen.getPrimary().getVisualBounds().getWidth() + b);
		} else if(m < 0) {
			line6.setStartX(0);
			line6.setStartY(b);
			line6.setEndX((-1 * b) / m);
			line6.setEndY(0);
		} else {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line7.setEndY(b);
		}
		
		//Point 3
		x1 = (point1B - point2B) / (point2M - point1M);
		y1 = point1M * x1 + point1B;
		
		point3.setCenterX(x1);
		point3.setCenterY(y1);
		
		//Line 7
		m1 = (line1.getEndY() - line1.getStartY()) / (line1.getEndX() - line1.getStartX());
		b1 = line1.getStartY() - m1 * line1.getStartX();
		m2 = (line2.getEndY() - line2.getStartY()) / (line2.getEndX() - line2.getStartX());
		b2 = line2.getStartY() - m1 * line2.getStartX();
		
		x1 = (b1 - b2) / (m2 - m1);
		y1 = m1 * x1 + b1;
		
		m3 = (line3.getEndY() - line3.getStartY()) / (line3.getEndX() - line3.getStartX());
		b3 = line3.getStartY() - m3 * line3.getStartX();
		m4 = (line4.getEndY() - line4.getStartY()) / (line4.getEndX() - line4.getStartX());
		b4 = line4.getStartY() - m4 * line4.getStartX();
		
		x2 = (b3 - b4) / (m4 - m3);
		y2 = m3 * x2 + b3;
		
		m = (y2 - y1) / (x2 - x1);
		b = y1 - m * x1;
		
		if(m > 0) {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line7.setEndY(m * Screen.getPrimary().getVisualBounds().getWidth() + b);
		} else if(m < 0) {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX((-1 * b) / m);
			line7.setEndY(0);
		} else {
			line7.setStartX(0);
			line7.setStartY(b);
			line7.setEndX((int) Screen.getPrimary().getVisualBounds().getWidth());
			line7.setEndY(b);
		}
	}
}
