package main;

import engine.io.Window;
import engine.render.Model;
import engine.render.Renderer;
import org.lwjgl.glfw.GLFW;

public class Main {
    public static final int WIDTH = 800, HEIGTH= 600,  FPS = 60;
    public static Renderer renderer = new Renderer();

    public static void main(String[] args) {
        Window window = new Window(WIDTH, HEIGTH, FPS, "First Display");
        window.create();
        window.setBackgroundColor(0.0f,0.0f,120.0f);

        Model model = new Model(new float[] {
                -0.5f, 0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,

                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                -0.5f, 0.5f, 0.0f,

        }, new int[]{
                0,1,2,
                3,4,5
        });

        model.create();


//        MY GAME LOOP
        while(!window.closed()){
            if(window.isUpdating()){
                window.update();
                renderer.renderModel(model);


                window.swapBuffers();
            }

        }

        window.stop();
        model.remove();
    }
}
