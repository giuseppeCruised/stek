package reWritten.domain;

import java.util.Stack;
import java.util.regex.Pattern;

public class IntegerInstruction implements Instruction {
    private final int value;
    private final int line;

    public IntegerInstruction(int value, int line) {
        this.value = value;
        this.line = line;
    }

    public static Pattern getPattern() {
        return Pattern.compile("[1-9]+");
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        //missing
    }
}
