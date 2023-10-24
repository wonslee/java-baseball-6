package baseball.game;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private int strikeCount;
    private int ballCount;
    List<Integer> computer;
    List<Integer> balls;

    public void start() {
        System.out.println("숫자 야구 게임을 시작합니다.");

        // strike, ball 카운트 초기화
        initializeMountCounts();
        // computer 초기화
        initializeComputer();

        // 숫자 입력 및 컴퓨터 야구공 초기화
        resolveInputBalls();

        // 스트라이크, 볼 카운트
        judgeStrikeOrBall();

        // 게임 결과 처리
        calculateMountResult();
    }

    private void calculateMountResult() {
        if (this.strikeCount == 0 && this.ballCount == 0) {
            System.out.println("낫싱");
        }

        if (this.strikeCount == 0 && this.ballCount != 0) {
            System.out.println(this.ballCount + "볼");
        } else if (this.strikeCount != 0 && this.ballCount == 0) {
            System.out.println(this.strikeCount + "스트라이크");
        } else {
            System.out.println(this.ballCount + "볼 " + this.strikeCount + "스트라이크");
        }
    }

    private void judgeStrikeOrBall() {
        for (int i = 0; i < balls.size(); i++) {
            if (Objects.equals(computer.get(i), balls.get(i))) {
                this.strikeCount++;
            } else if (computer.contains(balls.get(i))) {
                this.ballCount++;
            }
        }
    }

    private void initializeMountCounts() {
        this.strikeCount = 0;
        this.ballCount = 0;
    }

    private void initializeComputer() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        this.computer = computer;
    }

    private void resolveInputBalls() {
        System.out.print("숫자를 입력해주세요 : ");
        String input = Console.readLine();

        List<Integer> balls = new ArrayList<>();

        try {
            if (input.length() != 3) {
                throw new IllegalArgumentException("세자리 수를 입력해주세요");
            }
            for (String number : input.split("")) {
                int currentBall = Integer.parseInt(number);
                validateInputBallRange(currentBall);

                if (balls.contains(currentBall)) {
                    throw new IllegalArgumentException("각각 다른 수를 입력해주세요");
                } else {
                    balls.add(currentBall);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        this.balls = balls;
    }

    private void validateInputBallRange(int inputBall) {
        if (inputBall < 1 || inputBall > 9) {
            throw new IllegalArgumentException("1 ~ 9 만 입력해주세요");
        }
    }

}
