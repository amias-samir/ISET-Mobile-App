package np.com.naxa.iset.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import np.com.naxa.iset.R;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class EmergencyActivity extends AppCompatActivity {

    @BindView(R.id.iv_alert)
    ImageView ivAlert;
    @BindView(R.id.iv_strobe_light)
    ImageView ivStrobeLight;
    @BindView(R.id.sb_strobe_strength)
    SeekBar sbStrobeStrength;
    @BindView(R.id.iv_torch_light)
    ImageView ivTorchLight;

    private final int RESULT_CAMERA = 150;
    private MutableLiveData<Boolean> torchStatus;
    private MutableLiveData <Boolean> strobeStatus;
    private int strobeValue;

    public static void start(Context context) {
        Intent intent = new Intent(context, EmergencyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        ButterKnife.bind(this);

        initValues();

        requestPermissionForTorch();

        sbStrobeStrength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                strobeValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void initValues() {
        torchStatus = new MutableLiveData<>();
        strobeStatus = new MutableLiveData<>();
        torchStatus.setValue(false);
        strobeStatus.setValue(false);
        strobeValue = 0;
    }

    @OnClick({R.id.iv_alert, R.id.iv_strobe_light, R.id.iv_torch_light})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_alert:
                setWhistleTest();
                break;
            case R.id.iv_strobe_light:
                if (torchStatus.getValue()) {
                    torchStatus.setValue(false);
                }

                if (strobeStatus.getValue()) {
                    strobeStatus.setValue(false);

                } else {
                    strobeStatus.setValue(true);
                }
                break;
            case R.id.iv_torch_light:
                if (strobeStatus.getValue()) {
                    strobeStatus.setValue(false);
                }

                if (torchStatus.getValue()) {
                    torchStatus.setValue(false);
                } else {
                    torchStatus.setValue(true);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RESULT_CAMERA)
    private void requestPermissionForTorch() {
        String camera = Manifest.permission.CAMERA;
        if (EasyPermissions.hasPermissions(this, camera)) {
            torchStatus.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    if (aBoolean) {
                        setTorchLightState(true);
                    } else {
                        setTorchLightState(false);
                    }
                }
            });

            strobeStatus.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    if (aBoolean) {
                        sbStrobeStrength.setVisibility(View.VISIBLE);
                        setStrobeLight();
                    } else {
                        sbStrobeStrength.setVisibility(View.GONE);
                    }
                }
            });

        } else {
            EasyPermissions.requestPermissions(this, "Provide Camera Permission",
                    RESULT_CAMERA, camera);
        }
    }

    @SuppressLint("NewApi")
    private void toggleFlashLightNew(boolean value) {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, value);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void toggleFlashLightOld(boolean value) {
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        if (value) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();
        } else {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.stopPreview();
        }
    }

    private void setStrobeLight() {

        if (!strobeStatus.getValue()) {
            setTorchLightState(false);
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (torchStatus.getValue()) {
                    torchStatus.setValue(false);
                } else {
                    torchStatus.setValue(true);
                }
                setStrobeLight();
            }
        }, (100 - strobeValue) * 10);
    }

    private void setTorchLightState(boolean value) {
        boolean higherSdkVerison = Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP;
        if (higherSdkVerison) {
            toggleFlashLightNew(value);
        } else {
            toggleFlashLightOld(value);
        }
    }

    private void setWhistleTest() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.whistle);

        if (mp.isPlaying()) {
            mp.stop();
        } else {
            mp.start();
        }

    }

}
