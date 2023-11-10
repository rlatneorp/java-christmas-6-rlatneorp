# README.md

# **기능구현목록**

## **핵심기능*

요일에 맞게 이벤트 내용을 적용해서 증정,할인,배지 유무 계산 후 출력

### 1.`안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.`

인사말 메시지 띄우기

### 2.`12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)`

방문 날짜 물어보는 메시지 띄우기

1~31 중에 한 가지 숫자만 문자로 입력받기

정수 타입으로 변환해서 변수에 저장

1 이상 31 이하의 숫자가 아닌 경우, "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 에러 메시지

### 3.`주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)`

주문 요청 메시지 띄우기

```java
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```

위의 메뉴중에서 메뉴와 개수 입력 → 메뉴-개수 형식으로 입력

### 4.`12월 OO일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!`

2에서 입력한 날짜를 받아와서 OO부분에 입력하고 메시지 띄우기

### 5. 숫자에 1,000원 단위로 쉼표 (,) 붙여서 자릿수 나타내고 출력하기

1,000원 2,000원 …

### 6.이벤트 적용하기

1) 1~31일 동안 진행하는 이벤트

평일(일~목) 디저트 1개당 2,023원 할인

주말(금~토) 메인메뉴 1개당 2,023원 할인

특별 할인 : 일요일과 크리스마스때 총주문 금액에서 1,000원 할인

증정 이벤트 :  ‘할인전’ 총주문액이 12만원 이상일 때 샴페인 1개 증정

2)1~25일 동안 진행하는 크리스마스 디데이 이벤트

1000원으로 시작하여 크리스마스가 올 때 까지 할인 금액 100원 할인

총주문 금액에서 해당 금액만큼 할인 (25일엔 3,400원)

### 7.배지 부여하기

