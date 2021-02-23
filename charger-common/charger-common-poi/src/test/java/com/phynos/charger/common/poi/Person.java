package com.phynos.charger.common.poi;

/**
 * POI导出测试 实体类
 * @author by lupc
 * @date 2021-01-29 8:51
 */
public class Person {

    @ExcelField(name = "姓名",align = ExcelField.Align.CENTER)
    private String name;

    @ExcelField(name = "年龄",align = ExcelField.Align.CENTER)
    private int age;

    @ExcelField(name = "性别",align = ExcelField.Align.CENTER)
    private String sex;

    @ExcelField(name = "民族",align = ExcelField.Align.CENTER)
    private String nation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
