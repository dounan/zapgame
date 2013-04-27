package com.dounan.zapgame;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.Json.Serializable;

public class GameStage extends Group implements Serializable {

  public final Zapper zapper;
  private final Group gameContent;
  
  public GameStage() {
    zapper = new Zapper();
    gameContent = new Group();
    gameContent.setTransform(false);
    
    addGameContent(zapper);
    addActor(gameContent);
    setWidth(C.STAGE_W);
    setHeight(C.STAGE_H);
    setTransform(false);
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
