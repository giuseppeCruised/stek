package reWritten.parsemarse;

import reWritten.domain.Instruction;
import reWritten.domain.MethodInstruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {
    public static SafeParsedElement<MethodInstruction> runParser(String[] unparsed, int startLine,
                                                                 MethodInstruction[] methods) {
        boolean parsedSuccessfully = true;

        assert unparsed.length > 1;

        String errorMessage = "";


        String methodName = unparsed[0].split(":")[0];

        Pattern namePattern = Pattern.compile("[a-z,A-Z,0-9]+");

        if (!namePattern.matcher(methodName).matches()) {
            errorMessage = errorMessage + "Illegal character in method name: " + methodName + "\n";
            parsedSuccessfully = false;
        }

        String[] variableNames = unparsed[0].split(":")[1].split("=>")[0].substring(1).split(" ");
        parsedSuccessfully &= areVariableNamesCorrect(variableNames, errorMessage, startLine);

        int lineNumber = startLine + 1;
        for (String line : Arrays.copyOfRange(unparsed, 1, unparsed.length)) {
            String[] unparsedInstructions = line.substring(1).split(" ");
            for (String unparsedInstruction : unparsedInstructions) {
                SafeParsedElement<Instruction> parsedElement =
                        InstructionParser.runParser(unparsedInstruction, lineNumber, variableNames, methods);
                
            }

            lineNumber++;
        }

//        Pattern methodPattern = Pattern.compile("[a-z,0-9,A-Z]+: ([a-z,0-9,A-Z,?]+( |))*=>[a-z,A-z, ,0-9]*");
//        Matcher methodMatcher = methodPattern.matcher(unparsed[0]);
//        if (methodMatcher.matches()) {
//
//        } else {
//            errorMessage = ""
//        }


        return null;
    }

    private static boolean areVariableNamesCorrect(String[] variableNames, String currentErrorMessage, int startLine) {
        boolean flag = true;

        for (String variableName : variableNames) {
            if (!Pattern.compile("[a-z,A-Z,0-9]").matcher(variableName).matches()) {
                currentErrorMessage =
                        currentErrorMessage + "Illegal character in variable name: " + variableName + "\n";
                flag = false;
            }
        }

        return flag;
    }
}
