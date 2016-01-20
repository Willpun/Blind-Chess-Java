package com.billy.gameui;


public class VolumeControl {

	public static float volume = 1.0f;
	
	public static void disableSounds () {
		volume = 0.0f;
	}
	
	public static void enableSounds () {
		volume = 1.0f;
	}
	
	public static float volume() {
		return volume;
	}
}
