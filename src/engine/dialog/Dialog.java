package engine.dialog;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/** Dialog class */
public class Dialog {
    /** The lines of the dialog */
    private ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
    /** Line cursor */
    private int currentLine = 0;
    /** Adds a line to the dialog 
     * @param line
    */
    public void addLine(String line) {
        ArrayList<String> al = new ArrayList<String>();
        al.add(line);
        lines.add(al);
    }
    /** Adds one or multiple lines to the dialog 
     * @param lines
    */
    public void addLine(String[] lines) {
        ArrayList<String> al = new ArrayList<String>();
        for(String line: lines)
            al.add(line);
        this.lines.add(al);
    }
    /** Adds one or multiple lines to the dialog 
     * @param lines
    */
    public void addLine(ArrayList<String> lines) {
        ArrayList<String> al = new ArrayList<String>();
        for(String line: lines)
            al.add(line);
        this.lines.add(al);
    }
    /** Returns the next line and increments the current line cursor 
     * @return
    */
    public ArrayList<String> nextLine() {
        if(currentLine+1 <lines.size()) {
            ArrayList<String> line = lines.get(currentLine);
            currentLine++;
            return line;
        }
        return lines.get(currentLine);
    }
    /** Whether or not the dialog has a next line */
    public boolean hasNextLine(){
        return currentLine+1 < lines.size();
    }

    /** Returns the line by the given index 
     * @param lineIndex
     * @return
    */
    public ArrayList<String> getLine(int lineIndex) {
        return lines.get(lineIndex);
    }

    /** Returns the lines
     * @return
    */
    public ArrayList<ArrayList<String>> getLines() {
        return lines;
    }

    /** Draws the dialog on the given graphical context 
     * @param g
     * @param x
     * @param y
    */
    public void draw(Graphics g, int x, int y) {
        ArrayList<String> line = getLine(currentLine);
        for(int i = 1; i <= line.size(); i++) {
            Font temp = g.getFont();
            g.setFont(new Font(temp.getFontName(), Font.BOLD, temp.getSize()));
            g.drawString(line.get(i-1), 25+x, 25+y+g.getFont().getSize()*i);
            g.setFont(temp);
        }
    }
}
