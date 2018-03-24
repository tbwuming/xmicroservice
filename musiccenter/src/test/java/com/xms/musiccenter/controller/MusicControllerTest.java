package com.xms.musiccenter.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.xms.musiccenter.MusicCenterApplication;

import net.minidev.json.JSONObject;

/**
 * @SpringBootTest的 classes参数指定启动类,如果包名一致可以替换成
 *                  <p>
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicCenterApplication.class)
 * 
 * <p>
 * JSONPath详见：
 * https://blog.csdn.net/luxideyao/article/details/77802389
 * https://blog.csdn.net/qq_20641565/article/details/77162868
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicCenterApplication.class)
public class MusicControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		// mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
		// 由于是需要Service和Dao层，因此需要使用WebApplicationContext
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/*{
	    "success": true,
	    "code": 0,
	    "message": null,
	    "data": {
	        "id": 1,
	        "gmtCreate": null,
	        "gmtModified": null,
	        "features": null,
	        "name": "后来",
	        "singer": "刘若英",
	        "company": "北京人民大学"
	    }
	}*/
	@Test
	public void test_getMusicById() throws Exception {
		// 构建get请求，访问路径以及请求头，主要是Content-Type
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/xms/v1/musics/1").accept(MediaType.APPLICATION_JSON);

		// 执行请求
		ResultActions resultActions = mvc.perform(requestBuilder);

		// 打印响应结果
		resultActions.andDo(print());

		// 判断http响应状态是否是成功返回200, Content-Type是否是application/json;charset=UTF-8
		resultActions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		// 判断返回的Body中的json串id是否为1，name是否为jimmy,获取json的节点要以 $. 开头
		resultActions.andExpect(jsonPath("$.data.id").value("1")).andExpect(jsonPath("$.data.name").value("后来"));
	}
	
	/*{
	    "success": true,
	    "code": 0,
	    "message": null,
	    "data": [
	        {
	            "id": 5,
	            "gmtCreate": "2018-03-24T13:20:57.000+0000",
	            "gmtModified": "2018-03-24T13:23:25.000+0000",
	            "features": "school:1;hornor:2;",
	            "name": "xxoo",
	            "singer": "xxsinger",
	            "company": "马连洼北路too"
	        },
	        {
	            "id": 6,
	            "gmtCreate": "2018-03-24T13:28:15.000+0000",
	            "gmtModified": "2018-03-24T13:29:47.000+0000",
	            "features": null,
	            "name": "xxoo",
	            "singer": "xxsinger333",
	            "company": "马连洼北路3333"
	        }
	    ]
	}*/
	@Test
	public void test_getMusicByName() throws Exception {
		// 构建get请求，访问路径以及请求头，主要是Content-Type
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/xms/v1/musics/name/xxoo").accept(MediaType.APPLICATION_JSON);

		// 执行请求
		ResultActions resultActions = mvc.perform(requestBuilder);

		// 打印响应结果
		resultActions.andDo(print());

		// 判断http响应状态是否是成功返回200, Content-Type是否是application/json;charset=UTF-8
		resultActions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		// 判断返回的Body中的json串,该串是个数组，数组下标从0开始
		resultActions.andExpect(jsonPath("$.data[1].id").value("6")).andExpect(jsonPath("$.data[1].name").value("xxoo"));
	}

	/*{
	    "success": true,
	    "code": 0,
	    "message": null,
	    "data": {
	        "id": 7,
	        "gmtCreate": null,
	        "gmtModified": null,
	        "features": null,
	        "name": "test_addMusic",
	        "singer": "12",
	        "company": "菊园"
	    }
	}*/
	@Test
	public void test_addMusic() throws Exception {
		// 构建POST请求参数
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("name", "test_addMusic");
		param.put("singer", "12");
		param.put("company", "菊园");
		
		// 构建post请求，访问路径以及请求头，主要是Content-Type
		// 如果不添加contentType，则会报错：java.lang.AssertionError: Status expected:<200> but was:<415>
		// 415	Unsupported Media Type	服务器无法处理请求附带的媒体格式
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/xms/v1/musics/").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(param));

		// 执行请求
		ResultActions resultActions = mvc.perform(requestBuilder);

		// 打印响应结果
		resultActions.andDo(print());

		// 判断http响应状态是否是成功返回200, Content-Type是否是application/json;charset=UTF-8
		resultActions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		// 判断返回的Body中的json串,该串是个数组，数组下标从0开始
		resultActions.andExpect(jsonPath("$.success").value(true));
	}
	
	/*{
	    "success": true,
	    "code": 0,
	    "message": null,
	    "data": {
	        "id": 5,
	        "gmtCreate": null,
	        "gmtModified": null,
	        "features": null,
	        "name": "test_updateMusic",
	        "singer": "12",
	        "company": "菊园"
	    }
	}*/
	@Test
	public void test_updateMusic() throws Exception {
		// 构建POST请求参数
		Map<String, Object> param = new LinkedHashMap<String, Object>();
		param.put("name", "test_updateMusic");
		param.put("singer", "12");
		param.put("company", "菊园");
		
		// 构建put请求，访问路径以及请求头，主要是Content-Type
		// 如果不添加contentType，则会报错：java.lang.AssertionError: Status expected:<200> but was:<415>
		// 415	Unsupported Media Type	服务器无法处理请求附带的媒体格式
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/xms/v1/musics/5").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(param));

		// 执行请求
		ResultActions resultActions = mvc.perform(requestBuilder);

		// 打印响应结果
		resultActions.andDo(print());

		// 判断http响应状态是否是成功返回200, Content-Type是否是application/json;charset=UTF-8
		resultActions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		// 判断返回的Body中的json串,该串是个数组，数组下标从0开始
		resultActions.andExpect(jsonPath("$.success").value(true));
	}
	
}
