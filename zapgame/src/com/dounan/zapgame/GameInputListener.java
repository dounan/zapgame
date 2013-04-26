package com.dounan.zapgame;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class GameInputListener extends ActorGestureListener {

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
      Zap.gameScreen.gameStage.zapper.setPointer0Down(true);
    } else if (pointer == 1) {
      Zap.gameScreen.gameStage.zapper.setPointer1Down(true);
    }
    touchDragged(x, y, pointer);
  }

  @Override
  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    if (pointer == 0) {
      Zap.gameScreen.gameStage.zapper.setPointer0Down(false);
    } else if (pointer == 1) {
      Zap.gameScreen.gameStage.zapper.setPointer1Down(false);
    }
  }

  public void touchDragged(float x, float y, int pointer) {
    if (pointer == 0) {
      Zap.gameScreen.gameStage.zapper.setPointer0Position(x, y);
    } else if (pointer == 1) {
      Zap.gameScreen.gameStage.zapper.setPointer1Position(x, y);
    }
  }
}
