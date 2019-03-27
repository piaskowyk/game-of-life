package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class MainViewController {
	
	@FXML
	private Canvas ground;
	
	public void startGame() {
		Game game = new Game();
		game.ground = ground;
		game.drawPlayGround();
		game.randTab();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				game.render();
				game.nextTour();
			}
		}, 5000, 400);
	}

}
