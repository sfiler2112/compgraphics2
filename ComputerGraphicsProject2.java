/*
 *  Name:
 *  Date: 4/14/2019
 *  Author: Sean Filer
 *  Purpose: 
 */
package computergraphicsproject2;

/**
 *
 * @author Sean
 */


import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class ComputerGraphicsProject2 extends GLJPanel implements GLEventListener {

    public static void main(String[] args) {
        JFrame window = new JFrame("JOGL Program");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        ComputerGraphicsProject2 panel = new ComputerGraphicsProject2();
        window.setContentPane(panel);
        window.pack();
        window.setLocation((screen.width - window.getWidth()) / 2, (screen.height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }

    public ComputerGraphicsProject2() {
        setPreferredSize( new Dimension(640,640) );
        addGLEventListener(this);
    }
    
    // ---------------  Methods of the GLEventListener interface -----------

    public void init(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glViewport(0,0,640,640);
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glOrtho(-10,10,-10,10,-10,10);
        //gl2.glFrustum(-6,6,-6,6, 1, 10);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glClearColor( 0, 0, 0, 1 );
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glLineWidth(1);
        gl2.glPolygonOffset(1,1);
   }

    public void display(GLAutoDrawable drawable) {    
        GL2 gl2 = drawable.getGL().getGL2();
        
        gl2.glLoadIdentity();
        
        
//        gl2.glColor3f(1,1,1);
//        gl2.glBegin(GL2.GL_TRIANGLE_FAN);
//        gl2.glVertex3d(.1,.1,0);
//        gl2.glVertex3d(.1,-.1,0);
//        gl2.glVertex3d(-.1,-.1,0);
//        gl2.glVertex3d(-.1,.1,0);
//        gl2.glEnd();
//        
         gl2.glRotated(45,1,0,0);
       gl2.glRotated(45,0,1,0);
       // gl2.glTranslated(-4,-4,-4);
       
       gl2.glPushMatrix();
       gl2.glTranslated(0,2,0);
       tetrahemihexacron(gl2);
       gl2.glPopMatrix();
       
       gl2.glPushMatrix();
       gl2.glTranslated(0,0,4);
       gl2.glRotated(80,0,1,0);
       gl2.glScaled(2,2,2);
       octahedron(gl2);
       gl2.glPopMatrix();
       
       gl2.glPushMatrix();
       gl2.glTranslated(-4,0,-2);
       gl2.glRotated(85,1,0,0);
       tetrahedron(gl2,1);
       gl2.glPopMatrix();
       
       gl2.glPushMatrix();
       gl2.glTranslated(3,0,-3);
       gl2.glRotated(45,0,1,0);
       gl2.glScaled(1,1,2);
       octagonalPrism(gl2);
       gl2.glPopMatrix();
       
       gl2.glPushMatrix();
       dodecahedron(gl2);
       gl2.glPopMatrix();
       
       
    }
    
    public void dispose(GLAutoDrawable drawable) {
            // called when the panel is being disposed
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
            // called when user resizes the window
    }
    
    private void tetrahedron(GL2 gl2, double size){
        gl2.glPushMatrix(); //Tetrahedron push
        gl2.glScaled(size,size,size);
        gl2.glPushMatrix(); //Triangle push 1
        gl2.glRotated(-90,1,0,0);
        triangle(gl2);
        gl2.glPopMatrix(); //Triangle pop 1
        
        gl2.glPushMatrix(); //Triangle push 2
        gl2.glRotated(-19.47,1,0,0);
        triangle(gl2);
        gl2.glPopMatrix(); //Triangle pop 2
        
        gl2.glPushMatrix(); //Triangle push 3
        gl2.glRotated(60,0,1,0);
        gl2.glRotated(19.47,1,0,0);
        triangle(gl2);
        gl2.glPopMatrix(); //Triangle pop 3
        
        gl2.glPushMatrix(); //Triangle push 4
        gl2.glTranslated(1,0,-Math.sqrt(3));
        gl2.glRotated(-60,0,1,0);
        gl2.glRotated(19.47,1,0,0);
        triangle(gl2);
        gl2.glPopMatrix(); //Triangle pop 4
        
        gl2.glPopMatrix(); //Tetrahedron pop
    }
    
    private void triangle(GL2 gl2){
        //Draw faces
        gl2.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
        gl2.glColor3f(1,0,0);
        gl2.glBegin(GL2.GL_TRIANGLES);
        gl2.glVertex3d(1,Math.sqrt(3),0);
        gl2.glVertex3d(2,0,0);
        gl2.glVertex3d(0,0,0);
        gl2.glEnd();
        //Draw edges
        gl2.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
        gl2.glColor3f(0,0,1);
        gl2.glBegin(GL2.GL_LINE_LOOP);
        gl2.glVertex3d(1,Math.sqrt(3),0);
        gl2.glVertex3d(2,0,0);
        gl2.glVertex3d(0,0,0);
        gl2.glEnd();
        
        
    }
    
    private void octagonalPrism(GL2 gl2){
        double[][] octagonalPrismVertexList = {{-.5,1.5,0}, {.5,1.5,0}, {1.5, .5,0}, {1.5,-.5,0}, {.5,-1.5,0}, {-.5,-1.5,0}, {-1.5,-.5,0}, {-1.5,.5,0},{-.5,1.5,-3}, {.5,1.5,-3}, {1.5, .5,-3}, {1.5,-.5,-3}, {.5,-1.5,-3}, {-.5,-1.5,-3}, {-1.5,-.5,-3}, {-1.5,.5,-3}};
        int[][] octagonalPrismFaceList = {{0,1,2,3,4,5,6,7}, {8,9,10,11,12,13,14,15}, {0,1,9,8}, {1,2,10,9}, {2,3,11,10}, {3,4,12,11}, {4,5,13,12}, {5,6,14,13}, {6,7,15,14}, {7,0,8,15}};
        gl2.glPushMatrix();
        for(int i = 0; i < octagonalPrismFaceList.length; i++){
            //Draw faces
            gl2.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
            gl2.glColor3f(1,0,0);
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for(int j = 0; j < octagonalPrismFaceList[i].length; j++){
                int currentVertex = octagonalPrismFaceList[i][j];
                gl2.glVertex3dv(octagonalPrismVertexList[currentVertex],0);
            }
            gl2.glEnd();
            //Draw edges
            gl2.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
            gl2.glColor3f(0,0,1);
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for(int j = 0; j < octagonalPrismFaceList[i].length; j++){
                int currentVertex = octagonalPrismFaceList[i][j];
                
                gl2.glVertex3dv(octagonalPrismVertexList[currentVertex],0);
                
            }
            gl2.glEnd();
            
        }
        gl2.glPopMatrix();
    }
    
    private void pyramid(GL2 gl2){
        double[][] pyramidVertexList = {{0,1,0}, {.5,0,-.5}, {-.5,0,-.5}, {.5,0,.5}, {-.5,0,.5}};
        int[][] pyramidFaceList = {{1,2,4,3}, {0,1,2}, {0,3,2}, {0,4,3}, {0,2,4}};
        
        gl2.glPushMatrix();
        for(int i = 0; i < pyramidFaceList.length; i++){
            //Draw faces
            gl2.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
            gl2.glColor3f(1,0,0);
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for(int j = 0; j < pyramidFaceList[i].length; j++){
                int currentVertex = pyramidFaceList[i][j];
                gl2.glVertex3dv(pyramidVertexList[currentVertex],0);
            }
            gl2.glEnd();
            //Draw edges
            gl2.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
            gl2.glColor3f(0,0,1);
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for(int j = 0; j < pyramidFaceList[i].length; j++){
                int currentVertex = pyramidFaceList[i][j];
                
                gl2.glVertex3dv(pyramidVertexList[currentVertex],0);
                
            }
            gl2.glEnd();
            
        }
        gl2.glPopMatrix();
    }
    private void octahedron(GL2 gl2){
        gl2.glPushMatrix();
        gl2.glPushMatrix();
        gl2.glRotated(180,1,0,0);
        pyramid(gl2);
        gl2.glPopMatrix();
        pyramid(gl2);
        gl2.glPopMatrix();
    }
    
    private void cuboid(GL2 gl2, double width, double height, double length){
        double[][] cuboidVertexList = {{.5,.5,.5}, {-.5,.5,.5}, {-.5,-.5,.5}, {.5,-.5,.5}, {-.5,.5,-.5}, {.5,.5,-.5}, {.5,-.5,-.5}, {-.5,-.5,-.5}};
        int[][] cuboidFaceList = {{0,1,2,3}, {4,5,6,7}, {5,4,1,0}, {3,2,7,6}, {5,0,3,6}, {1,4,7,2}};
        gl2.glPushMatrix();
        gl2.glScaled(width,height,length);
        for(int i = 0; i < cuboidFaceList.length; i++){
            //Draw faces
            gl2.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
            gl2.glColor3f(1,0,0);
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            for(int j = 0; j < cuboidFaceList[i].length; j++){
                int currentVertex = cuboidFaceList[i][j];
                gl2.glVertex3dv(cuboidVertexList[currentVertex],0);
            }
            gl2.glEnd();
            //Draw edges
            gl2.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
            gl2.glColor3f(0,0,1);
            gl2.glBegin(GL2.GL_LINE_LOOP);
            for(int j = 0; j < cuboidFaceList[i].length; j++){
                int currentVertex = cuboidFaceList[i][j];
                
                gl2.glVertex3dv(cuboidVertexList[currentVertex],0);
                
            }
            gl2.glEnd();
        }
        gl2.glPopMatrix();
    }
    
    private void tetrahemihexacron(GL2 gl2){
        gl2.glPushMatrix();
        cuboid(gl2,1,1,3);
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        cuboid(gl2,1,3,1);
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        cuboid(gl2,3,1,1);
        gl2.glPopMatrix();
    }
    
    private void pentagon(GL2 gl2){
        gl2.glPushMatrix();
        
        //Draw faces
        gl2.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
        gl2.glColor3f(1,0,0);
        gl2.glBegin(GL2.GL_TRIANGLE_FAN);
        gl2.glVertex3d(0,1,0);
        gl2.glVertex3d(Math.sin((Math.PI*2)/5),Math.cos((Math.PI*2)/5),0);
        gl2.glVertex3d(Math.sin((Math.PI*4)/5),-Math.cos((Math.PI)/5),0);
        gl2.glVertex3d(-Math.sin((Math.PI*4)/5),-Math.cos((Math.PI)/5),0);
        gl2.glVertex3d(-Math.sin((Math.PI*2)/5),Math.cos((Math.PI*2)/5),0);
        gl2.glEnd();
        //Draw edges
        gl2.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
        gl2.glColor3f(0,0,1);
        gl2.glBegin(GL2.GL_LINE_LOOP);
        gl2.glVertex3d(0,1,0);
        gl2.glVertex3d(Math.sin((Math.PI*2)/5),Math.cos((Math.PI*2)/5),0);
        gl2.glVertex3d(Math.sin((Math.PI*4)/5),-Math.cos((Math.PI)/5),0);
        gl2.glVertex3d(-Math.sin((Math.PI*4)/5),-Math.cos((Math.PI)/5),0);
        gl2.glVertex3d(-Math.sin((Math.PI*2)/5),Math.cos((Math.PI*2)/5),0);
        gl2.glEnd();
        gl2.glPopMatrix();
    }
    
    private void halfDodecahedron(GL2 gl2){
        gl2.glTranslated(0,-2*Math.sin((Math.PI*4)/5)*1.113516364,0);
        gl2.glPushMatrix();
        gl2.glRotated(90,1,0,0);
        pentagon(gl2);
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glTranslated(0,0,-Math.cos((Math.PI)/5));
        gl2.glRotated(-26.57,1,0,0);
        gl2.glTranslated(0,Math.cos((Math.PI)/5),0);
        pentagon(gl2);
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glRotated(72,0,1,0);
        gl2.glTranslated(0,0,-Math.cos((Math.PI)/5));
        gl2.glRotated(-26.57,1,0,0);
        gl2.glTranslated(0,Math.cos((Math.PI)/5),0);
        pentagon(gl2);
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glRotated(-72,0,1,0);
        gl2.glTranslated(0,0,-Math.cos((Math.PI)/5));
        gl2.glRotated(-26.57,1,0,0);
        gl2.glTranslated(0,Math.cos((Math.PI)/5),0);
        pentagon(gl2);
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glRotated(144,0,1,0);
        gl2.glTranslated(0,0,-Math.cos((Math.PI)/5));
        gl2.glRotated(-26.57,1,0,0);
        gl2.glTranslated(0,Math.cos((Math.PI)/5),0);
        pentagon(gl2);
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glRotated(-144,0,1,0);
        gl2.glTranslated(0,0,-Math.cos((Math.PI)/5));
        gl2.glRotated(-26.57,1,0,0);
        gl2.glTranslated(0,Math.cos((Math.PI)/5),0);
        pentagon(gl2);
        gl2.glPopMatrix();
    }
    
    public void dodecahedron(GL2 gl2){
        
        gl2.glPushMatrix();
        halfDodecahedron(gl2);
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glRotated(180,1,0,0);
        halfDodecahedron(gl2);
        gl2.glPopMatrix();
    }
}