package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.BooleanItem;
import reWritten.domain.items.DataItem;
import reWritten.domain.items.MethodPointerItem;
import reWritten.utils.Log;

import java.util.regex.Pattern;

public class IfInstruction implements Instruction {
    private int line;

    public IfInstruction(int line){
        this.line = line;
    }

    public static Pattern getPattern(){
        return Pattern.compile("if");
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        if (dataStack.isEmpty()) {
            return Log.log("Error: Empty Stack in If Instruction in Line: " + this.line);
        } else {
            DataItem booleanItem = dataStack.popItem();
            if (booleanItem instanceof BooleanItem) {
                if (dataStack.isEmpty()) {
                    return Log.log("Error: Empty Stack in If Instruction in Line: " + this.line);
                } else {
                    DataItem methodPointer = dataStack.popItem();
                    if (methodPointer instanceof MethodPointerItem) {
                        if ((Boolean) ((BooleanItem) booleanItem).getValue()) {
                            dataStack.pushItem(methodPointer);
                            instructionStack.pushInstruction(new ExecutePointerInstruction(this.line));
                        }
                        return true;
                    } else {
                        return Log.log("Type Error: If Instruction in Line: " + this.line +
                                " could not get executed because second argument of If Instruction is not of Type MethodPointer");
                    }
                }
            } else {
                return Log.log("Type Error: If Instruction in Line: " + this.line +
                        " could not get executed because first argument of If Instruction is not of Type Boolean");
            }
        }
    }
}
