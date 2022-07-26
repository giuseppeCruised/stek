package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.StringItem;

import java.util.regex.Pattern;

public class StringInstruction implements Instruction{
    private int line;

    private String value;

    public StringInstruction(int line, String value){
        this.line = line;
        this.value = value;
    }

    public static Pattern getPattern(){
        return Pattern.compile("\"[a-z,A-Z,0-9]*\"");

    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        dataStack.pushItem(new StringItem(value));

        return true;
    }
}
