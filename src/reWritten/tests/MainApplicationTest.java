package reWritten.tests;

import org.junit.jupiter.api.Test;
import reWritten.domain.Program;
import reWritten.interpreter.Interpreter;
import reWritten.parsemarse.ProgramParser;

import java.io.File;
import java.io.IOException;

public class MainApplicationTest {
    @Test
    public void mainTest() throws IOException {
        File prog = new File("C:/Users/AnwenderIN/Desktop/Projects/stek/src/reWritten/programs/TestProgram2.txt");
        Program parsedProgram = ProgramParser.parseProgram(prog);
        Interpreter.tryRunningProgram(parsedProgram);
    }
}
