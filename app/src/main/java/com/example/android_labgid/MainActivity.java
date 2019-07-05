package com.example.android_labgid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] name = {"7.5标签生成", "4.2标签生成", "1.5标签生成"};
    private ArrayAdapter<String> adapter;
    Spinner spinner;
    String a, b, c, d;
    String randomcode = "";
    // 用字符数组的方式随机
    String model = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String filePath = "/sdcard/Test/";
    String fileName1 = "A7.txt";
    String fileName2 = "A4.txt";
    String fileName3 = "A1.txt";
    Button bt_lable7,bt_lable4,bt_lable1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        a = name[0];
        b = name[1];
        c = name[2];
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, name);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                d = adapter.getItem(position);

                if (d != null) {
                    if (d.equals(a)) {
                        Toast.makeText(getApplicationContext(), "点击了A7", Toast.LENGTH_SHORT).show();


                    } else if (d.equals(b)) {
                        Toast.makeText(getApplicationContext(), "点击了A4", Toast.LENGTH_SHORT).show();

                            Set<String> uuidSet = new HashSet<>();
                            int len = 1000000;
                            int let = 0;
                            for (int i = 0; i < len; i++) {
                                String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
                                uuidSet.add(uuid);
                                if (uuidSet.size() == 1000) {
                                    System.out.println("结束");
                                    break;
                                }
                            }
                            Iterator<String> it = uuidSet.iterator();
                            while (it.hasNext()) {
                                String str = it.next();
                                System.out.println();
                              //  writeTxtToFile("A4"+str, filePath, fileName);
                            }
                            System.out.println(let + "去重后的集合： " + uuidSet.size());
                        }

                } else {
                    Toast.makeText(getApplicationContext(), "点击了A1", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    // 将字符串写入到文本文件中
    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);
        String strFilePath = filePath+fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }


    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    private void init() {
        spinner = findViewById(R.id.spinner);
        bt_lable7 = findViewById(R.id.bt_lable7);
        bt_lable4 = findViewById(R.id.bt_lable4);
        bt_lable1 = findViewById(R.id.bt_lable1);

        bt_lable7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "生成中，请稍等.......", Toast.LENGTH_LONG).show();
                Set<String> uuidSet = new HashSet<>();
                int len = 1000000;
                int let = 0;
                for (int i = 0; i < len; i++) {
                    String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
                    uuidSet.add(uuid);
                    if (uuidSet.size() == 1000) {
                        System.out.println("结束");
                        break;
                    }
                }
                Iterator<String> it = uuidSet.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    System.out.println();
                    writeTxtToFile("A7"+str.toUpperCase(), filePath, fileName1);
                }

                System.out.println(let + "去重后的集合1： " + uuidSet.size());
                bt_lable7.setEnabled(false);
                Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
            }


        });
        bt_lable4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "生成中，请稍等.......", Toast.LENGTH_LONG).show();
                Set<String> uuidSet = new HashSet<>();
                int len = 1000000;
                int let = 0;
                for (int i = 0; i < len; i++) {
                    String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
                    uuidSet.add(uuid);
                    if (uuidSet.size() == 1000) {
                        System.out.println("结束");
                        break;
                    }
                }
                Iterator<String> it = uuidSet.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    System.out.println();
                    writeTxtToFile("A4"+str.toUpperCase(), filePath, fileName2);
                }
                System.out.println(let + "去重后的集合2： " + uuidSet.size());
                bt_lable4.setEnabled(false);
                Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();


            }
        });
        bt_lable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "生成中，请稍等.......", Toast.LENGTH_LONG).show();
                Set<String> uuidSet = new HashSet<>();
                int len = 1000000;
                int let = 0;
                for (int i = 0; i < len; i++) {
                    String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
                    uuidSet.add(uuid);
                    if (uuidSet.size() == 1000) {
                        System.out.println("结束");
                        break;
                    }
                }
                Iterator<String> it = uuidSet.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    System.out.println();
                    writeTxtToFile("A1"+str.toUpperCase(), filePath, fileName3);
                }
                System.out.println(let + "去重后的集合3： " + uuidSet.size());
                bt_lable1.setEnabled(false);
                Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public String getlinkNo() {
        String linkNo = "";
        // 用字符数组的方式随机
        String model = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] m = model.toCharArray();
        for (int j = 0; j < 6; j++) {
            char c = m[(int) (Math.random() * 26)];
            // 保证六位随机数之间没有重复的
            if (linkNo.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            linkNo = linkNo + c;
        }
        return linkNo;
    }

    @Override
    public void onClick(View v) {
    }


}
