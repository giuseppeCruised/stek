package reWritten.domain;

public class Program {
    private MethodInstruction[] methods;

    private String errorMessage;

    public Program(MethodInstruction[] methods, String errorMessage) {
        this.methods = methods;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MethodInstruction[] getMethods() {
        return methods;
    }

    public void setMethods(MethodInstruction[] methods) {
        this.methods = methods;
    }
}
