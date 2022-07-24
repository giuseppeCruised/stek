package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.DataItem;
import reWritten.domain.items.MethodPointerItem;
import reWritten.utils.Log;

import java.util.regex.Pattern;

public class ExecutePointerInstruction implements Instruction {
    private int line;

    public ExecutePointerInstruction(int line){
        this.line = line;
    }

    public static Pattern getPattern() {
        return Pattern.compile("!");
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        if (dataStack.isEmpty()) {
            Log.log("Error: Empty Stack in '!' Instruction in Line: " + this.line);
            return false;

        } else {
            DataItem item = dataStack.popItem();
            if (item instanceof MethodPointerItem) {
                MethodInstruction method = (MethodInstruction) ((MethodPointerItem) item).getValue();
                instructionStack.pushInstruction(method);

            } else {
                Log.log("TypeError: attempt to execute non Method Item in Line: " + this.line);

            }
            return true;
        }
    }
}
