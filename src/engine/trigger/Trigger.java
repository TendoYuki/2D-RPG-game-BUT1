package engine.trigger;
/** Trigger class */
public abstract class Trigger {
    /** Updates whether it is triggered or not */
    public abstract void onTriggered();
    /** Updates whether it is outside of the trigger zone*/
    public void onTriggerExit() {};
}
