package com.xms.musiccenter.mapper;

import java.util.List;

import com.xms.domain.Music;

public interface MusicMapper {

	Music selectMusicById(Long id);

	List<Music> selectMusicByName(String name);

	int insertMusic(Music music);

	int updateMusic(Music music);
}
