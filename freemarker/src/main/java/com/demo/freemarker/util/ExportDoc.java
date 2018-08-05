package com.demo.freemarker.util;

import com.demo.freemarker.entity.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportDoc {
    private Configuration configuration;
    private String encoding;

    public  ExportDoc(String encoding) throws IOException {
        this.encoding = encoding;
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setDefaultEncoding(encoding);
        configuration.setDirectoryForTemplateLoading(new File(getClass().getResource("/").getPath()));
    }

    public Template getTemplate(String name) throws Exception {
        return configuration.getTemplate(name);
    }

    public String getImageStr(String image) throws IOException {
        InputStream is = new FileInputStream(image);
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] data = new byte[is.available()];
        is.read(data); is.close();
        return encoder.encode(data);
    }

    public Map<String, Object> getDataMap() {
        Map<String, Object> dataMap = new HashMap<>();
        User user = new User();
        user.setRealName("张三");
        user.setAddress("四川宜宾");
        user.setBirthday("1995-06-26");
        user.setCollege("四川理工学院");
        user.setEducation("大学本科");
        user.setEmail("xx20510@163.com");
        user.setIntroduction("参与过三次大学生创业项目，有多次项目开发经验，并加入过大学生创业实验室。曾获得过校内二、三 " +
                "等奖学金和ACM三等奖。本人爱好计算机行业，对java对编程有浓厚的兴趣,有刻苦专研的精神，有良好 " +
                "的编程习惯和和解决问题的能力，并且能用Spring boot、Mybatis、mysql、Spring Cloud、Redis、" +
                "RabbitMq进行网站开发、APP接口设计和微信公众号开发,并对java虚拟机、分布式/微服务有一定的了");
        user.setJobIntention("Java工程师");
        user.setNation("汉");
        user.setStature(165);
        user.setTel("1585478950");
        dataMap.put("user", user);
        Education education = new Education();
        education.setCollege("四川理工学院");
        education.setCourses("计算机, 编译原理, X语言, JAVA, C++");
        education.setEndDate("2018-06");
        education.setStartDate("2014-9");
        education.setSpecialty("软件工程");
        education.setType("本科");
        dataMap.put("education", education);

        List<Credential> credentials = new ArrayList<>();
        Credential credential = new Credential();
        credential.setContent("大学英语四/六级（CET-4/6），良好的听说读写能力，快速浏览英语专业文件及书籍；");
        credentials.add(credential);
        credential = new Credential();
        credential.setContent("普通话一级甲等；");
        credentials.add(credential);
        dataMap.put("credentials", credentials);
        Job job = new Job();
        job.setCompany("成都BBD科技有限公司");
        job.setDescription("从事后端开发,使用java, mybatis,mysql进行开发");
        job.setPosition("java工程师");
        job.setStartDate("2016-8");
        job.setEndDate("2018-05");
        dataMap.put("job", job);
        Project project = new Project();
        project.setDescription("该系统,主要是对企业信息进行统计,用户可以登录,查看,导出各种统计结果");
        project.setEndDate("2018-6");
        project.setStartDate("2017-5");
        project.setName("XXXXXX企业系统");
        dataMap.put("project", project);
        return dataMap;
    }

    public void exportDoc(String doc, String name) throws Exception {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(doc), encoding));
        getTemplate(name).process(getDataMap(), writer);
    }
}
