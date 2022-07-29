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

        } else if (AndInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(
                    Optional.of(
                            new AndInstruction(lineNumber)
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

        } else if (CharInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(
                    Optional.of(
                            new CharInstruction(lineNumber, unparsed.charAt(1))
                    )
            );

        } else if (Arrays.stream(methods).anyMatch(method -> method.getName().equals(unparsed.substring(1)))
                && unparsed.charAt(0) == '?') {
            Arrays.stream(methods).filter(method -> method.getName().equals(unparsed.substring(1))).findFirst()
                    .flatMap(fittingMethod -> {
                        parsed.setParsedElementOptional(
                                Optional.of(new MethodPointerInstruction(lineNumber, fittingMethod)));
                        return Optional.of(fittingMethod);
                    });

        } else if (AddInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(Optional.of(new AddInstruction(lineNumber)));

        } else if (DupInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(Optional.of(new DupInstruction(lineNumber)));

        } else if (ExecutePointerInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(Optional.of(new ExecutePointerInstruction(lineNumber)));

        } else if (IfInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(Optional.of(new IfInstruction(lineNumber)));

        } else if (EqualsInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(Optional.of(new EqualsInstruction(lineNumber)));

        } else if (NotInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(Optional.of(new NotInstruction(lineNumber)));

        } else if (Arrays.stream(variables).anyMatch(var -> var.equals(unparsed))) {
            parsed.setParsedElementOptional(Optional.of(new VariableInstruction(lineNumber, unparsed)));

        } else if (StringInstruction.getPattern().matcher(unparsed).matches()) {
            parsed.setParsedElementOptional(
                    Optional.of(new StringInstruction(lineNumber, unparsed.substring(1, unparsed.length() - 1))));

        } else {
            parsed.setErrorMessage("Unknown instruction identifier: " + unparsed + " in line: " + lineNumber + "\n");
        }
        return parsed;
    }
}
