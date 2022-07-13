package main;

import domain.Instruction;
import domain.Method;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer2 {
    public static HashMap<String, Method> lexMethods(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader br = null;
        HashMap<String, Method> methods = new HashMap<>();
        String line;
        int lineNumber = 0;
        boolean readingMethod = false;
        String methodName = null;
        String[] arguments = null;
        ArrayList<Instruction> instructions = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Error: File was not found");
        }
        try {

            while ((line = br.readLine()) != null) {
                line = line.replaceAll(" +", " ");
                if (!readingMethod) {
                    Pattern methodPattern = Pattern.compile("[a-z,0-9,A-Z]+: ([a-z,0-9,A-Z,?]+( |))*=>( )*");
                    Matcher methodMatcher = methodPattern.matcher(line);
                    if (methodMatcher.matches()) {
                        readingMethod = true;
                        String important = line.split("=>")[0];
                        methodName = important.split(":")[0];
                        arguments = Arrays.stream(important.split(":")[1].substring(1).split(" +"))
                                .filter(s -> !s.equals("")).toArray(String[]::new);

                    }
                } else {
                    Pattern p = Pattern.compile("( )*");
                    Matcher m = p.matcher(line);

                    if (m.matches()) {
                        readingMethod = false;
                        methods.put(methodName, new Method(instructions.toArray(Instruction[]::new), arguments));
                        System.out.println(methodName + ":");
                        System.out.println("--Arguments:");
                        for (String arg : arguments) {
                            System.out.println(arg);
                        }
                        System.out.println("--Instructions:");
                        for (Instruction in : instructions) {
                            System.out.println(in.getName());
                        }
                        System.out.println("  ");
                        instructions.clear();
                    } else {
                        Pattern instPattern = Pattern.compile(" ([a-z,0-9,A-Z,?,!,+,==,-]+( |))*");
                        Matcher instMatcher = instPattern.matcher(line);
                        if (instMatcher.matches()) {
                            String[] identifiers = line.substring(1).split("( )+");
                            for (String identifier : identifiers) {
                                instructions.add(new Instruction(identifier, lineNumber));
                            }
                        }
                    }
                }

                lineNumber++;
            }
        } finally {
            br.close();
            methods.put(methodName, new Method(instructions.toArray(Instruction[]::new), arguments));
            System.out.println(methodName + ":");
            System.out.println("--Arguments:");
            for (String arg : arguments) {
                System.out.println(arg);
            }
            System.out.println("--Instructions:");
            for (Instruction in : instructions) {
                System.out.println(in.getName());
            }
            System.out.println("  ");
        }

        return methods;

    }

    private parsedWord parseWord(String line,Integer lineNumber){

        return null;
    }

    private parsedMethod parseMethod(Pair<String,Integer> lines){


        return null;
    }


}


