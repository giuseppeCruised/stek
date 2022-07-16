package main;

import domain.Method;
import reWritten.utils.Pair;

import java.util.Optional;

public class parsedMethod {
    private String errorMessage;
    private Optional<Pair<String,Method>> method;

    public parsedMethod(String errorMessage, Optional<Pair<String, Method>> method) {
        this.errorMessage = errorMessage;
        this.method = method;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Optional<Pair<String, Method>> getMethod() {
        return method;
    }

    public void setMethod(Optional<Pair<String, Method>> method) {
        this.method = method;
    }
}
