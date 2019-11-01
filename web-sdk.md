# 1.前端的发展

## 浏览器的限制

```
目前的浏览器只支持 HTML/CSS/JavaScript 为基础的页面

==============================================================
1.模块化需要转译
    原生HTML通过直接引用CSS/图片/JS代码形式无法适应大规模开发
    于是通过模块化把一个复杂的系统分解到多个模块以方便编码
    1.CommonJS规范 - require同步加载/modules.exports暴露接口
    2.AMD规范 - 需要第三方库requireJS
    3.ES6标准 - import/export 目前无法直接运行在大部分 JavaScript 运行环境下
	- 目前的模块化方案都需要工具转译为 HTML/CSS/JavaScript 
2.新框架需要转译
    React JSX 语法需要转译
    Vue 组件系统包含大量新语法 需要转译
3.新语言需要转译
	TypeScript/SCSS 无法直接执行

==============================================================
不断出现的 模块化/新语言/新框架 需求
我们需要构建工具，在编写完代码后生成能在线上运行的代码
通过webpack，我们能就能一次性满足
    模块合并：在采用模块化的项目里会有很多个模块和文件，需要构建功能把模块分类合并成一个文件。
    代码转换：TypeScript 编译成 JavaScript、SCSS 编译成 CSS 等。
    文件优化：压缩 JavaScript、CSS、HTML 代码，压缩合并图片等。
    代码分割：提取多个页面的公共代码、提取首屏不需要执行部分的代码让其异步加载。
    自动刷新：监听本地源代码的变化，自动重新构建、刷新浏览器。
    代码校验：在代码被提交到仓库前需要校验代码是否符合规范，以及单元测试是否通过。
    自动发布：更新完代码后，自动构建出线上发布代码并传输给发布系统。
```

## Webpack

```javascript
// webpack-config.js

const path = require('path');
module.exports = {
  // -------------------- 1.基础功能 --------------------
  entry: './main.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, './dist'),
  },
  // -------------------- 2.文件解析 - Loader --------------------
  module: {
    rules: [
      {
        test: /\.css$/, // 用正则去匹配要用该 loader 转换的 CSS 文件
        use: ['style-loader', 'css-loader?minimize'],
      }
    ]
  }j
  // -------------------- 3.功能拓展 - plugins --------------------
  plugins: [
    new htmlWebpackPlugins({
    	template: path.join(__dirname,'./src/index.html'),
        filename: 'index.html'
	})
  ]
  // -------------------- 配置结束 --------------------
};
```

### 模块化

```
模块化
    默认命令 webpack './src/main.js' './dist/bundle.js' 
    优化
    	配置 webpack.congig.js 文件
        const path = require('path')
        module.export = { 
        	entry: path.join(__dirname,'./src/main.js'),
        	output: {
        		path.join(__dirname,'./dist),
        		filename: 'bundle.js'
        	}
        }
        命令行输入 webpack 就能直接输出目标文件
        
==============================================================
最终
得到可以直接引用的资源 <srcipt src='../dist/bundle.js'> 
```

### 热门插件

```

==============================================================
html-webpack-plugin (npm)
	可以生成HTML入口文件
    1.先进行webpack-dev-serve (npm) 配置
    2.修改webpack.config.json
    	const htmlWebpackPlugins = require('html-webpack-plugin')
    	,plugins:[
   			new htmlWebpackPlugins({
    			template: path.join(__dirname,'./src/index.html'),
    			filename: 'index.html'
    		})
    	]
    在build后生成一个 html 文件，并且已经引用了相关的资源
	
==============================================================
webpack-dev-server (npm) 官方
	启动服务器
    1.修改 package.json 添加新 npm 命令
    	scripts { dev : webpack-dev-server contentBase src ---port 8080 }
    2.本地服务器启动优化
        修改webpack.config.json
        devServe:{
        	open:true, (是否自动打开浏览器)
        	port:8080,
        	contentBase:'src', (指mainJS需要的资源都在这)
        }
    3.页面不需要引用标签文件/自动加载bundle内存webpack-dev-serve (npm)
    4.最终
        启动了一个使用express的Http服务器
        会实时的编译src，但是最后的编译的文件保存在内存中
```

# 2.Js跨平台

## NodeJS

```
NodeJS 是一个基于 V8 JS编译器的 JS 运行环境，使得JS能在不同平台运行
==========================================================================
- 通用
    1.基于事件循环实现异步编程
    2.支持回调函数作为参数/实现异步操作同步化
    3.RESTful API
    4.多进程模式
==========================================================================
- 模块系统
==========================================================================
- 文件系统
==========================================================================
- 服务器模块
- Express
==========================================================================
- 数据库服务
	1.MySQL
	2.MongoDB
```

## npm (插件) 

```
淘宝镜像
	$ npm install -g cnpm --registry=https://registry.npm.taobao.org

初始化目标包目录
	$ npm init -y // 产生 package.json
搜索模块
	$ npm search
安装
	$ npm install name
        全局 - g : 将安装包放在 /usr/local 下或者你 node 的安装目录/可以直接在命令行里使用
        本地 - d/空 : 将安装包放在 ./node_modules 下
        - s 更新到package.json
删除包
	$ npm /uninstall/r/emove name
配置
    package.json
    - 全局命令	"scripts"
    - 项目依赖	"dependencies"
==========================================================================
当导入一个包时：require('vue')
    1.找到对应的 node_modules
    2.找到require的包文件夹
    3.根据package.json找到需要的JS库文件
```

# 3.版本控制

## Git

```
1.在代码托管区创建远程仓库
    - 局域网: GitLab
    - 外网: Github/码云
    
2.获取
	- 创建本地仓库
		$ git init
	- 通过远程仓库提供的 URL 克隆代码
        - HTTPS: 需要手动输入服务器账号密码
        - SSH: 需要本地生成/服务器设置SSH keys
		$ git clone [url]
    - 查看这个项目对应的远程仓库
        $ git remote -v
	- 获取最新代码
		$ git pull
		- git fetch: 获取远程代码
		- git merge: 和本地库合并
	
3.本地更改
	- 工作区 → 暂存区
    $ git add readme.txt
    - 暂存区 → 本地库
    $ git commit -m "description"
    
4.查看本地版本
	- 查看工作区及暂存区的状态
        $ git status
            on branch master
            no commits yet
	- 想知道历史提交 /空格翻页/b向上/q退出
        $ git log
        $ git log -pretty=oneline 或 git reflog // 获取索引值
    - 本地回退版本
        通过对 HEAD 指针来决定本地版本
        $ git reflog // 获取索引值
        $ git reset --hard 'key' // 设置 HEAD 指针
    - 比较
        git 以ROW为比较单位
        $ git diff [(或)文件名/版本] // 查看明细
        
==========================================================================
    生成本地 SSH keys 用于提供远程仓库访问权限
        1.生成本地SSH目录
            $ ssh-keygen -t rsa -C '邮箱/账号'
        2.查看生成的SSH keys
            $ cat id_rsa.pub
        3.在托管服务器上设置
            添加 SSH keys 
==========================================================================
	# 外包
        $ git fork 关联复制
        $ pull request 分支发起请求
        $ merge 同意请求并合并
==========================================================================
    配置签名
        - 提交时用于标识开发人员身份/与服务器账号密码无关
        - 项目级别
            $ git config user.name
            $ git config user.email
        - 系统级别
            $ git config --global user.name "Your Name"
            $ git config --global user.email "email@example.com"
        - 这些信息会保存到 .git/config 中
```


