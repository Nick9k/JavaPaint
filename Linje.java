import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

public class Linje extends Line implements Select{
    public Linje(double startX, double startY, double endX, double endY, Color line){
        super (startX, startY, endX, endY);
        this.setStroke(line);
        this.setStrokeWidth(3);
    }

    public Linje(){

    }
    
    public void selectCurr(boolean on){
        double fDragY = getEndY() - getStartY();
        double fDragX = getEndX() - getStartX();
        
        if(on){
            this.setOnMouseDragged(e -> {
              this.setStartY(e.getY());
              this.setStartX(e.getX());
              this.setEndY((e.getY() + fDragY));
              this.setEndX((e.getX()) + fDragX);
            });
        }
    }

    public boolean isSelected(boolean select){
        return true;
    }
}