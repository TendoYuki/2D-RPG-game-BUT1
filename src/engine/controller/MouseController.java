package engine.controller;

import java.util.ArrayList;

import engine.hud.Clickable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** MouseController class */
public class MouseController extends MouseAdapter{
    /** Elements that are interactable */
    ArrayList<Clickable> interactiveElements = new ArrayList<Clickable>();
    /** Elements that are pressed */
    ArrayList<Clickable> pressedElements = new ArrayList<Clickable>();
    /** Whether or not the controller is listening */
    private boolean isListening = true;
    /** Offset of the click */
    private int offsetY;

    /** Create a MouseController */
    public MouseController(int offsetY) {
        this.offsetY = offsetY;
    }
    /** Registers an interactable element 
     * @param clickable
    */
    public void register(Clickable clickable) {
        interactiveElements.add(clickable);
    }
    /** Unregisters an interactable element 
     * @param clickable
    */
    public void unRegister(Clickable clickable) {
        interactiveElements.remove(clickable);
    }
    /** Changes the mouse offset 
     * @param offsetY
    */
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
    /** Checks wheter or not the click event is within a given element 
     * @param element
     * @param event
     * @return
    */
    private boolean isActionInElement(Clickable element, MouseEvent event) {
        return(
            (
                event.getX() > element.getX() &&
                event.getX() < element.getX() +
                element.getWidth()
            ) &&
            (
                event.getY() + offsetY > element.getY() &&
                event.getY() + offsetY < element.getY() +
                element.getHeight()
            )
        );
    }

    
    /** 
     * @param e
     */
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
    /** Makes the controller listen */
    public void listen() {
        isListening = true;
    }
    /** Stop the listening of the controller */
    public void stopListening() {
        isListening = false;
    }
}
