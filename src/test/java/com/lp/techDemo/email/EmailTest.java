package com.lp.techDemo.email;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.lp.techDemo.AstractTestCase;

@ContextConfiguration(locations = { "classpath:spring-email.xml" })
public class EmailTest extends AstractTestCase {

	@Resource(name = "emailSender")
	JavaMailSender sender;
	@Resource(name = "velocityEngine")
	private VelocityEngine velocityEngine;

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		sender.createMimeMessage();
//		mail.setTo("569725931@qq.com");// 接受者
//		mail.setFrom("username@163.com");
//		mail.setSubject("hello");// 主题
//		Map<String, Object> model = new HashMap<>();
//		model.put("name", "Jimmy");
//		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "vm/sample.vm", "GBK", model);
//		mail.setText(text);// 邮件内容
//		sender.send(mail);

	}

}
