# 1.Html

```
浏览器渲染过程
    1.用HTML分析器，分析HTML元素，构建一颗DOM树
    	- onload函数触发
    2.用CSS分析器，分析CSS文件和元素样式，生成页面的样式表。
    3.将DOM树和样式表，关联起来，构建一颗Render树(这一过程又称为Attachment)。
        每个DOM节点都有attach方法，接受样式信息，返回一个render对象。
        这些render对象最终会被构建成一颗Render树。
    4.有了Render树，浏览器开始布局，为每个Render树上的节点确定一个在显示屏上出现的精确坐标。
    5.Render树和节点显示坐标都有了，就调用每个节点paint方法，把它们绘制出来。 
    # 构建DOM数是一个渐进过程，为达到更好用户体验，渲染引擎会尽快将内容显示在屏幕上。
    # 它不必等到整个HTML文档解析完毕之后才开始构建render数和布局。
***********************************************************************************
	rel : 关系 : icon/stylesheet
	href : 连接 : #顶部/#id/javascript/url
	
    网页图标 : <link rel="icon" src="logo.png" sizes="32x32">
    图片连接 : <a href><img src="URL" alt="替换文本"></a>
    文字区域 : <textarea> disabled="disabled"禁止拖动

    表单 : <form action="url" method="post">
        样式
            表单长度 : size
            底部文字 : placeholder
            内容长度 : maxlength
            内容选取 : name/value
        (type)
            内容text/复选checkbox/单选radio
            重置reset/下拉select+option
```

# 2.CSS

### 通用

```
选择器
	1.优先级: 行内> #ID选择器 > .类+伪类选择器/属性选择器 > 元素选择器 > *通用 > 继承
	2.语法: 多选择器: A，B，C / a -/+/~ b : 前/后/后所有 / A > a(仅仅子)
    3.伪类
        :link 未访问 :active 长按 :hover 鼠标放入 :visited 已访问
        :focus 文本框输入时 :selection 文本鼠标选中时候
        :before/after 文字前/后&标签之间 { content:"" }
    4.参数
        p:first-child p:nth-child(num):任意位置子元素 p:first-of-type:第一个p
*********************************************************************
常用样式
	背景图像
		1. 设置背景图: background-image: url(../img/a.jpg)
		2. 控制xy重复: background-repeat: repeat-x/y / no repeat
		3. 铺满屏幕：background-size:cover/num%
		4. 固定不动：background-attachment: fixed;
	行内块元素
		display:inline-block
		水平对齐 vertical-align: top
		间隙 font-size: 0
    Overflow
        visible	默认值。内容不会被修剪，会呈现在元素框之外。
        hidden	内容会被修剪，并且其余内容是不可见的。
        scroll	内容会被修剪，但是浏览器会显示滚动条以便查看其余的内容。
        auto	如果内容被修剪，则浏览器会显示滚动条以便查看其余的内容。
    背景透明:
    	opacity：0~1：所有 rgba(x,y,z,0~1)：单独
    文字
    	空格转义字符 &nbsp
    	默认: 
    		换行符无效 / 多个空格会被合并为一个 / 句子自动换行 / 单词超出边界
    	Block-换行: 
    		word-wrap: break-word
    	裁剪: 
    		white-space: nowrap;
    		text-overflow: ellipsis;
    		overflow: hidden;
```

### 动画

```
transform
    平移：transform: translate(X,Y)
    旋转：transform-origin:x y：旋转基点
          transform: rotate(30deg)
    放缩：transform: scale(X,Y);//倍数
    3D旋转：transform: rotateX/Y(120deg)
    
*********************************************************************
transition
    1.为指定属性(时长)添加过渡效果：transition: width 2s,height 1s...
    2.指定属性绑定事件监听：div:hover {width...height...}
    # transition: all 1s + css组合
    
*********************************************************************
复杂
    1.animation:name 1s 时间函数 开始时间 次数();
    	ease:慢快慢/linear:匀速
    2.@keyframes name
        0% {background:red;}
        100% {background:green;}}
支持
	-webkit- Chrome:IOS/-moz- firefox/
```

### 布局

