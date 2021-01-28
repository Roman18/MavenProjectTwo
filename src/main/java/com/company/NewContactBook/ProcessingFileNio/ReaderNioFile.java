package com.company.NewContactBook.ProcessingFileNio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

public class ReaderNioFile {

    private final Path path;

    public ReaderNioFile(Path path){
        this.path=path;
    }



    public void readByLine(Consumer<String> consumer){

        try (ByteChannel channel= Files.newByteChannel(this.path, StandardOpenOption.READ)){
            ByteBuffer byteBuffer=ByteBuffer.allocate(10);
            String buffer="";
            while (channel.read(byteBuffer)!=-1){
                byteBuffer.flip();
            buffer+= getString(byteBuffer);
            String[]parts=buffer.split("\r?\n");
                for (int i = 0; i <parts.length-1 ; i++) {
                    consumer.accept(parts[i]);
                }
        buffer=parts[parts.length-1];
                byteBuffer.clear();

            }
            if(buffer.length()!=0) {
                consumer.accept(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getString(ByteBuffer byteBuffer) {
        return new String(byteBuffer.array(),
                byteBuffer.position()
                ,byteBuffer.limit());
    }

}
