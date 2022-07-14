package reWritten.domain;

import java.util.Stack;

public class MethodInstruction implements Instruction{

    private int line;
    private String[] variables;
    private Instruction[] Instructions;
    private String name;

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public void executeInstruction(InstructionStack instructionStack, DataStack dataStack) {

    }
}
