package reWritten.domain;

import reWritten.domain.instructions.Instruction;

import java.util.Stack;

public class InstructionStack {
    private final Stack<Instruction> instructionStack;

    public InstructionStack (){
        this.instructionStack = new Stack<>();
    }

    public boolean isEmpty(){
        return this.instructionStack.isEmpty();
    }

    public Instruction popInstruction(){
        return this.instructionStack.pop();
    }

    public void pushInstruction(Instruction instruction){
        this.instructionStack.push(instruction);
    }
}
