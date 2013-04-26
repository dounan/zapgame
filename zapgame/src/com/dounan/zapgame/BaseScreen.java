package com.dounan.zapgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.dounan.libgdx.utils.BackgroundImage;

public class BaseScreen implements Screen, Serializable {

  protected float r;
  protected float g;
  protected float b;
  protected final Stage stage;
  public Rectangle scissor;

  private BackgroundImage bg;

  public BaseScreen() {
    stage = new Stage();
    scissor = new Rectangle();
  }

  @Override
  public void render(float delta) {
    if (delta > C.MAX_DELTA) {
      delta = C.MAX_DELTA;
    }
    float a = stage.getRoot().getColor().a;
    Gdx.gl.glClearColor(r * a, g * a, b * a, a);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    beforeDraw(delta);
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.setViewport(C.STAGE_W, C.STAGE_H, true);
    stage.getCamera().position.set(C.STAGE_W * .5f, C.STAGE_H * .5f, 0f);

    // Calculate scissor for the screen.
    float ratio;
    int horzDiff = width - C.STAGE_W;
    int vertDiff = height - C.STAGE_H;
    if (horzDiff < vertDiff) {
      ratio = ((float) width) / C.STAGE_W;
    } else {
      ratio = ((float) height) / C.STAGE_H;
    }
    float stageW = C.STAGE_W * ratio;
    float stageH = C.STAGE_H * ratio;
    float width2 = width * .5f;
    float height2 = height * .5f;
    scissor.x = width2 - stageW * .5f;
    scissor.y = height2 - stageH * .5f;
    scissor.width = stageW;
    scissor.height = stageH;
  }

  @Override
  public void show() {
    transitionIn();
  }

  @Override
  public void hide() {
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void dispose() {
    stage.dispose();
  }

  @Override
  public void write(Json json) {
  }

  @Override
  public void read(Json json, OrderedMap<String, Object> jsonData) {
  }

  public void setBackground(BackgroundImage bg) {
    this.bg = bg;
  }

  public void handleBackKey() {
  }

  public void transitionOut(Runnable callback) {
    if (bg != null) {
      bg.blendingEnabled = true;
    }
    Zap.setScreenInputProcessor(null);
    stage.getRoot().getColor().a = 1;
    stage.getRoot().addAction(Actions.fadeOut(getTransitionOutDuration()));
    stage.getRoot().addAction(Actions.after(Actions.run(callback)));
  }

  private void transitionIn() {
    if (bg != null) {
      bg.blendingEnabled = true;
    }
    Zap.setScreenInputProcessor(null);
    stage.getRoot().getColor().a = 0;
    stage.getRoot().addAction(Actions.fadeIn(getTransitionInDuration()));
    stage.getRoot().addAction(Actions.after(Actions.run(new Runnable() {
      @Override
      public void run() {
        if (bg != null) {
          bg.blendingEnabled = false;
        }
        Zap.setScreenInputProcessor(stage);
      }
    })));
  }

  protected float getTransitionInDuration() {
    return .25f;
  }

  protected float getTransitionOutDuration() {
    return .25f;
  }

  /**
   * Called after stage.act();
   */
  protected void beforeDraw(float delta) {
  }
}
