package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.BooleanItem;
import reWritten.domain.items.DataItem;
import reWritten.utils.Log;

import java.util.regex.Pattern;

public class EqualsInstruction implements Instruction {
    private int line;

    public EqualsInstruction(int line) {
        this.line = line;
    }

    public static Pattern getPattern() {
        return Pattern.compile("=");
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        if (dataStack.isEmpty()) {
            return Log.log("Error: Empty Stack in '=' Instruction in Line: " + this.line);
        } else {
            DataItem item1 = dataStack.popItem();

            if (dataStack.isEmpty()) {
                return Log.log("Error: Empty Stack in '=' Instruction in Line: " + this.line);
            } else {
                DataItem item2 = dataStack.popItem();

                if (item1.getClass() == item2.getClass()) {
                    if(item1.getValue() == item2.getValue()){
                        dataStack.pushItem(new BooleanItem(true));
                    } else {
                        dataStack.pushItem(new BooleanItem(false));
                    }
                    return true;
                } else {
                    return Log.log("Error: Types do not match in '=='  Instruction in Line: " + this.line);
                }
            }

        }
    }
}
