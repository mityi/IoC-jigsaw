package ru.iocmodule;


import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Loader<T> {

    public List<T> load(Collection<PathLayer> paths, Class<? extends T> clazz) {
        this.getClass().getModule().addUses(clazz);
        if (validate(paths)) {
            return Collections.emptyList();
        }
        Stream<? extends T> stream = loadAsStream(paths, clazz);
        return stream
                .collect(Collectors.toList());
    }

    public List<T> load(Collection<PathLayer> paths, Class<? extends T> clazz, Comparator<T> comparator) {
        //this module (ioc) don't know anything about clazz what he looking for
        //you need to add them
        this.getClass().getModule().addUses(clazz);
        if (validate(paths)) {
            return Collections.emptyList();
        }

        Stream<? extends T> stream = loadAsStream(paths, clazz);
        return stream
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private Stream<? extends T> loadAsStream(Collection<PathLayer> paths, Class<? extends T> clazz) {
        return paths.stream()
                .map(path -> loadLayer(path.path, path.moduleName))
                .map(moduleLayer -> ServiceLoader.load(moduleLayer, clazz))
                .flatMap(ServiceLoader::stream)
                .map(ServiceLoader.Provider::get);
    }

    private ModuleLayer loadLayer(String path, String name) {
        var finder = ModuleFinder.of(Paths.get(path));
        var parent = ModuleLayer.boot();
        var cf = parent.configuration().resolve(finder, ModuleFinder.of(), Set.of(name));
        return parent.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());
    }

    private boolean validate(Collection<PathLayer> paths) {
        if (paths == null) {
            return true;
        }
        return false;
    }

}
