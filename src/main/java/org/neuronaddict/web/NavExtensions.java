package org.neuronaddict.web;

import io.quarkus.qute.TemplateExtension;

public class NavExtensions {

    @TemplateExtension(namespace = "nav")
    public static String active(String path, String currentPath) {
        if (path != null && path.equals(currentPath)) {
             return "bg-indigo-600 text-white font-medium";
        }
        return "hover:bg-slate-800 hover:text-slate-200 transition";
    }

    @TemplateExtension(namespace = "nav")
    public static String active(String prefix, Long id, String currentPath) {
        return active(prefix + id, currentPath);
    }
}
