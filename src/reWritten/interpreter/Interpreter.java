package reWritten.interpreter;

import reWritten.domain.*;
import reWritten.domain.instructions.Instruction;
import reWritten.domain.instructions.MethodInstruction;
import reWritten.utils.Log;

import java.util.Arrays;
import java.util.Locale;

public class Interpreter {
    public static void tryRunningProgram(Program program) {
        boolean hasMainMethod = Arrays.stream(program.getMethods()).anyMatch(method -> method.getName().toLowerCase(
                Locale.ROOT).equals("main"));
        if (hasMainMethod) {
            if (program.getErrorMessage().equals("")) {
                runProgram(program.getMethods());
            } else {
                Log.log(program.getErrorMessage());
            }
        } else {
            String errorMessage = program.getErrorMessage() + "No main method found \n";
            Log.log(errorMessage);
        }
    }

    public static void runProgram(MethodInstruction[] methods) {
        DataStack dataStack = new DataStack();
        InstructionStack instructionStack = new InstructionStack();

        instructionStack.pushInstruction(
                Arrays.stream(methods)
                        .filter(method -> method.getName()
                                                .toLowerCase(Locale.ROOT)
                                                .equals("main"))
                        .findFirst()
                        .orElse(null));

        while(!instructionStack.isEmpty()){
            Instruction currentInstruction = instructionStack.popInstruction();
            boolean instructionWasSuccessfull = currentInstruction.executeInstruction(instructionStack,dataStack);
            if(!instructionWasSuccessfull) break;
        }
    }
}
