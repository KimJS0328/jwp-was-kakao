package webserver.response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import utils.FileIoUtils;

public class FileResponse implements Response {

    private final byte[] body;
    private final byte[] header;
    private final Map<String, String> contentTypeMap = Map.of(
        "html", "text/html",
        "css", "text/css",
        "js", "text/javascript",
        "png", "image/png",
        "woff", "font/woff",
        "woff2", "font/woff2",
        "ttf", "font/ttf"
    );

    private FileResponse(String path) throws IOException, URISyntaxException {
        String uri = FileIoUtils.convertPathToFilePath(path);
        this.body = FileIoUtils.loadFileFromClasspath(uri);
        this.header = ("HTTP/1.1 200 OK \r\n"
            + "Content-Type: " + getContentType(path) + ";charset=utf-8\r\n"
            + "Content-Length: " + body.length + "\r\n"
            + "\r\n").getBytes();
    }

    private String getContentType(String path) {
        int dotLocation = path.lastIndexOf(".");
        if (dotLocation == -1) {
            return "text/plain";
        }
        String extension = path.substring(dotLocation + 1);
        return contentTypeMap.getOrDefault(extension, "text/plain");
    }

    public static Response of(String path) {
        try {
            return new FileResponse(path);
        } catch (Exception e) {
            return new NotFoundResponse();
        }
    }

    @Override
    public byte[] getHeader() {
        return header;
    }

    @Override
    public byte[] getBody() {
        return body;
    }

}
