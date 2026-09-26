package gg.jte.generated.ondemand;
@SuppressWarnings("unchecked")
@javax.annotation.processing.Generated("gg.jte.TemplateEngine")
public final class JteloginGenerated {
	public static final String JTE_NAME = "login.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,0,18,18,18,19,19,19,20,20,26,26,26,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String error) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\"\n          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n    <link rel=\"stylesheet\" href=\"/styles/form.css\">\n    <title>Войти</title>\n</head>\n<body>\n<div class=\"container\">\n    <div class=\"registration\">\n        <form method=\"POST\" action=\"/login\">\n            <input type=\"text\" required name=\"username\" placeholder=\"Username\" class=\"input\">\n            <input type=\"password\" required name=\"password\" placeholder=\"Password\" class=\"input\">\n            ");
		if (error.equals("Invalid username or password")) {
			jteOutput.writeContent("\n                <span class=\"error\">");
			jteOutput.setContext("span", null);
			jteOutput.writeUserContent(error);
			jteOutput.writeContent("</span>\n            ");
		}
		jteOutput.writeContent("\n            <input type=\"submit\" value=\"Войти\" class=\"input submit\">\n        </form>\n    </div>\n</div>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String error = (String)params.get("error");
		render(jteOutput, jteHtmlInterceptor, error);
	}
}
