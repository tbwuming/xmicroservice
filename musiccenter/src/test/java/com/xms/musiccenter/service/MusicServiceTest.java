package com.xms.musiccenter.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xms.musiccenter.domain.Music;
import com.xms.musiccenter.service.MusicService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class MusicServiceTest {
	
	@Autowired
	private MusicService musicService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_getMusicById() {
		Music music = musicService.getMusicById(1L);
		Assert.assertTrue(music != null && music.getId() == 1L);
	}
	
	@Test
	public void test_getMusicByName() {
		List<Music> musicList = musicService.getMusicByName("朋友");
		for (Music music : musicList) {
			Assert.assertTrue(music != null && "朋友".equals(music.getName()));
		}
	}
	
	@Test
	public void test_addMusic() {
		Music music = new Music();
		music.setName("xxoo");
		music.setSinger("xxsinger");
		music.setCompany("马连洼北路");
		music.addFeature("school", "1");
		
		int ret = musicService.addMusic(music);
		Assert.assertTrue(ret == 1);
	}
	
	@Test
	public void test_updateMusic() {
		List<Music> MusicList = musicService.getMusicByName("xxoo");
		for (Music music : MusicList) {
			music.setCompany("马连洼北路too");
			music.addFeature("hornor", "2");
			
			int ret = musicService.updateMusic(music);
			Assert.assertTrue(ret == 1);
		}
		
	}

}
