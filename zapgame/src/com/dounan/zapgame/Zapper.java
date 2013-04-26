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
  
  public Vector2 pointer0;
  public Vector2 pointer1;
  public boolean isZapping;
  public Vector2 zapPos0;
  public Vector2 zapPos1;

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
      float dx = ZAP_BUFFER * MathUtils.cos(radians);
      float dy = ZAP_BUFFER * MathUtils.sin(radians);
      zapPos0.x = pointer0.x + dx;
      zapPos0.y = pointer0.y + dy;
      zapPos1.x = pointer1.x - dx;
      zapPos1.y = pointer1.y - dy;
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
}
