package com.example.administrator.mvp.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.administrator.mvp.Presenter.MainPresenterImpl;
import com.example.administrator.mvp.R;
import com.example.administrator.mvp.View.MainView;

public class MainActivity extends BaseActivity<MainView,MainPresenterImpl> implements MainView {

    private EditText etUserName;
    private EditText etPassWord;
    private MainPresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new MainPresenterImpl(this);

    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassWord = (EditText) findViewById(R.id.etPassWord);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(etUserName.getText().toString().trim(),etPassWord.getText().toString().trim());
            }
        });
    }

    @Override
    public void showToast(String userName, String password) {
        Toast.makeText(this,"userName;"+userName+"\nPassword:"+password, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
  
    }

    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl(this);
    }


}
