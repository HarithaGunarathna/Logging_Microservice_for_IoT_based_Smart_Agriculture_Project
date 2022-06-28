package com.example.co227_project.filters;

import org.springframework.util.Assert;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDelegatingServletInputStream extends ServletInputStream {
    private final InputStream sourceStream;
    private boolean finished = false;

    public MyDelegatingServletInputStream(InputStream sourceStream) {
        Assert.notNull(sourceStream, "Source InputStream must not be null");
        this.sourceStream = sourceStream;
    }

    public final InputStream getSourceStream() {
        return this.sourceStream;
    }

    public int read() throws IOException {
        int data = this.sourceStream.read();
        if (data == -1) {
            this.finished = true;
        }

        return data;
    }

    public int available() throws IOException {
        return this.sourceStream.available();
    }

    public void close() throws IOException {
        super.close();
        this.sourceStream.close();
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean isReady() {
        return true;
    }

    public void setReadListener(ReadListener readListener) {
        throw new UnsupportedOperationException();
    }
}
