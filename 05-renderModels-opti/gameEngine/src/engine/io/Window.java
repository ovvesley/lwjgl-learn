package engine.io;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.nio.DoubleBuffer;

public class Window {
    private int width, heigth;
    private String title;
    private long  window;
    private Vector3f backgroundColor;
    private double fpsCap, time, processedTime = 0;
    private boolean [] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean [] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    public Window(int width, int heigth, int fps, String title){
        this.width = width;
        this.heigth = heigth;
        this.title = title;
        this.fpsCap = fps;
        backgroundColor = new Vector3f(0.0f,0.0f,0.0f);
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

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width() - width) /2 ,(videoMode.height() - heigth) /2 );

        GLFW.glfwShowWindow(window);

        time = getTime();
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

        GL11.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();
    }

    public void swapBuffers(){
        GLFW.glfwSwapBuffers(window);
    }

    public double getTime(){
        return (double )System.nanoTime() / (double) 1000000000;
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

    public boolean isUpdating(){
        double nextTime = getTime();
        double passedTime = nextTime - time;
        processedTime += passedTime;
        time = nextTime;

        while(processedTime > 1.0/fpsCap){
            processedTime -= 1.0/fpsCap;
            return true;
        }

        return false;
    }

    public void stop(){
        GLFW.glfwTerminate();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getWindow() {
        return window;
    }

    public void setWindow(long window) {
        this.window = window;
    }

    public double getFPS() {
        return fpsCap;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setBackgroundColor(float r, float g, float b){
        backgroundColor = new Vector3f(r,g,b);
    }
}
