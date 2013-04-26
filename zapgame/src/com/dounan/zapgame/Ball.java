package com.dounan.zapgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.Pools;

public class Ball extends Group implements Serializable {

  public static Ball create(float x, float y, float vx, float vy) {
    Ball ball = Pools.obtain(Ball.class);
    ball.reset();
    ball.setPosition(x, y);
    ball.setVelocity(vx, vy);
    return ball;
  }
  
  private boolean flaggedForRemoval;
  private float vx;
  private float vy;
  
  public Ball() {
    setTransform(false);
    Image img = new Image(Assets.ball);
    img.setPosition(-img.getWidth() * .5f, -img.getHeight() * .5f);
    addActor(img);
  }

  @Override
  public void write(Json json) {
    // TODO Auto-generated method stub
  }

  @Override
  public void read(Json json, OrderedMap<String, Object> jsonData) {
    // TODO Auto-generated method stub
  }
  
  @Override
  public void act(float delta) {
    super.act(delta);
    translate(vx * delta, vy * delta);
    float x = getX();
    float y = getY();
    if (x < -50 || x > C.STAGE_W + 50 || y < -50 || y > C.STAGE_H + 50) {
      flaggedForRemoval = true;
    }
  }
  
  @Override
  public boolean remove() {
    boolean removed = super.remove();
    if (removed) {
      dispose();
    }
    return removed;
  }
  
  public void reset() {
    flaggedForRemoval = false;
  }
  
  public void setVelocity(float vx, float vy) {
    this.vx = vx;
    this.vy = vy;
  }
  
  public boolean isFlaggedForRemoval() {
    return flaggedForRemoval;
  }
  
  protected void dispose() {
    Pools.free(this);
  }
}
