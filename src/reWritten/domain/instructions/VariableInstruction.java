package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;

public class VariableInstruction implements Instruction{
    private int line;
    private Instruction instruction;
    private String name;

    public void setInstruction(Instruction instruction){
        this.instruction = instruction;
    }

    public VariableInstruction(int line, String name){
        this.line = line;
        this.name = name;
        this.instruction = new ErrorInstruction();
    }

    public void fillVariable(Instruction instruction){
        this.instruction = instruction;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        return instruction.executeInstruction(instructionStack,dataStack);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