총혜택 금액에 따라 배지 부여 (총혜택 금액 : 할인 금액의 합계 + 증정메뉴의 가격 (샴페인 - 25,000원)

5천원 이상 : 별

1만원 이상 : 트리

2만원 이상 : 산타

총혜택 금액에 따라 이벤트 배지의 이름 다르게 출력

### 8. 주의사항 조건 달기

총주문 금액 10,000원 이상부터 이벤트 적용

음료만 주문 시, 주문 불가능 (제로콜라, 레드와인, 샴페인)

메뉴는 한 번에 최대 20개 까지만 주문 가능

### 9.에러메시지 출력

공통 에러메시지 : "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."

고객이 메뉴판에 없는 메뉴를 입력하는 경우 에러 메시지

메뉴 개수 1이상의 숫자만 입력 가능 이외의 입력값은 위의 에러 메세지

메뉴 형식이 예시와 다르면 위의 에러 메시지

중복 메뉴를 입력한 경우(e.g. 시저샐러드-1,시저샐러드-1) 에러 메시지

### 10.주문 메뉴 출력 순서 자유롭게

나는 에파타이저 → 메인 → 디저트 → 음료 순으로 출력하도록 정함



### 11. 할인 후 예상 결제 내역 계산

할인 후 예상 결제 내역 금액은 할인 전 총주문 금액 - 할인 금액(증정이벤트 값을 뺀 금액 : 총혜택 금액 - 증정이벤트 금액)

### 12.아웃풋 출력

증정메뉴 : 증정이벤트에 해당되지 않으면 “없음”

혜택 내역 : 고객에게 적용된 이벤트 내역만 보여주기, 적용된 이벤트가 없으면 “없음”, 이벤트 내용출력 순서는 자유

이벤트 배지  : 배지가 부여되지 않으면 “없음”

각 금액과 내역 등을 계산,분류한 뒤 출력

<주문메뉴> <할인 전 총주문 금액><증정 메뉴><혜택 내역><총혜택 금액><할인 후 예상 결제 금액><12월 이벤트 배지> 순으로 출력

# 3주차 피드백

### [추가된 요구 사항](https://github.com/rlatneorp/java-christmas-6-rlatneorp#%EC%B6%94%EA%B0%80%EB%90%9C-%EC%9A%94%EA%B5%AC-%EC%82%AC%ED%95%AD)

- 아래 있는 `InputView`, `OutputView` 클래스를 참고하여 입출력 클래스를 구현한다.
    - 입력과 출력을 담당하는 클래스를 별도로 구현한다.
    - 해당 클래스의 패키지, 클래스명, 메서드의 반환 타입과 시그니처는 자유롭게 구현할 수 있다.

    ```
    public class InputView {
        public int readDate() {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = Console.readLine();
            // ...
        }
        // ...
    }
    ```

    ```
    public class OutputView {
        public void printMenu() {
            System.out.println("<주문 메뉴>");
            // ...
        }
        // ...
    }
    ```


### 1.클래스(객체) 분리 좀 더 살펴보기

### 2.함수(메서드) 라인에 대한 기준

함수 15라인 제한 → 15라인이 넘을 시 함수 분리를 고민

main()함수에도 해당된다

### 3.발생할 수 있는 예외 상황에 대해 고민

### 4.비즈니스 로직과 UI로직 분리

비즈니스 로직과 UI 로직을 한 클래스가 담당하지 않도록 한다. 단일 책임의 원칙에도 위배

현재 객체의 상태를 보기 위한 로그 메시지 성격이 강하다면 **toString()**을 통해 구현

View에서 사용할 데이터라면 getter 메서드를 통해 데이터를 전달

## 5.연관성이 있는 상수는 static final 대신 enum을 활용

## 6.final 키워드를 사용해 값의 변경을 막기

## 7.객체의 상태 접근을 제한

인스턴스 변수의 접근 제어자는 private으로 구현

## 8.객체는 객체스럽게 사용

**Lotto** 클래스는 **numbers**를 상태 값으로 가지는 객체이다. 그런데 이 객체는 로직에 대한 구현은 하나도 없고, **numbers**에 대한 getter 메서드만을 가진다.

```java
public class Lotto {
    private final List<Integer> numbers;
    
    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int getNumbers() {
        return numbers;
    }
}

public class LottoGame {
    public void play() {
        Lotto lotto = new Lotto(...);

        // 숫자가 포함되어 있는지 확인한다.
        lotto.getNumbers().contains(number);
        
        // 당첨 번호와 몇 개가 일치하는지 확인한다.
        lotto.getNumbers().stream()...
    }
}
```

---

**Lotto**에서 데이터를 꺼내지(get) 말고 메시지를 던지도록 구조를 바꿔 데이터를 가지는 객체가 일하도록 한다.

```java
public class Lotto {
    private final List<Integer> numbers;

    public boolean contains(int number) {
        // 숫자가 포함되어 있는지 확인한다.
        ...
    }
    
    public int matchCount(Lotto other) {
        // 당첨 번호와 몇 개가 일치하는지 확인한다.
        ...
    }
}

public class LottoGame {
    public void play() {
        Lotto lotto = new Lotto(...);
        lotto.contains(number);
        lotto.matchCount(...); 
    }
}
```

---

(**참고**. [getter를 사용하는 대신 객체에 메시지를 보내자](https://tecoble.techcourse.co.kr/post/2020-04-28-ask-instead-of-getter/))

## 9.필드(인스턴스 변수)의 수를 줄이기 위해 노력

필드(인스턴스 변수)의 수가 많은 것은 객체의 복잡도를 높이고, 버그 발생 가능성을 높일 수 있다. 필드에 중복이 있거나, 불필요한 필드가 없는지 확인해 필드의 수를 최소화한다.

예를 들어 총상금 및 수익률을 구하는 다음 객체를 보자.

```java
public class LottoResult {
    private Map<Rank, Integer> result = new HashMap<>();
    private double profitRate;
    private int totalPrize;
}
```

---

위 객체의 **profitRate**와 **totalPrize**는 등수 별 당첨 내역(**result**)만 있어도 모두 구할 수 있는 값이다. 따라서 위 객체는 다음과 같이 하나의 필드만으로 구현할 수 있다.

```java
public class LottoResult {
    private Map<Rank, Integer> result = new HashMap<>();

    public double calculateProfitRate() { ... }
    
    public int calculateTotalPrize() { ... }
}
```

---

## 10.성공하는 케이스 뿐만 아니라 예외에 대한 케이스도 테스트

## 11.테스트 코드도 코드

테스트 코드도 코드이므로 리팩터링을 통해 개선. 특히 반복적으로 하는 부분을 중복되지 않게 만들어야 한다.

## 12.테스트를 위한 코드는 구현 코드에서 분리

하지말것

- 테스트를 위해 접근 제어자를 바꾸는 경우
- 테스트 코드에서만 사용되는 메서드

## 13.단위 테스트하기 어려운 코드를 단위 테스트

아래 코드는 **Random** 때문에 **Lotto**에 대한 단위 테스트를 하기 힘들다. 단위 테스트가 가능하도록 리팩터링한다면 어떻게 하는 것이 좋을까?

```java
import camp.nextstep.edu.missionutils.Randoms;

public class Lotto {
    private List<Integer> numbers;

    public Lotto() {
        this.numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}

——————

public class LottoMachine {
    public void execute() {
        Lotto lotto = new Lotto();
    }
}
```

---

올바른 로또 번호가 생성되는 것을 테스트하기 어렵다. 테스트하기 어려운 것을 클래스 내부가 아닌 외부로 분리하는 시도를 해 본다.

```java
public class Lotto {
    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }
}

——————

import camp.nextstep.edu.missionutils.Randoms;

public class LottoMachine {
    public void execute() {
        List<Integer> numbers = Randoms
            .pickUniqueNumbersInRange(1, 45, 6);
        Lotto lotto = new Lotto(numbers);
    }
}
```

---

위 코드는 A 상황을 B로 바꾼 것이다.

A.

Application(테스트하기 어려움)

⬇️

LottoMachine(테스트하기 어려움)

⬇️

Lotto(테스트하기 어려움) ➡️ Randoms(테스트하기 어려움)

---

B.

Application(테스트하기 어려움)

⬇️

LottoMachine(테스트하기 어려움) ➡️ Randoms(테스트하기 어려움)

⬇️

Lotto(테스트하기 쉬움)

---

(**참고**. [메서드 시그니처를 수정하여 테스트하기 좋은 메서드로 만들기](https://tecoble.techcourse.co.kr/post/2020-05-07-appropriate_method_for_test_by_parameter/))

이처럼 단위 테스트를 할 때 테스트하기 어려운 부분은 분리하고 테스트 가능한 부분을 단위 테스트한다. 테스트하기 어려운 부분은 단위 테스트하지 않아도 된다. 남은 **LottoMachine**은 어떻게 테스트하기 쉽게 바꿀 수 있을지 고민해 본다.

## 14.private 함수를 테스트 하고 싶다면 클래스(객체) 분리를 고려

가독성의 이유만으로 분리한 private 함수의 경우 public으로도 검증 가능하다고 여겨질 수 있다. public 함수가 private 함수를 사용하고 있기 때문에 자연스럽게 테스트 범위에 포함된다. 하지만 가독성 이상의 역할을 하는 경우, 테스트하기 쉽게 구현하기 위해서는 해당 역할을 수행하는 다른 객체를 만들 타이밍이 아닐지 고민해 볼 수 있다. 다음 단계를 진행할 때에는 너무 많은 역할을 하고 있는 함수나 객체를 어떻게 의미 있는 단위로 분할할지에 초점을 맞춰 진행한다.