package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.utils.Utils;

public class MethodInstruction implements Instruction {

    private int line;
    private String[] variables;
    private Instruction[] instructions;
    private String name;

    public MethodInstruction(int line, String[] variables, Instruction[] instructions, String name) {
        this.line = line;
        this.variables = variables;
        this.instructions = instructions;
        this.name = name;
    }

    public String[] getVariables() {
        return variables;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    public String getName() {
        return name;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }

    public void setInstructions(Instruction[] instructions) {
        this.instructions = instructions;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        for(Instruction instruction: Utils.reverseInstructions(this.instructions)){
            instructionStack.pushInstruction(instruction);
        }
        return true;
    }
}
