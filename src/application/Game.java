package application;

import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {
	public int how = 100;
	double itemSize;
	GraphicsContext playGround;
	Canvas ground;
	public Boolean[][] ptr; 
	public Boolean[][] tmp = new Boolean[how][how]; 
	public Boolean[][] renderTab = new Boolean[how][how];
	
	public void randTab() {
		Random rand = new Random();
		for(int i=0; i<this.how; i++) {
			for(int k=0; k<this.how; k++) {
				renderTab[i][k] = (rand.nextInt(9) % 2 == 0) ? true : false;
				tmp[i][k] = renderTab[i][k];
			}
		}
	}
	
	public void render() {
		for(int i=0; i<how; i++) {
			for(int k=0; k<how; k++) {
				if(renderTab[i][k]) playGround.setFill(Color.BLACK);
				else playGround.setFill(Color.WHITE);
				playGround.strokeRect(itemSize*k, itemSize*i, itemSize, itemSize);
				playGround.fillRect(itemSize*k, itemSize*i, itemSize, itemSize);
			}
		}
	}
	
	public void drawPlayGround() {
	
		playGround = ground.getGraphicsContext2D();
		playGround.clearRect(0, 0, ground.getWidth(), ground.getHeight());
			
		playGround.setStroke(Color.BLACK);
		playGround.setFill(Color.WHITE);
		playGround.setLineWidth(1);
				
		double border = ground.getWidth();
		itemSize = border/how;
		
		for(int i=0; i<how; i++) {
			for(int k=0; k<how; k++) {
				playGround.strokeRect(itemSize*k, itemSize*i, itemSize, itemSize);
				playGround.fillRect(itemSize*k, itemSize*i, itemSize, itemSize);
			}
		}
		
	}
	
	public void nextTour() {
		int neighbour = 0;
		ptr = tmp;
		tmp = renderTab;
		renderTab = ptr;
		
		for(int i=0; i<how; i++) {
			for(int k=0; k<how; k++) {
				neighbour = howNeighbour(i, k);
				if(neighbour < 2 || neighbour > 3) renderTab[i][k] = false;
				else if(neighbour == 3) renderTab[i][k] = true;
				else renderTab[i][k] = tmp[i][k];
			}
		}
	}
	
	public int howNeighbour(int i, int k) {
		int out = 0;
		int x, y;
		
		x = i;
		y = k - 1;
		if(x >= 0 && x < how && y >= 0 && y < how && tmp[x][y]) out++;
		
		x = i + 1;
		y = k - 1;
		if(x >= 0 && x < how && y >= 0 && y < how && tmp[x][y]) out++;
		
		x = i + 1;
		y = k;
		if(x >= 0 && x < how && y >= 0 && y < how && tmp[x][y]) out++;
		
		x = i + 1;
		y = k + 1;
		if(x >= 0 && x < how && y >= 0 && y < how && tmp[x][y]) out++;
		
		x = i;
		y = k + 1;
		if(x >= 0 && x < how && y >= 0 && y < how && tmp[x][y]) out++;
		
		x = i - 1;
		y = k + 1;
		if(x >= 0 && x < how && y >= 0 && y < how && tmp[x][y]) out++;
		
		x = i - 1;
		y = k;
		if(x >= 0 && x < how && y >= 0 && y < how && tmp[x][y]) out++;
		
		x = i - 1;
		y = k - 1;
		if(x >= 0 && x < how && y >= 0 && y < how && tmp[x][y]) out++;
		
		return out;
	}
	
}
