package reWritten.parsemarse;

import reWritten.domain.instructions.Instruction;
import reWritten.domain.instructions.MethodInstruction;
import reWritten.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class MethodParser {
    public static SafeParsedElement<MethodInstruction> runParser(String[] unparsed, int startLine,
                                                                 MethodInstruction[] methods, String methodName) {
        boolean parsedSuccessfully = true;

        assert unparsed.length > 1;

        String errorMessage = "";

        String[] variableNames = unparsed[0].split(":")[1].split("=>")[0].substring(1).split(" ");
        if(variableNames.length==1 && variableNames[0].equals("")){
            variableNames = new String[0];
        } else {
            Pair<Boolean,String> correctVariableNames = areVariableNamesCorrect(variableNames, errorMessage, startLine);
            parsedSuccessfully = correctVariableNames.getFst();
            errorMessage += correctVariableNames.getSnd();
        }

        ArrayList<Instruction> instructions = new ArrayList<>();
        int lineNumber = startLine + 1;
        for (String line : Arrays.copyOfRange(unparsed, 1, unparsed.length)) {
            String[] unparsedInstructions = line.replaceAll(" +", " ").substring(1).split(" ");
            for (String unparsedInstruction : unparsedInstructions) {
                SafeParsedElement<Instruction> parsedElement =
                        InstructionParser.runParser(unparsedInstruction, lineNumber, variableNames, methods);
                parsedElement.getParsedElementOptional().flatMap(x -> {
                    instructions.add(x);
                    return Optional.of(x);
                });
                if(parsedElement.getParsedElementOptional().isEmpty()){
                    parsedSuccessfully = false;
                }
                errorMessage = errorMessage + parsedElement.getErrorMessage();
            }
            lineNumber++;
        }
        if (parsedSuccessfully) {
            return new SafeParsedElement<MethodInstruction>(errorMessage,
                    Optional.of(
                            new MethodInstruction(lineNumber, variableNames, instructions.toArray(Instruction[]::new),
                                    methodName)
                    ));
        } else {
            return new SafeParsedElement<MethodInstruction>(errorMessage,Optional.empty());
        }
    }

    public static Pair<Boolean,String> areVariableNamesCorrect(String[] variableNames, String currentErrorMessage, int startLine) {
        boolean flag = true;

        for (String variableName : variableNames) {
            if (!Pattern.compile("[a-z,A-Z,0-9]+").matcher(variableName).matches()) {
                currentErrorMessage =
                        currentErrorMessage + "Illegal character in variable name: " + variableName + " in Line: "+startLine+"\n";
                flag = false;
            }
        }

        return new Pair<>(flag,currentErrorMessage);
    }
}
