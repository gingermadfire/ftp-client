package com.gingermadfire.connection;

import com.gingermadfire.exception.FTPConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class FTPServerConnection implements ServerConnection {

    private URLConnection connection;

    @Override
    public void connect(String login, String password, String ipAddress, String endPoint) {

        String path = String.format("ftp://%s:%s@%s:%d/%s", login, password, ipAddress, 21, endPoint);

        try {
            connection = new URL(path).openConnection();
            connection.connect();

        } catch (IOException e) {
            throw new FTPConnectionException("Не удалось соединиться с сервером");
        }
    }

    public InputStream findInputStream() throws IOException {
        return connection.getInputStream();
    }

    public OutputStream findOutputStream() throws IOException {
        return connection.getOutputStream();
    }
}
