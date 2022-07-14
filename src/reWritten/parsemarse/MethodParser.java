package reWritten.parsemarse;

import reWritten.domain.MethodInstruction;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {
    public static SafeParsedElement<MethodInstruction> runParser(String[] unparsed, int startLine,
                                                                 String[] methodNames) {
        assert unparsed.length > 1;

        String errorMessage = "";


        String methodName = unparsed[0].split(":")[0];

        Pattern namePattern = Pattern.compile("[a-z,A-Z,0-9]");

        if (!namePattern.matcher(methodName).matches()) {
            errorMessage = errorMessage + "Illegal character in method name: " + methodName + "\n";
        }

        String[] variableNames = unparsed[0].split(":")[1].split("=>")[0].split(" ");


        for (String variableName : variableNames) {
            if (!Pattern.compile("[a-z,A-Z,0-9]").matcher(variableName).matches()) {
                errorMessage = errorMessage + "Illegal character in variable name: " + variableName + "\n";
            }
        }



        Pattern methodPattern = Pattern.compile("[a-z,0-9,A-Z]+: ([a-z,0-9,A-Z,?]+( |))*=>[a-z,A-z, ,0-9]*");
        Matcher methodMatcher = methodPattern.matcher(unparsed[0]);
        if (methodMatcher.matches()) {

        } else {
            errorMessage = ""
        }


        return null;
    }
}
