package com.wuhulala.javase.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/12/4<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class FileWatchServiceUtil {

    public static void makeWatch(Path targetPath, Consumer<Path> consumer){
        try {
            WatchService watchService = targetPath.getFileSystem().newWatchService();
            Files.walkFileTree(targetPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {
                    dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
                    return FileVisitResult.CONTINUE;
                }
            });
            Executors.newCachedThreadPool().execute(()->{
                WatchKey watchKey = null;
                while (true) {
                    try {
                        watchKey = watchService.take();

                        List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                        for (final WatchEvent<?> event : watchEvents) {
                            WatchEvent<Path> watchEvent = (WatchEvent<Path>) event;
                            WatchEvent.Kind<Path> kind = watchEvent.kind();
                            if(kind == StandardWatchEventKinds.ENTRY_CREATE){
                                Path watchable = ((Path) watchKey.watchable()).resolve(watchEvent.context());
                                if(Files.isDirectory(watchable)){
                                    watchable.resolve(watchEvent.context()).register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
                                }
                            }
                        }
                        consumer.accept(targetPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(watchKey != null){
                            watchKey.reset();
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        makeWatch(new File("D:\\study\\asdasd").toPath(), path -> {
            System.out.println(path.getFileName());
        });
    }

}
