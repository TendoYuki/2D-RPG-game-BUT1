package engine.controle;

import java.util.ArrayList;

import engine.hud.Clickable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControleSouris extends MouseAdapter{

    ArrayList<Clickable> interactiveElements = new ArrayList<Clickable>();

    public void register(Clickable clickable) {
        interactiveElements.add(clickable);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        interactiveElements.forEach(element -> {
            if(
                e.getX() > element.getX() &&
                e.getX() < element.getX() +
                element.getWidth()
            ) {
                if(
                    e.getY() > element.getY() &&
                    e.getY() < element.getY() +
                    element.getHeight()
                ) {
                    element.onClick();
                }
            }
        });
    }
    
}
