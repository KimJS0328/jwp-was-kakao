package webserver.response;

import java.io.IOException;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

public class TemplateResponse implements Response {
    private static final Handlebars handlebars;
    private final String body;

    static {
        ClassPathTemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");
        handlebars = new Handlebars(loader);
    }

    private TemplateResponse(String templatePath, Object model) throws IOException {
        Template template = handlebars.compile(templatePath);
        this.body = template.apply(model);
    }

    public static TemplateResponse of(String templatePath, Object model) throws IOException {
        return new TemplateResponse(templatePath, model);
    }

    @Override
    public byte[] getHeader() {
        return new HeaderBuilder()
            .addContentType("text/html; charset=utf-8")
            .addContentLength(body.length())
            .build();
    }

    @Override
    public byte[] getBody() {
        return body.getBytes();
    }
}
