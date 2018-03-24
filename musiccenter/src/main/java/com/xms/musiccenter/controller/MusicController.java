package com.xms.musiccenter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xms.domain.ResultDO;
import com.xms.musiccenter.domain.Music;
import com.xms.musiccenter.service.MusicService;

/**
 * url 路径为类上面的路径+方法的路径，如{[/xms/musics/{id}]} 和 {[/xms/{name}]}
 */
@RestController
@RequestMapping("/xms/v1/musics")
public class MusicController {
	private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

	@Autowired
	private MusicService musicService;

	/**
	 * http://localhost:8082/xms/v1/musics/5
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResultDO<Music> getMusicById(@PathVariable Long id) {
		Music Music = null;
		try {
			Music = musicService.getMusicById(id);
		} catch (Exception e) {
			logger.error("getMusicById error, id={}", id, e);
		}

		logger.info("getMusicById success, id={}, music.name={}", id, Music.getName());

		ResultDO<Music> result = new ResultDO<Music>(Music);
		return result;
	}

	/**
	 * http://localhost:8082/xms/v1/musics/name/xxoo
	 */
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public ResultDO<List<Music>> getMusicByName(@PathVariable String name) {
		List<Music> musicList = null;
		try {
			musicList = musicService.getMusicByName(name);
		} catch (Exception e) {
			logger.error("getMusicByName error, name={}", name, e);
		}

		logger.info("getMusicByName success, name={}, size={}", name, musicList == null ? 0 : musicList.size());

		ResultDO<List<Music>> result = new ResultDO<List<Music>>(musicList);
		return result;
	}

	/**
	 * http://localhost:8082/xms/v1/musics/
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResultDO<Music> addMusic(@RequestBody Music music) {
		int ret = -1;
		try {
			ret = musicService.addMusic(music);
		} catch (Exception e) {
			logger.error("addMusic error, music={}", music, e);
		}

		ResultDO<Music> result = null;
		if (ret > 0) {
			result = new ResultDO<Music>(music);
			logger.info("addMusic success, music={}", music);
		} else {
			result = new ResultDO<Music>(1001, "add music failure");
			logger.info("addMusic failure, music={}", music);
		}

		return result;
	}

	/**
	 * http://localhost:8082/xms/v1/musics/6
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResultDO<Music> updateMusic(@PathVariable Long id, @RequestBody Music music) {
		music.setId(id); // 有可能json串生成的Music里没有id值

		int ret = -1;
		try {
			ret = musicService.updateMusic(music);
		} catch (Exception e) {
			logger.info("updateMusic error, music={}", music, e);
		}
		
		ResultDO<Music> result = null;
		if (ret > 0) {
			result = new ResultDO<Music>(music);
			logger.info("updateMusic success, music={}", music);
		} else {
			result = new ResultDO<Music>(1001, "update music failure");
			logger.info("updateMusic failure, music={}", music);
		}

		return result;
	}
}
