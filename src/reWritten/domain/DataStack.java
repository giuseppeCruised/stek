package reWritten.domain;

import reWritten.domain.items.DataItem;

import java.util.Stack;

public class DataStack {
    private final Stack<DataItem> dataStack;

    public DataStack (){
        this.dataStack = new Stack<>();
    }

    public DataItem popItem(){
        return this.dataStack.pop();
    }

    public void pushItem(DataItem item){
        this.dataStack.push(item);
    }

    public boolean isEmpty(){ return dataStack.isEmpty(); }
}
