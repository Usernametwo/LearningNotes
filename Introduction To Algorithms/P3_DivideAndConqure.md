# Divide and conquer

## Merge sort

T(n) = 2T(n/2) + θ(n)
- using master method, logb(a) = 1
- belongs to case 2, k = 0
- T(n) = θ(nlgn)

## Binary search

T(n) = T(n/2) + θ(1) = θ(lgn)

## Powering(乘方) a number

Given number x, integer n >= 0, compute pow(x, n)

Naive algorithm:pow(x, n) = x\*x\*x\*....x   => θ(n)

Divide and conquer algorithm:
    
    pow(x, n) = pow(x, n/2) \* pow(x, n/2) if n is even(偶数)
    pow(x, n) = pow(x, (n-1)/2) \* pow(x, (n-1)/2) \* x if n is odd(奇数)
    T(n) = T(n/2) + θ(1) = θ(lgn)

## Fibonacci numbers
1. F(n) = 0 if n = 0
2. F(n) = 1 if n = 1
3. F(n) = F(n-1) + F(n-2) if n >= 2

### Naive recursive algorithm
T(n) = Ω(pow(φ, n)) (φ = (1+pow(5, 1/2))/2, 黄金分割)

### Bottom-up algorithm
Compute F(1), F(2), F(3),..., F(n)

T(n) = θ(n)

### Naive recursive squaring
Theorem: F(n) = pow(φ, n)/pow(5, 1/2) rounded to nearest integer

T(n) = θ(lgn)

But it is not allowed because of floating point machine

### Recursive squaring
Theorem: [[F(n+1), F(n)], [F(n), F(n-1)]] = pow([[1, 1], [1, 0]], n)

T(n) = θ(lgn)

Proof:by induction on n(省略)

## Matrix multiplication(乘法)

Input:square matrix:A, B

Output:C = A\*B

### Standard algorithm
```python
for i in 1..n
    for j in 1..n
        C[i][j] = 0
        for k in 1..n
            C[i][j] = C[i][j] + A[i][k] * B[k][j]
```

T(n) = pow(n, 3)

### Divide and conquer alogrithm
Idea: n x n matrix = 2 x 2 block matrix of n/2 x n/2 sub matrix

    C = A * B
    [[r, s], [t, u]] = [[a, b], [c, d]] \* [[e, f], [g, h]]
    r = ae + bg
    s = af + bh
    t = ce + dg
    u = cf + dh
    8 recursive multiplications of n/2 x n/2 matrix
    4 add options -- θ(pow(n, 2))
T(n) = 8T(n/2) + θ(pow(n, 2)) (Case 1)

T(n) = θ(pow(n, 3))

### Strassen algorithm
Idea: reduce the number of multiplications

    P1 = a* (f-h)
    P2 = (a+b) * h
    P3 = (c+d) * e
    P4 = g * (d-e)
    P5 = (a+d) * (e+h)
    P6 = (b-d) * (g+h)
    P7 = (a-c) * (e+f)

    r = P5 + P4 - P2 + P6
    s = P1 + P2
    t = P3 + P4
    u = P5 + P1 - P3 - P7
T(n) = 7T(n/2) + θ(pow(n, 2)) (Case 1)

T(n) = θ(pow(n, lg7))

## VLSI layout
Problem: Embed a complete binary tree on n leaves in a grid with minimum area

### Naive embedding

H(n) = θ(lgn)

W(n) = θ(n)

Area = θ(n\*lgn)

### H layout

H(n) = W(n) = L(n) = 2L(n/4) = θ(pow(n, 1/2))

Area = θ(n)