package com.masglobal.demo.utils;

import com.masglobal.demo.enums.Language;
import spark.Session;

import java.util.*;

import static com.masglobal.demo.utils.SessionUtil.Attributes.*;

public class SessionUtil {

    public interface Attributes {
        String LANGUAGE = "lang";
    }

    public static Language getLanguage(Session session) {
        return Optional.ofNullable(session.attribute(LANGUAGE))
                .map(language -> Language.valueOf(language.toString().toUpperCase()))
                .orElse(Language.ES);
    }

    public static Locale getLocale(Session session) {
        return Optional.ofNullable(session.attribute("lang"))
                .map(l -> new Locale((String) l))
                .orElse(new Locale.Builder().setLanguage(Language.ES.name()).build());
    }
}