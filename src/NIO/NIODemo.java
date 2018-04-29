package NIO;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIODemo {

    public static void main(String[] args) throws IOException {
        // methodOne();
        // methodTwo();
        methodThree();
    }

    public static void methodOne() throws IOException {
        String rFile = "nio_read.txt";
        String wFile = "nio_write.txt";
        FileChannel rFileChannel = new FileInputStream(rFile).getChannel();
        FileChannel wFileChannel = new FileOutputStream(wFile).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(1024);

        while (rFileChannel.read(buff) > 0) {
            buff.flip();
            wFileChannel.write(buff);
            buff.clear();
        }

        close(wFileChannel);
        close(rFileChannel);
    }

    public static void methodTwo() throws IOException {
        String rFile = "nio_read.txt";
        String wFile = "nio_write.txt";
        FileChannel rFileChannel = new FileInputStream(rFile).getChannel();
        FileChannel wFileChannel = new FileOutputStream(wFile).getChannel();

        rFileChannel.transferTo(0, rFileChannel.size(), wFileChannel);

        close(wFileChannel);
        close(rFileChannel);
    }

    public static void methodThree() throws IOException {
        String rFile = "nio_read.txt";
        String wFile = "nio_write.txt";
        RandomAccessFile raf = new RandomAccessFile(rFile, "rw");
        FileChannel randomChannel = raf.getChannel();
        FileChannel wFileChannel = new FileOutputStream(wFile).getChannel();

        // 将Channel中的所有数据映射成ByteBuffer
        ByteBuffer buff = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, raf.length());

        // 把Channel的指针移动到最后
        randomChannel.position(raf.length());
        wFileChannel.write(buff);

        close(wFileChannel);
        close(randomChannel);
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
