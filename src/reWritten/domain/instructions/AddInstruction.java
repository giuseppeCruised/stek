package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.DataItem;
import reWritten.domain.items.IntegerItem;
import reWritten.domain.items.NumberItem;
import reWritten.domain.items.StringItem;
import reWritten.utils.Log;

import java.util.regex.Pattern;

public class AddInstruction implements Instruction {
    private int line;

    public AddInstruction(int line) {
        this.line = line;

    }

    public static Pattern getPattern() {
        return Pattern.compile("\\+");
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        if (dataStack.isEmpty()) {
            return Log.log("Error: empty Stack error in '+' Instruction in Line: " + line);
        } else {
            DataItem item1 = dataStack.popItem();
            if (dataStack.isEmpty()) {
                return Log.log("Error: empty Stack error in '+' Instruction in Line: " + line);
            } else {
                DataItem item2 = dataStack.popItem();
                if (item1 instanceof NumberItem) {
                    if (item2 instanceof NumberItem) {
                        if (item1 instanceof IntegerItem && item2 instanceof IntegerItem) {
                            dataStack.pushItem(new IntegerItem((Integer) ((IntegerItem) item1).getValue() +
                                    (Integer) ((IntegerItem) item2).getValue()));
                        }
                    } else {
                        return Log.log(
                                "Error: second Argument of '+' Instruction could not be applied in Line: " + line);
                    }
                } else if (item1 instanceof StringItem && item2 instanceof StringItem) {
                    dataStack.pushItem(new StringItem((String) item2.getValue() + (String) item1.getValue()));

                } else {
                    return Log.log("Error: '+' Instruction could not be applied to Arguments in Line: " + line);
                }
            }
        }
        return true;
    }
}
