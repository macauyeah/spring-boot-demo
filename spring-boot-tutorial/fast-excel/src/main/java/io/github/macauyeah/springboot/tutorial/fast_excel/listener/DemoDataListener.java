package io.github.macauyeah.springboot.tutorial.fast_excel.listener;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import io.github.macauyeah.springboot.tutorial.fast_excel.excel_dto.DemoData;

public class DemoDataListener implements ReadListener<DemoData> {
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        System.out.println(
                "Parsed a data entry:" + data.getString() + ", " + data.getDate() + ", " + data.getDoubleData());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("All data parsed!");
    }
}
