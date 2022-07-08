package domain;

public class Method {
    private Instruction[] instructions;
    private String[] variables;

    public Method(Instruction[] instructions, String[] variables) {
        this.instructions = instructions;
        this.variables = variables;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    public void setInstructions(Instruction[] instructions) {
        this.instructions = instructions;
    }

    public String[] getVariables() {
        return variables;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }

}
