package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.DataItem;
import reWritten.utils.Log;
import reWritten.utils.Utils;

import java.util.Arrays;

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
        VariableInstruction[] variableInstructions = Arrays.stream(instructions)
                .filter(instruction -> instruction instanceof VariableInstruction)
                .toArray(VariableInstruction[]::new);

        fillMethodVariables(dataStack);

        for(Instruction instruction: Utils.reverseInstructions(this.instructions)){
            instructionStack.pushInstruction(instruction);
        }
        return true;
    }

    public void fillMethodVariables(DataStack dataStack){
        for(String variable:variables){
            if(dataStack.isEmpty()){
                Log.log("Error: empty stack while assigning variable: "+variable+" of Method: "+name+" in Line: "+line);
            } else {
                DataItem item = dataStack.popItem();
                Instruction correspondingInstruction = Utils.getInstructionFromItem(item,line);

//                Arrays.stream(instructions).map(instruction -> {
//                    if(instruction instanceof VariableInstruction){
//                        if(((VariableInstruction) instruction).getName().equals(variable)){
//                            return correspondingInstruction;
//                        }
//                    }
//                    return instruction;
//                });

                for(Instruction instruction:instructions){
                    if(instruction instanceof VariableInstruction){
                        if(((VariableInstruction) instruction).getName().equals(variable)){
                            ((VariableInstruction) instruction).setInstruction(correspondingInstruction);
                        }
                    }
                }
            }
        }
    }
}
