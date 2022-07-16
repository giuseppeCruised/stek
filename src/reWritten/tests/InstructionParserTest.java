package reWritten.tests;

import reWritten.domain.*;
import reWritten.parsemarse.InstructionParser;
import reWritten.parsemarse.SafeParsedElement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {
    private final int LINENUMBER = 0;

    @org.junit.jupiter.api.Test
    void parsePrintInstructionTest() {
        String unparsed = "print";
        String[] variables = new String[0];
        MethodInstruction[] methods = new MethodInstruction[0];

        SafeParsedElement<Instruction> result = InstructionParser.runParser(unparsed, LINENUMBER, variables, methods);
        assert (result.getParsedElementOptional().isPresent());
        result.getParsedElementOptional().flatMap(x -> {
            assert x instanceof PrintInstruction;
            return Optional.of(x);
        });
    }

    @org.junit.jupiter.api.Test
    void parseIntegerInstructionTest() {
        String unparsed = "3";
        String[] variables = new String[0];
        MethodInstruction[] methods = new MethodInstruction[0];

        SafeParsedElement<Instruction> result = InstructionParser.runParser(unparsed, LINENUMBER, variables, methods);
        assert (result.getParsedElementOptional().isPresent());
        result.getParsedElementOptional().flatMap(x -> {
            assert x instanceof IntegerInstruction;
            assert ((IntegerInstruction) x).getValue() == 3;
            return Optional.of(x);
        });

    }
}