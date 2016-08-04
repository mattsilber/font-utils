package com.guardanis.fontutils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import com.guardanis.fontutils.fonts.FontUtils;

public class EditText extends android.widget.EditText {

    private boolean deletePressed = false;

    public EditText(Context context) {
        super(context);
        init(null, 0);
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public EditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public EditText init(AttributeSet attrs, int defStyle) {
        setWillNotDraw(false);

        FontUtils.getInstance(getContext())
                .load(this, attrs, defStyle);

        return this;
    }

    public void setFont(int fontFamilyResId, int style){
        setFont(getResources().getString(fontFamilyResId), style);
    }

    public void setFont(String fontFamily, int style){
        FontUtils.getInstance(getContext())
                .setTypeface(this, fontFamily, style);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new CustomInputConnection(super.onCreateInputConnection(outAttrs), true);
    }

    public boolean isDeletePressed() {
        return deletePressed;
    }

    public void setDeletePressed(boolean deletePressed) {
        this.deletePressed = deletePressed;
    }

    private class CustomInputConnection extends InputConnectionWrapper {

        public CustomInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL)
                EditText.this.deletePressed = true;

            return super.sendKeyEvent(event);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            if(beforeLength == 1 && afterLength == 0)
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));

            return super.deleteSurroundingText(beforeLength, afterLength);
        }
    }
}