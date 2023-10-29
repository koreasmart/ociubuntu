package ksmart.oci;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OciubuntuApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(System.getProperty("os.name"));
	}

}
