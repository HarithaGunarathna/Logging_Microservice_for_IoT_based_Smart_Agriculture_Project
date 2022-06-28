package com.example.co227_project.filters;


import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import org.springframework.util.Assert;

public class MyDelegatingServletOutputStream extends ServletOutputStream {
    private final OutputStream targetStream;

    public MyDelegatingServletOutputStream(OutputStream targetStream) {
        Assert.notNull(targetStream, "Target OutputStream must not be null");
        this.targetStream = targetStream;
    }

    public final OutputStream getTargetStream() {
        return this.targetStream;
    }

    public void write(int b) throws IOException {
        this.targetStream.write(b);
    }

    public void flush() throws IOException {
        super.flush();
        this.targetStream.flush();
    }

    public void close() throws IOException {
        super.close();
        this.targetStream.close();
    }

    public boolean isReady() {
        return true;
    }

    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException();
    }
}
