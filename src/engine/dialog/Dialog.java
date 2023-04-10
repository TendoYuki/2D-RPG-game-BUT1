package engine.dialog;

import java.awt.Graphics;
import java.util.ArrayList;

public class Dialog {
    private ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
    private int currentLine = 0;

    public void addLine(String line) {
        ArrayList<String> al = new ArrayList<String>();
        al.add(line);
        lines.add(al);
    }

    public void addLine(String[] lines) {
        ArrayList<String> al = new ArrayList<String>();
        for(String line: lines)
            al.add(line);
        this.lines.add(al);
    }

    public void addLine(ArrayList<String> lines) {
        ArrayList<String> al = new ArrayList<String>();
        for(String line: lines)
            al.add(line);
        this.lines.add(al);
    }

    public ArrayList<String> nextLine() {
        if(currentLine+1 <lines.size()) {
            ArrayList<String> line = lines.get(currentLine);
            currentLine++;
            return line;
        }
        return lines.get(currentLine);
    }

    public ArrayList<String> getLine(int lineIndex) {
        return lines.get(lineIndex);
    }

    public void draw(Graphics g, int x, int y) {
        ArrayList<String> line = getLine(currentLine);
        for(int i = 1; i <= line.size(); i++) {
            g.drawString(line.get(i-1), x, y+g.getFont().getSize()*i);
        }
    }
}