```
定位
    1 文档流: relative：相对于原位置移动
    2 脱离文档流：
    	absolute：相对relative移动,会提升层级
            脱离文档流而产生的高度塌陷 & 相邻|垂直|内外边距重叠
            可以通过添加块元素解决,清除因浮动产生的移动影响
            .clearfix:before,.clearfix:after
            {content:"";display:table;clear:both;}
    	float：会产生挤压
    	fixed：相对于可视界面移动,会提升层级
    	
*********************************************************************
常用的居中方式
    1 水平居中
        margin:0 auto 
        (行内)text-align:center
        (弹性盒)display: flex; justify-content: center;
    2 垂直居中
        (单行内)line-height
        (多行)display:table-cell; vertical-align: middle;
        (弹性盒)display: flex; align-items: center;
    3 同时对齐
        ab+re定位：trbl:0; margin:auto;
        top/left：50%; transform(-50%,-50%)/margin-l/r:-50%;
        (弹性盒)display: flex; justify-content: center; align-items: center;
        (弹性盒)display: flex; margin:auto;
        
*********************************************************************
弹性盒子
	容器: flex/inline-flex
		#. flex-direction: 设置主轴方向 row | row-reverse | column | column-reverse
		#. flex-wrap: 换行 nowrap | wrap | wrap-reverse
		1. flex-flow: 合并 flex-direction 和 flex-wrap
		2. justify-content: 
			对齐方式 flex-start/flex-end/center | *space-between | *space-around
		3. align-items: 
			纵轴对齐 flex-start/flex-end/center | *baseline | *stretch
		4. (较少)align-content: 
			多行纵轴对齐 flex-start/flex-end/center | *space-between | *space-around | *stretch
		# 子元素 float/clear/vertical-align 会失效
	项目
		1. order: 值越小越靠前/默认 0
		2. flex: (占比) none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]
		3. align-self: 单独设置 align-items 个性的对齐
    	4. margin-?:auto 会获取剩余所有空白
    	
*********************************************************************
移动端适配 
# 响应式布局: 主要以@media为不同设备设计不同样式
# 自适应布局: 以比例布局为主
	1.定义默认宽度
        # 所有浏览器都有默认宽度,但是每个设备宽度都不一样
        # 所以用这个meta定义了浏览器宽度 = 设备宽度
        <meta name='viewport' content= 'width=device-width, initial-scale=1.0'>
        
*********************************************************************
百分比布局
	- 为不同设备编写不同的CSS样式文件
	- 在头部 <link @media='screen/speech'/(min-width:500px)(if)..>
	- 这样根据不同的条件 相关的样式就会加载/覆盖
	- 宽度默认 100%; - 但是字体可能会模糊
	@import 'common/mobile/pc.css'
	
*********************************************************************
rem布局
	# 像素设备比 -DPR -表示1个css像素对应（覆盖）的物理像素个数
	# HTML 默认font-size:16px / 下面动态设置HTML字体
    (function(doc, win) {
    	let html = doc.getElementsByTagName("html")[0],
        // orientationchange-> 手机屏幕转屏
        // resize -> 页面大小被缩放了
    	window.reEvt = "orientationchange" in win ? "orientationchange" : "resize",
        let reFontSize = function() {
            var clientW = doc.documentElement.clientWidth || doc.body.clientWidth;
            if(!clientW) {return;}
    		html.style.fontSize = 100 * (clientW / 375) + "px";}
        win.addEventListener(reEvt, reFontSize);
        // DOMContentLoaded->dom加载完就执行,onload要dom/css/js都加载完才执行
        doc.addEventListener("DOMContentLoaded", reFontSize);
    })(document, window);
```



# 3.JavaScript

## 加载过程

```
JS执行过程
	1.语法检查
	2.预编译
		1.进入全局/函数环境，创建相应的执行上下文，形成执行上下文优先加载栈
		2.创建执行上下文
            变量提升 (undefined)
            建立作用域链 : 可以访问/查找父级内存
            确定this的指向
    3.代码执行:按顺序(单线程)变量赋值/函数执行
		1.主线程执行(耗时任务放入回调队列:异步非阻塞)
		2.辅助线程初始化(相关回调代码放入回调队列)
		- 如初始化定时器，绑定监听动作，发送AJAX
		3.主线程执行完毕，查看是否有需要执行的回调代码再进入主线程(循环队列)
```

## 数据类型

