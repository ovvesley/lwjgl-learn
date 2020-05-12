package engine.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class Renderer {

    public void renderModel(Model model){
        GL30.glBindVertexArray(model.getVertexArrayID());

//        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCout());

        GL30.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCout(), GL11.GL_UNSIGNED_INT, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

}
