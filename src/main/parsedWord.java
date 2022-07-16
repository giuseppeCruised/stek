package main;

import domain.Instruction;
import reWritten.utils.Pair;

import java.util.Optional;

public class parsedWord {
    private String errorMessage;
    private Optional<Pair<String,Instruction>> instruction;

    public parsedWord(String errorMessage, Optional<Pair<String, Instruction>> instruction) {
        this.errorMessage = errorMessage;
        this.instruction = instruction;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Optional<Pair<String, Instruction>> getInstruction() {
        return instruction;
    }

    public void setInstruction(Optional<Pair<String, Instruction>> instruction) {
        this.instruction = instruction;
    }
}
