package reWritten.domain;

import java.util.Stack;

public class InstructionStack {
    private final Stack<Instruction> instructionStack;

    public InstructionStack (){
        this.instructionStack = new Stack<>();
    }

    public Instruction popInstruction(){
        return this.instructionStack.pop();
    }

    public void pushInstruction(Instruction instruction){
        this.instructionStack.push(instruction);
    }
}
