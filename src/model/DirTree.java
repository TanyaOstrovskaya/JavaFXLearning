package model;

import java.io.File;

public class DirTree {

    private static void list(String szDir) {
        File f = new File(szDir);
        String[] sDirList = f.list();

        int i;
        for(i = 0; i < sDirList.length; i++) {
            File f1 = new File(szDir + File.separator + sDirList[i]);

            if(f1.isFile())
                System.out.println(szDir + File.separator + sDirList[i]);
            else
                list(szDir + File.separator + sDirList[i]);
        }
    }
}
