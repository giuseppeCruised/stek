package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.utils.Log;

public class ErrorInstruction implements Instruction{
    @Override
    public int getLine() {
        return 0;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        Log.log("Error Something went wrong with variables");
        return false;
    }
}
