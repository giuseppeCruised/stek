package reWritten.domain;

import java.util.Stack;

public interface Instruction {

    public int getLine();

    public void executeInstruction(InstructionStack instructionStack, DataStack dataStack);

}
