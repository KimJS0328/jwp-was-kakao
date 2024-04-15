package webserver.request;

import java.util.Map;

import utils.ParseUtils;

public class Path {

    public static final int PATH_INDEX = 0;
    public static final int PARAMS_INDEX = 1;
    private final String path;
    private final Map<String, String> params;

    public Path(String path) {
        String[] tokens = splitPathParams(path);
        this.path = tokens[PATH_INDEX];
        this.params = ParseUtils.parseKeyValues(tokens[PARAMS_INDEX]);
    }

    private String[] splitPathParams(String path) {
        int splitIndex = path.indexOf("?");
        if (splitIndex == -1) {
            return new String[] {path, ""};
        }
        return new String[] {path.substring(0, splitIndex), path.substring(splitIndex + 1)};
    }

    public String getRoutePath() {
        return path;
    }

    public Map<String, String> getParams() {
        return params;
    }
}