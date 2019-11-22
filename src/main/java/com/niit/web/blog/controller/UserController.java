package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.domain.dto.UserDto;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.util.Message;
import com.niit.web.blog.util.ResponseObject;
import com.niit.web.blog.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyujie
 * @ClassName UserController
 * @Description 用户控制器
 * @Date 15:56 2019/11/9
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();

    List<User> userList = null;
    User user = null;
    Gson gson = new GsonBuilder().create();
    ResponseObject ro = new ResponseObject();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI().trim();
        if ("/api/user".equals(uri)) {
            String page = req.getParameter("page");
            if (page != null) {
                getUsersByPage(req, resp);
            } else {
                getHotUsers(req, resp);
            }
        } else {
            getUser(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/user/sign-in".equals(uri)) {
            signIn(req, resp);
        } else if ("/api/user/sign-up".equals(uri)) {
            signUp(req, resp);
        } else if ("/api/user/check".equals(uri)) {
            check(req, resp);
        }
    }

    private void getUserByMobile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestPath = req.getRequestURI();
        int position = requestPath.lastIndexOf("/");
        String id = requestPath.substring(position + 1);
        /*进入用户详情页*/
        user = userService.findUserById(Integer.parseInt(id));
        ro.setCode(resp.getStatus());
        if (resp.getStatus() == 200) {
            ro.setMsg("响应成功");
        } else {
            ro.setMsg("响应失败");
        }
        ro.setData(user);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }
    private void getHotUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.getHotUsers();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getUsersByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = req.getParameter("page");
        resp.getWriter().print("第" + page + "页");
    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userList = userService.ListUser();
        ro.setCode(resp.getStatus());
        if (resp.getStatus() == 200) {
            ro.setMsg("响应成功");
        } else {
            ro.setMsg("响应失败");
        }
        ro.setData(userList);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
//        String info = req.getPathInfo().trim();
//        //取得路径参数
//        String id = info.substring(info.indexOf("/") + 1);
//        resp.getWriter().println(id);
    }
    private void getUserByCreateTime(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获得前端提交的数据*/
        BufferedReader reader = req.getReader();
        /*新建一个可变的字符序列*/
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        /*通过while循环，一行一行的读入内容*/
        while ((line = reader.readLine()) != null){
            /*将得到的字符放入stringBuilder中*/
            stringBuilder.append(line);
        }
        /*将错误打入日志*/
        logger.info("登录用户信息:" + stringBuilder.toString());
        /*new一个gson*/
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        Map<String, Object> map = userService.signIn(userDto);
        String msg = (String) map.get("msg");
        ResponseObject ro;
        if(msg.equals(Message.SIGN_IN_SUCCESS)){
            ro = ResponseObject.success(200, msg, map.get("data"));
        }else {
            ro = ResponseObject.success(200, msg);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("验证账号");
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("注册");
    }


}
