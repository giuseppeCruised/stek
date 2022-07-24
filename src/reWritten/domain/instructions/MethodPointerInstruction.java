package reWritten.domain.instructions;

import reWritten.domain.DataStack;
import reWritten.domain.InstructionStack;
import reWritten.domain.items.MethodPointerItem;

import java.util.regex.Pattern;

public class MethodPointerInstruction implements Instruction {
    private int line;

    private MethodInstruction pointsTo;

    public MethodPointerInstruction(int line, MethodInstruction pointsTo){
        this.line = line;
        this.pointsTo = pointsTo;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public boolean executeInstruction(InstructionStack instructionStack, DataStack dataStack) {
        dataStack.pushItem(new MethodPointerItem(pointsTo));
        return true;
    }

    public Instruction getPointsTo() {
        return pointsTo;
    }

    public void setPointsTo(MethodInstruction pointsTo) {
        this.pointsTo = pointsTo;
    }
}
