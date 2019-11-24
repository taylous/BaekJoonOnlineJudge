# 17836. 공주님을 구해라!

- URL :  https://www.acmicpc.net/problem/17836 
- 출처 :  [University ](https://www.acmicpc.net/category/5)> [홍익대학교 ](https://www.acmicpc.net/category/363)> [2019 홍익대학교 프로그래밍 경진대회](https://www.acmicpc.net/category/detail/2106) B번 



## 개요

> 목표

(1, 1) 에서 시작하여 공주가 있는 (N, M) 까지 최단시간을 도달해야한다.



> 제약조건

$$
3 \leq N, M \leq 100
$$

$$

1 \leq T \leq 5000
$$

$$
0 \equiv 공간, 1 \equiv 마법의 벽, 2 \equiv 그람
$$



> 그 외

- 맵에 존재하는 '그람' 획득 시, 벽을 모두 무시할 수 있다.

- 정확히 T초만에 공주에게 도착해도 구출 가능하다.



## 계획

1. N, M의 크기가 최대 100 * 100 밖에 안되기 때문에 기본적인 BFS(탐색) 문제로 이해했습니다.
2. T만큼 for를 돌면서 공주(N, M)에게 도달할 수 있다면 해당 시간을 출력.



## 결과

**메모리 : 15244KB, 시간 : 128ms**

**푸는데 걸린시간 : ** ***약 1시간***



## 회고

1. 처음에 우선순위큐(PriorityQueue)를 이용하여 문제를 풀었습니다.
2. 방문배열(visited)를 int형 3차원 배열로 하여 최대값( > 50,000)으로 초기화해서 갈려고 하는 곳의 좌표 시간 값이 객체의 시간보다 낮으면 가지 않도록 설계했습니다. 
3. 하지만 메모리 초과가 발생하였고 원인을 파악한 결과, "그람"을 들고 있을 때와 안 들고 있을 때 어떤 좌표에 저장된 시간값이 동일한 경우를 고려하지 않아 해당 지점에서 더 이상 나아가지 못하고 멤도는 현상이 발생했습니다. (100 x 100에서 0으로 모두 초기화 되어있는 배열에서)
4. 문제를 해결하고 나니 결과가 정상적으로 나왔습니다.



## Source Code

 총 세 가지 .java 파일을 확인하실 수 있습니다. 각 파일에 대한 설명은 아래에 명시 해놓았습니다.



1. Main_bfs : 기본적인  BFS 탐색입니다. (메모리 :  15244KB, 시간 : 128ms)
2. Main_pq : 우선순위큐를 이용한 탐색입니다. (메모리 :  15888KB, 시간 :  156ms) 시간이 더 단축될 것으로 예상했으나 더 오래 걸렸습니다.
3. Main_implement_pq : 위와 동일하나, 직접 구현한 우선순위큐를 사용하였습니다.(메모리 :  16440KB, 시간 :  168ms) 직접 자료구조를 구현하면 더 빠를 것이라 예상했으나 더 느리게 나왔습니다.



 저의 예상과는 다르게 우선순위큐보다 일반적인 큐를 이용한 탐색이 더 빨랐고, 직접 구현한 자료구조보다 라이브러리를 사용한 것이 더 빠르게 나왔습니다.