package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.DataItem;
import reWritten.utils.Log;

import java.util.regex.Pattern;

public class DupInstruction implements Instruction {
    private int line;

    public DupInstruction(int line) {
        this.line = line;
    }

    public static Pattern getPattern(){
        return Pattern.compile("dup");
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        if (dataStack.isEmpty()) {
            Log.log("Error: emptyStack in duplicate Instruction in Line: " + this.line);
            return false;
        } else {
            DataItem item = dataStack.popItem();
            dataStack.pushItem(item);
            dataStack.pushItem(item);
            return true;
        }
    }
}
