package com.ce.game.screenlocker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ce.game.screenlocker.R;
import com.ce.game.screenlocker.inter.Clickable;
import com.ce.game.screenlocker.inter.KeyboardButtonClickedListener;
import com.ce.game.screenlocker.view.KeyboardButtonView.KeyType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KyleCe on 2016/5/25.
 *
 * @author: KyleCe
 */
public class KeyboardView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private KeyboardButtonClickedListener mKeyboardButtonClickedListener;

    private List<KeyboardButtonView> mButtons;

    private Clickable mClickableController;


    public KeyboardView(Context context) {
        this(context, null);
    }

    public KeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;
        initializeView();
    }

    private void initializeView() {
        if (!isInEditMode()) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            KeyboardView view = (KeyboardView) inflater.inflate(R.layout.view_keyboard, this);

            initKeyboardButtons(view);
        }
    }

    public void assignClickableController(Clickable clickable) {
        mClickableController = clickable;
    }


    /**
     * Init the keyboard buttons (onClickListener)
     */
    private void initKeyboardButtons(KeyboardView view) {
        mButtons = new ArrayList<>();
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_0));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_1));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_2));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_3));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_4));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_5));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_6));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_7));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_8));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_9));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_10));
        mButtons.add((KeyboardButtonView) view.findViewById(R.id.pin_code_button_clear));

        for (View button : mButtons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (mKeyboardButtonClickedListener == null ) return;

        switch (v.getId()) {
            case R.id.pin_code_button_0:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K0);
                break;
            case R.id.pin_code_button_1:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K1);
                break;
            case R.id.pin_code_button_2:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K2);
                break;
            case R.id.pin_code_button_3:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K3);
                break;
            case R.id.pin_code_button_4:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K4);
                break;
            case R.id.pin_code_button_5:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K5);
                break;
            case R.id.pin_code_button_6:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K6);
                break;
            case R.id.pin_code_button_7:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K7);
                break;
            case R.id.pin_code_button_8:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K8);
                break;
            case R.id.pin_code_button_9:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K9);
                break;
            case R.id.pin_code_button_clear:
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K_BACKSPACE);
                break;
            case R.id.pin_code_button_10:// back
                mKeyboardButtonClickedListener.onKeyboardClick(KeyType.K_BACK);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isViewUnClickable()) return true;

        return super.dispatchTouchEvent(ev);

//        return true;
    }

    private boolean isViewUnClickable() {
        return mClickableController != null && !mClickableController.clickable();
    }
    /**
     */
    public void setKeyboardButtonClickedListener(KeyboardButtonClickedListener keyboardButtonClickedListener) {
        this.mKeyboardButtonClickedListener = keyboardButtonClickedListener;
        for (KeyboardButtonView button : mButtons) {
            button.setOnRippleAnimationEndListener(mKeyboardButtonClickedListener);
        }
    }

}
