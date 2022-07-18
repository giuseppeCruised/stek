package reWritten.parsemarse;


import java.util.Optional;

public class SafeParsedElement<X> {
    private String errorMessage;
    private Optional<X> parsedElementOptional;

    public SafeParsedElement(String errorMessage,
                             Optional<X> parsedElementOptional) {
        this.errorMessage = errorMessage;
        this.parsedElementOptional = parsedElementOptional;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Optional<X> getParsedElementOptional() {
        return parsedElementOptional;
    }

    public void setParsedElementOptional(Optional<X> parsedElementOptional) {
        this.parsedElementOptional = parsedElementOptional;
    }
}
