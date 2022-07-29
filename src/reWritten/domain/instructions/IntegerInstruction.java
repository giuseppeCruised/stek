package reWritten.domain.instructions;

import reWritten.domain.items.DataItem;
import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.IntegerItem;

import java.util.regex.Pattern;

public class IntegerInstruction implements Instruction, CompilableInstruction{
    private final int value;
    private final int line;

    public IntegerInstruction(int value, int line) {
        this.value = value;
        this.line = line;
    }

    public int getValue(){
        return value;
    }

    public static Pattern getPattern() {
        return Pattern.compile("[0-9]+");
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        dataStack.pushItem(new IntegerItem(this.value));
        return true;
    }

    @Override
    public String compileInstruction() {
        return "  push";
    }
}
