package com.niit.web.blog.util;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.util.UUID;

/**
 * @author tj
 * @ClassName AliossUtil
 * @Description 阿里云
 * @Date 2019/11/6
 * @Version 1.0
 **/
public class AliossUtil {

    public static String ossUpload(File file){
        String bucketDomain = "https://niit-soft.oss-cn-beijing.aliyuncs.com/";

        //Endpoint 按实际情况填写
        String endpoint = "oss-cn-beijing.aliyuncs.com";

        //accessKeyId和accessKeySecret 填写自己的
        String accessKeyId="LTAI4FiNz28RZdADSiGjabuj";
        String accessKeySecret="Cgd3RZYjbeyBehK0CyaIekvuFER5yC";

        //bucket名称
        String bucketName="niit-zyj";

        //目录名称
        String filedir="avatar/";

        //获得本地文件名
        String fileName=file.getName();
        System.out.println(fileName);

        //上传后的文件名生成，这里会将客户的文件保留扩展名，主文件名用UUID生成
        String fileKey= "赵玉杰"+fileName.substring(fileName.indexOf("."));
//        String fileKey= UUID.randomUUID().toString()+fileName.substring(fileName.indexOf("."));
        OSSClient ossClient=new OSSClient(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,filedir+fileKey,file);
        //创建一个可变长字符串变量，用来存放上传文件的URL
        StringBuffer url=new StringBuffer();

        //域名+目录+新文件名， 生成外链
        url.append(bucketDomain).append(filedir).append(fileKey);
        ossClient.shutdown();
        return url.toString();
    }

    public static void main(String[] args){
        File file=new File("D:/hua.jpg");
        String url=ossUpload(file);
        System.out.println(url);
    }

}