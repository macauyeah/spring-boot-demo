package io.github.macauyeah.springboot.tutorial.fast_excel;

import java.io.IOException;
import java.io.InputStream;

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
		
		// Read file
		FastExcel.read(inputStream, DemoData.class, new DemoDataListener()).sheet().doRead();
		inputStream.close();
	}

}
