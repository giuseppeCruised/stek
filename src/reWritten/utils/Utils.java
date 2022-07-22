package reWritten.utils;


import reWritten.domain.instructions.BooleanInstruction;
import reWritten.domain.instructions.ErrorInstruction;
import reWritten.domain.instructions.Instruction;
import reWritten.domain.instructions.IntegerInstruction;
import reWritten.domain.items.BooleanItem;
import reWritten.domain.items.DataItem;
import reWritten.domain.items.IntegerItem;

public class Utils {

    public static boolean isInteger(String instruction){
        if (instruction == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(instruction);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static Instruction[] reverseInstructions(Instruction[] instructions){
        Instruction[] newInstructions = new Instruction[instructions.length];
        for(int i = 0; i < instructions.length; i++){
            newInstructions[i] = instructions[instructions.length - 1 - i];
        }
        return newInstructions;
    }

    public static Instruction getInstructionFromItem(DataItem item,int line){
        Instruction ans;

        if(item instanceof IntegerItem){
            ans = new IntegerInstruction((int)((IntegerItem) item).getValue(),line);
        }else if(item instanceof BooleanItem){
            ans = new BooleanInstruction(line,(Boolean)((BooleanItem) item).getValue());
        }else {
            ans = new ErrorInstruction();
        }

        return ans;
    }
}
