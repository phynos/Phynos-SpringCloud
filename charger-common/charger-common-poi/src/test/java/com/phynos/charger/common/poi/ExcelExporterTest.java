package com.phynos.charger.common.poi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * POI导出相关的单元测试
 *
 * @author by lupc
 * @date 2021-01-29 8:50
 */
@SpringBootTest(classes = ExcelExporterTest.class)
public class ExcelExporterTest {

    @Test
    public void testExport1() {
        List<Person> data = craeteData();
        ExcelExporter<Person> exporter = new ExcelExporter<>(Person.class);
        try (FileOutputStream fos = new FileOutputStream("/excel_export_test.xlsx")) {
            exporter.exportExcel(data, "人员信息", fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImport1() {

    }

    private List<Person> craeteData() {
        List<Person> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            Person p = new Person();
            p.setName("姓名-" + i);
            p.setAge(1);
            p.setSex("男");
            p.setNation("汉族");
            data.add(p);
        }
        return data;
    }

}