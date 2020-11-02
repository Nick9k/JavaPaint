import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Tekst extends Text implements Select{
    public Tekst(double x, double y, String t, Color line){
        super(x, y, t);
        this.setStroke(line);
        this.setFont(new Font(20));

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