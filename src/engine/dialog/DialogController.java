package engine.dialog;

/** DialogController class */
public abstract class DialogController {
    /** The current dialog */
    private static Dialog currentDialog;
    /** Constructs a dialog */
    public DialogController() {}
    /** Returns the current dialog 
     * @return
    */
    public static Dialog getCurrentDialog() {
        return currentDialog;
    }
    /** Updates the current dialog 
     * @param currentDialog
    */
    public static void setCurrentDialog(Dialog currentDialog) {
        DialogController.currentDialog = currentDialog;
    }
}
