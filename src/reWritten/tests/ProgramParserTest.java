package reWritten.tests;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import reWritten.domain.Program;
import reWritten.parsemarse.ProgramParser;
import reWritten.utils.Pair;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProgramParserTest {

    @Test
    void parseProgram() {
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