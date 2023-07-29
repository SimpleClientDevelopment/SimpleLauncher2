package simpleclient.simplelauncher2;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import simpleclient.simplelauncher2.util.NodeUtil;
import simpleclient.simplelauncher2.util.ResouceUtil;

public class SimpleLauncherApplication extends Application {

	private StringProperty version = new SimpleStringProperty("1.20.1");

	public static void main(final String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(final Stage stage) throws IOException {
		final Group group = new Group();
		final Rectangle2D bounds = javafx.stage.Screen.getPrimary().getVisualBounds();
		final Scene scene = new Scene(group, bounds.getWidth() / 2.d, bounds.getHeight() / 2.d);
		{
			scene.setFill(Color.WHITE);
		}

		final ObservableList<Node> nodes = group.getChildren();

		{ // Background
			final ImageView imageView = new ImageView(new Image(ResouceUtil.getResouce("background.png")));

			imageView.fitWidthProperty().bind(scene.widthProperty());
			imageView.fitHeightProperty().bind(scene.heightProperty());
			imageView.setEffect(new GaussianBlur(20));

			nodes.add(imageView);
		}

		final Button launchButton = NodeUtil.init(new Button(), button -> {
			button.setBorder(new Border(new BorderStroke(null, null, null, null, BorderStrokeStyle.SOLID,
					BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
					new CornerRadii(5, 1, 1, 5, false), BorderWidths.DEFAULT, new Insets(0, 0, 0, 1))));
			button.setTextFill(Color.WHITE);
			button.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			button.setFocusTraversable(false);
			button.textProperty().bind(new SimpleStringProperty("LAUNCH ").concat(this.version));
			button.prefWidthProperty().bind(scene.widthProperty().divide(10));
			button.prefHeightProperty().bind(scene.heightProperty().divide(20));
			button.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(button.widthProperty()));
			button.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(button.heightProperty().divide(2)));
		});

		nodes.add(launchButton);

		final MenuButton versionMenuButton = NodeUtil.init(new MenuButton(), menuButton -> {
			final ObservableList<MenuItem> menuItems = menuButton.getItems();
			{
				final EventHandler<ActionEvent> actionEventHandler = event -> {
					if (event.getSource() instanceof MenuItem menuItem) {
						this.version.set(menuItem.getText());
					}
				};

				menuItems.add(
						NodeUtil.init(new MenuItem("1.20.1"), menuItem -> menuItem.setOnAction(actionEventHandler)));
				menuItems
						.add(NodeUtil.init(new MenuItem("1.20"), menuItem -> menuItem.setOnAction(actionEventHandler)));
				menuItems.add(
						NodeUtil.init(new MenuItem("1.19.4"), menuItem -> menuItem.setOnAction(actionEventHandler)));
				menuItems.add(
						NodeUtil.init(new MenuItem("1.8.9"), menuItem -> menuItem.setOnAction(actionEventHandler)));
			}

			menuButton.setBorder(new Border(new BorderStroke(null, null, null, null, BorderStrokeStyle.SOLID,
					BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
					new CornerRadii(1, 5, 5, 1, false), BorderWidths.DEFAULT, new Insets(0, 0, 0, -1))));
			menuButton.setTextFill(Color.WHITE);
			menuButton.setBackground(
					new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			menuButton.setFocusTraversable(false);
			menuButton.textProperty().bind(this.version);
			menuButton.prefWidthProperty().bind(scene.widthProperty().divide(10));
			menuButton.prefHeightProperty().bind(scene.heightProperty().divide(20));
			menuButton.layoutXProperty().bind(scene.widthProperty().divide(2));
			menuButton.layoutYProperty()
					.bind(scene.heightProperty().divide(2).subtract(menuButton.heightProperty().divide(2)));
		});
		nodes.add(versionMenuButton);

		final ChangeListener<? super Number> changeSceneSizeListener = (observableValue, oldSceneNumber,
				newSceneNumber) -> {
			launchButton.setFont(Font.font((launchButton.prefWidthProperty().get() + launchButton.prefHeightProperty().get()) / 15));
			versionMenuButton.setFont(Font.font((versionMenuButton.prefWidthProperty().get() + versionMenuButton.prefHeightProperty().get()) / 15));
		};

		scene.widthProperty().addListener(changeSceneSizeListener);
		scene.heightProperty().addListener(changeSceneSizeListener);

		stage.setScene(scene);
		stage.show();
	}
}