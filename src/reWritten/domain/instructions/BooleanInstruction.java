package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.BooleanItem;

import java.util.regex.Pattern;

public class BooleanInstruction implements Instruction{

    private int line;

    private Boolean value;

    public BooleanInstruction (int line,Boolean value){
        this.line = line;
        this.value = value;
    }

    public static Pattern getPattern(){
        return Pattern.compile("(true|false)");
    }

    public Boolean getValue(){
        return this.value;
    }

    @Override
    public int getLine() {
        return 0;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        dataStack.pushItem(new BooleanItem(value));
        return true;
    }
}
