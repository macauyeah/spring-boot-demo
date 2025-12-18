package io.github.macauyeah.springboot.tutorial.fast_excel.listener;

import java.util.ArrayList;
import java.util.List;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import io.github.macauyeah.springboot.tutorial.fast_excel.excel_dto.DemoData;

public class DemoDataListener implements ReadListener<DemoData> {
    private final List<DemoData> dataList = new ArrayList<>();
    public List<DemoData> getDataList() {
        return dataList;
    }

    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        System.out.println(
                "Parsed a data entry:" + data.getString() + ", " + data.getDate() + ", " + data.getDoubleData());
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("All data parsed!");
    }

    
}
