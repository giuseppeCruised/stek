package reWritten.domain.items;

import reWritten.domain.instructions.Instruction;
import reWritten.domain.instructions.MethodInstruction;
import reWritten.utils.Log;

public class MethodPointerItem implements DataItem,PrintableItem{
    private MethodInstruction value;

    public MethodPointerItem(MethodInstruction value){
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object newValue) {
        value = (MethodInstruction) newValue;
    }

    @Override
    public void printItem() {
        Log.log("Pointer to Method: "+value.getName());
    }
}
