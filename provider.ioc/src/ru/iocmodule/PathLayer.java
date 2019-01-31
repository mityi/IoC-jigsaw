package ru.iocmodule;

public class PathLayer {
    public final String path;
    public final String moduleName;

    private PathLayer(String path, String moduleName) {
        this.path = path;
        this.moduleName = moduleName;
    }

    public static PathLayer of(String path, String moduleName) {
        return new PathLayer(path, moduleName);
    }
}
