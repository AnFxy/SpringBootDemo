package com.example.springbootdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.springbootdemo.databinding.ActivityMainBinding;

import java.util.HashMap;

import io.reactivex.rxjava3.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Toast globalToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    public void initView() {
        globalToast = new Toast(MainActivity.this);
        binding.btnSubmit.setOnClickListener(v -> {
            // 简单校验
            String phoneNumber = binding.editPhone.getText().toString();
            String name = binding.editName.getText().toString();
            String password = binding.editPassword.getText().toString();
            if (phoneNumber.equals("") || name.equals("") || password.equals("")) {
                globalToast.setText("请将信息填写完整！");
                globalToast.show();
                return;
            }
            // call API
            HashMap<String, String> map = new HashMap<>();
            map.put("name", name);
            map.put("phoneNumber", phoneNumber);
            map.put("password", password);
            IServiceImpl.provideService().doRegister(map).subscribe(baseResponse -> {
                if (baseResponse.getCode() != 200) {
                    globalToast.setText(baseResponse.getMessage());
                } else {
                    globalToast.setText("注册成功");
                }
                globalToast.show();
            }, error -> {
                error.printStackTrace();
                globalToast.setText("网络故障");
                globalToast.show();
            });
        });
    }
}