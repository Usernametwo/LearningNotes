# Shell基础

## Bash的基本功能

- shell是一种命令解释器
    > vi /etc/shells 查看Linux系统里面支持的shell
- DOS和UNIX换行符的区别(可以在vim模式下 :set ff=unix/dos 来互相转换)
    > 可以通过cat -A 来查看换行符的区别，unxi格式的换行符为$,windows下面的为^M$
- history
    - c:clear
    - w:write
    > 默认保存1000条记录,可以在/etc/profile文件里面的HISTSIZE属性处更改条数
    > !n默认会执行history中的第n条命令，!!会重复执行前一条命令
- alias [newname=origincommand]
    > 命令执行优先级:绝对路径或者相对路径可以执行的命令->别名->执行Bash内部命令(cd...)->$PATH环境变量定义的目录查找命令
    > echo $PATH 查看环境变量
    > 永久生效需要写入对应的.bashrc文件(~/.basrc)
 - unalias newname
 - Bash常用快捷键
    - Ctrl+U:剪切光标前命令
    - Ctrl+Y:粘贴
    - Ctrl+Z:暂停并放入后台
- 输入输出重定向
    - 标准输入:/dev/stdin
        - command < file:将文件作为命令的输入，例如 wc -l < file
    - 标准输出:/dev/stdout
        - command > file:将命令的输出覆盖输出到指定文件
        - command >> file:追加
        - command 2> file:错误输出
        - command 2>> file
        - command &> file:正确输出和错误输出都保存下来
        - command &>> file
        - command &> /dev/null:将输出丢弃
        - command > correctfile 2> failfile
- 多命令顺序执行
    - command1;command2:命令之间无任何逻辑关系
    - command1&&command2:只有当command1正确执行之后command2才会执行
    - command1||command2:只有当command1不正确执行之后command2才会执行
    > command && echo 'yes' || echo 'no'
- dd:复制硬盘数据
- | : 管道符
    - command1 | command2 : command1的正确输出作为command2的操作对象
- grep [选项] keyword file
    - i : ignore case
    - n : line number
- 通配符(和正则表达式有区别，通配符仅用来匹配文件名
    - ?:任意一个字符
    - *:任意一个或者多个字符
    - []:匹配[]中的任意一个字符
    - [-]:匹配一个范围中的任意一个字符,例如[a-z]
    - [^]:匹配不是[]中的一个字符,例如[^1-9]
- Bash中的特殊字符
    - '':单引号中的所有特殊符号都没有特殊含义
    - "":双引号中的除了$,`,\之外的特殊字符没有特殊含义
    - ``:反引号中的字符串会当做系统命令优先执行,``相当于$()
    - $:获取变量的值，比如$PATH
    - \:转义符号，取消特殊字符的特殊作用

## Bash变量

- 用户自定义变量
    - Bash的变量的默认类型都是字符串
    - 赋值等号两边不能有空格
    - set:查看所有已定义的变量
    - unset variable:删除变量
    - 只在当前Shell中生效
    > variable=value 用户自定义变量
    > unset variable
- 环境变量
    - 环境变量在当前Shell和子Shell生效，如果将环境变量写到了配置文件，对于所有的Shell都生效
    - env查看所有的环境变量
    - unset variable:删除
    - PATH环境变量：系统查找命令的路径
    - PS1：定义系统提示符的变量
    > export variable=value
- 位置参数变量:用来向脚本中传递参数
    - $n：代表第n(0<=n<10)个参数,$0代表命令本身，n>=10需要使用${n}
    - $*：把整个参数当成一个字符串返回
    - $@：把所有参数当成一个数组返回
    - $#：参数个数，使用空格分隔，不包含$0
- 预定义变量
    - $?:上一个命令的返回状态:0-执行正确,非0-执行错误
    - $$:当前进程的进程号
    - $!:后台运行的最后一个进程的进程号
    > command &:代表把命令放入后台执行
- read [选项] [变量名]:接受键盘输入
    - p "information":提示信息
    - t seconds
    - n charLength
    - s:secure
    
## Bash运算符
### 数值运算与运算符
- declare [+/-][选项] variable
    - '-':设定属性类型
    - '+':取消变量的属性类型
    - i:integer
    - x:环境变量
    - p:显示指定变量的被声明类型
    > declare -i cc=$aa+$bb
- expr
    > cc=$(expr $aa + $bb)
- $((运算式))和$[运算式]
    > cc=$(($aa+$bb))
    > cc=$[$aa+$bb]
    > $(系统命令)

### 变量测试与内容替换
> x=${y-new}:如果y存在,则x=$y,否则x=new
> x=${y:-new}:如果y存在且不为空(length=0),则x=$y,否则x=new
...

## 环境变量配置文件
- source 配置文件 或者 . 配置文件
    > 让配置文件生效
- 环境变量配置文件
    > 环境变量配置文件中主要定义对系统操作环境生效的系统默认环境变量，比如PATH,HISTSIZE,PS1,HOSTNAME等
    - /etc/profile:登录时调用的文件，在里面调用了/etc/bash.bashrc和/etc/profile.d/*.sh文件
    - /etc/bash.bashrc
    - /etc/profile.d/*.sh
    - ~/.bashrc
    > /etc目录下面的环境变量对所有用户生效
- 其他配置文件
    - ~/.bash_logout:登出时的一些操作
    - ~/.bash_history:历史命令保存文件
    - /etc/issue:本地登录之后提示信息
    - /etc/issue.net和/etc/ssh/sshd_config:remote