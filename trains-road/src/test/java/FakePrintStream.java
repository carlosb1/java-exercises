import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public final class FakePrintStream {
    private final ByteArrayOutputStream outContent;
    private final PrintStream out;

    public FakePrintStream() throws IOException {
        outContent = new ByteArrayOutputStream();
        out = new PrintStream(outContent);

    }

    public boolean equals(String valueToCompare) {
        return (outContent.toString().equals(valueToCompare));

    }

    public PrintStream getOut() {
        return out;
    }

}