import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.Cursor;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Node;



public class Main extends Application {
    
    Stage vindu;
    double sX = 0, sY = 0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        vindu = primaryStage;
        vindu.setTitle("Tegneprogram");

        ColorPicker fLinje = new ColorPicker(Color.BLACK);
        ColorPicker fFyll = new ColorPicker(Color.TRANSPARENT);

        TextArea tekst = new TextArea();
        tekst.setPrefRowCount(1);
        tekst.setPrefWidth(1);

        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: white;");
        

        HBox topMenu = new HBox(20);
        topMenu.setStyle("-fx-background-color:grey");
        Button slettknp = new Button("Slett");
        ToggleButton linjeknp = new ToggleButton("Linje");
        ToggleButton rektangelknp = new ToggleButton("Rektangel");
        ToggleButton[] hBoxKnapper = {linjeknp, rektangelknp};
        topMenu.getChildren().addAll(slettknp, linjeknp, rektangelknp, fLinje, fFyll);
        


        for(ToggleButton hBtn : hBoxKnapper) {
            hBtn.setMinWidth(90);
            hBtn.setCursor(Cursor.HAND);
            hBtn.setTextFill(Color.BLACK);
            hBtn.setPadding(new Insets(5));
            hBtn.setPrefWidth(100);
            hBtn.setStyle("-fx-margin: 20px;" + "-fx-spacing: 10;");
        }

        VBox leftMenu = new VBox(20);
        leftMenu.setStyle("-fx-background-color:grey");
        ToggleButton sirkelknp = new ToggleButton("Sirkel");
        ToggleButton tekstknp = new ToggleButton("Tekst");
        ToggleButton velgknp = new ToggleButton("Velg");
        ToggleButton[] vBoxKnapper = {sirkelknp, tekstknp, velgknp};
        leftMenu.getChildren().addAll(sirkelknp, velgknp, tekstknp, tekst);

        for(ToggleButton vBtn : vBoxKnapper) {
            vBtn.setMinWidth(90);
            vBtn.setCursor(Cursor.HAND);
            vBtn.setTextFill(Color.BLACK);
            vBtn.setPadding(new Insets(5));
            vBtn.setPrefWidth(100);
            vBtn.setStyle("-fx-margin: 20px;" + "-fx-spacing: 10;");
        }

        //Holder på temp var
        Linje linje = new Linje();
        Rectangle rektangel = new Rectangle();
        Circle sirkel = new Circle();

        Handler handler = new Handler(canvas, canvas);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, handler.getMousePressedEventHandler());

        canvas.setOnMousePressed(e -> {
            //Logger hvor musa blir først trykket
            if(linjeknp.isSelected()){

                linje.setStartX(e.getX());
                linje.setStartY(e.getY());
                System.out.println("X: " + e.getX());
                System.out.println("Y: " + e.getY());              

            } else if(rektangelknp.isSelected()){
                rektangel.setX(e.getX());
                rektangel.setY(e.getY());

            }else if(sirkelknp.isSelected()){

                sirkel.setCenterX(e.getX());
                sirkel.setCenterY(e.getY());

            }else if(tekstknp.isSelected()){

                canvas.getChildren().add(new Tekst(e.getX(), e.getY(), tekst.getText(), fLinje.getValue()));

            }
        });
        
        
        canvas.setOnMouseReleased(e -> {
            //Henter ut og oppretter forskjellige figurer ette hvor bruker slipper musa
            if(linjeknp.isSelected()) {

                linje.setEndX(e.getX());
                linje.setEndY(e.getY());
                canvas.getChildren().add(new Linje(linje.getStartX(), linje.getStartY(), linje.getEndX(), linje.getEndY(), fLinje.getValue()));

            } else if(rektangelknp.isSelected()){
                rektangel.setWidth(Math.abs((e.getX() - rektangel.getX())));
                rektangel.setHeight(Math.abs((e.getY() - rektangel.getY())));
                if(rektangel.getX() > e.getX()) {
                    rektangel.setX(e.getX());
                }
                if(rektangel.getY() > e.getY()){
                    rektangel.setY(e.getY());
                }
                //Legger til nye figurer i tegningen
                canvas.getChildren().add(new Rektangel(rektangel.getX(), rektangel.getY(), rektangel.getWidth(), rektangel.getHeight(), fLinje.getValue(), fFyll.getValue()));

            }else if(sirkelknp.isSelected()) {
                sirkel.setRadius((Math.abs(e.getX() - sirkel.getCenterX()) + Math.abs(e.getY() - sirkel.getCenterY())) /2);
                if(sirkel.getCenterY() > e.getY()){
                    sirkel.setCenterY(e.getY());
                }
                if(sirkel.getCenterX() > e.getX()) {
                    sirkel.setCenterX(e.getX());
                }
                canvas.getChildren().add(new Sirkel(sirkel.getCenterX(), sirkel.getCenterY(), sirkel.getRadius(), fLinje.getValue(), fFyll.getValue()));
        }});

        
            


        slettknp.setOnMouseClicked(e -> {
            //Sletter tegning
            canvas.getChildren().clear();
        });












        

        //Slider
        /*
        slider.valueProperty().addListener(e->{
            double width = slider.getValue();
            if(tekstknp.isSelected()){
                canvas.setLineWidth(1);
                canvas.setFont(Font.font(slider.getValue()));
                line_width.setText(String.format("%.1f", width));
                return;
            }
            line_width.setText(String.format("%.1f", width));
            canvas.setLineWidth(width);
        });*/












        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(canvas);
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        

        
        Scene scene = new Scene(borderpane, 1200, 900);
        vindu.setScene(scene);
        vindu.show();
    }
}
