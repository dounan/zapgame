package com.dounan.zapgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Zapper extends Actor {

  // The amount of space between where the pointer is touching the screen
  // and the actual point of the zapper.
  private static final float ZAP_BUFFER = 250;
  private static final float MIN_DIST_SQ = ZAP_BUFFER * 2 * ZAP_BUFFER * 2;

  public boolean isZapping;
  public Vector2 zapPos0;
  public Vector2 zapPos1;

  private boolean pointer0Down;
  private boolean pointer1Down;
  private Vector2 pointer0;
  private Vector2 pointer1;

  private final ShapeRenderer shapeRenderer;

  public Zapper() {
    pointer0 = new Vector2();
    pointer1 = new Vector2();
    zapPos0 = new Vector2();
    zapPos1 = new Vector2();
    shapeRenderer = new ShapeRenderer();
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    if (isZapping) {
      float radians = MathUtils.atan2(pointer1.y - pointer0.y, pointer1.x - pointer0.x);
      float offX = ZAP_BUFFER * MathUtils.cos(radians);
      float offY = ZAP_BUFFER * MathUtils.sin(radians);
      zapPos0.x = pointer0.x + offX;
      zapPos0.y = pointer0.y + offY;
      zapPos1.x = pointer1.x - offX;
      zapPos1.y = pointer1.y - offY;
    }
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha) {
    if (isZapping) {
      batch.end();
      shapeRenderer.begin(ShapeType.Line);
      shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
      shapeRenderer.line(zapPos0.x, zapPos0.y, zapPos1.x, zapPos1.y);
      shapeRenderer.end();
      batch.begin();
    }
  }

  public void setPointer0Down(boolean down) {
    pointer0Down = down;
    updateZapping();
  }

  public void setPointer1Down(boolean down) {
    pointer1Down = down;
    updateZapping();
  }

  public void setPointer0Position(float x, float y) {
    pointer0.x = x;
    pointer0.y = y;
    updateZapping();
  }

  public void setPointer1Position(float x, float y) {
    pointer1.x = x;
    pointer1.y = y;
    updateZapping();
  }

  private void updateZapping() {
    if (!pointer0Down || !pointer1Down) {
      isZapping = false;
      return;
    }
    float dx = pointer1.x - pointer0.x;
    float dy = pointer1.y - pointer0.y;
    float dSq = dx * dx + dy * dy;
    isZapping = dSq >= MIN_DIST_SQ;
  }
}
