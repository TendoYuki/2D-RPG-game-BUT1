package engine.physics;

/** Trigger zone class */
public class TriggerZone {
    /** Width of the trigger zone */
    private int width;
    /** Height of the trigger zone */
    private int height;

    /**
     * Creates a new trigger zone
     * @param width
     * @param height
     */
    public TriggerZone(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Retrurns the width of the trigger zone
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the trigger zone
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Retrurns the height of the trigger zone
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the trigger zone
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
