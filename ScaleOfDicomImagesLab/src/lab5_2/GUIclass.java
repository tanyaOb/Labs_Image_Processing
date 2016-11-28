package lab5_2;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import ij.ImagePlus;
import ij.plugin.DICOM;

public class GUIclass extends GLCanvas{
	int imageNumber1=1;
	int imageNumber2=100;
	int imageNumber3=100; 
	float coeficient1 = 1.0f;
	float coeficient2 = 1.0f;
	float coeficient3 = 1.0f;
	int valueInSm1 = 0;
	int valueInsm2 = 0;
	int valueInSm3 = 0;
	static GLCanvas canvas2 = new GLCanvas();
	static GLCanvas canvas3 = new GLCanvas();
	static JTextArea text = new JTextArea("Some output text... ");
	static GLCanvas canvas1 = new GLCanvas();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static DICOM image = new DICOM();

	GUIclass()
	{	
		super();
		image.open("images\\brain_001.dcm");
		JFrame frame=new JFrame("Dicom images");
		frame.setSize(530, 700);
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		JPanel panel_new = new JPanel();
		panel_new.setSize(image.getWidth()*3, image.getHeight()*2);
		panel_new.setLayout(new GridLayout(2,2));
		final JPanel panel1 = new JPanel();
		final JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		final JSlider slider1 = new JSlider(0, 9, 1);
		JButton buttonPanel1 = new JButton("Next");
		final JSlider slider2 = new JSlider(0, 9, 1);
		JButton buttonPanel2 = new JButton("Next");
		final JSlider slider3 = new JSlider(0, 9, 1);
		JButton buttonPanel3 = new JButton("Next");
		panel1.setLayout(new BorderLayout()); 
		panel2.setLayout(new BorderLayout()); 
		panel3.setLayout(new BorderLayout()); 
		panel4.setLayout(new BorderLayout()); 
		panel1.setSize(image.getWidth(), image.getHeight() + buttonPanel1.getHeight());
		System.out.println("The hight of button " + buttonPanel1.getHeight());
		panel1.setBounds(0, 0, image.getWidth(), image.getHeight());
		panel2.setSize(image.getWidth(), image.getHeight());
		panel2.setBounds(image.getWidth(), 0, image.getWidth(), image.getHeight());
		panel1.setBackground(Color.RED);
		panel2.setBackground(Color.GREEN);
		panel3.setSize(image.getWidth(), image.getHeight());
		panel1.setBounds(image.getWidth(), image.getHeight(), image.getWidth(), image.getHeight());
		panel4.setSize(image.getWidth(), image.getHeight());
		panel1.setBounds(image.getWidth()*2, image.getHeight(), image.getWidth(), image.getHeight());
		panel3.setBackground(Color.BLUE);
		panel4.setBackground(Color.YELLOW);
		panel_new.add(BorderLayout.SOUTH, panel1);
		panel_new.add(BorderLayout.SOUTH, panel2);
		panel_new.add(BorderLayout.NORTH, panel3);
		panel_new.add(BorderLayout.NORTH, panel4);
		frame.add(BorderLayout.EAST, panel_new);
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
	    GLCapabilities capabilities = new GLCapabilities(profile);
	      // The canvas 
	    final GLCanvas glcanvas = new GLCanvas(capabilities);
	    canvas1 = new GLCanvas(capabilities);
		canvas2 = new GLCanvas(capabilities);
		canvas3 = new GLCanvas(capabilities);
		
		ActionListener actionListener = new TestActionListener();
		buttonPanel2.addActionListener(actionListener);
	    buttonPanel3.addActionListener(actionListener);
	    
		 slider1.addChangeListener(new ChangeListener() {
	          
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					 int value = ((JSlider)e.getSource()).getValue();
					 System.out.println("Slider1 value " + value);
					 valueInSm1 = value;
					 String cont = "1." + value;
					 float value_new = Float.parseFloat(cont);
					 setCoordinate1(value_new);
					 coeficient1=value_new;
					
				}
	        });
		 
