package org.didd.dev.scheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.didd.dev.R;
import org.didd.scheme.SchemeUtil;

import java.io.File;

public class SchemeActivity extends AppCompatActivity {

    private TextView schemeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);

        schemeTv = findViewById(R.id.text_scheme);

        String codePath = getPackageCodePath();

        String value = SchemeUtil.getScheme(new File(codePath));
        String channel = SchemeUtil.getScheme(new File(codePath), "channel");
        String uniqueCode = SchemeUtil.getScheme(new File(codePath), "uniqueCode");
        String cid = SchemeUtil.getScheme(new File(codePath), "cid");
        String flavor = SchemeUtil.getScheme(new File(codePath), "flavor");

        addTxt("codePath" + ":" + codePath);
        addTxt("value" + ":" + value);
        addTxt("channel" + ":" + channel);
        addTxt("uniqueCode" + ":" + uniqueCode);
        addTxt("cid" + ":" + cid);
        addTxt("flavor" + ":" + flavor);
    }

    private void addTxt(String text) {
        String txt = schemeTv.getText() + "\n" + text;
        schemeTv.setText(txt);
    }
}
