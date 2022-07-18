package reWritten.parsemarse;

import reWritten.domain.Instruction;
import reWritten.domain.IntegerInstruction;
import reWritten.domain.MethodInstruction;
import reWritten.domain.PrintInstruction;
import reWritten.utils.Utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class InstructionParser {


    public static SafeParsedElement<Instruction> runParser(String unparsed, Integer lineNumber, String[] variables,
                                                           MethodInstruction[] methods) {
        assert !unparsed.equals("");

        SafeParsedElement<Instruction> parsed =
                new SafeParsedElement<Instruction>("", Optional.ofNullable((Instruction) null));

        if (IntegerInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(
                    Optional.of(
                            new IntegerInstruction(Integer.parseInt(unparsed), lineNumber))
            );
        } else if (PrintInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(
                    Optional.of(
                            new PrintInstruction(lineNumber)
                    )
            );
        } else if (Arrays.stream(methods).anyMatch(method -> method.getName().equals(unparsed))) {
            MethodInstruction method =
                    Arrays.stream(methods).filter(m -> m.getName().equals(unparsed)).findFirst().orElse(null);
            parsed.setParsedElementOptional(Optional.of(method));
        } else {
            parsed.setErrorMessage("Unknown instruction identifier: " + unparsed + " in line: " + lineNumber + "\n");
        }
        return parsed;
    }
}
