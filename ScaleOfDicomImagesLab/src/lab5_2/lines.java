package lab5_2;


import javax.swing.*;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class lines
         extends JFrame implements GLEventListener 
{
    static Texture currentTexture = null;
    int number=0;
    int imageNumberCanvas1=1;
    int imageNumberCanvas2=50;
    int imageNumberCanvas3=1;
	 private static final long serialVersionUID = 1L;
	 static int x1=0;
	static int y1 = 100;
	 static int x2 =0;
	static int y2 = -100;
	static int new_x1= -10;
	static int new_y1 = 0;
	 static int new_x2 = 0;
	static int new_y2 = 0;
	static int new_x1_1 = -5;
	static int new_y1_1 = 0;
	 static int new_x2_1 = 0;
	static int new_y2_1 = 0;
	static GL2 gl = null;
	public static int window = 0;
	float coeficient1 = 1;
	float coeficient2 = 1;
	float coeficient3 = 1;
	
    public void setWindow(int canvasWindow)
    {
    	window = canvasWindow;
    }
    public void setCoeficient1(float value)
    {
    	coeficient1 = value;
    	System.out.println("Coeficient is " + coeficient1);
    }

    public void setCoeficient2(float value)
    {
    	coeficient2 = value;
    }
    public void setCoeficient3(float value)
    {
    	coeficient3 = value;
    }

	public void run()
	{
		lines l = new lines();
		System.out.println("The number of window is " + window);
	}
	
    public void setImageNumber(int imageN)
    {
    
    	imageNumberCanvas1=imageN;
    	System.out.println("the number is " + imageNumberCanvas1);
    }
    
    public void setImageNumber2(int imageN)
    {
    	imageNumberCanvas2=imageN;
    	System.out.println("the number of second window image is " + imageNumberCanvas2);
    }
    
    public void setImageNumber3(int imageN)
    {
    	imageNumberCanvas3=imageN;
    	System.out.println("the number sagital " + imageNumberCanvas3);
    }
    
	 public static void drawLineWithCoherence4(GL2 gl, int x1, int x2, int y1, int y2) 
	 {
		  gl.glLineWidth(3f);
          gl.glBegin(GL2.GL_LINES);
          gl.glLineWidth(10f);
          gl.glVertex2i(x1, y1);
          gl.glVertex2i(x2, y2);
	      gl.glEnd();
	      gl.glFlush();
	 }

	 @Override
	 public void display(GLAutoDrawable drawable)
	 {
          gl = drawable.getGL().getGL2();
          gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
          gl.glMatrixMode(GL2.GL_PROJECTION);
          gl.glLoadIdentity();
          GLU glu = new GLU();
          gl.glOrtho(-128.0, 128.0, -128.0, 128.0, -128.0, 128.0);
          //glu.gluOrtho2D( -halfImageWidth, halfImageWidth, -halfImageHeight, halfImageHeight);
          gl.glMatrixMode(GL2.GL_MODELVIEW);
          gl.glLoadIdentity();
          gl.glPushMatrix();
          switch(window)
          {
          case 1: gl.glScalef(coeficient1, coeficient1, 1.0f);
          break;
          case 2: gl.glScalef(coeficient2, coeficient2, 1.0f);
          break;
          case 3: gl.glScalef(coeficient3, coeficient3, 1.0f);
          break;
          case 0: System.out.println("The window is  0 ");
          }  
          gl.glEnable(GL2.GL_TEXTURE_2D);
          currentTexture.bind(gl);
          gl.glBegin(GL2.GL_POLYGON);
          gl.glTexCoord2i(0, 1);
          gl.glVertex2i(-128, 128);
          gl.glTexCoord2i(0, 0);
          gl.glVertex2i(-128, -128);
          gl.glTexCoord2i(1, 0);
          gl.glVertex2i(128, -128);
          gl.glTexCoord2i(1, 1);
          gl.glVertex2i(128, 128);
          gl.glEnd();  
          gl.glFlush();
          drawLineWithCoherence4(gl, x1, x2, y1, y2);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1, new_y2);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1 -80 , new_y2 -80);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1 -60 , new_y2 -60);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1 -40 , new_y2 -40);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1 -20 , new_y2 -20);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1 +20 , new_y2 +20);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1 +40, new_y2 + 40);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1 +60 , new_y2 +60);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1 +80 , new_y2 +80);
          drawLineWithCoherence4(gl, new_x1, new_x2, new_y1, new_y2);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1, new_y2_1);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1 + 10, new_y2_1 +10);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1 + 30, new_y2_1 +30);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1 + 50, new_y2_1 +50);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1 + 70, new_y2_1 +70);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1 - 10, new_y2_1 -10);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1 - 30, new_y2_1 -30);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1 - 50, new_y2_1 -50);
          drawLineWithCoherence4(gl, new_x1_1, new_x2_1, new_y1_1 - 70, new_y2_1 -70);
          
	 }

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable glAutoDrawable) {
		// TODO Auto-generated method stub	
	    gl = glAutoDrawable.getGL().getGL2();	    
	    switch(window)
        {
           case 1: 
        	   currentTexture = AWTTextureIO.newTexture(glAutoDrawable.getGLProfile(), Imageformation.axialImage(imageNumberCanvas1), false);
        	   break;
           case 2: 
        	   currentTexture = AWTTextureIO.newTexture(glAutoDrawable.getGLProfile(), Imageformation.coronarImage(imageNumberCanvas2), false);
        	   break;
           case 3:
        	   currentTexture = AWTTextureIO.newTexture(glAutoDrawable.getGLProfile(), Imageformation.sagitalImage(imageNumberCanvas3), false);
        	   break;
           case 0:
        	   System.out.println("Zero window");
        	   break;
        }
	}

	@Override
	public void reshape(GLAutoDrawable glAutoDrawable, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		gl = glAutoDrawable.getGL().getGL2();
		gl.glEnable(GL2.GL_LINES);
	    gl.glBegin(GL2.GL_LINES);
	    gl.glVertex2i(0, 256);
	    gl.glVertex2i(0, -256);      
	    gl.glVertex2i(256, 0);
	    gl.glVertex2i(-256, 0);
	    gl.glLineWidth(50);
	    gl.glColor3f(1,1,1);
	    gl.glEnd();
	}
}




