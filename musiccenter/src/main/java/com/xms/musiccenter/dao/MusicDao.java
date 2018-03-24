package com.xms.musiccenter.dao;

import java.util.List;

import com.xms.musiccenter.domain.Music;

public interface MusicDao {

	Music getMusicById(Long id);

	List<Music> getMusicByName(String name);

	int addMusic(Music music);

	int updateMusic(Music music);

}
