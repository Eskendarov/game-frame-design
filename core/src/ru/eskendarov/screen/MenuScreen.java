package ru.eskendarov.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.eskendarov.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private Texture img;

    private Vector2 pos;
    private Vector2 v;

    private Vector2 touch;
    private Vector2 buf;

    @Override
    public void show() {
        super.show();
        img = new Texture("ball.png");
        pos = new Vector2(0,0);
        v = new Vector2(1f, 1f);
        touch = new Vector2();
        buf = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0.3f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buf.set(touch);
        if (buf.sub(pos).len() >= 5f) {
            pos.add(v);
        } else {
            pos.set(touch);
        }
        batch.begin();
        batch.draw(img, 0f, 0f, 5f, 5f);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(touch.cpy().sub(pos).setLength(0.5f));
        System.out.println("touch X = " + touch.x + " touch Y = " + touch.y);
        return false;
    }
}
