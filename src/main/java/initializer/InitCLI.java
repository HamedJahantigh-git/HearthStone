package initializer;


import controller.FileManagement;
import defaults.FilesPath;

import java.io.File;

public class InitCLI {

    public static int screenLength = 70;

    public static void headerBox(String title) {
        int length = screenLength;
        System.out.println(org.apache.commons.lang3.StringUtils.rightPad("+", length - 1, "-") + "+");
        System.out.println(org.apache.commons.lang3.StringUtils.center(org.apache.commons.lang3.StringUtils.center(
                "*" + title + "*", length - 2), length, "|"));
        System.out.println(org.apache.commons.lang3.StringUtils.rightPad("+", length - 1, "-") + "+");
        System.out.println();
    }

    public static void frameCreator(String title, String[] str) {
        int length = screenLength;
        System.out.println(org.apache.commons.lang3.StringUtils.rightPad("/", length - 1, "*") + "\\");
        System.out.println(org.apache.commons.lang3.StringUtils.center(org.apache.commons.lang3.StringUtils.center(
                "@ " + title + " @", length - 2), length, " "));
        for (int i = 0; i < str.length; i++)
            System.out.println(str[i]);
        System.out.println(org.apache.commons.lang3.StringUtils.rightPad("\\", length - 1, "*") + "/");
    }


}
