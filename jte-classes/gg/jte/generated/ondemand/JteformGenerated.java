package gg.jte.generated.ondemand;
import java.util.List;
import hexlet.code.searchFormJTE.model.Error;
@SuppressWarnings("unchecked")
@javax.annotation.processing.Generated("gg.jte.TemplateEngine")
public final class JteformGenerated {
	public static final String JTE_NAME = "form.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,3,3,22,22,22,23,23,24,24,24,25,25,26,26,28,28,29,29,30,30,30,31,31,32,32,34,34,35,35,36,36,36,37,37,38,38,44,44,44,3,4,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<Error> errors, String username, String email) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\"\n          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n    <link rel=\"stylesheet\" href=\"/styles/form.css\">\n    <title>Регистрация</title>\n</head>\n<body>\n    <div class=\"container\">\n        <div class=\"registration\">\n            <form method=\"POST\" action=\"/registration\">\n                <input type=\"text\" required name=\"username\" placeholder=\"Username\" class=\"input\">\n                ");
		for (Error e : errors) {
			jteOutput.writeContent("\n                    ");
			if (e.getField().equals("username")) {
				jteOutput.writeContent("\n                        <span class=\"error\">");
				jteOutput.setContext("span", null);
				jteOutput.writeUserContent(e.getError());
				jteOutput.writeContent("</span>\n                    ");
			}
			jteOutput.writeContent("\n                ");
		}
		jteOutput.writeContent("\n                <input type=\"password\" required name=\"password\" placeholder=\"Password\" class=\"input\">\n                ");
		for (Error e : errors) {
			jteOutput.writeContent("\n                    ");
			if (e.getField().equals("password")) {
				jteOutput.writeContent("\n                        <span class=\"error\">");
				jteOutput.setContext("span", null);
				jteOutput.writeUserContent(e.getError());
				jteOutput.writeContent("</span>\n                    ");
			}
			jteOutput.writeContent("\n                ");
		}
		jteOutput.writeContent("\n                <input type=\"email\" required name=\"email\" placeholder=\"Email\" class=\"input\">\n                ");
		for (Error e : errors) {
			jteOutput.writeContent("\n                    ");
			if (e.getField().equals("email")) {
				jteOutput.writeContent("\n                        <span class=\"error\">");
				jteOutput.setContext("span", null);
				jteOutput.writeUserContent(e.getError());
				jteOutput.writeContent("</span>\n                    ");
			}
			jteOutput.writeContent("\n                ");
		}
		jteOutput.writeContent("\n                <input type=\"submit\" value=\"Зарегистрироваться\" class=\"input submit\">\n            </form>\n        </div>\n    </div>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<Error> errors = (List<Error>)params.get("errors");
		String username = (String)params.get("username");
		String email = (String)params.get("email");
		render(jteOutput, jteHtmlInterceptor, errors, username, email);
	}
}
