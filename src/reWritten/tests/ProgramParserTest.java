package reWritten.tests;

import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import reWritten.domain.Program;
import reWritten.parsemarse.ProgramParser;

import java.io.*;
import java.util.Locale;

class ProgramParserTest {

    @Test
    void parseProgram() throws IOException {

        TemporaryFolder tmpFolder = new TemporaryFolder();
        tmpFolder.create();
        File testProgram = tmpFolder.newFile("testProgram.txt");
        FileWriter fW = new FileWriter(testProgram);

        fW.write("MAIN: a => \n" +
                " 3 YEAH MAIN \n" +
                " \n" +
                "YEAH: q => \n" +
                " 3 print MAIN \n" +
                " \n");
        fW.close();

        Program p = ProgramParser.parseProgram(testProgram);
    }

    @Test
    void splitProgramIntoUnparsedMethodsTest() throws IOException {
        TemporaryFolder tmpFolder = new TemporaryFolder();
        tmpFolder.create();
        File testProgram = tmpFolder.newFile("testProgram.txt");
        FileWriter fW = new FileWriter(testProgram);

        fW.write("MAIN: a => \n" +
                " 3 print \n" +
                " \n" +
                "YEAH: b => \n" +
                " 3 print \n" +
                " \n");
        fW.close();

        ProgramParser.parseProgram(testProgram);
//        ArrayList<Pair<ArrayList<String>,Integer>> res = ProgramParser.splitProgramIntoUnparsedMethods(testProgram);

    }
}