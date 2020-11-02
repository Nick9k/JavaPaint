import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.shape.Line;
import java.util.Collections;
import java.util.List;


 public class Handler {
	private brett board;
	
	private EventHandler<MouseEvent> mousePressedEventHandler;

	//Henter info fra ObligMks
	private Pane canvas;

	public Handler(final Parent source, Pane canvas) {
		//Definerer alle elementene jeg hentet inn slik at de blir gyldige
		
		this.canvas = canvas;
		this.board = new brett();

		//Eventhandler når man trykker på canvas siden canvas er definert som en ny NodeHandler
		this.mousePressedEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Handler.this.doOnMousePressed(source, event);
				event.consume();
			}
		};
 	}

	 //Når musa trykkes på canvas kjører følgende kode
	private void doOnMousePressed(Parent source, MouseEvent event) {

		Node target = (Node) event.getTarget();
		if(target.equals(source))
			board.unselectAll();
		 //Sjekker om det som skal bli laget kommer fra interface
		if(target instanceof Select) {
			Select selectableTarget = (Select) target;
			if(!board.getSelectedItems().contains(selectableTarget))
				board.unselectAll();
			board.select(selectableTarget, true);
        }
    }

	 //Metode for å starte programmet fra ObligMks
	public EventHandler<MouseEvent> getMousePressedEventHandler() {
		return mousePressedEventHandler;
	}

	//Lager board class som brukes for å flytte nodes
	//Brukes også for lagring av nodes i array
	private class brett{
		private ObservableList<Select> selectedItems = FXCollections.observableArrayList();
 		public ObservableList<Select> getSelectedItems() {
			return selectedItems;
		}
		//Metode for å unselecte alt
		public void unselectAll() {
			List<Select> unselectList = new ArrayList<>();
			unselectList.addAll(selectedItems);
 			for (Select sN : unselectList) {
				select(sN, false);
			}
		}

 		public boolean select(Select n, boolean selected) {
			 //Sjekker hva som er valgt fra de forskjellige klassene
			if(n.isSelected(selected)) {
				if (selected) {
					selectedItems.add(n);
				} else {
					selectedItems.remove(n);
				}
				n.selectCurr(selected);
				
				return true;
			} else {
				return false;
            }
            
            
		}
	}
}