```
1.数据结构
    基本数据(值)类型：本质是内存代表的值保存在栈中
    1.undefined : undefined，代表未赋值的变量(var a)默认指向唯一实例
    2.null : Object
    3.Number
    4.String
    5.Boolean
Number
	变为数字: Number(str): Number
	parseInt/Float(): 先转换成字符串: 从头逐字符取出有效数字并返回数字
typeof a
	返回a的所属具体对象字符串，如具体构造函数名:string)
	无法判断null:obj(语言bug)和Array:obj
a instanceof Obj
	返回a是否是B构造函数的实例：判断对象
# 用 if 判断值为 undefined/null/'' 效果与 false 相同
```

### Symbols

```
Symbol
	# 标识唯一的值 - 解决：字符串不具有唯一性
	应用1.常量声明 const NAME = Symbol('name')
	应用2.键声明
Symbol.for('tar'):全局搜索/若无则新建
Symbol.keyFor('tar'):全局键搜索
***********************************************************************************
迭代器
	Symbol.iterator()
```

### Object  Map

```
Object - key只能是 Symbol/String
	增加1/修改/查询 obj.key = new
	增加2 Object.assign(target, source) // 潜拷贝/拷贝了引用
	删除 delete obj.key
	--------------------------
	数组 Object.keys(obj)
	键数组 Object.keys(obj)
	值数组 Object.values(obj)
	值键对二维数组 Object.entries(obj)
	--------------------------
    遍历
    	# 由于不同的浏览器引擎采用的规范可能不同，所以输出的顺序也就不一样
    	for(let e in obj){ console.log(obj[e]) }
	--------------------------
	# 判断对象为空 JSON.stringfy()==={} || Object.keys(obj).length===0
***********************************************************************************
Map - key 是任意类型
	新建 new Map(二维数组)
	增加 map.set(key,value)
	删除 map.delete(key)
	克隆/合并 new Map(oldMap)
	长度 size
	--------------------------
	键数组 map.keys(map)
	值数组 map.values(map)
	值键对二维数组 Array.from(map)
	--------------------------
    遍历
    	# 按插入顺序输出
    	for(let [key, value] of map){}
    	forEach( function(value,key){}, map)
```

### Array/Set  String

```
Array
    shift()	删除并返回数组的第一个元素
    pop()	删除并返回数组的最后一个元素
    push()	向数组的末尾添加一个或更多元素，并返回新的长度
	--------------------------
    合并：A.concat(B)
    截取 slice(start, end之前)
    替换 splice(start, 删除num个数, '新元素')
    带分隔符的字符串：join(分隔符)
	--------------------------
    排序：
        reverse()	颠倒数组中元素的顺序
        sort(compare) 默认排序顺序是根据字符串UniCode码
        需要传参 function compare(start,end){ return start-end // 升序 }
        对象排序
        function compare(e){
            return function(start,end){ return start[e]-end[e] }}
	--------------------------
    遍历
    	arr.forEach(function(item,index,thisArr){ console.log(item) })
***********************************************************************************
Set - 值唯一/任意类型
	新建 new Set(Array/String) // 数组去重
	增加 set.add(value)
	长度 size
	--------------------------
	数组 let arr = [...set]
	--------------------------
	并集 new Set([...a, ...b])
	交集 new Set([...a].filter(x => b.has(x)))
	差集 new Set([...a].filter(x => !b.has(x)))
***********************************************************************************
String
    合并：concat()
    截取
    	slice(statr, end之前)
    	substring(statr, end之前)
    首个下标：indexOf()/lastIndexOf()
    指定下标：charAt()
    #
    新字符串：String(ins): String
    新字符串：Object.toString(): 返回新字符串, undefined/null 报错
    带分隔符的数组	split(分隔符)
	#
    match()	找到一个或多个正则表达式的匹配。
    replace()	替换与正则表达式匹配的子串。
    search()	检索与正则表达式相匹配的值。
```

## 创建对象

