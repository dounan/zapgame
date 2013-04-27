package com.dounan.zapgame;

import com.badlogic.gdx.math.MathUtils;

public class BallDropper {

  private static final float BALL_SPEED = 800;

  private float timer;
  private float nextBallDropTime;

  public BallDropper() {
  }

  public void act(float delta) {
    timer += delta;
    while (timer > nextBallDropTime) {
      dropRandomBall();
      advanceBallDropTime();
    }
  }

  private void advanceBallDropTime() {
    nextBallDropTime += MathUtils.random(.2f);
  }

  private void dropRandomBall() {
    float x, y, vx, vy;
    switch (MathUtils.random(3)) {
      case 0:
        x = -50;
        y = MathUtils.random(C.STAGE_H);
        vx = MathUtils.random(BALL_SPEED);
        vy = -BALL_SPEED + MathUtils.random(2 * BALL_SPEED);
        break;
      case 1:
        x = C.STAGE_W + 50;
        y = MathUtils.random(C.STAGE_H);
        vx = -MathUtils.random(BALL_SPEED);
        vy = -BALL_SPEED + MathUtils.random(2 * BALL_SPEED);
        break;
      case 2:
        x = MathUtils.random(C.STAGE_W);
        y = -50;
        vx = -BALL_SPEED + MathUtils.random(2 * BALL_SPEED);
        vy = MathUtils.random(BALL_SPEED);
        break;
      default:
        x = MathUtils.random(C.STAGE_W);
        y = C.STAGE_H + 50;
        vx = -BALL_SPEED + MathUtils.random(2 * BALL_SPEED);
        vy = -MathUtils.random(BALL_SPEED);
        break;
    }
    int type = MathUtils.random(2) + 1;
    Zap.gameScreen.addBall(Ball.create(x, y, vx, vy, type));
  }
}
