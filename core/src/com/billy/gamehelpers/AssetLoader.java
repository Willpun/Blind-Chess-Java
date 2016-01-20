package com.billy.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture textureBoard, texturePB, textureChessPieces;
	public static TextureRegion board;
	public static TextureRegion pieceBack, pbDim, pbBright;
	public static Animation pbAnimation;
	public static TextureRegion[] blue = new TextureRegion[10];
	public static TextureRegion[] red = new TextureRegion[10];
	
	public static Texture textureGear, textureBlendedGear;
	public static TextureRegion gear, blendedGear;
	
	public static Texture textureResume, textureQuit;
	public static TextureRegion resume, quit;
	
	public static Texture textureMouseOver;
	public static TextureRegion mouseOver;
	
	public static Texture textureSoundsOn, textureSoundsOff;
	public static TextureRegion soundsOn, soundsOff;

	public static Texture textureBlur;
	public static TextureRegion blur;
	
	public static Texture texturePVPbutton, textureSOLObutton, textureABOUTbutton;
	public static TextureRegion pvpButton, soloButton, aboutButton;

	public static Texture textureBan, textureQi, textureL1, textureL2, textureVer;
	public static TextureRegion ban, qi, l1, l2, ver;

	public static Texture textureIntro;
	public static TextureRegion intro;
	
	public static Sound capture, invalid, move, select, uncover, changes, game_over;
	
	public static BitmapFont font;
	
	public static Texture logoTexture;
	public static TextureRegion logo;
	
	public static void load() {
	
		
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 128);
		
		
		// fonts
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        float g = Gdx.graphics.getDensity();       
        font.getData().setScale(g * 0.467f, g * -0.467f);
    

		// sound effects
        game_over = Gdx.audio.newSound(Gdx.files.internal("data/game_over.wav"));
        capture = Gdx.audio.newSound(Gdx.files.internal("data/capture.wav"));
        invalid = Gdx.audio.newSound(Gdx.files.internal("data/invalid.wav"));
        move = Gdx.audio.newSound(Gdx.files.internal("data/move.wav"));
        select = Gdx.audio.newSound(Gdx.files.internal("data/select.wav"));
        uncover = Gdx.audio.newSound(Gdx.files.internal("data/uncover.wav"));
        changes = Gdx.audio.newSound(Gdx.files.internal("data/changes.wav"));
        
        textureIntro = new Texture(Gdx.files.internal("data/introduction.png"));
        textureIntro.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        intro = new TextureRegion(textureIntro, 0, 0, 360, 640);
        intro.flip(false, true);
        
        // Main Menu & Title
		texturePVPbutton = new Texture(Gdx.files.internal("data/PvPButton.png"));
		texturePVPbutton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		pvpButton = new TextureRegion(texturePVPbutton, 0, 0, 179, 80);
		pvpButton.flip(false, true);	
        
		textureSOLObutton = new Texture(Gdx.files.internal("data/SoloButton.png"));
		textureSOLObutton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		soloButton = new TextureRegion(textureSOLObutton, 0, 0, 179, 80);
		soloButton.flip(false, true);
		
		textureABOUTbutton = new Texture(Gdx.files.internal("data/AboutButton.png"));
		textureABOUTbutton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		aboutButton = new TextureRegion(textureABOUTbutton, 0, 0, 179, 80);
		aboutButton.flip(false, true);	
		
		textureBan = new Texture(Gdx.files.internal("data/title/ban.png"));
		textureBan.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		ban = new TextureRegion(textureBan, 0, 0, 360, 640);
		ban.flip(false, true);	
		
		textureQi = new Texture(Gdx.files.internal("data/title/qi.png"));
		textureQi.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		qi = new TextureRegion(textureQi, 0, 0, 360, 640);
		qi.flip(false, true);	
		
		textureL1 = new Texture(Gdx.files.internal("data/title/Layer1.png"));
		textureL1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		l1 = new TextureRegion(textureL1, 0, 0, 360, 640);
		l1.flip(false, true);	
		
		textureL2 = new Texture(Gdx.files.internal("data/title/Layer2.png"));
		textureL2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		l2 = new TextureRegion(textureL2, 0, 0, 360, 640);
		l2.flip(false, true);	
		
		textureVer = new Texture(Gdx.files.internal("data/title/hkver.png"));
		textureVer.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		ver = new TextureRegion(textureVer, 0, 0, 360, 640);
		ver.flip(false, true);	
		
		// Buttons for Sub-menu and in-game screen
		textureBlur = new Texture(Gdx.files.internal("data/gradient.png"));
		textureBlur.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		blur = new TextureRegion(textureBlur, 0, 0, 360, 640);
		blur.flip(false, true);
		
		textureSoundsOn = new Texture(Gdx.files.internal("data/SoundsOn.png"));
		textureSoundsOn.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		soundsOn = new TextureRegion(textureSoundsOn, 0, 0, 179, 80);
		soundsOn.flip(false, true);		
		
		textureSoundsOff = new Texture(Gdx.files.internal("data/SoundsOff.png"));
		textureSoundsOff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		soundsOff = new TextureRegion(textureSoundsOff, 0, 0, 179, 80);
		soundsOff.flip(false, true);		

		textureGear = new Texture(Gdx.files.internal("data/gear.png"));
		textureGear.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		gear = new TextureRegion(textureGear, 0, 0, 16, 16);
		gear.flip(false, true);		
		
		textureBlendedGear = new Texture(Gdx.files.internal("data/blendedgear.png"));
		textureBlendedGear.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		blendedGear = new TextureRegion(textureBlendedGear, 0, 0, 16, 16);
		blendedGear.flip(false, true);

		textureResume = new Texture(Gdx.files.internal("data/ResumeButton.png"));
		textureResume.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		resume = new TextureRegion(textureResume, 0, 0, 179, 80);
		resume.flip(false, true);
		
		textureQuit = new Texture(Gdx.files.internal("data/QuitButton.png"));
		textureQuit.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		quit = new TextureRegion(textureQuit, 0, 0, 179, 80);
		quit.flip(false, true);
		
		textureMouseOver = new Texture(Gdx.files.internal("data/ButtonMouseOver.png"));
		textureMouseOver.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		mouseOver = new TextureRegion(textureMouseOver, 0, 0, 175, 41);  // ratio x = 175 / 179  y = 41 / 80
		mouseOver.flip(false, true);
		
		// Board
		textureBoard = new Texture(Gdx.files.internal("data/Board_Simple.png"));
		textureBoard.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		board = new TextureRegion(textureBoard, 0, 0, 67, 135);
		board.flip(false, true);
		
		// Chess Piece Back
		texturePB = new Texture(Gdx.files.internal("data/PieceBack.png"));
		texturePB.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		pieceBack = new TextureRegion(texturePB, 0, 0, 16, 16);
		pieceBack.flip(false, true);
		
		pbDim = new TextureRegion(texturePB, 16, 0, 16, 16);
		pbDim.flip(false, true);
		
		pbBright = new TextureRegion(texturePB, 32, 0, 16, 16);
		pbBright.flip(false, true);
		
		TextureRegion[] pieceBacks = { pieceBack, pbDim, pbBright }; 
		pbAnimation = new Animation(0.2f, pieceBacks);
		pbAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		// Chess Pieces
		textureChessPieces = new Texture(Gdx.files.internal("data/ChineseChessPieces.png"));
		textureChessPieces.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		for (int i = 6; i >= 0; i--) {
			blue[i] = new TextureRegion(textureChessPieces, (6 - i) * 32, 0, 16, 16);
			blue[i].flip(false, true);
		}
		for (int i = 6; i >= 0; i--) {
			red[i] = new TextureRegion(textureChessPieces, 16 + ((6 - i )* 32), 0, 16, 16);
			red[i].flip(false, true);
		}
		
	}
	
	public static void dispose() {
		// image files
		textureBoard.dispose();
		texturePB.dispose();
		textureChessPieces.dispose();
		textureGear.dispose();
		textureBlendedGear.dispose();
		textureResume.dispose();
		textureQuit.dispose();
		textureMouseOver.dispose();
		textureSoundsOn.dispose();
		textureSoundsOff.dispose();
		textureBlur.dispose();
		
		texturePVPbutton.dispose();
		textureSOLObutton.dispose();
		textureABOUTbutton.dispose();
		textureBan.dispose();
		textureQi.dispose();
		textureL1.dispose();
		textureL2.dispose();
		textureVer.dispose();
				
		// wav files
		capture.dispose();
		invalid.dispose();
		move.dispose();
		select.dispose();
		uncover.dispose();
		changes.dispose();
		game_over.dispose();
		
		// font files
		font.dispose();

	}

}
