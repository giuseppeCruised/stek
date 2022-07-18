package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.DataItem;
import reWritten.domain.items.PrintableItem;
import reWritten.utils.Log;

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
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        DataItem item = null;
        if (!dataStack.isEmpty()){
            item = dataStack.popItem();
        } else {
            Log.log("Error: Data Stack was empty in PrintInstruction in Line: "+this.line );
            return false;
        }
        if(item instanceof PrintableItem){
            ((PrintableItem) item).printItem();
            return true;

        } else {
            Log.log("Error: printed item is not printable");
            return false;
        }
    }
}
