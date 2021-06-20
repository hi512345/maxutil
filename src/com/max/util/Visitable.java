package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;

public interface Visitable {
    void printDescription();
    int  doFile(File file) throws FileNotFoundException;
    int  doFolder(File folder);
}
