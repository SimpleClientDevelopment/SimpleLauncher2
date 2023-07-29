package simpleclient.simplelauncher2.util;

import java.util.function.Consumer;

public class NodeUtil {

	public static <T> T init(final T node, Consumer<T> initializer) {
		initializer.accept(node);

		return node;
	}
}
