package com.dounan.zapgame;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class GameInputListener extends ActorGestureListener {

  private boolean pointer0Down;
  private boolean pointer1Down;

  @Override
  public boolean handle(Event e) {
    boolean retVal = super.handle(e);
    // LibGdx does not provides us a touchDragged method.
    // Detect it manually.
    if (!(e instanceof InputEvent)) {
      return false;
    }
    InputEvent event = (InputEvent) e;
    if (event.getType() == Type.touchDragged) {
      float x = event.getStageX();
      float y = event.getStageY();
      touchDragged(x, y, event.getPointer());
    }
    return retVal;
  }

  @Override
  public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
    if (pointer == 0) {
      pointer0Down = true;
    } else if (pointer == 1) {
      pointer1Down = true;
    }
    Zap.gameScreen.gameStage.zapper.isZapping = pointer0Down && pointer1Down;
    touchDragged(x, y, pointer);
  }

  @Override
  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    if (pointer == 0) {
      pointer0Down = false;
    } else if (pointer == 1) {
      pointer1Down = false;
    }
    Zap.gameScreen.gameStage.zapper.isZapping = pointer0Down && pointer1Down;
  }

  public void touchDragged(float x, float y, int pointer) {
    Zapper zapper = Zap.gameScreen.gameStage.zapper;
    if (pointer == 0) {
      zapper.pointer0.x = x;
      zapper.pointer0.y = y;
    } else if (pointer == 1) {
      zapper.pointer1.x = x;
      zapper.pointer1.y = y;
    }
  }
}
