package mg.studio.android.survey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        SharedPreferences sPreferences = getSharedPreferences("config", MODE_PRIVATE);
        String info = sPreferences.getString("info", "");
        String[] temp = new String[12];
        temp = info.split(";");
        TextView textView_1 = findViewById(R.id.tv_question_1);
        TextView textView_2 = findViewById(R.id.tv_question_2);
        TextView textView_3 = findViewById(R.id.tv_question_3);
        TextView textView_4 = findViewById(R.id.tv_question_4);
        TextView textView_5 = findViewById(R.id.tv_question_5);
        TextView textView_6 = findViewById(R.id.tv_question_6);
        TextView textView_7 = findViewById(R.id.tv_question_7);
        TextView textView_8 = findViewById(R.id.tv_question_8);
        TextView textView_9 = findViewById(R.id.tv_question_9);
        TextView textView_10 = findViewById(R.id.tv_question_10);
        TextView textView_11 = findViewById(R.id.tv_question_11);
        TextView textView_12 = findViewById(R.id.tv_question_12);
        textView_1.setText(temp[0]);
        textView_2.setText(temp[1]);
        textView_3.setText(temp[2]);
        textView_4.setText(temp[3]);
        textView_5.setText(temp[4]);
        textView_6.setText(temp[5]);
        textView_7.setText(temp[6]);
        textView_8.setText(temp[7]);
        textView_9.setText(temp[8]);
        textView_10.setText(temp[9]);
        textView_11.setText(temp[10]);
        textView_12.setText(temp[11]);
        Map<String, Object> resultMap = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 12; i++) {
            String q = "Question " + (i + 1);
            resultMap.put(q, temp[i]);
        }
        jsonArray.put(resultMap);
        final String data = jsonArray.toString();
        Button button = findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                verifyStoragmissions(FinalActivity.this);
                File sdFile = Environment.getExternalStorageDirectory();
                File saveData = new File(sdFile, "data.json");
                try {
                    FileOutputStream fout = new FileOutputStream(saveData);
                    fout.write(data.getBytes());
                    fout.flush();
                    fout.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                File appFile = getApplicationContext().getDataDir();
                File saveDataInner = new File(appFile, "data.json");
                try {
                    FileOutputStream foutinner = new FileOutputStream(saveDataInner);
                    foutinner.write(data.getBytes());
                    foutinner.flush();
                    foutinner.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private void verifyStoragmissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        int permission1 = ActivityCompat.checkSelfPermission(activity,
                "android.permission.READ_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED || permission1 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }
}
