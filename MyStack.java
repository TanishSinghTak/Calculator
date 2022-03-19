public class MyStack implements StackInterface {
    public Object[] stack;
    public int top;

    public MyStack() {
        this.top = -1;
        this.stack = new Object[1];
    }

    public void push(Object o) {
        if(o == null){
        }
        else {
            if (this.stack.length - 1 == this.top) {
                Object[] temp = new Object[2 * this.stack.length];
                for (int i = 0; i < this.stack.length; i++) {
                    temp[i] = this.stack[i];
                }
                this.stack = temp;
            }
            this.top += 1;
            this.stack[this.top] = o;
        }
    }

    public Object pop() throws EmptyStackException {
        if (this.top == -1) {
            throw new EmptyStackException();
        } else {
            Object temp = this.stack[this.top];
            this.top -= 1;
            return temp;
        }
    }

    public Object top() throws EmptyStackException {
        if (this.top == -1) {
            throw new EmptyStackException();
        } else {
            return this.stack[this.top];
        }
    }

    public boolean isEmpty() {
        if (this.top == -1) {
            return true;
        }
        return false;
    }

    public String toString() {
        String ans = "[";
        for (int i = this.top; i >= 0; i--) {
            if (i == 0) {
                ans += this.stack[i].toString();
            } else {
                ans += this.stack[i].toString();
                ans += ", ";
            }
        }
        ans += "]";
        return ans;
    }
}

class EmptyStackException extends Exception{
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