```
声明：function name(){}
    # 赋值：button.onclick/var x = function(){};
    # 直接return;等于直接退出函数
    # 立即调用匿名函数(function(){})();//目的是为了隐藏实现
    - arguments伪数组来保存参数
    - arguments.callee来保存当前执行的函数对象
创建对象
	声明对象
        通过大写函数首字母：表明这是对象的构造方法
	    回调函数：自定义/由其他途径执行的函数;
    创建对象
        //不推荐：直接创建：var o = {};
        //工厂不推荐：通过函数返回一个内存，但是本质都是Object类型
        构造函数：var o = new O();
        	1.new后立即划分新对象内存
            2.将构造函数的this属性指向新对象
            3.逐行执行构造函数
            4.将新对象作为返回值返回
        把公共数据放到对象的原型(公共区)里面
    遍历对象：for(var x in object){}
        访问对象属性A：.a 或者 A[a]
	# 对象分类
		1.内建对象(Function/Array)
		2.宿主对象(由运行环境'浏览器'提供的对象)：DOM/BOM
		3.自定义对象(Object衍生
***********************************************************************************
原型/构造链
	公共Object
        公共Object：.prototype = null
        公共Object：.construct = 公共Function
	A的公共(空)
        A的公共：.__proto__ = 公共Object
        A的公共：.construct(本体是Function) = 内存A
    内存A
        内存A：.prototype = A的公共(空)
        内存A：.construct = 公共Function
    实例a
    	实例a：.__proto__ = A的公共
    	实例a：.construct(继承) = 内存A
    	******************************
    # hasOwnProperty("查找属性")：查找自身是否有某属性
    # 继承
        1. AA.prototype = new A(); //容易修改父对象
        2. AA(){  A.call(this)  };//复制了父对象，不会被修改，但是内存消耗大
        3. 结合两者：需要复用的地方放入A.prototype，其他的复制到AA上
        # 注意aa.prototype.construct是A而不是AA，应该修改
***********************************************************************************
闭包产生
	1.有嵌套的内部函数
	2.内部函数调用了外部函数的变量
	3.内部函数被外部变量引用
	3.外部变量执行，即内部函数执行(闭包产生)
	# 闭包就是外部函数执行结束前，调用的变量集合
        - 闭包不被销毁的根本原因是
            外部变量没被销毁=内部函数没被销毁=外部函数没被销毁
        - 外部变量是在内部函数预加载时被引用的 = 闭包产生
        - 预加载的原因是因为内存执行
    闭包用途
        1.匿名函数自调用,把相关数据暴露给window.模块名
        2.可以根据模块名直接使用相关数据
        function foo() {
        	var x = 3;
            function a(){return x++;}
        	return a;
		}
		var m = foo();
		# 全局引用foo(已经执行)/导致函数a不消失/导致变量x不消失
***********************************************************************************
函数防抖
	短时间内连续触发事件，回调只能执行最后一次。
	const debounce = (func, wait) => {
		let timer;
		return () => {
            clearTimeout(timer);//触发事件会先清空计时器,再重新启动
            timer = setTimeout(func, wait);};};//所以只有wait秒后才执行回调
函数节流
	事件触发后,执行回调，一定时间后才能继续触发
	const throttle = (func, wait) => {
		let timer;
		return () => {
			if (timer) {return;}//连续触发事件判断计时器是否执行
			timer = setTimeout(()=>{
				func();
				timer = null;
			}, wait);};};//wait秒后执行回调
```

## DOM 编程

```
基本常识
	页面以结点Node为基本组成单位
        → 文档结点(document)：9 
        → 元素结点(标签)：1
        → Attribute属性结点：2
        → TextNode文字结点：3
    document.element可以直接调用结点的基本属性
    document.element.nodeName/nodeType/nodeValue:元素基本值
    # innerHTML返回串中含标签/innerText不含标签
增加结点
	createElement("")
	appendChild
	insertBefore(新，旧)
    删除：e.parentNode.removeChild(e)：需要找出父元素
    修改：replaceChild
任意结点
	getElementById()
	getElementsByName/ClassName/TagName()：集合
	# 必须在document下进行查找(TagName除外)
    querySelector(“css字符串”)：只会返回第一个符合结点
    querySelectorAll(“css字符串”)：返回数组
    父节点：parentNode
    子结点：
        first/last/parentElementChild
        previous/nextElementSibling
        children：集合
        hasChildNodes()
    # 以下查询有可能返回字符结点(如空白字符)
        first/last/parentChild
        previous/nextSibling
        childNodes
    
***********************************************************************************
BOM
    1.window：窗口/网页全局对象
      window.定时器    
        setTimeout(expression,time)：单次延迟执行
        setInterval(expression,time)：永续延迟执行
        clearTimeout/Interval(name)：清除定时器
        # 通过变量的返回数字来区分定时器
        # 多次调用定时器函数=多个定时器同时在启动（所以在开启前关闭）
```

