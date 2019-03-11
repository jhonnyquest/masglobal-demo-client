package com.masglobal.demo;

import com.masglobal.demo.utils.SessionUtil;
import spark.ModelAndView;
import spark.Request;
import spark.Session;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public interface Template {

    VelocityTemplateEngine instance = new VelocityTemplateEngine();

    String ERROR_PAGE = "/templates/error-page.vm";
    String HOME = "/templates/home-page.vm";

    static String render(Request request, String templatePath, ModelEntry... entries) {
        String pathInfo = request.pathInfo();
        Session session = request.session();

        session.removeAttribute("errorMessage");
        Locale locale = SessionUtil.getLocale(session);
        HashMap<String, Object> model = new HashMap<String, Object>() {{
            put("lang", locale);
            put("texts", ResourceBundle.getBundle("texts", locale));
            put("pathInfo", pathInfo);
        }};

        Arrays.asList(entries).forEach(e -> model.put(e.getKey(), e.getValue()));

        return instance.render(new ModelAndView(model, templatePath));
    }

}