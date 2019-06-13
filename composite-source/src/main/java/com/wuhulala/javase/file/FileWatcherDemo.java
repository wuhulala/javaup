package com.wuhulala.javase.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * 文件监听器
 *
 * @author xueah20964 2018/11/29 Create 1.0  <br>
 * @version 1.0
 */
public class FileWatcherDemo {

    private static final Logger logger = LoggerFactory.getLogger(FileWatcherDemo.class);

    private WatchService watcher;

    private Path path;

    public FileWatcherDemo(Path path) throws IOException {
        this.path = path;
        watcher = FileSystems.getDefault().newWatchService();
        this.path.register(watcher, OVERFLOW, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
    }

    public void handleEvents() throws InterruptedException {
        // start to process the data files
        while (true) {
            // start to handle the file change event
            final WatchKey key = watcher.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                // get event type
                final WatchEvent.Kind<?> kind = event.kind();

                // get file name
                @SuppressWarnings("unchecked")
                final WatchEvent<Path> pathWatchEvent = (WatchEvent<Path>) event;
                final Path fileName = pathWatchEvent.context();
                logger.debug("file {} is {}", fileName, kind);

                if (kind == ENTRY_CREATE) {
                    //logger.debug("file {} is created", fileName);

                } else if (kind == ENTRY_DELETE) {
                    // todo
                } else if (kind == ENTRY_MODIFY) {
                    // todo
                } else if (kind == OVERFLOW) {
                    // todo
                }
            }

            // IMPORTANT: the key must be reset after processed
            if (!key.reset()) {
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("D:\\code\\javase\\java_up\\target\\classes\\com\\wuhulala\\javase");
        new FileWatcherDemo(file.toPath()).handleEvents();
    }

}
