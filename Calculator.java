public class Calculator {

    public int evaluatePostFix(String s) throws InvalidPostfixException {
        MyStack stack = new MyStack();
        try {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {}
                else if (Character.isDigit(c)) {
                    int num = 0;
                    while (Character.isDigit(c)) {
                        num = num * 10 + (int) (c - '0');
                        i++;
                        if(i<s.length()) {
                            c = s.charAt(i);
                        }
                        else{break;}
                    }
                    i--;
                    stack.push(num);
                } else {
                    int val1 = (int) stack.pop();
                    int val2 = (int) stack.pop();

                    if (c == '+') {
                        stack.push(val2 + val1);
                    } else if (c == '-') {
                        stack.push(val2 - val1);
                    } else if (c == '/') {
                        stack.push(val2 / val1);
                    } else if (c == '*') {
                        stack.push(val2 * val1);
                    }
                }
            }
            int ans = (int) stack.pop();
            if(!stack.isEmpty()){
                throw new InvalidPostfixException();
            }
            return ans;

        } catch (EmptyStackException e) {
            throw new InvalidPostfixException();
        }
    }

    public int precedence(char s) {
        if (s == '+' || s == '-') {
            return 1;
        } else if (s == '/' || s == '*') {
            return 2;
        }
        return -1;
    }

    public String convertExpression(String s) throws InvalidExprException {
        MyStack stack = new MyStack();
        String ans = "";
        int intno = 0;
        int operno = 0;
        try {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {}
                else if (Character.isDigit(c)) {
                    while (Character.isDigit(c)) {
                        ans += c;
                        i++;
                        if(i<s.length()) {
                            c = s.charAt(i);
                        }
                        else{break;}
                    }
                    intno++;
                    ans += ' ';
                    i--;
                } else if (c == '(')
                    stack.push(c);
                else if (c == ')') {
                    while (!stack.isEmpty() && (Character) stack.top() != '('){
                        ans += stack.pop();
                        operno+=1;
                        if(!stack.isEmpty()){
                            ans += " ";
                        }
                        if(intno<=operno){
                            throw new InvalidExprException();
                        }
                    }
                    stack.pop();
                    if(stack.isEmpty()){
                        ans = ans.substring(0,ans.length()-1);
                    }
                }
                else {
                    while (!stack.isEmpty() && precedence(c) <= precedence((Character) stack.top())) {
                        ans += stack.pop();
                        operno+=1;
                        ans += " ";
                        if(intno<=operno || s.charAt(i-1) == '('){
                            throw new InvalidExprException();
                        }
                    }
                    stack.push(c);
                }
            }
            while (!stack.isEmpty()) {
                System.out.println();
                if(stack.top().equals('(')){
                    throw new InvalidExprException();
                }
                else {
                    ans += stack.pop();
                    operno+=1;
                    if (!stack.isEmpty()) {
                        ans += " ";
                    } else {
                    }
                }
            }
            if(intno-1!=operno){
                throw new InvalidExprException();
            }
            return ans;
        }
        catch(EmptyStackException e){
            throw new InvalidExprException();
        }
    }

    public static void main(String[] args) {
        Calculator obj = new Calculator();
        try{
            String s = "1+2+3(4)";
            System.out.println(obj.convertExpression(s));
        }
        catch (Exception e){
            System.out.println("error");
        }
    }

}

class InvalidPostfixException extends Exception{
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

class InvalidExprException extends Exception{
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
