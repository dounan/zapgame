package com.dounan.zapgame;

import java.util.Iterator;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;

public class GameScreen extends BaseScreen {

  public final GameStage gameStage;

  private final GameInputListener gameInputListener;
  private final Vector2 tmpBallPos;

  private Array<Ball> balls;

  public GameScreen() {
    gameStage = new GameStage();
    gameInputListener = new GameInputListener();
    gameStage.addListener(gameInputListener);
    tmpBallPos = new Vector2();
    balls = new Array<Ball>();
    stage.addActor(gameStage);

    // TODO(dounanshi): remove
    for (int i = 0; i < 100; i++) {
      float vx = -500 + MathUtils.random(1000);
      float vy = -500 + MathUtils.random(1000);
      int type = MathUtils.random(2) + 1;
      addBall(Ball.create(MathUtils.random(C.STAGE_W), MathUtils.random(C.STAGE_H), vx, vy, type));
    }
  }

  @Override
  public void read(Json json, OrderedMap<String, Object> jsonData) {
    // TODO Auto-generated method stub
    super.read(json, jsonData);
  }

  @Override
  public void write(Json json) {
    // TODO Auto-generated method stub
    super.write(json);
  }

  @Override
  public void handleBackKey() {
    // TODO Auto-generated method stub
  }

  @Override
  public void render(float delta) {
    // TODO Auto-generated method stub
    super.render(delta);
  }

  @Override
  protected void beforeDraw(float delta) {
    super.beforeDraw(delta);
    Iterator<Ball> ballIter = balls.iterator();
    while (ballIter.hasNext()) {
      Ball ball = ballIter.next();
      if (ball.isFlaggedForRemoval()) {
        ballIter.remove();
        gameStage.removeGameContent(ball);
        continue;
      }
      if (gameStage.zapper.isZapping) {
        tmpBallPos.x = ball.getX();
        tmpBallPos.y = ball.getY();
        if (Intersector.intersectSegmentCircle(gameStage.zapper.zapPos0, gameStage.zapper.zapPos1,
            tmpBallPos, ball.radiusSq)) {
          ball.flagForRemoval(true);
        }
      }
    }
  }

  public void addBall(Ball ball) {
    balls.add(ball);
    gameStage.addGameContent(ball);
  }
}
