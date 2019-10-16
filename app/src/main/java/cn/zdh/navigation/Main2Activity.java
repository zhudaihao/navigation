package cn.zdh.navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Main2Activity extends AppCompatActivity {
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //初始化
        showFragment(0);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    showFragment(1);
                    return true;
                case R.id.navigation_notifications:
                    showFragment(2);
                    return true;
                case R.id.navigation_s:
                    showFragment(3);
                    return true;
            }
            return false;
        }
    };


    //定义个常量为当前页
    private int index = -1;
    private Fragment[] fragments = new Fragment[fragmentName.length];

    public void showFragment(int tag) {
        if (index == tag) {
            return;
        }
        //获取事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (index != -1) {
            //隐藏fragment
            transaction.hide(fragments[index]);

        }
        if (fragments[tag] == null) {
            //创建fragment对象，注意是:fragments[tag]=创建方法
            // fragments[tag] = createFragment(tag);

            //使用反射创建
            createFragment2(tag);
            //添加fragment
            transaction.add(R.id.my_nav_host_fragment, fragments[tag]);
        } else {
            transaction.show(fragments[tag]);
        }

        //提交事务
        transaction.commit();
        index = tag;


    }

    public static final String[] fragmentName = {
            Fragment_A.class.getName(),
            Fragment_B.class.getName(),
            Fragment_C.class.getName(),
            Fragment_D.class.getName(),};

    //创建fragment
    public void createFragment2(int tag) {
        try {
            fragments[tag] = (Fragment) Class.forName(fragmentName[tag]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
