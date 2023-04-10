package engine.controller;

import java.util.ArrayList;

import engine.hud.Clickable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseController extends MouseAdapter{

    ArrayList<Clickable> interactiveElements = new ArrayList<Clickable>();
    private boolean isListening = true;

    public void register(Clickable clickable) {
        interactiveElements.add(clickable);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(isListening)
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
    
    public void listen() {
        isListening = true;
    }

    public void stopListening() {
        isListening = false;
    }
}
