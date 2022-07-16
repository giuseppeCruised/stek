package reWritten.domain;

public class Program {
    private MethodInstruction[] methods;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public Program() {
        methods = null;
        errorMessage = "";
    }

    public MethodInstruction[] getMethods() {
        return methods;
    }

    public void setMethods(MethodInstruction[] methods) {
        this.methods = methods;
    }
}
