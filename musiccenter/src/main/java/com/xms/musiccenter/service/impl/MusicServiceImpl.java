package com.xms.musiccenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xms.musiccenter.dao.MusicDao;
import com.xms.musiccenter.domain.Music;
import com.xms.musiccenter.service.MusicService;

@Service
public class MusicServiceImpl implements MusicService {

	@Autowired
	private MusicDao musicDao;

	@Override
	public Music getMusicById(Long id) {
		return musicDao.getMusicById(id);
	}

	@Override
	public List<Music> getMusicByName(String name) {
		return musicDao.getMusicByName(name);
	}

	@Override
	public int addMusic(Music music) {
		return musicDao.addMusic(music);
	}

	@Override
	public int updateMusic(Music music) {
		return musicDao.updateMusic(music);
	}

}
