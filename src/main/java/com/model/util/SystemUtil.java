package com.model.util;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QSettings;

import java.io.File;
import java.io.IOException;

/**
 * @author HRH
 * @version 1.0
 * @date 2020/6/6 15:41
 */
public class SystemUtil {

    /**
     * 创建快捷方式
     *
     * @param srcFilePath 源文件路径
     * @param tarLinkPath 目标快捷方式路径
     * @apiNote targetShortCutPath要包含.lnk后缀
     */
    public static boolean createShortCut(String srcFilePath, String tarLinkPath) {
        boolean isLink = false;
        try {
            //相对路径转绝对路径
            srcFilePath = new File(srcFilePath).getCanonicalPath();
            tarLinkPath = new File(tarLinkPath).getCanonicalPath();

//            System.out.println("srcFilePath = " + srcFilePath);
//            System.out.println("tarLinkPath = " + tarLinkPath);

            //删除原有快捷方式
            if (QFile.exists(tarLinkPath)) {
                if (!QFile.remove(tarLinkPath)) {
                    return false;
                }
            }
            //创建新快捷方式
            isLink = QFile.link(srcFilePath, tarLinkPath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isLink;
    }

    /**
     * 开启或关闭指定文件的开机自启
     *
     * @param isAutoStart 开启或关闭自启
     * @param filePath    文件路径
     * @apiNote filePath若是快捷方式, 要包含.lnk拓展名
     */
    public static void setAutoStart(boolean isAutoStart, String filePath) {
        try {
            //相对路径转绝对路径
            filePath = new File(filePath).getCanonicalPath();
            QSettings settings = new QSettings("HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run",
                    QSettings.Format.NativeFormat);

            if (isAutoStart) {
                settings.setValue(QCoreApplication.applicationName(), filePath);
            } else {
                settings.remove(QCoreApplication.applicationName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
