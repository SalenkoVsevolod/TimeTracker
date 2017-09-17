package svs.timetracker.presentation.custom_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import svs.timetracker.R;


public class NeonTextView extends AppCompatTextView implements ValueAnimator.AnimatorUpdateListener {
    private static final String TAG = "NeonTextView";
    private static final int CONSTANT = 0;
    private static final int BLINKING = 1;
    private static final int SEQUENTIAL_PULSATION = 2;
    private static final int RANDOM_SPLASH = 3;
    private static final int INTERACTIVE = 4;

    private float neonRadius;
    private int neonColor;
    private int blinkDuration;
    private int pulseDuration;
    private int pulsationPause;
    private int textColor;
    private ValueAnimator valueAnimator;

    public NeonTextView(Context context) {
        super(context);
    }

    public NeonTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NeonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray neonTypedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NeonTextView, 0, 0);
        final int[] defAttrs = {android.R.attr.textColor};
        final TypedArray textTypedArray = context.getTheme().obtainStyledAttributes(attrs, defAttrs, 0, 0);
        try {
            final int mode = neonTypedArray.getInt(R.styleable.NeonTextView_neonMode, 0);
            neonRadius = neonTypedArray.getFloat(R.styleable.NeonTextView_neonRadius, -1);
            if (neonRadius == -1) {
                initNeonRadius(mode);
            }
            blinkDuration = neonTypedArray.getInt(R.styleable.NeonTextView_blinkDuration, 1);
            pulseDuration = neonTypedArray.getInt(R.styleable.NeonTextView_pulseDuration, 2000);
            pulsationPause = neonTypedArray.getInt(R.styleable.NeonTextView_pulsationPause, 1000);
            neonColor = neonTypedArray.getInt(R.styleable.NeonTextView_neonColor, -1);
            textColor = textTypedArray.getColor(0, 0);
            if (neonColor == -1) {
                neonColor = textColor;
            }
            handleNeonMode(mode);
        } finally {
            neonTypedArray.recycle();
        }

    }

    private void initNeonRadius(final int mode) {
        switch (mode) {
            default:
            case CONSTANT:
                neonRadius = 20;
                break;
            case BLINKING:
                neonRadius = 80;
                break;
            case SEQUENTIAL_PULSATION:
                neonRadius = 40;
                break;
        }
    }

    private void handleNeonMode(final int mode) {
        switch (mode) {
            default:
            case CONSTANT:
                setShadowLayer(neonRadius, 0, 0, neonColor);
                setTextColor(changeColorBrightness(neonRadius));
                break;
            case BLINKING:
                startBlinking();
                break;
            case SEQUENTIAL_PULSATION:
                startPulsation();
                break;
        }
    }

    private void startBlinking() {
        valueAnimator = ValueAnimator.ofFloat(0, neonRadius);
        valueAnimator.addUpdateListener(this);
        valueAnimator.setDuration(blinkDuration);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }

    private void startPulsation() {
        Observable.interval(0, pulsationPause + pulseDuration * 2, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        valueAnimator = ValueAnimator.ofFloat(6, neonRadius);
                        valueAnimator.addUpdateListener(NeonTextView.this);
                        valueAnimator.setDuration(pulseDuration);
                        valueAnimator.setRepeatCount(1);
                        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
                        valueAnimator.start();
                    }
                });
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        final float value = (float) valueAnimator.getAnimatedValue();
        Log.i(TAG, "onAnimationUpdate: " + value);
        setTextColor(changeColorBrightness(value));
        setShadowLayer(value, 0, 0, neonColor);
    }

    private int changeColorBrightness(float brightnessValue) {
        final float[] hsl = new float[3];
        ColorUtils.colorToHSL(textColor, hsl);
        hsl[2] += (brightnessValue / 100);
        Log.i(TAG, "changeColorBrightness: h:" + hsl[0] + ", s:" + hsl[1] + ", l:" + hsl[2]);
        Log.i(TAG, "changeColorBrightness: brightnessValue:" + brightnessValue);
        return ColorUtils.HSLToColor(hsl);
    }
}
