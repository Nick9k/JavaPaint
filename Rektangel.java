import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;

public class Rektangel extends Rectangle implements Select{
    public Rektangel(double x, double y, double w, double h, Color line, Color fill){
        super (x, y, w, h);
        this.setStroke(line);
        this.setFill(fill);
    }

    public Rektangel(){

    } 


    public void selectCurr(boolean on){
        if(on){
            
            this.setOnMouseDragged(e -> {
                this.setX(e.getX());
                this.setY(e.getY());
            });

        }
    }
    public boolean isSelected(boolean select){
        return true;
    }
}