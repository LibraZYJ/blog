<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="styles/general.css">
    <link rel="stylesheet" href="styles/cell.css">
    <link rel="stylesheet" href="styles/form.css">
    <script type="text/javascript" src="js/ref.js"></script>
    <style type="text/css" >

        .logo-container {
            margin-top: 50px ;
        }
        .logo-container img {
            width: 100px ;
        }

        .message-container {
            height: 80px ;
        }

        .link-container {
            height: 40px ;
            line-height: 40px ;
        }

        .link-container a {
            text-decoration: none ;
        }

    </style>

</head>
<body>
<div class="container form-container">
    <form action="/code" method="post">
        <div class="form"> <!-- 注册表单开始 -->

            <div class="form-row">
                        <span class="cell-1">
                            <i class="fa fa-user"></i>
                        </span>
                <span class="cell-11" style="text-align: left;">
                            <input type="text" name="username" placeholder="请输入用户名">
                        </span>
            </div>

            <div class="form-row">
                        <span class="cell-1">
                            <i class="fa fa-key"></i>
                        </span>
                <span class="cell-11" style="text-align: left;">
                            <input type="password" name="password" placeholder="请输入密码">
                        </span>
            </div>

            <div class="form-row">
                        <span class="cell-1">
                            <i class="fa fa-keyboard-o"></i>
                        </span>
                <span class="cell-11" style="text-align: left;">
                            <input type="password" name="confirm" placeholder="请确认密码">
                        </span>
            </div>

            <div class="form-row">
                <span class="cell-7">
                    <input type="text" name="verifyCode" placeholder="请输入验证码">
                </span>
                <span class="cell-5" style="text-align: center;">
                     <img src="/code"  onclick="my(this)">
                </span>
            </div>

            <div class="form-row" style="border: none;">
                        <span class="cell-6" style="text-align: left">
                           <input type="reset" value="重置">
                        </span>
                <span class="cell-6"  style="text-align:right;">
                            <input type="submit" value="注册">
                        </span>
            </div>

        </div> <!-- 注册表单结束 -->
    </form>
</div>

</body>
<script>
    function my(e) {

        const source = e.src ; // 获得原来的 src 中的内容
        //console.log( "source : " + source  ) ;

        var index = source.indexOf( "?" ) ;  // 从 source 中寻找 ? 第一次出现的位置 (如果不存在则返回 -1 )
        //console.log( "index : " + index  ) ;

        if( index > -1 ) { // 如果找到了 ?  就进入内部
            var s = source.substring( 0 , index ) ; // 从 source 中截取 index 之前的内容 ( index 以及 index 之后的内容都被舍弃 )
            //console.log( "s : " + s  ) ;

            var date = new Date(); // 创建一个 Date 对象的 一个 实例
            var time = date.getTime() ; // 从 新创建的 Date 对象的实例中获得该时间对应毫秒值
            e.src = s + "?time=" + time ; // 将 加了 尾巴 的 地址 重新放入到 src 上

            //console.log( e.src ) ;
        } else {
            var date = new Date();
            e.src = source + "?time=" + date.getTime();
        }
    }
</script>
</html>
