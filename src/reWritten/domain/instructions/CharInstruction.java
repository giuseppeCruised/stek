package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.CharItem;

import java.util.regex.Pattern;

public class CharInstruction implements Instruction {
    private int line;
    private char value;

    public CharInstruction(int line, char value){
        this.line = line;
        this.value = value;
    }

    public static Pattern getPattern(){
        return Pattern.compile("'.'");
    }

    @Override
    public int getLine() {
        return 0;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        dataStack.pushItem(new CharItem(value));
        return true;
    }
}
