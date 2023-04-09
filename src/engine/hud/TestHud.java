package engine.hud;

import java.awt.Graphics;

public class TestHud extends Hud{

    public TestHud() {
        super();
        addElement(new HudElement(50, 50 ,100, 100) {
            @Override
            public void draw(Graphics g) {
                g.fillRect(getX(), getY(), getWidth(), getHeight());
            }

            public void onClick() {
                System.out.println("Clicked");
            }
        });
    }
    
}
