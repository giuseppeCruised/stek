package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;

public class BooleanInstruction implements Instruction{

    private int line;

    private boolean value;

    public BooleanInstruction (int line,boolean value){
        this.line = line;
        this.value = value;
    }

    @Override
    public int getLine() {
        return 0;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        return false;
    }
}
