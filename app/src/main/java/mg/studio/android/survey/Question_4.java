package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;


public class Question_4 extends AppCompatActivity {
    private String selection;

    private LinearLayout mlinearlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_4);

        mlinearlayout=findViewById(R.id.linearlayout);

        Button btn4=(Button)findViewById(R.id.btn_question_4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        StringBuilder as4 = new StringBuilder();
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
                                    as4.append(checkbox.getText()+",");
                                }
                            }
                        }
                        if(as4.length()!= 0)
                            selection=String.valueOf(as4);

                SharedPreferences sPreferences = getSharedPreferences("config", MODE_PRIVATE);
                String info=sPreferences.getString("info","");
                selection = info+";"+selection;
                Save.saveInformation(Question_4.this,selection);
                Intent i = new Intent(Question_4.this,Question_5.class);
                startActivity(i);
            }
        });
    }

}
