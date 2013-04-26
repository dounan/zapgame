package com.dounan.zapgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Zap extends Game {

  public static Zap instance;
  public static GameScreen gameScreen;

  private InputMultiplexer inputMultiplexer;
  private InputProcessor screenInputProcessor;

  public Zap() {
    instance = this;
  }

  @Override
  public void create() {
    // TODO: move to LoadingScreen
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/zapgame.atlas"));
    Assets.atlas = atlas;

    FileHandle debugFontFile = Gdx.files.internal("data/debugempty.fnt");
    Assets.debugFont = new BitmapFont(debugFontFile, atlas.createSprite("debugempty"), false);

    Assets.ball = atlas.createSprite("ball");

    initInputProcessors();
    gameScreen = new GameScreen();
    setScreen(gameScreen);
  }

  public static void setScreenInputProcessor(InputProcessor inputProcessor) {
    if (instance.screenInputProcessor != null) {
      instance.inputMultiplexer.removeProcessor(instance.screenInputProcessor);
    }
    if (inputProcessor != null) {
      instance.screenInputProcessor = inputProcessor;
      instance.inputMultiplexer.addProcessor(inputProcessor);
    }
  }

  // ///////////////////////////////////////////////////////////////
  // Helper methods
  // ///////////////////////////////////////////////////////////////

  private void initInputProcessors() {
    Gdx.input.setCatchBackKey(true);
    Gdx.input.setCatchMenuKey(true);
    inputMultiplexer = new InputMultiplexer();
    inputMultiplexer.addProcessor(new MyInputProcessor());
    Gdx.input.setInputProcessor(inputMultiplexer);
  }

  private class MyInputProcessor extends InputAdapter {
    @Override
    public boolean keyDown(int keyCode) {
      Gdx.app.log("keyCode", keyCode + "");
      switch (keyCode) {
        case Input.Keys.BACK:
          BaseScreen currentScreen = (BaseScreen) Zap.this.getScreen();
          if (currentScreen != null) {
            currentScreen.handleBackKey();
          }
          return true;
      }
      return false;
    }
  }
}
