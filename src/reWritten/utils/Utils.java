package reWritten.utils;


import reWritten.domain.instructions.Instruction;

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
}
