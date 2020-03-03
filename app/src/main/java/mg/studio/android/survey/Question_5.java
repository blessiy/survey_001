package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Question_5 extends AppCompatActivity {

    private String selection;
    private LinearLayout mlinearlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_5);

        mlinearlayout=findViewById(R.id.linearlayout);
        Button btn5=(Button)findViewById(R.id.btn_question_5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder as5 = new StringBuilder();
                //检查checkbox
                int count = mlinearlayout.getChildCount();
                for(int i = 0;i < count;i++){
//					获得子控件对象
                    View child = mlinearlayout.getChildAt(i);
//					判断是否是CheckBox
                    if(child instanceof CheckBox){
//						转为CheckBox对象
                        CheckBox checkbox = (CheckBox)child;
                        if(checkbox.isChecked()){
                            as5.append(checkbox.getText()+",");
                        }
                    }
                }
                if(as5.length()!= 0)
                    selection=String.valueOf(as5);


                SharedPreferences sPreferences = getSharedPreferences("config", MODE_PRIVATE);
                String info=sPreferences.getString("info","");
                selection = info+";"+selection;
                Save.saveInformation(Question_5.this,selection);

               Intent i = new Intent(Question_5.this,Question_6.class);
                startActivity(i);
            }
        });
    }
}
