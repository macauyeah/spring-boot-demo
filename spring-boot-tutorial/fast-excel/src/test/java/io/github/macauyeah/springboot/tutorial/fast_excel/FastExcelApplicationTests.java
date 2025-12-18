package io.github.macauyeah.springboot.tutorial.fast_excel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import cn.idev.excel.FastExcel;
import io.github.macauyeah.springboot.tutorial.fast_excel.excel_dto.DemoData;
import io.github.macauyeah.springboot.tutorial.fast_excel.listener.DemoDataListener;

@SpringBootTest
class FastExcelApplicationTests {

	@Test
	void contextLoads() throws IOException {
		ClassPathResource resource = new ClassPathResource("excel/demoData.xlsx");
		InputStream inputStream = resource.getInputStream();
		DemoDataListener listener = new DemoDataListener();
		// Read file
		FastExcel.read(inputStream, DemoData.class, listener).sheet().doRead();
		inputStream.close();
		List<DemoData> demoData = listener.getDataList();
		assertEquals(2, demoData.size());
	}

}
