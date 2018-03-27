package com.xms.musiccenter.service;

import java.util.List;

import com.xms.domain.Music;

public interface MusicService {

	Music getMusicById(Long id);

	List<Music> getMusicByName(String name);

	int addMusic(Music music);

	int updateMusic(Music music);
}
