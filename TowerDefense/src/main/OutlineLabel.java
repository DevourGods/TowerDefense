package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JLabel;

public class OutlineLabel extends JLabel 
{
	private static final long serialVersionUID = 1L;
	private Color outlineColor;
    private boolean isPaintingOutline = false;
    private boolean forceTransparent = false;

    public OutlineLabel()
    {
        super();
    }

    public OutlineLabel(String text, boolean isCenter, Color outlineColor)
    {
        super(text);
        this.outlineColor = outlineColor;
        if (isCenter)
        {
        	setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    public Color getOutlineColor()
    {
        return outlineColor;
    }

    public void setOutlineColor(Color outlineColor)
    {
        this.outlineColor = outlineColor;
        this.invalidate();
    }

    @Override
    public Color getForeground() 
    {
        if ( isPaintingOutline ) 
        {
            return outlineColor;
        }
        else
        {
            return super.getForeground();
        }
    }

    @Override
    public boolean isOpaque()
    {
        if ( forceTransparent )
        {
            return false;
        }
        else
        {
            return super.isOpaque();
        }
    }

    @Override
    public void paint(Graphics g)
    {
        String text = getText();
        if ( text == null || text.length() == 0 )
        {
            super.paint(g);
            return;
        }

        // 1 2 3
        // 8 9 4
        // 7 6 5

        if ( isOpaque() )
        {
        	super.paint(g);
        }
        
        forceTransparent = true;
        isPaintingOutline = true;
        g.translate(-1, -1); super.paint(g); // 1 
        g.translate( 1,  0); super.paint(g); // 2 
        g.translate( 1,  0); super.paint(g); // 3 
        g.translate( 0,  1); super.paint(g); // 4
        g.translate( 0,  1); super.paint(g); // 5
        g.translate(-1,  0); super.paint(g); // 6
        g.translate(-1,  0); super.paint(g); // 7
        g.translate( 0, -1); super.paint(g); // 8
        g.translate( 1,  0); // 9
        isPaintingOutline = false;

        super.paint(g);
        forceTransparent = false;
    }
}    