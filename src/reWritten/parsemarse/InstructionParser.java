package reWritten.parsemarse;

import reWritten.domain.Instruction;
import reWritten.domain.IntegerInstruction;
import reWritten.domain.MethodInstruction;
import reWritten.domain.PrintInstruction;
import reWritten.utils.Utils;

import java.util.Optional;
import java.util.regex.Matcher;

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
        } else {
            parsed.setErrorMessage("Unknown character: " + unparsed);
        }
        return parsed;
    }
}
