/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class TurtleSoup {

	public static void drawSquare(Turtle turtle, int sideLength) {
        //throw new RuntimeException("implement me!");
        int i;
        for(i=1;i<=4;i++) {   //��ת�Ĵλ�һ��������
        	turtle.forward(sideLength);  
        	turtle.turn(90);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {  //������������ڽ�
        //throw new RuntimeException("implement me!");
    	if(sides<=2) {
    		return 0;
    	}
    	else {
    		 double internalAngle=((double)(sides-2)*(double)(180))/(double)(sides);
    	     return internalAngle;
    	}
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {  //���ڽǵõ�����
        //throw new RuntimeException("implement me!");
    	double throwangle=180-angle;
    	int sides;
    	double a=360/throwangle;
    	sides=(int)Math.round(a); //ȡ��
    	return sides;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {  //�����߻�һ���������
       // throw new RuntimeException("implement me!");
       int i;
       double inAngel=180-TurtleSoup.calculateRegularPolygonAngle(sides);
       for(i=1;i<=sides;i++) {  //��ת�����������
    	   turtle.turn(inAngel);
    	   turtle.forward(sideLength);
       }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) { //�����һ���㵽����һ������Ҫת���ĽǶ�
        //throw new RuntimeException("implement me!");
    	double differencex=(double)(targetX-currentX);
    	double differencey=(double)(targetY-currentY);
    	double onedegree=Math.toDegrees(Math.atan2(differencey,differencex));
    	double twodegree=90-onedegree;
    	double difdegree=twodegree-currentBearing;
    	if(difdegree<0) {
    		difdegree+=360;
    		}
    	return difdegree;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {  //����һϵ�е����ÿ����ת�ĽǶȷ���
        //throw new RuntimeException("implement me!");
    	ArrayList<Double> thedegree=new ArrayList<Double>();
    	double difdegree;
    	if(xCoords.size()==2) {  //ֻ�������
    		difdegree=calculateBearingToPoint(0,xCoords.get(0),yCoords.get(0),xCoords.get(1),yCoords.get(1));
        	thedegree.add(difdegree);
    	}
    	if(xCoords.size()>2){    //�ж����
    		difdegree=calculateBearingToPoint(0,xCoords.get(0),yCoords.get(0),xCoords.get(1),yCoords.get(1));
        	thedegree.add(difdegree);
    		for(int i=2;i<xCoords.size();i++) {
    			difdegree=calculateBearingToPoint(thedegree.get(i-2),xCoords.get(i-1),yCoords.get(i-1),xCoords.get(i),yCoords.get(i));
    			thedegree.add(difdegree);
    		}
    	}
    	return thedegree;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {  //����͹��
        //throw new RuntimeException("implement me!");
    	ArrayList<Point> tubao=new ArrayList<Point>();
    	ArrayList<Point> temppoint=new ArrayList<Point>();
    	temppoint.addAll(points);  //�����µĵ㼯����ֹ��ԭ�е�point�����˸ı�
    	int length;
    	length=temppoint.size();
    	if(length<=3) {  //����������ֱ����͹��
    		return points;
    	}
    	else {
    		Point one=temppoint.get(0);  //�����ҵ����½ǵĵ���Ϊ��ʼ��
    		for(Point temp:points) {
    			if(temp.x()<one.x()) {
    				one=temp;
    			}
    			else if(temp.x()==one.x()&&temp.y()<one.y()) {
    				one=temp;
    			}
    		}
    		tubao.add(one); //�������½ǳ�ʼ�����͹������
    		int i=0;
    		temppoint.remove(one);  //ԭ���ϳ�ȥ��ʼ��
    		Point forpoint=one;
    		do {
    			if(i==1) {
    				temppoint.add(one);  //���¼�����ʼ���жϽ�ֹ
    			}
    			double toangle=360,todif=0;
    			Point topoint=null;  //��Ҫ����ĵ�
    			for(Point temp:temppoint) {
    				double tempdushu=calculateBearingToPoint(0,(int)forpoint.x(),(int)forpoint.y(),(int)temp.x(),(int)temp.y());//ǰһ���㵽�����е��ƫת��
    				double tempdistance=Math.pow(forpoint.x() - temp.x(), 2) + Math.pow(forpoint.y() - temp.y(), 2);//�������
    				if(tempdushu<toangle) {  //�ҵ���С��ƫת��
    					toangle=tempdushu;
    					topoint=temp;
    					todif=tempdistance;
    				}
    				else if(tempdushu==toangle&&tempdistance>todif) { //��������ͬ������Զ�ĵ�
    					topoint=temp;
    					todif=tempdistance;
    				}	
    			}
    			tubao.add(topoint);
    			temppoint.remove(topoint);
    			forpoint=topoint;
    			i++;
    		}while(tubao.get(i)!=one);
    		Set<Point> finalresult = new HashSet<Point>();
    		finalresult.addAll(tubao);
    		return finalresult;
    	}
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
       //throw new RuntimeException("implement me!");
    	PenColor[] color = new PenColor[5];
    	color[0] = PenColor.GREEN;
		color[1] = PenColor.BLUE;
		color[2] = PenColor.RED;
		color[3] = PenColor.PINK;
		color[4] = PenColor.YELLOW;
		int i=0,a;
		do {
			drawRegularPolygon(turtle,(i+3),(i*2));
			a=i%5;
			turtle.color(color[a]);
			turtle.turn(100);
			i++;
		}while(i<50);
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
    }

}
