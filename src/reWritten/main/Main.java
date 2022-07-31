package reWritten.main;

import reWritten.domain.Program;
import reWritten.interpreter.Interpreter;
import reWritten.parsemarse.MethodParser;
import reWritten.parsemarse.ProgramParser;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        assert args.length > 0;
        Program program = ProgramParser.parseProgram(new File(args[0]));
        Interpreter.tryRunningProgram(program);
    }
}
