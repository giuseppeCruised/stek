package reWritten.tests;

import org.junit.jupiter.api.Test;
import reWritten.domain.Program;
import reWritten.interpreter.Interpreter;
import reWritten.parsemarse.ProgramParser;

import java.io.File;
import java.io.IOException;

public class AndProgramTest {

    @Test
    public void andProgramTest() throws IOException {
        File prog = new File("C:/Users/AnwenderIN/Desktop/Projects/stek/src/reWritten/programs/andProgram.txt");
        Program parsedProgram = ProgramParser.parseProgram(prog);
        Interpreter.tryRunningProgram(parsedProgram);
    }
}
