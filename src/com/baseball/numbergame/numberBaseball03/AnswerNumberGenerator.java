package src.com.baseball.numbergame.numberBaseball03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class AnswerNumberGenerator {
    public static void main(String[] args){
        //정답 숫자 생성 & 저장-------------------------------------
        HashSet<Integer> answerNumbers = new HashSet<Integer>();//정답 숫자
        Random randomValue = new Random();//정답 숫자 랜덤값 생성
        ArrayList<Integer> answerNumbersList = new ArrayList<Integer>();//정답 숫자 list 저장

        //정답 숫자 랜덤으로 생성---------------------
        while (answerNumbers.size() < 3){// 사이즈 3까지 반복문
            //1부터 9까지 랜덤 정수 생성
            int randomNumber = randomValue.nextInt(9) + 1;
            answerNumbers.add(randomNumber);//answerNumbers에 랜덤 number 넣기
            answerNumbersList = new ArrayList<Integer>(answerNumbers);
        }//while

        System.out.println("\n정답 숫자 hashSet" + answerNumbers);
    }//main
}//end class
