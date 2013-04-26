package com.dounan.zapgame;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.Json.Serializable;

public class GameStage extends Group implements Serializable {

  private final Group gameContent;
  public final Zapper zapper;
  
  public GameStage() {
    setTransform(false);
    gameContent = new Group();
    gameContent.setTransform(false);
    zapper = new Zapper();
    
    addGameContent(zapper);
    addActor(gameContent);
    setWidth(C.STAGE_W);
    setHeight(C.STAGE_H);
  }
  
  @Override
  public void write(Json json) {
    // TODO Auto-generated method stub

  }

  @Override
  public void read(Json json, OrderedMap<String, Object> jsonData) {
    // TODO Auto-generated method stub

  }
  
  public void addGameContent(Actor actor) {
    gameContent.addActor(actor);
  }
  
  public void removeGameContent(Actor actor) {
    if (actor.getParent() == gameContent) {
      actor.clearActions();
      actor.remove();
    }
  }
}
