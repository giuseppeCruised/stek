package reWritten.domain;

import java.util.Stack;

public class MethodInstruction implements Instruction{

    private int line;
    private String[] variables;
    private Instruction[] Instructions;
    private String name;

    public MethodInstruction(int line, String[] variables, Instruction[] instructions, String name) {
        this.line = line;
        this.variables = variables;
        Instructions = instructions;
        this.name = name;
    }

    public String[] getVariables() {
        return variables;
    }

    public Instruction[] getInstructions() {
        return Instructions;
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
        Instructions = instructions;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public void executeInstruction(InstructionStack instructionStack, DataStack dataStack) {

    }
}
