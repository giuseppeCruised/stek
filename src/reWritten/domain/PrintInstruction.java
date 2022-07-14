package reWritten.domain;

import java.util.Stack;
import java.util.regex.Pattern;

public class PrintInstruction implements Instruction {
    private final int line;

    public PrintInstruction( int line) {
        this.line = line;
    }

    public static Pattern getPattern() {
        return Pattern.compile("print");
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