		 slider2.addChangeListener(new ChangeListener() {
	          
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					 int value = ((JSlider)e.getSource()).getValue();
					 System.out.println("Slider2 value " + value);
					 valueInsm2 = value;
					 String cont = "1." + value;
					 float value_new = Float.parseFloat(cont);
					 setCoordinate2(value_new);
					 coeficient2=value_new;
				}
	        });
		 
		 slider3.addChangeListener(new ChangeListener() {
	          
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					 int value = ((JSlider)e.getSource()).getValue();
					 System.out.println("Slider3 value " + value);
					 valueInSm3 = value;
					 String cont = "1." + value;
					 float value_new = Float.parseFloat(cont);
					 setCoordinate3(value_new);
					 coeficient3=value_new;
				}
	        });
		
        lines l1_window = new lines();
        l1_window.setImageNumber(imageNumber1);
		l1_window.setWindow(1);
		l1_window.run();
		canvas1.addGLEventListener(l1_window);
        canvas1.setSize(new Dimension(image.getWidth(), image.getHeight()));
        panel1.add(BorderLayout.NORTH, slider1);
        panel1.add(BorderLayout.SOUTH, buttonPanel1);
        panel1.add(canvas1);
		buttonPanel1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	imageNumber1++;
           	    if (imageNumber1>=21)
           	    {
           	    	imageNumber1=1;
           	    }
           	     lines l = new lines(); 
                 l.setImageNumber(imageNumber1);
                 l.setCoeficient1(coeficient1);
                 l.setWindow(1);
                 //l.run();
                 canvas1.addGLEventListener(l);
                 canvas1.display();
            }
       });
		lines l2_window = new lines();
		l2_window.setWindow(2);
		l2_window.run();
		canvas2.addGLEventListener(l2_window);
        canvas2.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        panel2.add(BorderLayout.NORTH, slider2);
        panel2.add(BorderLayout.SOUTH, buttonPanel2);
        panel2.add(canvas2);
        buttonPanel2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	imageNumber2++;
           	    if (imageNumber2>=255)
           	    {
           	    	imageNumber2=100;
           	    }
           	    
           	 lines l = new lines(); 
             l.setImageNumber2(imageNumber2);
             canvas2.addGLEventListener(l);
             l.setCoeficient2(coeficient2);
             l.setWindow(2);
             l.run();
             canvas2.display();

            }
       });
        
        lines l3 = new lines();
        l3.setWindow(3);
        l3.run();
        canvas3.addGLEventListener(l3);
        canvas3.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        panel3.add(BorderLayout.NORTH, slider3);
        panel3.add(BorderLayout.SOUTH, buttonPanel3);
        panel3.add(canvas3);
        canvas3.display();
        
        buttonPanel3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	imageNumber3++;
           	    if (imageNumber3>=255)
           	    {
           	    	imageNumber3=100;
           	    }
           	    
           	 lines l = new lines(); 
             l.setImageNumber3(imageNumber3);
             canvas3.addGLEventListener(l);
             l.setCoeficient3(coeficient3);
             l.setWindow(3);
             l.run();
             canvas3.display();

            }
       });

        panel4.add(text);
        frame.setVisible(true);
	}
	
	
	 public class TestActionListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
              System.out.println("The button was pressed!");
         }
	 }
	 
	 public void setCoordinate1(float value)
	 {
		lines l = new lines(); 
		canvas1.addGLEventListener(l);
		l.setImageNumber(imageNumber1);
		l.setCoeficient1(value);
     	l.setWindow(1);
     	l.run();
     	canvas1.display();
     	text.setText("Coefficient in first window is " + value + "\n");
     	text.append("Scale in centimeters is " + (Imageformation.pixelSpacing*256/10-valueInSm1));
	 }
	 public void setCoordinate2(float value)
	 {
		 lines l = new lines(); 
		 canvas2.addGLEventListener(l);
		 l.setImageNumber2(imageNumber2);
		 l.setCoeficient2(value);
	     l.setWindow(2);
	     l.run();
	     canvas2.display();
	     text.setText("Coefficient in second window is " + value + "\n");
	     text.append("Scale in centimeters is " + (Imageformation.pixelSpacing*256/10-valueInsm2));
	 }
	 public void setCoordinate3(float value)
	 {
		 lines l = new lines(); 
		 canvas3.addGLEventListener(l);
		 l.setImageNumber3(imageNumber3);
		 l.setCoeficient3(value);
	     l.setWindow(3);
	     l.run();
	     canvas3.display();
	     text.setText("Coefficient in third window is " + value + "\n");
	     text.append("Scale in centimeters is " + (Imageformation.pixelSpacing*256/10-valueInSm3));
	 }
	

}
