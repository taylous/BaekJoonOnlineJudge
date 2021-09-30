# [백준3425] 고스택

- URL :  https://www.acmicpc.net/problem/3425
- 출처 :  [Central European Regional Contest](https://www.acmicpc.net/category/13) > [CERC 2011](https://www.acmicpc.net/category/detail/34) J번



## 개요

> 목표

문제에서 주어진 동작을 수행하는 Stack 자료구조 구현



> 제약조건

- 정수값은 어떠한 경우에도(ADD, SUB, DIV, MOD) 10^9을 넘지 않습니다.
- 입력값 Vi (0 ≤ Vi ≤ 10^9)
- 명령어가 100,000개를 넘어가는 경우와 스택이 수행될 때, 1,000개 이상의 숫자를 저장하는 경우는 없습니다.






## 계획

1. 기본적인 Stack을 구현하고자 했습니다.
2. 문제에 나온 동작대로 메소드를 구현하면 되었으므로 크게 어렵진 않았습니다.



## 결과

걸린시간: 약 3시간



## 회고

1. 생각하지 못한 Input이 많았습니다. (ex: 처음부터 "END"가 입력되는 경우)
2. "NUM" 명령이 발생했을 때 index 설정을 잘 못하여 헤맸습니다.
3. Modulo 연산시 피제수는 "0"이어도 상관이 없다는 점을 간과하여 오답이 발생했습니다.



사실 문제 자체는 크게 어렵지 않은데 예외 상황을 잘 고려해야하는 점이 이 문제의 특징인 듯 합니다.

