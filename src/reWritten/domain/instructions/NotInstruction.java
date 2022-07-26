package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.BooleanItem;
import reWritten.domain.items.DataItem;
import reWritten.utils.Log;

import java.util.regex.Pattern;

public class NotInstruction implements Instruction{
    private int line;

    public NotInstruction(int line) {
        this.line = line;
    }

    public static Pattern getPattern(){
        return Pattern.compile("not");
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        if(dataStack.isEmpty()){
            return Log.log("Error: Empty Stack in 'not' Instruction in Line: " + this.line);
        } else {
            DataItem booleanItem = dataStack.popItem();

            if(booleanItem instanceof BooleanItem){
                if((Boolean) booleanItem.getValue()){
                    dataStack.pushItem(new BooleanItem(false));

                } else {
                    dataStack.pushItem(new BooleanItem(true));

                }

                return true;
            } else {
                return Log.log("Type Error: argument of 'not' Instruction was not of Type Boolean in Line: " + this.line);
            }

        }

    }
}
