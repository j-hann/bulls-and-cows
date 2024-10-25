package src.com.baseball.numbergame.numberBaseball01;

import java.util.*;

//게임 통합 로직 클래스
public class NumberBaseBallGame {
    public static void main(String[] args) {
        //로직 하나로 만들기

        boolean gameProgramRun = true;//프로그램 실행

        //정답 숫자 생성 & 저장-------------------------------------
        HashSet<Integer> answerNumbers = new HashSet<Integer>();//정답 숫자
        Random randomValue = new Random();//정답 숫자 랜덤값 생성
        ArrayList<Integer> answerNumbersList = new ArrayList<Integer>();//정답 숫자 list 저장

        //사용자 입력-------------------------------------
        Scanner scanner = new Scanner(System.in);//값 입력받기
        ArrayList<Integer> userSelectNumberList = new ArrayList<Integer>();//사용자가 입력한 숫자 저장

        //게임 실행 기록-------------------------------------
        Map<Integer, Integer> gameHistory = new HashMap<>();
        int gamePlayCount = 0;//게임 실행 횟수
        int gameTryCount = 0;//게임 시도 횟수


        //프로그램 실행-------------------
        while (gameProgramRun) {

            //정답 숫자 랜덤으로 생성-------------------
            while (answerNumbers.size() < 3) {// 사이즈 3까지 반복문
                //1부터 9까지 랜덤 정수 생성
                int randomNumber = randomValue.nextInt(9) + 1;
                answerNumbers.add(randomNumber);//answerNumbers에 랜덤 number 넣기
                answerNumbersList = new ArrayList<Integer>(answerNumbers);
            }//while
            //출력 확인을 위한 코드 (삭제 예정)
            System.out.println("\n 정답 숫자 hashSet" + answerNumbers);
            System.out.println("정답 숫자 ArrayList" + answerNumbersList);

            System.out.println();
            System.out.println("환영합니다!✨ 원하시는 번호를 입력해주세요.\n" +
                    ("---------------------------------------------------------------------------\n") +
                    "1. 숫자야구 게임 시작하기🎮 | 2. 숫자야구 게임 기록 보기📃 | 3. 숫자야구 게임 종료하기🛑\n" +
                    ("---------------------------------------------------------------------------"));

            int chooseMenuNumber = scanner.nextInt();

            switch (chooseMenuNumber) {
                case 1:
                    System.out.println("🎮 숫자야구 게임을 시작합니다.");

                    while (true) {
                        //사용자가 숫자 입력하기
                        System.out.println("1부터 9 사이의 중복되지 않는 3자리의 숫자를 입력하세요 : ");

                        if (scanner.hasNextInt()) {//int값 넣으면 true 반환

                            String userInputNumber = scanner.next();//사용자가 입력한 숫자

                            //숫자 입력값 유효성 검사
                            if (userInputNumber.length() == 3) {//입력 값의 길이가 3자리
                                if (userInputNumber.matches(".[1-9].")) {//1부터 9까지 숫자 포함여부

                                    //문자 분리 후, Int 타입으로 변환
                                    for (String numberText : userInputNumber.split("")) {//문자열 분할
                                        userSelectNumberList.add(Integer.parseInt(numberText));
                                    }//enhanced for

                                    // 중복 검사
                                    Set<Integer> duplicateNumber = new HashSet<>(userSelectNumberList);
                                    if (duplicateNumber.size() == userSelectNumberList.size()) {//사이즈로 비교
                                        //출력 확인을 위한 코드 (삭제 예정)
                                        System.out.println("올바른 숫자가 입력되었습니다.");
                                        System.out.println("userSelectNumberList" + userSelectNumberList);
                                        //--------------------------------
                                        gameTryCount++;//게임 시도 횟수 저장

                                        //게임 로직
                                        int strike = 0;
                                        int ball = 0;
                                        String out = "임시";
                                        for (int indexAnswer = 0; indexAnswer < answerNumbers.size(); indexAnswer++) {

                                            if (userSelectNumberList.get(indexAnswer).equals(answerNumbersList.get(indexAnswer))) {
                                                strike++;
                                            } else if (userSelectNumberList.contains(answerNumbersList.get(indexAnswer))) {
                                                ball++;
                                            } else if(strike == 0 && ball ==0){
                                                out = "아웃입니다.";
                                            }//if-else
                                        }//
                                        System.out.println("출력---------------------");
                                        System.out.println("strike : " + strike);
                                        System.out.println("ball : " + ball);
                                        System.out.println(out);
                                        userSelectNumberList.clear();//사용자 입력 숫자 초기화

                                        //정답일 경우, 게임 종료 & 초기화------------
                                        if(strike == 3){
                                            answerNumbers.clear();//정답 숫자 초기화
                                            System.out.println("3 스트라이크!✨ 축하합니다!🎉 정답입니다.");
                                            System.out.println("총 시도 횟수는 : " + gameTryCount + "번 입니다.");
                                            gamePlayCount++;//게임 실행 횟수 카운트
                                            gameHistory.put(gamePlayCount,gameTryCount);
                                            break;
                                        }//if

                                    } else {//중복된 숫자 입력되었을 시
                                        System.out.println("중복된 숫자가 입력되었습니다. 다시 입력해주세요.");
                                        userSelectNumberList.clear();//userSelectNumberList 초기화
                                    }//if-else

                                } else if(userInputNumber.contains("0")){//특정 문자열 포함
                                    System.out.println("범위 이상의 숫자가 입력되었습니다. 다시 입력해주세요.");
                                }//if-else

                            } else {//입력 값이 3자리가 아닐 경우
                                System.out.println("입력값이 3자리가 아닙니다. 다시 입력해주세요.");
                            }//if-else-inner
                        } else {//문자 입력시
                            System.out.println("문자가 입력되었습니다. 다시 입력해주세요.");
                            scanner.nextLine();//문자만 여러번 입력시 엔터 눌러야 됨 -> 오류남
                        }//if-else
                        scanner.nextLine();
                    }//while-inner

                    break;//switch 종료

                case 2:
                    System.out.println("📃 숫자야구 게임 기록을 확인합니다.");

                    //기록이 없을 경우
                    if(gameHistory.isEmpty()){
                        System.out.println("🚨 기록된 게임 데이터가 없습니다.");
                    }//if

                    //게임 기록 출력
                    gameHistory.forEach((key, value) ->{
                        System.out.println(key + "번째 게임 : " + " 시도 횟수 : " + value);
                    });//forEach

                    break;

                case 3:
                    System.out.println("⚾숫자야구 게임⚾ 프로그램을 종료합니다.");
                    gameHistory.clear();//게임 기록 초기화
                    gameProgramRun = false;//프로그램 종료
                    break;

                default:
                    System.out.println("올바르지 않은 입력값입니다. 다시 입력해주세요.");
            }//switch
        }//while-outer

    }//main
}//end class
