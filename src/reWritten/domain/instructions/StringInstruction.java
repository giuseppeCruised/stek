package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;

public class StringInstruction implements Instruction{
    private int line;

    private String value;

    public StringInstruction(int line, String value){
        this.line = line;
        this.value = value;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {

        return false;
    }
}
