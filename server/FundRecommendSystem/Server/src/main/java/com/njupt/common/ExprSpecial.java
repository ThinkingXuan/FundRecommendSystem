package com.njupt.common;

public class ExprSpecial {


    //regex对输入特殊字符转义
    public static String SpecialWord(String keyword) {
        if (!"".equals(keyword)) {

            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
