package learning;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class DragAndDrop extends JFrame
{
    WATCanvas canvas;

    public DragAndDrop()
    {
        super();
        Container container = getContentPane();
        setBackground(Color.WHITE);
        canvas = new WATCanvas(800,600,45,40);
        container.add(canvas);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        container.add(panel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });
            setSize(1200,1000);
            setVisible(true);

    }

    class WATCanvas extends JPanel
    {
        Cursor curSP, curCSF, curCDF, curUB, curPP, curMod, curKD, cur;
        int buttonLeftBorder = 900;
        int buttonTopBorder = 20;
        double spX=buttonLeftBorder, spY=buttonTopBorder+10, spW=20, spH=20;//singlePallet coordinates
        double csFX=buttonLeftBorder, csFY=buttonTopBorder+90, csFW=80, csFH=40;//caseFlow coordinates
        double cdFX=buttonLeftBorder, cdFY=buttonTopBorder+150, cdFW=40, cdFH=40;//caddyFlow coordinates
        double ubX=buttonLeftBorder, ubY=buttonTopBorder+210, ubW=80, ubH=20;//unloadBulk coordinates
        double ppX=buttonLeftBorder, ppY=buttonTopBorder+250, ppW=80, ppH=20;//prePick coordinates
        double modX=buttonLeftBorder, modY=buttonTopBorder+290, modW=40, modH=40;//module coordinates
        double kdX=buttonLeftBorder, kdY=buttonTopBorder+350, kdW=20, kdH=20;//kd coordinates
        int x1, y1, x2, y2;
        int width, height, rows, cols;

        Rectangle2D singlePallet;
        Rectangle2D caseFlow;
        Rectangle2D caddyFlow;
        Rectangle2D unloadBulk;
        Rectangle2D prePick;
        Rectangle2D module;
        Rectangle2D kd;
        Rectangle2D selectedShape;
        Rectangle2D boundingRec;

        public WATCanvas(int w, int h, int r, int c)
        {
            setBackground(Color.white);
            setSize(width = w, height = h);
            rows = r;
            cols = c;
            addMouseListener(new MyMouseListener());
            addMouseMotionListener(new MyMouseMotionListener());
            setTitle("Warehouse Allocation Model");
        }

        public void paint(Graphics g)
        {
            Font insideDrawing = new Font("TimesRoman",Font.PLAIN, 9);
            Font boldDescription = new Font("TimesRoman", Font.BOLD, 10);

            Graphics2D g2 = (Graphics2D) g;

            //single pallet location
            singlePallet = new Rectangle2D.Double(spX,spY,spW,spW);
            g2.draw(singlePallet);
            g2.setFont(insideDrawing);
            //g2.drawString("xxx", buttonLeftBorder+05, buttonTopBorder+15);
            g2.setFont(boldDescription);
            //g2.drawString("Single Pallet Lane", buttonLeftBorder+25, buttonTopBorder+20);

            //Multiple Pallets
            g2.setFont(insideDrawing);
            //g2.drawString("xxx",buttonLeftBorder+05,buttonTopBorder+55);
            g2.drawRect(buttonLeftBorder, buttonTopBorder+50, 20, 20);
            g2.drawRect(buttonLeftBorder+20, buttonTopBorder+50, 20, 20);
            g2.drawRect(buttonLeftBorder+40, buttonTopBorder+50, 20, 20);
            g2.drawRect(buttonLeftBorder+60, buttonTopBorder+50, 20, 20);
            g2.setFont(boldDescription);
            //g2.drawString("Flow Lane (4-deep example)", buttonLeftBorder+85, buttonTopBorder+60);

            //Case Flow
            caseFlow = new Rectangle2D.Double(csFX,csFY,csFW,csFH);
            g2.setFont(insideDrawing);
            //g2.drawString("Case Flow", buttonLeftBorder+20, buttonTopBorder+115);
            g2.draw(caseFlow);
            g2.setFont(boldDescription);
            //g2.drawString("Standard Case Flow Rack", buttonLeftBorder+85, buttonTopBorder+115);

            //Caddy Flow

            caddyFlow = new Rectangle2D.Double(cdFX,cdFY,cdFW,cdFH);
            g2.setFont(insideDrawing);
            //g2.drawString("Caddy",buttonLeftBorder+10, buttonTopBorder+170);
            //g2.drawString("Flow",buttonLeftBorder+12,buttonTopBorder+180);
            g2.draw(caddyFlow);
            g2.setFont(boldDescription);
            //g2.drawString("Standard Caddy Flow Rack", buttonLeftBorder+45, buttonTopBorder+175);

            //Unload Bulk Door

            unloadBulk = new Rectangle2D.Double(ubX,ubY,ubW,ubH);
            g2.setFont(insideDrawing);
            g2.setColor(Color.BLACK);
            g2.fill(unloadBulk);
            g2.setColor(Color.WHITE);
            //g2.drawString("Unload/Bulk Door", buttonLeftBorder+3, buttonTopBorder+223);
            g2.setColor(Color.BLACK);
            g2.setFont(boldDescription);
            //g2.drawString("Unload/Bulk Door", buttonLeftBorder+85, buttonTopBorder+223);

            //Pre-Pick Trad

            prePick = new Rectangle2D.Double(ppX,ppY,ppW,ppH);
            g2.setFont(insideDrawing);
            g2.fill(prePick);
            g2.setColor(Color.WHITE);
            //g2.drawString("PP/Trad",buttonLeftBorder+20, buttonTopBorder+263);
            g2.setFont(boldDescription);
            g2.setColor(Color.BLACK);
            //g2.drawString("Pre-Pick Module", buttonLeftBorder+85, buttonTopBorder+263);

            //Module with spotted pattern

            module = new Rectangle2D.Double(modX,modY,modW,modH);
            g2.setFont(insideDrawing);
            BufferedImage bi = new BufferedImage(5,5,BufferedImage.TYPE_INT_RGB);
            Graphics2D big = bi.createGraphics();
            big.setColor(Color.WHITE);
            big.fillRect(0, 0, 5, 5);
            big.setColor(Color.LIGHT_GRAY);
            big.fillOval(2, 2, 2, 2);

            TexturePaint tp = new TexturePaint(bi, new Rectangle(5,5));

            g2.setPaint(tp);
            g2.fill(module);
            g2.setFont(boldDescription);
            g2.setColor(Color.BLACK);
            //g2.drawString("Module", buttonLeftBorder+04, buttonTopBorder+310);
            g2.draw(module);
            g2.setFont(boldDescription);
            //g2.drawString("Standard Caddy Flow Rack", buttonLeftBorder+45, buttonTopBorder+310);

            //KD with spotted pattern

            kd = new Rectangle2D.Double(kdX,kdY,kdW,kdH);
            BufferedImage bi2 = new BufferedImage(5,5,BufferedImage.TYPE_INT_RGB);
            Graphics2D big2 = bi2.createGraphics();
            big2.setColor(Color.DARK_GRAY);
            big2.fillRect(0, 0, 5, 5);
            big2.setColor(Color.BLACK);
            big2.fillOval(2, 2, 2, 2);

            TexturePaint tp2 = new TexturePaint(bi2, new Rectangle(5,5));

            g2.setPaint(tp2);
            g2.fill(kd);
            g2.setFont(boldDescription);
            g2.setColor(Color.BLACK);
            //g2.drawString("KD", buttonLeftBorder+4, buttonTopBorder+365);
            g2.draw(kd);
            g.setFont(boldDescription);
            //g.drawString("KD Stands", buttonLeftBorder+25, buttonTopBorder+365);

            width = 900;
            height = 800;
            int start = 25;

            int rowHt = 20;
            for(int j=0;j<=rows;++j)
                g.drawLine(start, start + j*rowHt, height+start,start + j*rowHt);

            int rowWid = 20;
            for(int k=0;k<=cols;++k)
                g.drawLine(start+k*rowWid, start, start + k*rowWid, width+start);

            if (cur != null)
                setCursor(cur);
        }

        class MyMouseListener extends MouseAdapter
        {
            public void mousePressed(MouseEvent e)
            {
                if (singlePallet.contains(e.getX(), e.getY()))
                {
                    selectedShape = singlePallet;
                    if (boundingRec != null)
                        boundingRec = singlePallet.getBounds2D();
                }
                else if (caseFlow.contains(e.getX(), e.getY()))
                {
                    selectedShape = caseFlow;
                    if (boundingRec != null)
                        boundingRec = caseFlow.getBounds2D();
                }
                else if (caddyFlow.contains(e.getX(), e.getY()))
                {
                    selectedShape = caddyFlow;
                    if (boundingRec != null)
                        boundingRec = caddyFlow.getBounds2D();
                }
                else if (unloadBulk.contains(e.getX(), e.getY()))
                {
                    selectedShape = unloadBulk;
                    if (boundingRec != null)
                        boundingRec = unloadBulk.getBounds2D();
                }
                else if (prePick.contains(e.getX(), e.getY()))
                {
                    selectedShape = prePick;
                    if (boundingRec != null)
                        boundingRec = prePick.getBounds2D();
                }
                else if (module.contains(e.getX(), e.getY()))
                {
                    selectedShape = module;
                    if (boundingRec != null)
                        boundingRec = module.getBounds2D();
                }
                else if (kd.contains(e.getX(), e.getY()))
                {
                    selectedShape = kd;
                    if (boundingRec != null)
                        boundingRec = kd.getBounds2D();
                }
                else
                {
                    boundingRec = null;
                }

                canvas.repaint();
                x1 = e.getX();
                y1 = e.getY();
            }

            public void mouseReleased(MouseEvent e)
            {
                if (singlePallet.contains(e.getX(), e.getY()))
                {
                    boundingRec = singlePallet.getBounds2D();
                    selectedShape = singlePallet;
                }
                else if (caseFlow.contains(e.getX(), e.getY()))
                {
                    boundingRec = caseFlow.getBounds2D();
                    selectedShape = caseFlow;
                }
                else if (caddyFlow.contains(e.getX(), e.getY()))
                {
                    boundingRec = caddyFlow.getBounds2D();
                    selectedShape = caddyFlow;
                }
                else if (unloadBulk.contains(e.getX(), e.getY()))
                {
                    boundingRec = unloadBulk.getBounds2D();
                    selectedShape = unloadBulk;
                }
                else if (prePick.contains(e.getX(), e.getY()))
                {
                    boundingRec = prePick.getBounds2D();
                    selectedShape = prePick;
                }
                else if (module.contains(e.getX(), e.getY()))
                {
                    boundingRec = module.getBounds2D();
                    selectedShape = module;
                }
                else if (kd.contains(e.getX(), e.getY()))
                {
                    boundingRec = kd.getBounds2D();
                    selectedShape = kd;
                }

                //canvas.repaint();
            }

            public void mouseClicked(MouseEvent e)
            {
                if (singlePallet.contains(e.getX(), e.getY()))
                {
                    selectedShape = singlePallet;
                    boundingRec = singlePallet.getBounds2D();
                }
                else if (caseFlow.contains(e.getX(), e.getY()))
                {
                    selectedShape = caseFlow;
                    boundingRec = caseFlow.getBounds2D();
                }
                else if (caddyFlow.contains(e.getX(), e.getY()))
                {
                    selectedShape = caddyFlow;
                    boundingRec = caddyFlow.getBounds2D();
                }
                else if (unloadBulk.contains(e.getX(), e.getY()))
                {
                    selectedShape = unloadBulk;
                    boundingRec = unloadBulk.getBounds2D();
                }
                else if (prePick.contains(e.getX(), e.getY()))
                {
                    selectedShape = prePick;
                    boundingRec = prePick.getBounds2D();
                }
                else if (module.contains(e.getX(), e.getY()))
                {
                    selectedShape = module;
                    boundingRec = module.getBounds2D();
                }
                else if (kd.contains(e.getX(), e.getY()))
                {
                    selectedShape = kd;
                    boundingRec = kd.getBounds2D();
                }
                else
                {
                    if (boundingRec != null)
                        boundingRec = null;
                }
                canvas.repaint();
            }
        }

        class MyMouseMotionListener extends MouseMotionAdapter
        {
            public void mouseDragged(MouseEvent e) {
                if (singlePallet.contains(e.getX(), e.getY()))
                {
                    boundingRec = null;
                    selectedShape = singlePallet;
                    x2 = e.getX();
                    y2 = e.getY();
                    spX = spX + x2 - x1;
                    spY = spY + y2 - y1;
                    x1 = x2;
                    y1 = y2;       
                }
                else if (caseFlow.contains(e.getX(), e.getY()))
                {
                    boundingRec = null;
                    selectedShape = caseFlow;
                    x2 = e.getX();
                    y2 = e.getY();
                    csFX = csFX + x2 - x1;
                    csFY = csFY + y2 - y1;
                    x1 = x2;
                    y1 = y2;

                }
                else if (caddyFlow.contains(e.getX(), e.getY()))
                {
                    boundingRec = null;
                    selectedShape = caseFlow;
                    x2 = e.getX();
                    y2 = e.getY();
                    cdFX = cdFX + x2 - x1;
                    cdFY = cdFY + y2 - y1;
                    x1 = x2;
                    y1 = y2;

                }
                else if (unloadBulk.contains(e.getX(), e.getY()))
                {
                    boundingRec = null;
                    selectedShape = caseFlow;
                    x2 = e.getX();
                    y2 = e.getY();
                    ubX = ubX + x2 - x1;
                    ubY = ubY + y2 - y1;
                    x1 = x2;
                    y1 = y2;

                }
                else if (prePick.contains(e.getX(), e.getY()))
                {
                    boundingRec = null;
                    selectedShape = caseFlow;
                    x2 = e.getX();
                    y2 = e.getY();
                    ppX = ppX + x2 - x1;
                    ppY = ppY + y2 - y1;
                    x1 = x2;
                    y1 = y2;

                }
                else if (module.contains(e.getX(), e.getY()))
                {
                    boundingRec = null;
                    selectedShape = caseFlow;
                    x2 = e.getX();
                    y2 = e.getY();
                    modX = modX + x2 - x1;
                    modY = modY + y2 - y1;
                    x1 = x2;
                    y1 = y2;

                }
                else if (kd.contains(e.getX(), e.getY()))
                {
                    boundingRec = null;
                    selectedShape = caseFlow;
                    x2 = e.getX();
                    y2 = e.getY();
                    kdX = kdX + x2 - x1;
                    kdY = kdY + y2 - y1;
                    x1 = x2;
                    y1 = y2;

                }
                canvas.repaint();
      }

            public void mouseMoved(MouseEvent e)
            {
                if(singlePallet != null && caseFlow != null && caddyFlow != null && unloadBulk != null
                        && prePick != null && module != null && kd != null)
                {
                    if (singlePallet.contains(e.getX(), e.getY()))
                    {
                        cur = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                    }

                    else if (caseFlow.contains(e.getX(), e.getY()))
                    {
                        cur = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                    }

                    else if (caddyFlow.contains(e.getX(), e.getY()))
                    {
                        cur = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                    }

                    else if (unloadBulk.contains(e.getX(), e.getY()))
                    {
                        cur = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                    }

                    else if (prePick.contains(e.getX(), e.getY()))
                    {
                        cur = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                    }

                    else if (module.contains(e.getX(), e.getY()))
                    {
                        cur = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                    }

                    else if (kd.contains(e.getX(), e.getY()))
                    {
                        cur = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                    }

                    else
                    {
                        cur = Cursor.getDefaultCursor();                   
                    }
            }
                canvas.repaint();
            }
        }

}
        public static void main(String arg[])
        {
            new DragAndDrop();
        }

}