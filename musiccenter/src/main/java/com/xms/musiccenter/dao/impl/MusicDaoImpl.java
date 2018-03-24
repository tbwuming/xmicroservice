package com.xms.musiccenter.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xms.musiccenter.dao.MusicDao;
import com.xms.musiccenter.domain.Music;
import com.xms.musiccenter.mapper.MusicMapper;

@Repository
public class MusicDaoImpl implements MusicDao {

	@Autowired
	private MusicMapper musicMapper;

	@Override
	public Music getMusicById(Long id) {
		return musicMapper.selectMusicById(id);
	}

	@Override
	public List<Music> getMusicByName(String name) {
		return musicMapper.selectMusicByName(name);
	}

	@Override
	public int addMusic(Music music) {
		return musicMapper.insertMusic(music);
	}

	@Override
	public int updateMusic(Music music) {
		return musicMapper.updateMusic(music);
	}

}
