package com.xms.usercenter.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.xms.domain.Music;
import com.xms.domain.ResultDO;

/**
 * 对用户最喜欢的音乐进行管理
 */
@RestController
@RequestMapping("/xms")
public class FavoriteMusicController {
	private static final Logger logger = LoggerFactory.getLogger(FavoriteMusicController.class);

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * restTemplate.getForEntity("http://localhost:8082/xms/v1/musics/name/{1}", ResultDO.class, "xxoo");
	 * 
	 * 可以用一个数字做占位符，最后是一个可变长度的参数，来一一替换前面的占位符
	 * ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/sayhello?name={1}", String.class, "张三");
	 * 
	 * 也可以前面使用name={name}这种形式，最后一个参数是一个map，map的key即为前边占位符的名字，map的value为参数值
	 * Map<String, String> map = new HashMap<>();
	 * map.put("name", "李四");
	 * ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/sayhello?name={name}", String.class, map);
	 * 
	 * http://localhost:8081/xms/v1/users/{id}/musics/name/{name}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/v1/users/{id}/musics/name/{name}", method = RequestMethod.GET)
	public ResultDO<Music> findFavoriteMuiscV1(@PathVariable Long id, @PathVariable(value = "name") String musicName) {
		ResultDO<Music> result = null;

		ResponseEntity<ResultDO> response = restTemplate.getForEntity("http://localhost:8082/xms/v1/musics/name/{1}", ResultDO.class, "xxoo");
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			// 响应是ResultDO<Music>，要从中剥离出music来
			result = response.getBody();
			if(result.isSuccess()) {
				logger.debug("get music success, id={}, musicName={}, result={}", id, musicName, result);
			} else {
				logger.warn("get music failure, id={}, musicName={}, result={}", id, musicName, result);
			}
		} else {
			result = new ResultDO<Music>(1001, "get music error");
			logger.error("get music error, id={}, musicName={}, statusCode={}", id, musicName, response.getStatusCode());
		}

