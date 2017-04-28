# EasyVerticalSwitcher
A light vertical switcher for Android.

## ScreenShots
![easyverticalswitcher](https://github.com/huzenan/EasyVerticalSwitcher/blob/master/screenshots/easy%20vertical%20switcher.gif)

## Usage
### 1.Define your custom layout "layout_switcher.xml":
```xml
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#ffffff"
        android:gravity="center_vertical"
        android:paddingBottom="5dp"
        android:paddingLeft="16dp"
        android:paddingRight="16dp"
        android:paddingTop="5dp"
        >

        <ImageView
            android:id="@+id/iv_icon"
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@mipmap/ic_launcher"
            />

        <TextView
            android:id="@+id/tv_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:ellipsize="end"
            android:textColor="#000000"
            android:textSize="16sp"
            />
    </LinearLayout>
```
### 2.Inherit EasyVerticalSwitcher and write your own updateView(Object, View) codes:
```java
    public class CustomTextSwitcher extends EasyVerticalSwitcher {

        ...

        @Override
        public void updateView(Object data, View view) {
            final MyEntity entity = (MyEntity) data;
            TextView tvText = (TextView) view.findViewById(R.id.tv_text);
            tvText.setText(entity.text);
            tvText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), entity.text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
```

### 3.Enjoy your Custom Switcher
>layout

```xml
    <com.hzn.easyverticalswitcher.CustomTextSwitcher
        android:id="@+id/switcher"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
```
>Activity

```java
    // data list
    ArrayList<Object> dataList = new ArrayList<>();
    dataList.add(new MyEntity("text"));
    ...
    
    // init switcher
    switcher.setView(R.layout.layout_switcher);
    switcher.setDataList(dataList);
    switcher.setAutoSwitchDelay(2000);
    
    // show next
    switcher.showNextView();
    
    // auto switch
    switcher.startAutoSwitch();
    switcher.stopAutoSwitch();
```
