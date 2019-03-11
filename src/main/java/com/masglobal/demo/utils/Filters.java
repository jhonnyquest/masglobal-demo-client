package com.masglobal.demo.utils;

import spark.Request;
import spark.Response;

public class Filters {

    public static void captureLanguage(Request request, Response response) {
        String language = request.queryParams(SessionUtil.Attributes.LANGUAGE);
        if (language != null) {
            request.session().attribute(SessionUtil.Attributes.LANGUAGE, language);
            response.redirect(request.pathInfo());
        }
    }

    public static void checkNotFound(Request request, Response response) {
        if (!request.pathInfo().equals("/login")) {
            return;
        }
    }
}