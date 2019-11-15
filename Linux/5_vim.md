# Vim常用命令

## 命令模式
- 进入插入模式:aio
- gg:第一行
- G:最后一行
- x:删除光标所在字符
- nx:删除光标所在字符后n个
- dd:删除行
- ndd
- dG:删除光标所在行到文件末尾的内容
- yy:复制
- p/P:粘贴在当前光标所在行下或行上
- nyy:复制多行
- dd:剪切
- ndd:剪切多行
- r:替换单个字符
- R:进入替换模式
- u:取消上一步操作(undo)
- /string:搜索
- n:搜索下一个

## 插入模式

## 编辑模式
- set nu
- set nonu
- n : 跳到指定行
- n1.n2d:删除指定范围的行
- set ic:忽略大小写
- %s/old/new/g:全文搜索替换
- n1,n2s/old/new/g:指定行范围的替换
    > :n1,n2s/^/#/g:连续注释行
    > :n1/n2s/^#//g:取消注释行
    > :n1,n2/^/\/\//g:在行首添加//注释
- wq
- w:new_filename
- r filename:导入文件
- !command:在vim中执行命令
- r !command:在vim中执行命令并将结果导入
    > :r !date : 快速导入时间
- map command operation:定义快捷键
    > :map Ctrl+V+P I#<ESC> 定义Ctrl+P为注释一行
    > :map Ctrl+V+B 0x 定义Ctrl+B为取消注释
    > :mao Ctrl+V+H iguobei@gmail.com 定义Ctrl+H为输入邮箱
- ab alias string
    > :ab mymail guobei@gmail.com 在键入mymail之后会自动被替换为guobei@gmail.com

## 保存设置

在home目录下面新建.vimrc文件，将需要保存的命令键入就可以永久保存