### 事件

```
***********************************************************************************
结点具有事件属性
	 # onclick / onscroll / onmouseover / onmousewheel(wheelDelta:滚动方向 )
	 # 可以为DOM事件设置监听函数
	 	1. e.click = function(event){}
	 	2. dom.addEventListener("click",fun,是否在捕获阶段触发//0)
	 # 默认存在event参数保存所有事件信息，如：
        1. event.clientX/Y(鼠标可视坐标)
        2. event.pageX/Y(鼠标页面实际坐标)
        3. event.target = 触发事件的DOM结点
        
***********************************************************************************
事件冒泡
	子元素的事件触发，也会导致所有祖先元素绑定的相同事件触发
	取消：在子元素的事件对象中.cancelBubble = true;
事件委派：
	# 利用事件冒泡,只绑定一次监听函数
    1.在共同父元素上设置响应函数
    - 由于子元素和父元素有共同的响应事件，所以不用循环为子元素绑定监听函数
    2.if(event.target.class=="目标"){监听函数}
```

### 样式

```
获取目前样式
	1.
		Window.getComputedStyle(node,null) : obj : 只读
		e.currentStyle.样式//仅仅IE支持
    2.
    	实际宽高
        e.clientWidth/Height（ content+padding ）
    3.
    	相对于视觉父元素的量
        e.offsetParent：视觉父元素对象
        e.offsetWidth/Height/Left/Top（ box ）
    4.
    	可滚动的实际宽高
        scrollWidth/Height/Left/Top：获取滚动条滚动的距离
    	# scrollHeight-scrollTop=clientHeight：滚动到底了
批量修改样式
	.style.cssText = {display:bolck;...}
```

# ES6

```
0.箭头函数
    var x = function(a1){ return a1();};
    var x a1 => a1( //this);
    箭头函数中的this仅仅指向本级别
    1.
        用 var 声明的变量，在全局范围内有效
        let 声明的变量
            - 只在 let 命令所在的代码块内有效
            - 不存在预处理:会产生报错
        const 声明一个只读的常量，一旦声明，常量的值就不能改变。
    2.新增原始数据类型
        # 表示独一无二的值，最大的用法是用来定义对象的唯一属性名。
        let sy = Symbol("objName");
        查询/创建:let sy = Symbol.for("objName");
    3.新增数据结构
        - Map
        - Set(唯一Key)
        # Object的key只能是string/symbols
    4.迭代器
        Symbol.iterator
    5.模块化
    6.Promise：表示一个异步操作
    	# 异步请求的数据进行return,可能会返回undefined,需要回调函数返回结果
    	# 异步回调函数需要顺序执行，这时候会造成回调地狱
    	解决：
    		event : retutn 
                new Promise(function(resolve,reject){
                    //0.Promise只要被创建就会立即执行
                    let res = aFun();//1.立即执行异步操作-需要时间-此时执行then
                    if(res) return reject(err);//2.失败回调
                    return resolve(res)	//2.成功回调
                })
    		event() //异步请求1开始
    			.then(function(成功回调){
    				return event();//异步请求2开始})//返回新Promise
    			.then(function(成功回调){});
    			.catch(异常捕获)//如果失败会终止回调队列进行报错
    		# 主线程快于异步操作: then先执行→创建回调内存→异步操作执行
    		# .then的第二个参数是失败回调-可以省略
    		# 在顺序异步时,可以在reject中返回Promise,防止整个.then顺序全部不执行
```

# JSON

```
JSON    
    JS对象无法被其他语言识别
    JSON是一种特殊的字符串，可以被任意语言识别
    
    # JS为我们提供了工具类JSON来操作服务器传回的JSON字符串
        1.获取JS对象
            JSON.parse(s)：object
        2.转换为字符串给服务器
            JSON.stringify(obj)：string

```

# 浏览器

# 4.数据结构

# 5.算法