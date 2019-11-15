# 渐进符号

## Big O notation(upper bound)
    define:
        f(n) = O(g(n)) means there are constants c>0, n0>0 such that 0<=f(n)<=c*g(n) for all n>=n0
        eg. 2*(n squared) = O(n cubed)    '=' not means equals, it means belongs to
    set defination:
        O(g(n)) = {f(n):there are constants c>0,n0>0 such that 0<=f(n)<=cg(n) for all n>=n0}

    Macro convention
        A set in a formula represents an anonymous(匿名) function in that set
        Ex. f(n) = (n cubed) + O(n squared) means there is a function h(n) belongs to O(n squared) such that f(n) = (n cubed) + h(n)
        Ex. (n squared) + O(n) = O(n squared)  '=' not means equals, it means belongs to

## Big Ω notation(lower bound)
    Ω(g(n)) = {f(n):there exists constants c>0, n0>0 such that 0<=c*g(n)<=f(n) for all n>=n0}
    Ex. pow(n, 1/2) = Ω(lgn)

## Big θ notation
    θ(g(n)) = O(g(n)) ∩ Ω(g(n))

## Little o and little ω notations
    this inequality must hold for all c > 0
    Ex. 2*pow(n, 2) = o(pow(n, 3))    n0 = 2/c
        1/2 * pow(n, 2) = θ(pow(n, 2)) but not belongs to o(pow(n, 2))

    Tips : So I thounght o(g(n)) = O(g(n)) - θ(g(n)), and ω(g(n)) = Ω(g(n)) - θ(g(n)) ?

# Solving recurrences(递归)

## Substitution method

1. Guess the form of the solution
2. Verify by induction(数学归纳法)
3. Solve for constants

Ex. T(n) = 4T(n/2) + n
- Guess T(n) = O(pow(n, 3))
- Assume T(k) <= c*pow(k, 3) for k<n  (c>0)
- T(1) = θ(1) <= c*pow(1, 3) 成立 if c is sufficiently large
- T(n) = 4T(n/2) + n <= 4c\*pow(n/2, 3) + n = c\*pow(n, 3)/2 + n, so T(n) <= c\*pow(n, 3)/2 + n
- so c\*pow(n, 3) should >= c\*pow(n, 3)/2 + n => the residual c\*pow(n, 3)/2 - n should >= 0 存在c使得不等式成立
- so O(pow(n, 3)) is a upper bound

Try T(n) = O(pow(n, 2))
- T(1) = θ(1) <= c*pow(1, 2) 成立 if c is sufficiently large
- T(n) = 4T(n/2) + n <= 4c\*pow(n/2, 2) + n = c\*pow(n, 2) + n, so T(n) <= c\*pow(n, 2) + n
- so c\*pow(n, 2) should >= c\*pow(n, 2) + n => the residual -n should >= 0 for all n > k, 不等式不成立

Assume T(k) <= c1 * pow(k, 2) - c2 * k for k < n
- T(1) = θ(1) <= c1*pow(1, 2) - c2 * 1 => c1 should be sufficient large so that c1 >= c2
- T(n) = 4T(n/2) + n <= 4c1\*(pow(n/2, 2) - c2*n/2 ) + n = c1\*pow(n, 2) + (1 - 2c2) \* n = c1\*pow(n, 2) - c2\*n - (-1 + c2) * n
- (-1 + c2) * n should >= 0  => c2 >= 1

## Recursion tree method

Ex. T(n) = T(n/4) + T(n/2) + pow(n, 2)
Assume it is a tree
- T(n) = T(n/4) + T(n/2) + pow(n, 2)                                                ---pow(n, 2)
- T(n/4) + T(n/2) = T(n/16) + T(n/8) + pow(n/4, 2) + T(n/8) + T(n/4) + pow(n/2, 2)  ---5/16 * pow(n, 2)
- T(n/16) + T(n/8) + T(n/8) + T(n/4) = ...                                          ---25/256 * pow(n, 2)
- ...            ---pow(5, k)/pow(16, k) * pow(n, 2)
- last generation = θ(1) + θ(1) + ... + θ(1)                                        ---\<n

so add them up T(n) = pow(n, 2) * (1 + 5/16 + 25/256 + pow(5, k)/pow(16, k) + ...) <= 2 * pow(n, 2)

so the upper bound is O(pow(n, 2))

## Master method

Applies to recurrences of the form T(n) = aT(n/b) + f(n) where a >= 1, b > 1, f(n) is asymptotically positive(渐进趋正)

Compare f(n) with pow(n, logb(a))         ------ pow(n, logb(a)) is number of leaves in the recursion tree
1. f(n) = O(pow(n, logb(a) - ε)) for some ε > 0    => T(n) = θ(pow(n, logb(a)))
2. f(n) = θ(pow(n, logb(a)) \* pow(lgn, k)) for some k >= 0   =>   T(n) = θ(pow(n, logb(a)) \* pow(lgn, k+1))
3. f(n) = Ω(pow(n, logb(a) + ε)) for some ε > 0 & a \* f(n/b) <= (1-ε1)f(n) for some ε1 > 0(每一层的f(n)都要比上一层的f(n)小)  => T(n) = θ(f(n))

You can use recursion tree method to prove it.

Ex. T(n) = 4T(n/2) + n.
- Caculate the pow(n, logb(a)) = pow(n, 2)
- belongs to case 1
- so T(n) = θ(pow(n, 2))

T(n) = 4T(n/2) + pow(n, 2).
- belongs to case 2, k = 0
- so T(n) = θ(pow(n, 2) \* lgn)

T(n) = 4T(n/2) + pow(n, 3).
- belongs to case 3
- so T(n) = θ(pow(n, 3))

T(n) = 4T(n/2) + pow(n, 2) / lgn.
- master method can not apply

