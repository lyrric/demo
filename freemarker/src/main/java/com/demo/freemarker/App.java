package com.demo.freemarker;

import com.demo.freemarker.util.ExportDoc;

public class App {

    public static void main(String[] args) throws Exception {
        ExportDoc maker = new ExportDoc("UTF-8");
        maker.exportDoc("D:/test.doc", "temp.ftl");
    }
}
