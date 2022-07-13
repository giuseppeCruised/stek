package reWritten.parsemarse;


import reWritten.domain.Instruction;

import java.util.Optional;

public class SafeParsedElement<X> {
    private String errorMessage;
    private Optional<Instruction> parsedElementOptional;

    public SafeParsedElement(String errorMessage,
                             Optional<Instruction> parsedElementOptional) {
        this.errorMessage = errorMessage;
        this.parsedElementOptional = parsedElementOptional;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Optional<Instruction> getParsedElementOptional() {
        return parsedElementOptional;
    }

    public void setParsedElementOptional(Optional<Instruction> parsedElementOptional) {
        this.parsedElementOptional = parsedElementOptional;
    }
}
