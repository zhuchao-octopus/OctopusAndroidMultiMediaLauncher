package com.octopus.android.car.apps.bluetooth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.octopus.android.car.apps.R;
import com.octopus.android.car.apps.common.BaseViewBindingFragment;
import com.octopus.android.car.apps.databinding.FragmentBluetoothDialBinding;

/**
 * A fragment representing a list of Items.
 */
public class BluetoothDialFragment extends BaseViewBindingFragment<FragmentBluetoothDialBinding> implements View.OnClickListener {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final String TAG = "BluetoothDialFragment";

    public BluetoothDialFragment() {
    }

    public static String getPhoneStateString(int state) {
        return null;
    }

    @Override
    protected FragmentBluetoothDialBinding onCreateViewBinding() {
        return FragmentBluetoothDialBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setListener() {
        int childCount = binding.viewDial.getChildCount();
        for (int i = 0; i < childCount; i++) {
            LinearLayout child = (LinearLayout) binding.viewDial.getChildAt(i);
            int childLL = child.getChildCount();
            for (int j = 0; j < childLL; j++) {
                TextView childView = (TextView) child.getChildAt(j);
                childView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 处理点击事件
                        if (binding.tvPhoneNumber.getText().toString().length() > 12) {
                            //                            Toast.makeText(getContext(), "号码错误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String number = binding.tvPhoneNumber.getText().toString();
                        String numberAll = number + childView.getText().toString();
                        binding.tvPhoneNumber.setText(numberAll);
                    }
                });
            }
        }
        binding.viewDelete.setOnClickListener(this);
        binding.viewCall.setOnClickListener(this);
        binding.viewHung.setOnClickListener(this);
        binding.viewVoice.setOnClickListener(this);
        binding.viewDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                binding.tvPhoneNumber.setText("");
                return false;
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.viewDelete) {
            //删除后一个号码
            String number = binding.tvPhoneNumber.getText().toString();
            if (number.length() == 1) {
                binding.tvPhoneNumber.setText("");
                return;
            }
            if (number.isEmpty()) {
                return;
            }
            number = number.substring(0, number.length() - 1);
            binding.tvPhoneNumber.setText(number);
        } else if (v.getId() == R.id.viewCall) {
            //拨打电话
        } else if (v.getId() == R.id.viewHung) {
            //挂断电话
        } else {
            v.getId();//音量调节
        }

    }

    private void showDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.bottom_vol_dialog, null);
        TextView volTv = dialogView.findViewById(R.id.vol_tv);
        AppCompatSeekBar appCompatSeekBar = dialogView.findViewById(R.id.vol_dial);
        // 创建BottomSheetDialog
        BottomSheetDialog dialog = new BottomSheetDialog(requireActivity());

        // 设置布局到弹框
        dialog.setContentView(dialogView);
        dialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        // 显示弹框
        dialog.show();
        appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volTv.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}