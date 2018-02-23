package com.xbb.Bean;

/**
 * Created by VULCAN on 2017/11/1.
 */
public class MyProcess {
     static java.lang.Process proc = null;

    public static java.lang.Process getProc() {
        return proc;
    }

    public static void setProc(java.lang.Process proc) {
        MyProcess.proc = proc;
    }
    public static void ProcDestroy() {
        if(proc != null)
            proc.destroy();
        proc = null;
    }
}
