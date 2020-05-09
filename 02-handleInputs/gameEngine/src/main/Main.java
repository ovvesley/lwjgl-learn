package main;

import engine.io.Window;
import org.lwjgl.glfw.GLFW;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(800, 600, "First Display");
        window.create();

//        MY GAME LOOP
        while(!window.closed()){
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
