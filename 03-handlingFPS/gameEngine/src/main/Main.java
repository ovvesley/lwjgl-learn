package main;

import engine.io.Window;
import org.lwjgl.glfw.GLFW;

public class Main {
    public static final int WIDTH = 800, HEIGTH= 600,  FPS = 60;
    public static void main(String[] args) {
        Window window = new Window(WIDTH, HEIGTH, FPS, "First Display");
        window.create();

//        MY GAME LOOP
        while(!window.closed()){
            if(window.isUpdating()){
                window.update();

                if(window.isKeyPressed(GLFW.GLFW_KEY_SPACE)){
                    System.out.println("space key pressed");
                }

                if(window.isKeyReleased(GLFW.GLFW_KEY_SPACE)){
                    System.out.println("space key released");
                }

                if(window.isMousePressed(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
                    String msg = "mouse button left pressed. Xpos: %f; yPos: %f %n";
                    System.out.format(msg, window.getMouseX(), window.getMouseY());
                }

                if(window.isMouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
                    System.out.println("mouse button left released");
                }

                window.swapBuffers();
            }

        }
    }
}
