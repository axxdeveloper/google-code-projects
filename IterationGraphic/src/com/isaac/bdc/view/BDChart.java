
package com.isaac.bdc.view;

import com.isaac.bdc.utils.SwingHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author Isaac
 */
public class BDChart extends JComponent {

    private Dimension fixedSize  = new Dimension( 110, 110 );
    private List<Integer> resolvedDays = new ArrayList<Integer>();
    private Integer workDay = new Integer(20);
    private Integer manDay  = new Integer(20);
    
    public BDChart( Dimension fixedSize, Integer workDay, Integer manDay, List<Integer> resolvedDays ) {
        setFixedSize( fixedSize );
        setWorkDay( workDay );
        setManDay( manDay );
        setResolvedDays(resolvedDays);
        addListeners();
    }

    private Point mouseMovePoint = new Point( 0, 0 );
    private void addListeners() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseMovePoint.x = e.getX();
                mouseMovePoint.y = e.getY();
                aimLineColor = Color.PINK;
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseMovePoint.x = e.getX();
                mouseMovePoint.y = e.getY();
                aimLineColor = Color.MAGENTA;
                repaint();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return getFixedSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        paintBg( g2 );
        paintWorkDays( g2 );
        paintXYLines( g2 );
        paintIdealLine( g2 );
        paintResolvedLine( g2 );
        paintMouseMove( g2 );
    }

    private void paintResolvedLine(Graphics2D g2) {
        g2.setColor( Color.RED );
        double xUnit = (double) getOuterRect().width / (double) getWorkDay().intValue();
        double yUnit = (double) getOuterRect().height / (double) getManDay().intValue();
        Point startP = new Point();
        Point endP   = new Point();
        int totalResolvedDays = 0;
        for ( int i = 0; i < getResolvedDays().size(); i++ ) {
            Integer d = getResolvedDays().get(i);
            d = d == null ? new Integer(0) : d;
            if ( i > getWorkDay().intValue() ) {
                g2.drawString("警告 : 已超過開發循環期限.", getOuterRect().x, getOuterRect().y - 10);
                break;
            }
            if ( i == 0 ) {
                startP.x = getOuterRect().x;
                startP.y = getOuterRect().y;
                endP.x = (int) (getOuterRect().x + ( (i + 1) * xUnit ));
                endP.y = (int) (getOuterRect().y + ( (totalResolvedDays + d.intValue()) * yUnit ));
            } else {
                startP.x = (int) (getOuterRect().x + ( i * xUnit ));
                startP.y = (int) (getOuterRect().y + ( totalResolvedDays * yUnit ));
                endP.x = (int) (getOuterRect().x + ( (i + 1) * xUnit ));
                endP.y = (int) (getOuterRect().y + ( (totalResolvedDays + d.intValue()) * yUnit ));
            }
            startP.x = startP.x > getOuterRect().x + getOuterRect().width ? getOuterRect().x + getOuterRect().width : startP.x;
            startP.y = startP.y > getOuterRect().y + getOuterRect().height ? getOuterRect().y + getOuterRect().height : startP.y;
            endP.x = endP.x > getOuterRect().x + getOuterRect().width ? getOuterRect().x + getOuterRect().width : endP.x;
            endP.y = endP.y > getOuterRect().y + getOuterRect().height ? getOuterRect().y + getOuterRect().height : endP.y;

            totalResolvedDays += d.intValue();
            g2.drawLine(startP.x, startP.y, endP.x, endP.y);
        }
    }

    private Color aimLineColor = Color.PINK;
    private void paintMouseMove(Graphics2D g2) {
        if ( !getOuterRect().contains( mouseMovePoint ) ) {
            return;
        }
        g2.setColor( aimLineColor == null ? Color.PINK : aimLineColor );
        g2.drawLine(mouseMovePoint.x, getOuterRect().y, mouseMovePoint.x, getOuterRect().y + getOuterRect().height);
        g2.drawLine(getOuterRect().x, mouseMovePoint.y, getOuterRect().x + getOuterRect().width, mouseMovePoint.y);
    }

    private void paintWorkDays(Graphics2D g2) {
        g2.setColor( new Color(200,255,255) );
        for ( int i = 1; i <= getWorkDay().intValue(); i++ ) {
            double unit = (double) getOuterRect().width / (double) getWorkDay().intValue();
            double sitX = getOuterRect().x + ( i * (unit) );
            g2.drawLine((int)sitX, getOuterRect().y, (int)sitX, getOuterRect().y + getOuterRect().height );
        }
        for ( int i = 1; i <= getManDay().intValue(); i++ ) {
            double unit = (double) getOuterRect().height / (double) getManDay().intValue();
            double sitY = getOuterRect().y + getOuterRect().height - ( i * (unit) );
            g2.drawLine(getOuterRect().x, (int)sitY, getOuterRect().x + getOuterRect().width, (int)sitY );
        }
    }

    private void paintIdealLine(Graphics2D g2) {
        g2.setColor( Color.BLUE );
        int startX = getOuterRect().x;
        int startY = getOuterRect().y;
        int endX = getOuterRect().x + getOuterRect().width;
        int endY = getOuterRect().y + getOuterRect().height;
        g2.drawLine(startX, startY, endX, endY);
    }

    private Rectangle xyLinePoints = null;
    private Rectangle getOuterRect() {
        if ( xyLinePoints == null ) {
            int widthUnit = getFixedSize().width / 10;
            int heightUnit = getFixedSize().height / 10;
            int fixedStartX = widthUnit;
            int fixedStartY = heightUnit;
            int fixedEndX = fixedStartX + widthUnit * 8;
            int fixedEndY = fixedStartY + heightUnit * 8;
            xyLinePoints = new Rectangle( fixedStartX, fixedStartY, fixedEndX - fixedStartX, fixedEndY - fixedStartY );
        }
        return xyLinePoints;
    }

    private void paintXYLines(Graphics2D g2) {
        g2.setColor( Color.BLACK );
        g2.drawLine( getOuterRect().x, getOuterRect().y, getOuterRect().x, getOuterRect().height + getOuterRect().y );
        g2.drawLine( getOuterRect().x, getOuterRect().height + getOuterRect().y, getOuterRect().width + getOuterRect().x, getOuterRect().height + getOuterRect().y );
        g2.drawString( String.valueOf(getWorkDay().intValue()), getOuterRect().x, getOuterRect().y + getOuterRect().height + 15 );
        g2.drawString( "0", getOuterRect().x + getOuterRect().width, getOuterRect().y + getOuterRect().height + 15 );
        g2.drawString( "剩餘的工作天", getOuterRect().width / 2, getOuterRect().y + getOuterRect().height + 15 );
        g2.drawString( String.valueOf(getManDay().intValue()), getOuterRect().x - 15, getOuterRect().y );
        g2.drawString( "0", getOuterRect().x - 15, getOuterRect().y + getOuterRect().height );
        
        Graphics2D anotherG2 = (Graphics2D) g2.create();
        anotherG2.rotate(Math.toRadians(90), getOuterRect().x - 15, getOuterRect().height / 2 );
        anotherG2.drawString( "剩餘的人天", getOuterRect().x - 15, getOuterRect().height / 2 );
    }

    private void paintBg(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fill3DRect(0, 0, fixedSize.width, fixedSize.height, true);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add( new Integer(0) ); // 第 1 天沒解決任何需求
        list.add( new Integer(0) ); // 第 2 天沒解決任何需求
        list.add( new Integer(0) ); // 第 3 天沒解決任何需求
        list.add( new Integer(0) ); // 第 4 天沒解決任何需求
        list.add( new Integer(0) ); // 第 5 天沒解決任何需求
        list.add( new Integer(18) ); // 第 6 天解決 18 人天的需求
        list.add( new Integer(3) ); // 第 7 天解決 3 人天的需求
        list.add( new Integer(0) ); // 第 8 天沒解決任何需求
        list.add( new Integer(0) ); // 第 9 天沒解決任何需求
        list.add( new Integer(0) ); // 第 10 天沒解決任何需求
        list.add( new Integer(0) ); // 第 11 天沒解決任何需求
        list.add( new Integer(0) ); // 第 12 天沒解決任何需求
        list.add( new Integer(18) ); // 第 13 天解決 18 人天的需求
        list.add( new Integer(18) ); // 第 14 天解決 18 人天的需求
        list.add( new Integer(5) ); // 第 15 天解決 5 人天的需求

        SwingHelper.show( new BDChart( new Dimension( 200, 200 ), new Integer(20), new Integer(60), list ) );
    }


    private Dimension getFixedSize() {
        if ( fixedSize == null ) {
            fixedSize = new Dimension( 110, 110 );
        }
        return fixedSize;
    }

    private void setFixedSize(Dimension fixedSize) {
        this.fixedSize = fixedSize;
    }

    private Integer getWorkDay() {
        if ( workDay == null ) {
            workDay = new Integer( 20 );
        }
        return workDay;
    }

    private void setWorkDay(Integer workDay) {
        this.workDay = workDay;
    }

    private Integer getManDay() {
        if ( manDay == null ) {
            manDay = new Integer( 20 );
        }
        return manDay;
    }

    private void setManDay(Integer manDay) {
        this.manDay = manDay;
    }

    private List<Integer> getResolvedDays() {
        if ( resolvedDays == null ) {
            resolvedDays = new ArrayList<Integer>();
        }
        return resolvedDays;
    }

    private void setResolvedDays(List<Integer> resolvedDays) {
        this.resolvedDays = resolvedDays;
    }
}
