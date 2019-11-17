# Shell编程

## 正则表达式基础
> 通配符用来匹配符合条件的文件名，通配符是完全匹配，例如ls,find,cp这些命令支持通配符
> 正则表达式用来在文件中匹配符合条件的字符串，正则是包含匹配，例如grep,awk,sed支持正则表达式
- *:前一字符匹配0次或者任意多次，也就是前一字符的\*闭包
- .:匹配除了换行符外的任意一个字符
- ^:匹配行首
- $:匹配行尾
- []:匹配中括号中指定的任意一个字符
- [^]:取反
- \:转义符
- \{n\}:指定次数的闭包
- \{n,\}:次数>=n的闭包
- \{n,m\}:次数>=n,<=m的闭包

> grep "a*" test.txt:表示匹配任意行
> grep "aa*" test.txt:表示匹配包含至少一个连续a的行，或者写作grep "a\{1,\}" test.txt
> grep "a..f" test.txt:表示匹配包含a,f且a,f之间只有两个字符的行
> grep "a.*f" test.txt:表示匹配包含a,f且af之间有任意个字符(包括零个)的行，'.*'代表匹配任意多个字符
> grep "^d" test.txt：表示匹配以d开始的行
> grep "m$" test.txt:表示匹配以m结尾的行
> grep "^$" test.txt:表示匹配空白行

## 字符截取
- cut [选项] 文件名
    - f:列号(1-index)
    - d:分隔符
    > 默认分隔符为制表符
- printf 'format' content
    > 不支持读取文件，不支持管道符
    > printf "%s" $(cat test.txt) 无格式输出文件内容
    > printf "%s\t%s\t%s\t%s\n" $(cat test.txt) 格式化输出
    > print命令只能在awk中使用，比printf多了个自动换行
- awk 'condition1 {action1} condition2 {action2} ...'
    > 默认换行符为[ ]\{1,\}
    - BEGIN:condition
    - END:condition
    - F:指定分隔符
    > awk 'BEGIN {print "begin"} {print $0} END {print "end"}' test.txt
    > cat test.txt | grep -v NAME | awk '$4 >= 80 {print $0}'
- sed [选项] '[动作]' 文件名
    > 轻量级流编辑器,可以对数据进行选取，替换，删除，新增
    - n:输出到屏幕
    - e:

## 字符处理

## 条件判断

## 流程控制