package com.dounan.zapgame;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.Pools;

public class Ball extends Group implements Serializable {

  public static final int T1 = 1;
  public static final int T2 = 2;
//  public static final int T3 = 3;

  public static Ball create(float x, float y, float vx, float vy, int type) {
    Ball ball = Pools.obtain(Ball.class);
    ball.reset();
    ball.setPosition(x, y);
    ball.setType(type);
    ball.setVelocity(vx, vy);
    return ball;
  }

  public float radiusSq;

  private final Image img;

  private boolean flaggedForRemoval;
  private int type;
  private float vx;
  private float vy;

  public Ball() {
    setTransform(false);
    img = new Image(Assets.ball);
    img.setPosition(-img.getWidth() * .5f, -img.getHeight() * .5f);
    float r = img.getWidth() * .5f;
    radiusSq = r * r;
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
      flagForRemoval(true);
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

  public void setType(int type) {
    this.type = type;
    switch (type) {
      case T1:
        img.setColor(1, 0, 0, 1);
        break;
      case T2:
        img.setColor(0, 1, 0, 1);
        break;
//      case T3:
//        img.setColor(0, 0, 1, 1);
//        break;
    }
  }
  
  public int getType() {
    return type;
  }

  public void setVelocity(float vx, float vy) {
    this.vx = vx;
    this.vy = vy;
  }

  public void flagForRemoval(boolean remove) {
    flaggedForRemoval = remove;
  }

  public boolean isFlaggedForRemoval() {
    return flaggedForRemoval;
  }

  protected void dispose() {
    Pools.free(this);
  }
}
