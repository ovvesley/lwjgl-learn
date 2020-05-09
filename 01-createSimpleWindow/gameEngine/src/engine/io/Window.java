package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
    private int width, heigth;
    private String title;
    private long  window;

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
        GLFW.glfwPollEvents();
    }

    public void swapBuffers(){
        GLFW.glfwSwapBuffers(window);
    }
}
