package com.xms.usercenter.util;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class JasyptEncryptor {

	@Autowired
    StringEncryptor stringEncryptor;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String result = stringEncryptor.encrypt("rootroot");
        System.out.println(result); 
	}

}
