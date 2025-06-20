import java.util.*;

class Solution {
    String operators = "+-*";
    long answer = 0;

    ArrayList<Character> operator = new ArrayList<>();
    ArrayList<Long> operand = new ArrayList<>();

    public long solution(String expression) {
        // 연산자와 피연산자 구분
        int idx = 0;
        for(int i = 0; i < expression.length(); i++){
            if(operators.contains(expression.charAt(i)+"")){
                operator.add(expression.charAt(i));
                operand.add(Long.parseLong(expression.substring(idx, i)));
                idx = i+1;
            }
        }
        operand.add(Long.parseLong(expression.substring(idx)));

        // 모든 연산자 우선순위 경우의 수 생성
        permutation("");
        return answer;
    }

    public void permutation(String ops){
        if(ops.length() == 3){
            answer = Math.max(answer,calculate(ops));
            return;
        }

        for(int i = 0; i < 3; i++){
            if(ops.contains(operators.charAt(i)+"")) continue;
            permutation(ops + operators.charAt(i));
        }
    }

    public long calculate(String ops){
        LinkedList<Character> operatorTemp = new LinkedList<>(operator);
        LinkedList<Long> operandTemp = new LinkedList<>(operand);

        // 연산자 우선순위에 따라 계산
        for(int i = 0; i < 3; i++){
            char op = ops.charAt(i);
            for(int j = 0; j < operatorTemp.size(); j++){
                if(op == operatorTemp.get(j)){
                    operandTemp.set(j, cal(operandTemp.get(j),operandTemp.get(j+1), op));
                    operatorTemp.remove(j);
                    operandTemp.remove(j+1);
                    j--;
                }
            }
        }

        return Math.abs(operandTemp.get(0));
    }

    public long cal(long a, long b, char op){
        if(op == '+') return a+b;
        else if(op == '-') return a-b;
        else return a*b;
    }
}