		return result;
	}
	
	/**
	 * 使用URI组件构建
	 * UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://localhost:8082/xms/v1/musics/name/{1}").build().expand("xxoo").encode();
	 * URI uri = uriComponents.toUri();
	 * ResponseEntity<ResultDO> response = restTemplate.getForEntity(uri, ResultDO.class);
	 * 
	 * http://localhost:8081/xms/v1/users/{id}/musics/name/{name}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/v2/users/{id}/musics/name/{name}", method = RequestMethod.GET)
	public ResultDO<Music> findFavoriteMuiscV2(@PathVariable Long id, @PathVariable(value = "name") String musicName) {
		ResultDO<Music> result = null;
		
		UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://localhost:8082/xms/v1/musics/name/{1}").build().expand("xxoo").encode();
	    URI uri = uriComponents.toUri();

		ResponseEntity<ResultDO> response = restTemplate.getForEntity(uri, ResultDO.class);
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			// 响应是ResultDO<Music>，要从中剥离出music来
			result = response.getBody();
			if(result.isSuccess()) {
				logger.debug("get music success, id={}, musicName={}, result={}", id, musicName, result);
			} else {
				logger.warn("get music failure, id={}, musicName={}, result={}", id, musicName, result);
			}
		} else {
			result = new ResultDO<Music>(1001, "get music error");
			logger.error("get music error, id={}, musicName={}, statusCode={}", id, musicName, response.getStatusCode());
		}

		return result;
	}
	
	/**
	 * getForObject函数实际上是对getForEntity函数的进一步封装，如果你只关注返回的消息体的内容，对其他信息都不关注，此时可以使用getForObject
	 * 
	 * http://localhost:8081/xms/v1/users/{id}/musics/name/{name}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/v3/users/{id}/musics/name/{name}", method = RequestMethod.GET)
	public ResultDO<Music> findFavoriteMuiscV3(@PathVariable Long id, @PathVariable(value = "name") String musicName) {
		ResultDO<Music> result = null;

		ResultDO response = restTemplate.getForObject("http://localhost:8082/xms/v1/musics/name/{1}", ResultDO.class, "xxoo");
		if (response != null && response.isSuccess()) {
			// 响应是ResultDO<Music>，要从中剥离出music来
			result = response;
			logger.debug("get music success, id={}, musicName={}, result={}", id, musicName, result);
		} else {
			result = new ResultDO<Music>(1001, "get music error");
			logger.warn("get music failure, id={}, musicName={}, result={}", id, musicName, result);
		}

		return result;
	}
	
	/**
	 * postForEntity, 第一参数是要调用的服务地址，第二个参数表示上传的参数，第三个参数表示返回的消息体的数据类型
	 * 注意：在MusicController中post的地址是http://localhost:8082/xms/v1/musics/，结尾带有 "/"，因此此处请求必须也带"/",否则报404错误
	 * 
	 * http://localhost:8081/xms/v1/users/{id}/musics
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/v1/users/{id}/musics", method = RequestMethod.POST)
	public ResultDO<Music> addFavoriteMuiscV1(@PathVariable Long id, @RequestBody Music music) {
		ResultDO<Music> result = null;

		ResponseEntity<ResultDO> response = restTemplate.postForEntity("http://localhost:8082/xms/v1/musics/", music, ResultDO.class);
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			// 响应是ResultDO<Music>，要从中剥离出music来
			result = response.getBody();
			if(result.isSuccess()) {
				logger.debug("add music success, id={}, music={}, result={}", id, music, result);
			} else {
				logger.warn("add music failure, id={}, music={}, result={}", id, music, result);
			}
		} else {
			result = new ResultDO<Music>(1001, "add music error");
			logger.error("get music error, id={}, music={}, statusCode={}", id, music, response.getStatusCode());
		}

		return result;
	}
	
	/**
	 * 只关注返回的消息体，可以直接使用postForObject。用法和getForObject一致。
	 * 
	 * http://localhost:8081/xms/v2/users/{id}/musics
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/v2/users/{id}/musics", method = RequestMethod.POST)
	public ResultDO<Music> addFavoriteMuiscV2(@PathVariable Long id, @RequestBody Music music) {
		ResultDO<Music> result = null;

		ResultDO response = restTemplate.postForObject("http://localhost:8082/xms/v1/musics/", music, ResultDO.class);
		if (response != null && response.isSuccess()) {
			// 响应是ResultDO<Music>，要从中剥离出music来
			result = response;
			logger.debug("add music success, id={}, music={}, result={}", id, music, result);
		} else {
			result = new ResultDO<Music>(1001, "add music error");
			logger.warn("add music failure, id={}, music={}, result={}", id, music, result);
		}


		return result;
	}
	
	/**
	 * postForLocation也是提交新资源，提交成功之后，返回新资源的URI，postForLocation的参数和前面两种的参数基本一致，只不过该方法的返回值为Uri，这个只需要服务提供者返回一个Uri即可，该Uri表示新资源的位置。
	 * 
	 * http://localhost:8081/xms/v3/users/{id}/musics
	 */
	@RequestMapping(value = "/v3/users/{id}/musics", method = RequestMethod.POST)
	public ResultDO<URI> addFavoriteMuiscV3(@PathVariable Long id, @RequestBody Music music) {
		ResultDO<URI> result = null;

		URI uri = restTemplate.postForLocation("http://localhost:8082/xms/v1/musics/", music, ResultDO.class);
		if (uri != null) {
			result = new ResultDO<URI>(uri);
			logger.debug("add music success, id={}, music={}, result={}", id, music, result);
		} else {
			result = new ResultDO<URI>(1001, "aa music error");
			logger.warn("add music failure, id={}, music={}, result={}", id, music, result);
		}


		return result;
	}
	
	/**
	 * 只关注返回的消息体，可以直接使用postForObject。用法和getForObject一致。
	 * 
	 * http://localhost:8081/xms/v1/users/{userId}/musics/{musicId}
	 */
	@RequestMapping(value = "/v1/users/{userId}/musics/{musicId}", method = RequestMethod.PUT)
	public ResultDO<Integer> updateFavoriteMuiscV1(@PathVariable Long userId, @PathVariable Long musicId, @RequestBody Music music) {
		ResultDO<Integer> result = null;
		
		try {
			restTemplate.put("http://localhost:8082/xms/v1/musics/6", music, ResultDO.class);
			logger.debug("update music success, userId={}, music={}", userId, music);
			result = new ResultDO<Integer>(1);
		} catch (Exception e) {
			logger.error("update music error, userId={}, music={}", userId, music, e);
			result = new ResultDO<>(1001, "update music error");
		}

		return result;
	}
	
}
