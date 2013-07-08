package com.f0gg.imagenoid.swing;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author f0gg
 * @see http://github.com/f0gg
 */
class ImageFileFilter extends FileFilter {
    private final String[] extensions;
    private final String desc;

    public ImageFileFilter(String[] extensions, String desc) {
        this.extensions = extensions;
        this.desc = desc;
    }

    @Override
    public boolean accept(File file) {
        if(file.isDirectory()) {
            return true;
        }
        
        for (String extension : extensions) {
            System.out.println(">>>" + file.getName().toLowerCase() + "=>" + extension);
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return desc;
    }
}
