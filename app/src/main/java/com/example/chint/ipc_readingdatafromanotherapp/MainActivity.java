package com.example.chint.ipc_readingdatafromanotherapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText uname;
    Button b;
    String pname = "com.example.chint.internalstorage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = (EditText)findViewById(R.id.txt);
    }
    public void onClick(View v){
        String fp;
        PackageManager pm = getPackageManager();
        FileInputStream fis = null;
        try {
            ApplicationInfo ap = pm.getApplicationInfo(pname, PackageManager.GET_META_DATA);
            fp = ap.dataDir+"/files/file.txt";
            fis = new FileInputStream(new File(fp));
            int read = -1;
            StringBuffer sb = new StringBuffer();
            while((read = fis.read())!=-1){
                sb.append((char)read);
            }
            String a = sb.toString();
            uname.setText(a);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
