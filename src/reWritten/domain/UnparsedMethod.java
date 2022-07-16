package reWritten.domain;

public class UnparsedMethod {
    private String[] unparsedLines;
    private String methodName;

    public UnparsedMethod(String[] unparsedLines, String methodName, int startLine) {
        this.unparsedLines = unparsedLines;
        this.methodName = methodName;
        this.startLine = startLine;
    }

    public String[] getUnparsedLines() {
        return unparsedLines;
    }

    public void setUnparsedLines(String[] unparsedLines) {
        this.unparsedLines = unparsedLines;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    private int startLine;
}
