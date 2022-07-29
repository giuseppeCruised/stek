package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.BooleanItem;
import reWritten.domain.items.DataItem;
import reWritten.utils.Log;

import java.util.regex.Pattern;

public class AndInstruction implements Instruction {
    private int line;
    private static final String NAME = "and";

    public AndInstruction(int line) {
        this.line = line;
    }

    public static Pattern getPattern() {
        return Pattern.compile(NAME);
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        DataItem item1 = dataStack.safePop(line, NAME);
        DataItem item2 = dataStack.safePop(line, NAME);

        if (item1 != null && item2 != null) {

            if (item1 instanceof BooleanItem) {
                if (item2 instanceof BooleanItem) {
                    boolean val = ((Boolean) ((BooleanItem) item1).getValue()) &&
                            ((Boolean) ((BooleanItem) item2).getValue());

                    dataStack.pushItem(new BooleanItem(val));
                    return true;
                } else {
                    return Log.log("TypeError: Second argument of 'and' Instruction was not of Type Boolean");
                }
            } else {
                return Log.log("TypeError: First argument of 'and' Instruction was not of Type Boolean");
            }
        } else {
            return false;
        }
    }
}
