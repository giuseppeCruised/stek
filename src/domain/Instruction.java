package domain;

public class Instruction {
    private String name;

    private int line;

    public Instruction(String instructionName, int line){
        this.name = instructionName;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String instructionName) {
        this.name = instructionName;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
