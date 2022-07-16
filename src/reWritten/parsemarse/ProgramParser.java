package reWritten.parsemarse;

import reWritten.domain.MethodInstruction;
import reWritten.domain.UnparsedMethod;
import reWritten.utils.Pair;
import reWritten.domain.Program;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ProgramParser {
    public static Program parseProgram(File file) throws IOException {
        ArrayList<UnparsedMethod> unparsedMethods = splitProgramIntoUnparsedMethods(file);
        SafeParsedElement<MethodInstruction>[] parsedMethods =
                unparsedMethods.stream().map(unparsedMethod -> MethodParser
                        .runParser(unparsedMethod.getUnparsedLines(), unparsedMethod.getStartLine(), null,
                                unparsedMethod.getMethodName()))
                        .toArray(SafeParsedElement[]::new);


        return null;
    }

    public static ArrayList<UnparsedMethod> splitProgramIntoUnparsedMethods(File file)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String errorMessage = "";
        String line = "";
        int lineNumber = 0;

        int startLineNumber = 0;
        ArrayList<UnparsedMethod> unparsedMethods = new ArrayList<>();
        ArrayList<String> unparsedMethodContent = new ArrayList<>();
        boolean readingMethod = false;
        String methodName = "";
        UnparsedMethod unparsedMethod = null;
        try {
            while ((line = br.readLine()) != null) {
                if (readingMethod) {
                    if (line.replaceAll(" +", " ").equals(" ")) {
                        readingMethod = false;
                        ArrayList<String> unparsedMethodCopy = new ArrayList<>(unparsedMethodContent);
                        unparsedMethods.add(new UnparsedMethod(unparsedMethodCopy.toArray(String[]::new), methodName,
                                startLineNumber));
                        unparsedMethodContent.clear();
                    } else {
                        unparsedMethodContent.add(line);
                    }
                } else {
                    if (line.contains(":")) {
                        methodName = line.split(":")[0];
                        if (methodName.matches("[a-z,A-Z,0-9]+")) {
                            unparsedMethodContent.add(line);
                            methodName = methodName;
                            readingMethod = true;
                            startLineNumber = lineNumber;

                        } else {
                            errorMessage += "Illegal Character in MethodName: " + methodName + "in Line: " + lineNumber;

                        }
                    }

                }


                lineNumber++;
            }

        } finally {
            br.close();
        }


        return unparsedMethods;

    }
}
