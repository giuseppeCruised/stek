package reWritten.domain;

import java.util.Stack;
import java.util.regex.Pattern;

public interface Instruction {

    public int getLine();

    public void executeInstruction(Stack<Instruction> instructionStack, Stack<DataItem> dataStack);

}
