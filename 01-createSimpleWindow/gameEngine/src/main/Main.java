package main;

import engine.io.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(800, 600, "First Display");
        window.create();

//        MY GAME LOOP
        while(!window.closed()){
            window.update();
            System.out.println("Windows Opened");
            window.swapBuffers();
        }
    }
}
