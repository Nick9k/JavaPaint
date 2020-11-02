import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Sirkel extends Circle implements Select{
    public Sirkel(double centerX, double centerY, double radius, Color line, Color fill){
        super (centerX, centerY, radius);
        this.setStroke(line);
        this.setFill(fill);
    }

    public Sirkel(){
        
    }

    public void selectCurr(boolean on){
        if(on){
            this.setOnMouseDragged(e -> {
                this.setCenterX(e.getX());
                this.setCenterY(e.getY());
            });
        }
    }

    public boolean isSelected(boolean select){
        return true;
    }
}