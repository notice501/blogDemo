package com.notice.chatlist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class ChatListActivity extends Activity {

    private ArrayList<DetailEntity> date;
    private ListView                lv;
    private Button                  bt_send;
    private Drawable                background = null;
    private ImageButton             choice;
    private DetailEntity            item;
    private EditText                et;
    private DetailAdapter           mAdapter;
    private PopupWindow             mPop;
    private View                    root;
    private RadioGroup              rg;
    private LayoutInflater          inflater;
    private Button                  bt_sm;
    private Button                  bt_pic;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        this.mPop = new PopupWindow(this);
        lv = (ListView) findViewById(R.id.lv);
        bt_send = (Button) findViewById(R.id.chatting_send_btn);
        et = (EditText) findViewById(R.id.chatting_content_et);
        choice = (ImageButton) findViewById(R.id.chatting_attach_btn);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = (ViewGroup) inflater.inflate(R.layout.msg_type_chooser, null);
        preShow();

        // mPop = new PopupWindow(getLayoutInflater().inflate(R.layout.msg_type_chooser, null),
        // LayoutParams.WRAP_CONTENT,
        // LayoutParams.WRAP_CONTENT);

        mPop.setTouchInterceptor(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    mPop.dismiss();

                    return true;
                }

                return false;
            }
        });
        rg = (RadioGroup) root.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mode_voice_btn:
                        Toast.makeText(ChatListActivity.this, "voice", Toast.LENGTH_SHORT).show();
                        mPop.dismiss();
                        break;
                    case R.id.mode_msg_btn:
                        Toast.makeText(ChatListActivity.this, "msg", Toast.LENGTH_SHORT).show();
                        mPop.dismiss();
                        break;
                }
            }
        });
        bt_sm = (Button) root.findViewById(R.id.smiley_btn);
        bt_pic = (Button) root.findViewById(R.id.add_camera_btn);

        bt_sm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(ChatListActivity.this, "smily", Toast.LENGTH_SHORT).show();
                mPop.dismiss();
            }
        });

        date = new ArrayList<DetailEntity>();
        mAdapter = new DetailAdapter(this, date);
        if (!date.isEmpty()) {

            lv.setAdapter(mAdapter);
        }
        bt_send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = et.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss     ");
                Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
                String time = formatter.format(curDate);
                item = new DetailEntity(content, R.layout.message_detail_friend_item, time);
                date.add(item);
                lv.setAdapter(mAdapter);
            }
        });

        choice.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mPop.isShowing()) {
                    mPop.dismiss();
                } else {
                    mPop.showAtLocation(ChatListActivity.this.findViewById(R.id.ll), Gravity.BOTTOM, 0, 55);
                }
            }
        });

    }

    /**
     * 预处理窗口
     */
    protected void preShow() {
        if (root == null) {
            throw new IllegalStateException("需要为弹窗设置布局");
        }

        // 背景是唯一能确定popupwindow宽高的元素，这里使用root的背景，但是需要给popupwindow设置一个空的BitmapDrawable
        if (background == null) {
            mPop.setBackgroundDrawable(new BitmapDrawable());
        } else {
            mPop.setBackgroundDrawable(background);
        }

        mPop.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mPop.setTouchable(true);
        mPop.setFocusable(true);
        mPop.setOutsideTouchable(true);
        // 指定布局
        mPop.setContentView(root);
    }

    // private void initPopWindow() {
    // if (mPop == null) {
    // mPop = new PopupWindow(getLayoutInflater().inflate(R.layout.msg_type_chooser, null),
    // LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    //
    // }
    // if (mPop.isShowing()) {
    // mPop.dismiss();
    // }
    //
    // }

    // private void prepareDate() {
    // date = new ArrayList<DetailEntity>();
    // DetailEntity item1 = new DetailEntity("try++++++++++++++++++++++" + "\n" + "MMMMM",
    // R.layout.message_detail_me_item, "2011-8-22");
    // date.add(item1);
    // DetailEntity item2 = new DetailEntity("ok", R.layout.message_detail_friend_item, "2011-8-22");
    // date.add(item2);
    //
    // }
}
