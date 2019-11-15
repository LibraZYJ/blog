package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.ArticleService;
import com.niit.web.blog.util.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author yujie_zhao
 * @ClassName ArticleController
 * @Description TODO
 * @Date 2019/11/14
 * @Version 1.0
 **/
    @WebServlet(urlPatterns = {"/article"})
public class ArticleController extends HttpServlet {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson  = new GsonBuilder().create();

        List<Article> articleList = articleService.ListArticle();

        ResponseObject ro = new ResponseObject();
        ro.setCode(resp.getStatus());
        if (resp.getStatus() == 200){
            ro.setMsg("响应成功");
        }else {
            ro.setMsg("响应失败");
        }
        ro.setData(articleList);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }
}
