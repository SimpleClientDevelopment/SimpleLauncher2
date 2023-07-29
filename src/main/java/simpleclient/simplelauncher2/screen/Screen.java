package simpleclient.simplelauncher2.screen;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;

public abstract class Screen {

	private final List<Node> nodes;

	public Screen() {
		this.nodes = new ArrayList<>();
	}

	public abstract void init(final Rectangle2D bounds);

	public void update(final Rectangle2D bounds) {
		this.nodes.clear();
		this.init(bounds);
	}
}
