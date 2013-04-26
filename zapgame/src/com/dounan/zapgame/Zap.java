package com.dounan.zapgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

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
