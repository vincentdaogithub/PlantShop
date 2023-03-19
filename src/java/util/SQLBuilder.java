package util;

public class SQLBuilder {

    private StringBuilder builder;

    public SQLBuilder() {
        this.builder = new StringBuilder();
    }
    
    public final void reset() {
        this.builder = new StringBuilder();
    }
    
    public final void add(String sequence) {
        builder.append(sequence);
    }

    public final void addLine(String line) {
        builder.append(line);
        builder.append('\n');
    }
    
    public final void newLine() {
        builder.append('\n');
    }

    @Override
    public final String toString() {
        return builder.toString();
    }
}
