package reWritten.tests;

import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import reWritten.domain.Program;
import reWritten.interpreter.Interpreter;
import reWritten.parsemarse.ProgramParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class InterpreterTest {
    @Test
    void runProgramTest() throws IOException {
        TemporaryFolder tmpFolder = new TemporaryFolder();
        tmpFolder.create();
        File testProgram = tmpFolder.newFile("testProgram.txt");
        FileWriter fW = new FileWriter(testProgram);

        fW.write("MAIN: a => \n" +
                " 3 PRINT \n" +
                " \n" +
                "PRINT: q => \n" +
                " print print \n" +
                " \n");
        fW.close();

        Program p = ProgramParser.parseProgram(testProgram);

        Interpreter.tryRunningProgram(p);
    }
}
