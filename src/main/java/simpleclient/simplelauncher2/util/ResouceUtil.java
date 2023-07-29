package simpleclient.simplelauncher2.util;

import java.io.InputStream;

import simpleclient.simplelauncher2.SimpleLauncherApplication;

public class ResouceUtil {

	public static InputStream getResouce(final String identifier) {
		return SimpleLauncherApplication.class.getResourceAsStream(identifier);
	}
}
