package engine.io;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import java.nio.DoubleBuffer;

public class Window {
    private int width, heigth;
    private String title;
    private long  window;
    private boolean [] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean [] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    public Window(int width, int heigth, String title){
        this.width = width;
        this.heigth = heigth;
        this.title = title;
    }

    public void create(){
        if(!GLFW.glfwInit()){
            System.err.println("Error: couldn't initialize GLFW");
        }

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        window = GLFW.glfwCreateWindow(width, heigth, title, 0, 0);

        if(window ==0 ){
            System.err.println("Error: Window couldnt be created");
            System.exit(-1);
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width() - width) /2 ,(videoMode.height() - heigth) /2 );

        GLFW.glfwShowWindow(window);
    }
    public boolean closed(){
        return GLFW.glfwWindowShouldClose(window);
    }

    public void update(){
        for(int indexI = 0; indexI< GLFW.GLFW_KEY_LAST; indexI++){
            keys[indexI] = isKeyDown(indexI);
        }

        for (int indexI =0; indexI< GLFW.GLFW_MOUSE_BUTTON_LAST; indexI++){
            mouseButtons[indexI] = isMouseDown(indexI);
        }

        GLFW.glfwPollEvents();
    }

    public void swapBuffers(){
        GLFW.glfwSwapBuffers(window);
    }

    public boolean isKeyDown(int keyCode){
        return GLFW.glfwGetKey(window, keyCode) == 1 ;
    }

    public boolean isMouseDown(int mouseButton){
        return GLFW.glfwGetMouseButton(window, mouseButton) == 1 ;
    }

    public boolean isKeyPressed(int keyCode){
        return isKeyDown(keyCode) && !keys[keyCode];
    }

    public boolean isKeyReleased(int keyCode){
        return !isKeyDown(keyCode) && keys[keyCode];
    }

    public boolean isMousePressed(int mouseButton){
        return isMouseDown(mouseButton) && !mouseButtons[mouseButton];
    }

    public boolean isMouseReleased(int mouseButton){
        return !isMouseDown(mouseButton) && mouseButtons[mouseButton];
    }

    public double getMouseX(){
        DoubleBuffer dbuffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, dbuffer, null);
        return dbuffer.get(0);
    }

    public double getMouseY(){
        DoubleBuffer dbuffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, null, dbuffer);
        return dbuffer.get(0);
    }

}
