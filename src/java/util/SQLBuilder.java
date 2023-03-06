package util;

public class SQLBuilder {

    private StringBuilder builder;

    public SQLBuilder() {
        this.builder = new StringBuilder();
    }

    public final void addLine(String line) {
        builder.append(line);
        builder.append('\n');
    }

    @Override
    public final String toString() {
        return builder.toString();
    }
}
