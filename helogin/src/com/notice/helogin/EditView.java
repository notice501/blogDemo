package com.notice.helogin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


/**
 * 构造的带取消按钮 editText
 * 
 * @author kindac
 */
public class EditView extends RelativeLayout {

    private static final int OBJECT   = 1;
    private static final int PHONE    = 2;
    private static final int EMAIL    = 3;
    private static final int PASSWORD = 4;
    private static final int NAME     = 5;
    private int              point    = 0;

    private Context          context;
    private Medt             edt;
    private ImageButton      btn;
    // 图片2张 true/false
    private Bitmap           icon;
    // 提示信息
    private String           message;
    // 验证控件
    private boolean          isVerify;

    public EditView(Context context) {
        super(context);
        this.context = context;
        message = "";
    }

    public EditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        message = "";
    }

    public EditView isNameEdt() {
        point = NAME;
        init();
        return this;
    }

    public EditView isObjectEdt() {
        point = OBJECT;
        init();
        return this;
    }

    public EditView isPhoneEdt() {
        point = PHONE;
        init();
        if (edt != null) edt.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        return this;
    }

    public EditView isEmailEdt() {
        point = EMAIL;
        init();
        return this;
    }

    public EditView isPassWord() {
        point = PASSWORD;
        init();
        if (edt != null) edt.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        return this;
    }

    /** 控件是否结束 */
    public void isImeOptionDone() {
        if (edt != null) edt.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }

    /** 控件验证信息 */
    public String getMessage() {
        return message;
    }

    /** 控件验证通过 */
    public boolean isVerifiy() {
        return isVerify;
    }

    public void setText(String str) {
        if (edt != null) edt.setText(str);
    }

    public void setText(int id) {
        if (edt != null) edt.setText(id);
    }

    public void setEdit(boolean value) {
        if (value) return;
        edt.setFilters(new InputFilter[] { new InputFilter() {

            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                return source.length() < 1 ? dest.subSequence(dstart, dend) : "";
            }
        } });
        btn.setVisibility(View.INVISIBLE);
    }

    private void init() {
        // 初始化imageButton
        icon = BitmapFactory.decodeResource(getResources(), R.drawable.menu_delete);
        btn = new ImageButton(context);
        btn.setImageBitmap(icon);
        btn.setBackgroundColor(000000);
        btn.setVisibility(View.INVISIBLE);
        btn.setOnTouchListener(tl);
        // 初始化editText对象，根据不同属性，定义不同验证方式
        edt = new Medt(context) {

            @Override
            public void verifyText(final String msg) {
                isVerify = false;
                message = "";
                switch (point) {
                    case OBJECT:
                        verifyOjbect(msg);
                        break;
                    case PHONE:
                        verifyPhone(msg);
                        break;
                    case EMAIL:
                        verifyEmail(msg);
                        break;
                    case PASSWORD:
                        verifyOther(msg);
                        break;
                    case NAME:
                        verifyOther(msg);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void showBtn() {
                if (!btn.isShown()) btn.setVisibility(View.VISIBLE);
            }

            @Override
            public void hideBtn() {
                if (btn.isShown()) btn.setVisibility(View.GONE);
                isVerify = false;
                message = "error";
            }

        };
        edt.setSingleLine();
        edt.setBackgroundResource(R.drawable.myedit);
        edt.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));


        this.addView(edt);
        // rlt排版
        LayoutParams sLayoutParams2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        sLayoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        this.addView(btn, sLayoutParams2);
    }

    private final OnTouchListener tl = new OnTouchListener() {

                                         @Override
                                         public boolean onTouch(View v, MotionEvent event) {
                                             if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                                 v.setBackgroundResource(R.drawable.menu_button_bg);
                                             } else if (event.getAction() == MotionEvent.ACTION_UP
                                                        || event.getAction() == MotionEvent.ACTION_CANCEL) {
                                                 btn.setBackgroundColor(000000);
                                                 edt.setText("");
                                                 isVerify = false;
                                             }
                                             return false;
                                         }

                                     };

    private void verifyOther(String msg) {
        // TODO:
        isVerify = true;
        message = msg.trim();
    }

    private void verifyOjbect(String msg) {
        String name = msg.trim();
        if (name.length() > 255 || !name.matches("^[^\\\\/\\<>\\*\\?\\:\"\\|]+$")) {
            message = "dddd";
        } else {
            isVerify = true;
            message = name;
        }
    }

    private void verifyPhone(String msg) {
        String phone = msg.trim();
        if (phone.length() < 11 || !phone.matches("^(0|[0-9]\\d*)$")) {
            message = "ssssssss";

        } else {
            isVerify = true;
            message = phone;
        }
    }

    private void verifyEmail(String msg) {
        String email = msg.trim();
        if (email.length() > 255 || !email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            message = "rrrrrrr";

        } else {
            isVerify = true;
            message = email;
        }
    }
}

abstract class Medt extends EditText implements EdtInterface {

    // =========================================
    // Private members
    // =========================================

    // =========================================
    // Constructors
    // =========================================
    public Medt(Context context) {
        super(context);
        addTextChangedListener(tx);
    }

    public Medt(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(tx);
    }

    /**
     * @return True = the callback will fire immediately when the content of the EditText is emptied False = The
     * threshold will be used even on empty input
     */

    TextWatcher tx = new TextWatcher() {

                       @Override
                       public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                       }

                       @Override
                       public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                       }

                       @Override
                       public void afterTextChanged(Editable s) {
                           if (s.length() == 0) {
                               hideBtn();
                           } else {
                               showBtn();
                               verifyText(s.toString());
                           }
                       }
                   };

}

interface EdtInterface {

    public void hideBtn();

    public void showBtn();

    public void verifyText(String msg);
}
