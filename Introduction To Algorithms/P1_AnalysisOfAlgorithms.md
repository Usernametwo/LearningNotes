# 算法分析

focus on performance and resource

## Probelm:Sorting

Input: sequence A<a1, a2, a3 ... an> of numbers
Output: Sorting them

1. Insertion sort:
    ```python
    for j in 2..n
        key = A[j]
        i = j-1
        while i>0 and A[i]>key
            A[i+1] = A[i]
            i--
        A[i+1] = key
    ```
    Running Time:
    - Depends on inputs(eg. already sorted)
    - Depends on the input size

    We want the upper bounds : guarantee to the user
2. Kinds of analysis
    - Worst-case(usually)
        T(n) : max time on any input of size n
    - Average-case(sometimes)
        T(n) : expected time over all inputs of size n
    - Bset-case(bogus:假象)
3. Asymptotic analysis(渐进分析)
    - Ignore machine-dependent constants
    - Look at growth of T(n) as n->∞

    Asymptotic notation
    - θ notation : drop lower order term, ignore leading constants.

4. Insertion sort analysis
    - Worst-case : T(n) = θ(n squared)

5. Merge sort
    |Merge sort A[1...n]|T(n)|
    |-----|-----|
    |If n = 1, done                                                            | θ(1)|
    |Recursively(递归) sort A[1...(n/2向上取整) ] and A[(n/2想上取整)+1...n]     | 2T(n/2)|
    |Merge two sorted lists                                                    | θ(n)|

    Key subroutine(子程序) - Merge

        lists:
        0 2 3 5
        1 4 6 7
        merge:
        0 1 2 3 4 5 6 7
        Time : θ(n) on n total elements

    Recurrence(循环)
    - T(n) = θ(1) if n = 1
    - T(n) = 2T(n/2) + θ(n) + θ(1) = 2T(n/2) + θ(n) if n > 1

    Recursion Tree(递归树)   (不知道怎么在markdown里面画树，不过可以想象一下)
    - T(n) = 2T(n/2) + cn 
    - 2T(n/2) = 4T(n/4) + cn
    - 4T(n/4) = 8T(n/8) + cn
    - ...
    - T(n/n) = n * θ(1)
    
    the height of the tree is logn, sum them up, T(n) = cn * lgn + θ(n) = θ(n * lgn)

