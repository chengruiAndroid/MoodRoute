package com.wantide.cr_chen.moodroute.view;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.wantide.cr_chen.moodroute.MyApplication;
import com.wantide.cr_chen.moodroute.R;

/**
 * Created by zcr on 2016/2/23.
 */
public class DrawerView {
    private Resources resources = MyApplication.getAppContext().getResources();
    private ItemClickListener itemClickListener;

    public interface ItemClickListener{
        void itemclick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }



    public Drawer setupDrawerContent(Activity activity , Toolbar toolbar){

        /*初始化侧边栏头部view*/
        AccountHeader headerResult = getDrawerHead(activity);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("我的心情").withIcon(R.drawable.ic_loyalty_black_24dp).withTextColor(resources.getColor(R.color.md_dark_appbar)).withSelectedIcon(R.drawable.ic_loyalty).withSelectedTextColor(resources.getColor(R.color.color_theme)).withSelectedColor(resources.getColor(R.color.app_white_Clear));
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName("心历路程").withIcon(R.drawable.ic_timeline_black_24dp).withTextColor(resources.getColor(R.color.md_dark_appbar)).withSelectedIcon(R.drawable.ic_timeline).withSelectedTextColor(resources.getColor(R.color.color_theme)).withSelectedColor(resources.getColor(R.color.app_white_Clear));
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withName("心情计划").withIcon(R.drawable.ic_bubble_chart_black_24dp).withTextColor(resources.getColor(R.color.md_dark_appbar)).withSelectedIcon(R.drawable.ic_bubble_chart).withSelectedTextColor(resources.getColor(R.color.color_theme)).withSelectedColor(resources.getColor(R.color.app_white_Clear));
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withName("同步数据").withIcon(R.drawable.ic_language_black_24dp).withTextColor(resources.getColor(R.color.md_dark_appbar)).withSelectedIcon(R.drawable.ic_language).withSelectedTextColor(resources.getColor(R.color.color_theme)).withSelectedColor(resources.getColor(R.color.app_white_Clear));
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withName("用户注册").withIcon(R.drawable.ic_perm_data_setting_black_24dp).withTextColor(resources.getColor(R.color.md_dark_appbar)).withSelectedIcon(R.drawable.ic_perm_data_setting).withSelectedTextColor(resources.getColor(R.color.color_theme)).withSelectedColor(resources.getColor(R.color.app_white_Clear));

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        new DividerDrawerItem(),
                        item5
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        itemClickListener.itemclick(position == 6 ? 4 : position - 1);
                        return false;
                    }
                }).build();

        result.getRecyclerView().setVerticalScrollBarEnabled(false);

        return result;
    }


    /**
     * 初始化侧边栏头部view
     * @return
     */
    private AccountHeader getDrawerHead(Activity activity){

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("刘伟").withEmail("liuwei@gmail.com").withIcon(resources.getDrawable(R.drawable.user_head)),
                        new ProfileDrawerItem().withName("孙健").withEmail("sunjian@gmail.com").withIcon(resources.getDrawable(R.drawable.user_head2))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return true;
                    }
                })
                .build();

        return headerResult;
    }








}
