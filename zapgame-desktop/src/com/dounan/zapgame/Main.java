package com.dounan.zapgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Main {
  public static void main(String[] args) {

    if (args.length == 1) {
      Settings settings = new Settings();
      settings.flattenPaths = true;
      settings.filterMag = TextureFilter.Nearest;
      settings.filterMin = TextureFilter.MipMapLinearNearest;
      TexturePacker2.process(settings, "../assets/images", "../zapgame-android/assets/data",
          "zapgame");
      return;
    }

    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = "zapgame";
    cfg.useGL20 = true;
    cfg.width = 800;
    cfg.height = 450;

    new LwjglApplication(new Zap(), cfg);
  }
}
