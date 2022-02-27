package com.njupt.controller;


import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import com.njupt.common.FileUtil;
import com.njupt.common.MarkdownUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("frs")
@RestController
public class UserAPIController {

    @GetMapping("/api")
    public ModelAndView index() {


//        String content = FileUtil.readByLines("/usr/local/src/fund.md");
//        C:\Users\Administrator\Desktop\基金推荐系统\fund.md

        AtxMarkdownToc.newInstance().genTocFile("/usr/local/src/fund.md");

        String content = FileUtil.readByLines("/usr/local/src/fund.md");
        String HTMLContent = MarkdownUtils.markdownToHtmlExtensitons(content);//MarkDown文本转成HTML文本

        ModelAndView model = new ModelAndView();
        model.setViewName("user_api.html");
        model.getModel().put("name", HTMLContent);

        return model;
    }
}
