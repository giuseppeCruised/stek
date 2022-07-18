package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;

import java.util.Stack;

public interface Instruction {

    public int getLine();

    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack);

}
