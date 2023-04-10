package engine.dialog;

public abstract class DialogController {
    
    private static Dialog currentDialog;

    public DialogController() {}

    public static Dialog getCurrentDialog() {
        return currentDialog;
    }

    public static void setCurrentDialog(Dialog currentDialog) {
        DialogController.currentDialog = currentDialog;
    }

}
