package reWritten.parsemarse;

import reWritten.domain.instructions.MethodInstruction;
import reWritten.domain.UnparsedMethod;
import reWritten.utils.Pair;
import reWritten.domain.Program;

import java.io.*;
import java.util.*;

public class ProgramParser {
    public static Program parseProgram(File file) throws IOException {
        Pair<ArrayList<UnparsedMethod>, String> split = splitProgramIntoUnparsedMethods(file);
        ArrayList<UnparsedMethod> unparsedMethods = split.getFst();
        String errorMessage = split.getSnd();

        MethodInstruction[] initializedMethods = unparsedMethods.stream()
                .map(unparsedMethod -> new MethodInstruction(unparsedMethod.getStartLine(), null, null,
                        unparsedMethod.getMethodName()))
                .toArray(MethodInstruction[]::new);

        SafeParsedElement<MethodInstruction>[] parsedMethods =
                unparsedMethods.stream().map(unparsedMethod ->
                        MethodParser.runParser(unparsedMethod.getUnparsedLines(), unparsedMethod.getStartLine(),
                                initializedMethods, unparsedMethod.getMethodName()))
                        .toArray(SafeParsedElement[]::new);

        ArrayList<MethodInstruction> fullyParsedMethods = new ArrayList<>();
        if (Arrays.stream(parsedMethods).allMatch(parsedMethod -> parsedMethod.getErrorMessage().equals(""))) {
            for (MethodInstruction initializedMethod : initializedMethods) {
                for (SafeParsedElement<MethodInstruction> parsedMethod : parsedMethods) {
                    if (initializedMethod.getName()
                            .equals(parsedMethod.getParsedElementOptional()
                                    .orElse(new MethodInstruction(0, null, null, "")).getName())) {
                        MethodInstruction tmpMethod = parsedMethod.getParsedElementOptional().orElse(null);
                        initializedMethod.setInstructions(tmpMethod.getInstructions());
                        initializedMethod.setLine(tmpMethod.getLine());
                        initializedMethod.setVariables(tmpMethod.getVariables());
                        fullyParsedMethods.add(initializedMethod);
                    }
                }
            }
        } else {
            for (SafeParsedElement<MethodInstruction> parsed : parsedMethods) {
                errorMessage += parsed.getErrorMessage();
            }
        }

        return new Program(fullyParsedMethods.toArray(MethodInstruction[]::new),errorMessage);
    }

    public static Pair<ArrayList<UnparsedMethod>, String> splitProgramIntoUnparsedMethods(File file)
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
                            errorMessage +=
                                    "Illegal Character in MethodName: " + methodName + " in Line: " + lineNumber + "\n";

                        }
                    }

                }


                lineNumber++;
            }

        } finally {
            br.close();
        }


        return new Pair<>(unparsedMethods, errorMessage);

    }
}
