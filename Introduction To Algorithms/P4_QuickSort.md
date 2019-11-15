# Quick sort

Feature:
1. Divide and conquer algorithm
2. sorts "in place"

Steps:
1. Divide : Partition array into 2 subarrays around pivot x such that elements in lower subarray <= x <= elements in upper subarray
2. Conquer : Recursively sort 2 subarrays
3. Combine : trivial(不重要的)

Key: 

Partition(A, p, q)  // A[p..q]      T(n) = θ(n)
```python
x = A[p]
i = p
for j in p+1 .. q
    if(A[j] <= x)
        i = i+1
        exch(A[i], A[j])
exch(A[p], A[i])
return i
```

QuickSort(A, p, q)
```python
if p < q
    r = Partition(A, p, q)
    QuickSort(A, p, r)
    QuickSort(A, r+1, q)
```

## Analysis - assume all elements distinct
### Worst-case
T(n) = worst-case time when input sorted or reverse sorted

T(n) = T(0) + T(n-1) + θ(n) => T(n) = θ(pow(n, 2))   (through recursion tree)

### Best-case
T(n) = best-case time when partition always splits the array n/2:n/2

T(n) = 2T(n/2) + θ(n) => T(n) = θ(nlgn)    (case 2)

Sup split is always 1/10 : 9/10

T(n) = T(n/10) + T(9n/10) + θ(n)

cn \* log10(n) + θ(n) <= T(n) <= cn \* log10/9(n) + θ(n)  (through recursion tree)

### Sup we alternate lulcky, unlucky
    L(n) = 2U(n/2) + θ(n) lucky
    U(n) = L(n-1) + θ(n)  unlucky
    then: L(n) = 2L(n/2 - 1) + θ(n/2) + θ(n)
               = 2L(n/2 - 1) + θ(n)
               = θ(nlgn)  

# Randomize quick sort
1. Running time is independent of input ordering
2. No assumptions about input distribution
3. no specific input elicit(引出) worst-case behavior
4. worst-case determined only by random number generator

Pivot on random element
## Analysis
T(n) = random variable for running time assuming random numbers are independent
```
for k = 0, 1... n-1, let
    X[k] = 1 if partition generates k : n-k-1 split
    X[k] = 0 otherwise
X is indicator random variable
E[X[k]] = 1/n

T(n) = T(0) + T(n-1) + θ(n)  if 0:n-1 split
T(n) = T(1) + T(n-2) + θ(n)  if 1:n-2 split
...
T(n) = T(n-1) + T(0) + θ(n)  if n-1:0 split

T(n) = X[k] * (T(k) + T(n-k-1) + θ(n)) for k in 0..n-1
E[T(n)] += E[X[k] * (T(k) + T(n-k-1) + θ(n))]     for k in 0..n-1
        += E[X[k]] * E[T(k) + T(n-k-1) + θ(n)]    for k in 0..n-1
        += 1/n * (E[T(k)] + E[(T(n-k-1))] + θ(n)) for k in 0..n-1
        += 2/n * (E[T(k)]) + θ(n)                 for k in 0..n-1
Absorb k = 0, 1 terms into θ(n) for technical convenience
E[T(n)] += 2/n * (E[T(k)]) + θ(n)                 for k in 2..n-1

Prove : E[T(n)] <= a * nlgn for constant a > 0
    Choose a big enough so that a * nlgn >= E[T(n)] for small n
    Use fact : sum(klgk) for k in 2.. n-1 <= 1/2 * pow(n, 2) * lgn - 1/8 * pow(n, 2)
Substitution:
    E[T(n)] <= 2/n * sum(a * klgk) for k in 2.. n-1 + θ(n)
            <= 2a/n * (1/2 * pow(n, 2) * lgn - 1/8 * pow(n, 2))+ θ(n)
            <= anlgn - (an/4 - θ(n))
            <= anlgn if a is big enough so that an/4 dominates θ(n)
```
So that T(n) = θ(nlgn)

