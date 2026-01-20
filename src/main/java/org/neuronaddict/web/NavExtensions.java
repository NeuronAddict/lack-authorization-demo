package org.neuronaddict.web;

import io.quarkus.qute.TemplateExtension;

import java.util.Objects;

public class NavExtensions {

    public static final String BUTTON_STYLE_ACTIVE = "bg-indigo-600 text-white font-medium";
    public static final String BUTTON_STYLE_INACTIVE = "hover:bg-slate-800 hover:text-slate-200 transition";

    @TemplateExtension(namespace = "nav")
    public static String active(String startPath, String currentPath) {
        if (Objects.equals(startPath, "/")) {
            return (Objects.equals(currentPath, "/")) ? BUTTON_STYLE_ACTIVE : BUTTON_STYLE_INACTIVE;
        }
        if (startPath != null && currentPath.startsWith(startPath)) {
            return BUTTON_STYLE_ACTIVE;
        }
        return BUTTON_STYLE_INACTIVE;
    }
}
