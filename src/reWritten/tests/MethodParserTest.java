package reWritten.tests;

import domain.Method;
import org.junit.jupiter.api.Test;
import reWritten.domain.IntegerInstruction;
import reWritten.domain.MethodInstruction;
import reWritten.domain.PrintInstruction;
import reWritten.parsemarse.MethodParser;
import reWritten.parsemarse.SafeParsedElement;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MethodParserTest {

    @Test
    void parseIntegerPrintSimpleInstruction(){
        //prep
        String[] unparsed = new String[2];
        unparsed[0] = "MAIN: a => ";
        unparsed[1] = "  3 print";

        MethodInstruction[] methods = null;

        //actions
        SafeParsedElement<MethodInstruction> result = MethodParser.runParser(unparsed,0,methods,"MAIN");

        //assertions
        assert result.getErrorMessage().equals("");
        assertEquals(result.getErrorMessage(), "");
        assert result.getParsedElementOptional().isPresent();
        result.getParsedElementOptional().flatMap(x -> {
            assertEquals(x.getName(),"MAIN");
            assert x.getInstructions()[0] instanceof IntegerInstruction;
            assertEquals(((IntegerInstruction) x.getInstructions()[0]).getValue(),3);
            assert x.getInstructions()[1] instanceof PrintInstruction;
            assert x.getVariables()[0].equals("a");
            assert x.getVariables().length == 1;
            return Optional.of(x);
        });

    }

    @Test
    void invalidMethodName(){
        //prep
        String[] unparsed = new String[2];
        unparsed[0] = "MA$$IN: a => ";
        unparsed[1] = "  3 print";

        MethodInstruction[] methods = null;

        //actions
        SafeParsedElement<MethodInstruction> result = MethodParser.runParser(unparsed,0,methods,null);
        assertEquals(result.getErrorMessage(),"Illegal character in method name: MA$$IN in Line: 0\n");

    }

    @Test
    void invalidVariableName(){
        //prep
        String[] unparsed = new String[2];
        unparsed[0] = "MAIN: $ => ";
        unparsed[1] = "  3 print";

        MethodInstruction[] methods = null;

        //actions
        SafeParsedElement<MethodInstruction> result = MethodParser.runParser(unparsed,0,methods,"MAIN");
        assertEquals(result.getErrorMessage(),"Illegal character in variable name: $ in Line: 0\n");

    }

    @Test
    void areVariableNamesCorrect(){
        assert MethodParser.areVariableNamesCorrect(new String[] {"a", "b"},"",0).getFst();
        assert !MethodParser.areVariableNamesCorrect(new String[] {"$"},"",0).getFst();
    }
}
