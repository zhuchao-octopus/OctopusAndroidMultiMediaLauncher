package com.octopus.android.car.apps.radio;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.octopus.android.car.apps.R;
import com.octopus.android.car.apps.databinding.ActivityRadioBinding;
import com.zhuchao.android.session.base.BaseActivity;

public class RadioActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RadioActivity";
    private final String RADIO_PACKAGE_NAME = "com.car.radio";
    private final String[] recommendChannelFM = new String[]{"89.80", "94.20", "91.20", "95.80", "99.10", "101.20"};
    private final String[] recommendChannelAM = new String[]{"522", "612", "635", "755", "845", "956"};
    private ActivityRadioBinding binding;
    private int sBand, sFreq;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        // 初始化绑定类实例
        binding = ActivityRadioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.viewFmOrAm.setOnClickListener(this);
        binding.viewRight.setOnClickListener(this);
        binding.viewLeft.setOnClickListener(this);
        binding.viewSearch.setOnClickListener(this);
        binding.viewNextChannel.setOnClickListener(this);
        binding.viewPrevChannel.setOnClickListener(this);
        binding.viewAF.setOnClickListener(this);
        binding.imageAfBg.setOnClickListener(this);
        binding.viewTA.setOnClickListener(this);
        binding.imageTa.setOnClickListener(this);
        binding.viewLeftChannel1.setOnClickListener(this);
        binding.viewLeftChannel2.setOnClickListener(this);
        binding.viewLeftChannel3.setOnClickListener(this);
        binding.viewRightChannel1.setOnClickListener(this);
        binding.viewRightChannel2.setOnClickListener(this);
        binding.viewRightChannel3.setOnClickListener(this);
        binding.viewRDS.setOnClickListener(this);
        binding.viewRDSVi.setOnClickListener(this);
        binding.viewDoubleCircle.setOnClickListener(this);
        binding.viewPTY.setOnClickListener(this);
        binding.viewSignal.setOnClickListener(this);
        binding.viewEq.setOnClickListener(this);

        binding.seekBarFm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int index, boolean fromUser) {
                progress = index;
                //                ApiRadio.freq(ApiRadio.FREQ_DIRECT, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onProgressChanged: stop" + progress);

            }
        });
        binding.seekBarAm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int index, boolean fromUser) {
                progress = index;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onProgressChanged: stop" + progress);
            }
        });
    }

    /**
     * @param list 频道内容
     * @param fmAm 0代表 fm  1代表 AM
     */
    @SuppressLint("SetTextI18n")
    private void setChanelDate(String[] list, int fmAm) {
        //赋值到推荐频道
        if (fmAm == 0) {
            binding.tvSearchUnit1.setText("MHz");
            binding.tvSearchUnit2.setText("MHz");
            binding.tvSearchUnit3.setText("MHz");
            binding.tvSearchUnit4.setText("MHz");
            binding.tvSearchUnit5.setText("MHz");
            binding.tvSearchUnit6.setText("MHz");
            binding.tvMaxChannel.setText("108.00");
            binding.tvMinChannel.setText("87.50");
        } else {
            binding.tvSearchUnit1.setText("KHz");
            binding.tvSearchUnit2.setText("KHz");
            binding.tvSearchUnit3.setText("KHz");
            binding.tvSearchUnit4.setText("KHz");
            binding.tvSearchUnit5.setText("KHz");
            binding.tvSearchUnit6.setText("KHz");
            binding.tvMaxChannel.setText("1620");
            binding.tvMinChannel.setText("522");
        }
        binding.tvSearchChannel1.setText(list[0]);
        binding.tvSearchChannel2.setText(list[1]);
        binding.tvSearchChannel3.setText(list[2]);
        binding.tvSearchChannel4.setText(list[3]);
        binding.tvSearchChannel5.setText(list[4]);
        binding.tvSearchChannel6.setText(list[5]);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

    }

    /**
     * 弹框选项
     *
     * @param items pty类型
     */
    private void showAlertDialogPTY(String[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("PTY");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 这里处理选项点击事件
                Toast.makeText(getApplicationContext(), "选择了: " + items[which], Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // 关闭弹框
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭收音机
        //        CarService.me().cmd(ApiMain.CMD_KILL_APP, RADIO_PACKAGE_NAME);
    }
}