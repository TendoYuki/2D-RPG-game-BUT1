package engine.dialog;

import java.util.ArrayList;

public class Dialog {
    private ArrayList<String> lines = new ArrayList<String>();
    private int currentLine = 0;

    public void addLine(String line) {
        lines.add(line);
    }

    public void addLine(String[] lines) {
        for(String line: lines)
            this.lines.add(line);
    }

    public void addLine(ArrayList<String> lines) {
        for(String line: lines)
            this.lines.add(line);
    }

    public String nextLine() {
        String line = lines.get(currentLine);
        currentLine++;
        return line;
    }

    public String getLine(int lineIndex) {
        return lines.get(lineIndex);
    }
}
