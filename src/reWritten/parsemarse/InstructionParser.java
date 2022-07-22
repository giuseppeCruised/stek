package reWritten.parsemarse;

import reWritten.domain.instructions.*;

import java.util.Arrays;
import java.util.Optional;

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
        } else if (BooleanInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(
                    Optional.of(
                            new BooleanInstruction(lineNumber, Boolean.parseBoolean(unparsed))
                            )
            );
        } else if (AddInstruction.getPattern().matcher(unparsed).matches()){
            parsed.setParsedElementOptional(
                    Optional.of(
                            new AddInstruction(lineNumber)
                    )
            );
        } else if (Arrays.stream(variables).anyMatch(var -> var.equals(unparsed))){
            parsed.setParsedElementOptional(
                    Optional.of(
                            new VariableInstruction(lineNumber,unparsed)
                    )
            );
        } else {
            parsed.setErrorMessage("Unknown instruction identifier: " + unparsed + " in line: " + lineNumber + "\n");
        }
        return parsed;
    }
}
