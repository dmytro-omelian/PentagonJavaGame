package game.states;

import framework.gamestates.GameState;
import game.Game;
import framework.display.Window ;

import java.awt.*;
import java.awt.event.KeyEvent;


public class MainMenu extends GameState {

	protected String[] options;
	protected int selected;
	
	@Override
	protected void init() {
		options = new String[] {
				"Start Game",
				"Highlights",
				"Info",
				"Exit"
		};
		selected = 0;
		System.out.println("[Game][States]: Created main menu");
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics graphics) {
		drawBackground(graphics);
		drawButtons(graphics);
		drawOptions(graphics);
	}

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_DOWN){
			if( selected < 3 )
				selected ++ ;
			else selected = 0 ;
		} else if(key == KeyEvent.VK_UP){
			if(selected > 0)
				selected -- ;
			else selected = 3;
		}else if(key == KeyEvent.VK_ENTER){
			if(selected == 0)
				Game.STATE_MANAGER.changeState(new PlayingState());
			else if(selected == 1)
				Game.STATE_MANAGER.changeState(new Info());
			else if(selected == 2)
				Game.STATE_MANAGER.changeState(new HighscoresMenu());
			else System.exit(0);
		}
	}

	@Override
	public void keyReleased(int key) {}

	private void drawBackground(Graphics graphics) {
		graphics.setColor(new Color(54, 51, 51));
		graphics.fillRect(0,0, Window.getWidth(), Window.getHeight());
	}
	private void drawOptions(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Roboto", Font.PLAIN + Font.ITALIC, 25));
		for(int i = 0; i < options.length; i ++ ){
			if(selected == i){
				graphics.setColor(Color.GREEN);
			} else {
				graphics.setColor(Color.WHITE);
			}
			drawCenteredString(options[i], Window.WIDTH, Window.HEIGHT, graphics, i) ;
		}
	}

	private void drawCenteredString(String option, int width, int height, Graphics graphics, int diffBetweenLines) {
		FontMetrics fm = graphics.getFontMetrics() ;
		int x = (width - fm.stringWidth(option)) / 2 ;
		int y = (fm.getAscent() + (200 + 200 * diffBetweenLines - (fm.getAscent() + fm.getDescent())) / 2);
		graphics.drawString(option, x, y);
	}

	private void drawButtons(Graphics graphics) {

	}
}
