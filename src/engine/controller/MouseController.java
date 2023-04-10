package engine.controller;

import java.util.ArrayList;

import engine.hud.Clickable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseController extends MouseAdapter{

    ArrayList<Clickable> interactiveElements = new ArrayList<Clickable>();
    ArrayList<Clickable> pressedElements = new ArrayList<Clickable>();
    private boolean isListening = true;

    public void register(Clickable clickable) {
        interactiveElements.add(clickable);
    }

    private boolean isActionInElement(Clickable element, MouseEvent event) {
        return(
            (
                event.getX() > element.getX() &&
                event.getX() < element.getX() +
                element.getWidth()
            ) &&
            (
                event.getY() > element.getY() &&
                event.getY() < element.getY() +
                element.getHeight()
            )
        );
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isListening)
            interactiveElements.forEach(element -> {
                if(isActionInElement(element, e)) {
                    element.onPressed();
                    pressedElements.add(element);
                    element.setPressed(true);
                }
            });
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isListening)
            pressedElements.forEach(element -> {
                element.setPressed(false);
            });
            pressedElements.clear();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(isListening)
            interactiveElements.forEach(element -> {
                if(isActionInElement(element, e)) {
                    element.onClick();
                }
            });
    }
    
    public void listen() {
        isListening = true;
    }

    public void stopListening() {
        isListening = false;
    }
}
