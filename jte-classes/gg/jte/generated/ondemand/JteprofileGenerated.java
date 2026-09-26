package gg.jte.generated.ondemand;
@SuppressWarnings("unchecked")
@javax.annotation.processing.Generated("gg.jte.TemplateEngine")
public final class JteprofileGenerated {
	public static final String JTE_NAME = "profile.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,0,21,21,21,21,22,22,22,32,32,32,0,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String email, String username) {
		jteOutput.writeContent("\n\n<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\"\n          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n    <link rel=\"stylesheet\" href=\"/styles/form.css\">\n    <title>Типо сайт</title>\n</head>\n<body>\n    <div class=\"container-2\">\n        <div class=\"profile\">\n            <div class=\"profile-image\">\n                <img src=\"/assets/images/profile.webp\" alt=\"Ваше фото\">\n            </div>\n            <div class=\"profile-information\">\n                <p>Имя: ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(username);
		jteOutput.writeContent("</p>\n                <p>Почта: ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(email);
		jteOutput.writeContent("</p>\n            </div>\n            <div class=\"profile-search-another-profiles\">\n                <form action=\"/profile\" method=\"POST\">\n                    <input type=\"button\" value=\"искать друзей\" class=\"input submit\">\n                </form>\n            </div>\n        </div>\n    </div>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String email = (String)params.get("email");
		String username = (String)params.get("username");
		render(jteOutput, jteHtmlInterceptor, email, username);
	}
}
