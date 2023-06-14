package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CodeLine {
    
    private String content;
    private BooleanProperty breakpoint;

    public CodeLine(int lineNumber, String content) {
        
        this.content = content;
        this.breakpoint = new SimpleBooleanProperty(false);
    }

    // Getters and setters for lineNumber, content, and breakpoint


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isBreakpoint() {
        return breakpoint.get();
    }

    public BooleanProperty breakpointProperty() {
        return breakpoint;
    }

    public void setBreakpoint(boolean breakpoint) {
        this.breakpoint.set(breakpoint);
    }
}
