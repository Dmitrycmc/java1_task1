package ru.gb.star.screen.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import ru.gb.star.screen.ScreenManager;

public class Assets {
    private static final Assets instance = new Assets();

    private Assets() {
        assetManager = new AssetManager();
    }

    public static Assets get() {
        return instance;
    }

    private AssetManager assetManager;

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void loadAssets(ScreenManager.ScreenType type) {
        switch (type) {
            case GAME:
                assetManager.load("images/game.pack", TextureAtlas.class);
                createStandardFont(32);
                break;
            case MENU:
                assetManager.load("images/game.pack", TextureAtlas.class);
                createStandardFont(72);
                createStandardFont(24);
                break;
        }
    }

    public TextureAtlas getTextureAtlas() {
        return assetManager.get("images/game.pack", TextureAtlas.class);
    }

    public BitmapFont getFont(int size) {
        return assetManager.get("fonts/font-" + size + ".ttf", BitmapFont.class);
    }

    private void createStandardFont(int size) {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParameter.fontFileName = "fonts/Roboto-Medium.ttf";
        fontParameter.fontParameters.size = size;
        fontParameter.fontParameters.color = Color.WHITE;
        fontParameter.fontParameters.shadowOffsetX = 1;
        fontParameter.fontParameters.shadowOffsetY = 1;
        fontParameter.fontParameters.shadowColor = Color.DARK_GRAY;
        assetManager.load("fonts/font-" + size + ".ttf", BitmapFont.class, fontParameter);
    }

    public void clear() {
        assetManager.clear();
    }
}
