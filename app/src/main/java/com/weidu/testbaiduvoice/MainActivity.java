package com.weidu.testbaiduvoice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.voicerecognition.android.VoiceRecognitionConfig;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;

import java.util.ArrayList;

import static android.speech.SpeechRecognizer.RESULTS_RECOGNITION;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView iv_send;

    private String res;//语音识别的结果
    private Button bt_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        iv_send = (ImageView) findViewById(R.id.iv_send);
        bt_start = findViewById(R.id.bt_start);
        bt_start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startVoice();

    }

    public void startVoice() {
        //开启语音识别
        Intent intent = new Intent("com.baidu.action.RECOGNIZE_SPEECH");
        // 设置离线的授权文件(离线模块需要授权), 该语法可以用自定义语义工具生成, 链接http://yuyin.baidu.com/asr#m5
        intent.putExtra("decoder",0);
        intent.putExtra("grammar", "asset:///baidu_speech_grammardemo.bsg");
        startActivityForResult(intent, 1);
    }

    //语音识别返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle results = data.getExtras();
            ArrayList<String> results_recognition = results.getStringArrayList("results_recognition");

            //将数组形式的识别结果变为正常的String类型，例：[给张三打电话]变成给张三打电话
            String str = results_recognition + "";
            res = str.substring(str.indexOf("[") + 1, str.indexOf("]"));
            Log.i("test_voice", "str = " + results_recognition.get(0));

        }
    }

}
