package reWritten.domain;

import reWritten.domain.items.DataItem;
import reWritten.utils.Log;

import java.util.Stack;

public class DataStack {
    private final Stack<DataItem> dataStack;

    public DataStack (){
        this.dataStack = new Stack<>();
    }

    public DataItem popItem(){
        return this.dataStack.pop();
    }

    public DataItem safePop(int line,String name){
        if(dataStack.isEmpty()){
            Log.log("Error: Empty String in "+ name +" Instruction in Line: "+line);
            return null;
        } else {
            return dataStack.pop();
        }
    }

    public void pushItem(DataItem item){
        this.dataStack.push(item);
    }

    public boolean isEmpty(){ return dataStack.isEmpty(); }
}
