package com.ClientControl.Control;

import com.ClientControl.Bean.MyProcess;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by VULCAN on 2017/10/31.
 */
@RestController
public class ClientControl {
    static  String  root =  "D:\\jars\\DemoB\\Bin\\";
    @RequestMapping("/OnLineServerStart")
    public String OnLineServerStart() {
        String path =root + "start.bat";
        System.out.println(path);
        return GetRunStatus(path,"程序开始!");
    }
    @RequestMapping("/OnLineServerStop")
    public String OnLineServerStop() {
        String path =root + "stop.bat";
        System.out.println(path);
        return GetRunStatus(path,"程序关闭!");
    }

    private String GetRunStatus(String path,String type){
        int status = RunScript(path);
        if (status == 1)
            return "程序正常执行:"+type;
        return "程序未正常运行";
    }

    private int RunScript(String Path){
        //String cmdstring = "chmod a+x test.sh";
       // String cmdstring = "bash test.sh";
        int status = 0;
        String cmd = "cmd /c start "+Path;
        try{
            Process proc = Runtime.getRuntime().exec(cmd);  //  run.exec("cmd /c taskkill /f /t /im java.exe");
            proc.waitFor(); //阻塞，直到上述命令执行完
            //MyProcess.setProc(proc);
            // 注意下面的操作
            String ls_1;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ( (ls_1=bufferedReader.readLine()) != null)
                System.out.println(ls_1);
            bufferedReader.close();
            proc.waitFor();
            status = 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("执行异常!!!");
        }
        return status;

    }
}
