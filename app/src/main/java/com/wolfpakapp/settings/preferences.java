package com.wolfpakapp.settings;

//SETTINGS/PREFERENCES PAGE, ALL THE MAIN IMPORTANTISH STUFF FOR THE SETTINGS ARE HERE

import android.content.Context;
import android.net.Uri;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ListAdapter;

public class preferences extends PreferenceActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        //Load preferences from xml
        addPreferencesFromResource(R.xml.preferences);

            //THIS PART IS FOR THE SOCIAL MEDIA BUTTONS
        Preference myPref = (Preference) findPreference("followus");
        myPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                createListPreferenceDialog();
                return true;

            }
        });




            //IN SETTINGS BUTTONS ARE DEFINED/MADE HERE
            //THE TUTORIAL WILL BE MADE HERE



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        root.addView(bar, 0); // insert at top
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //THIS CREATES THE DIALOG FOR THE SOCIAL MEDIA BUTTONS
    private void createListPreferenceDialog() {

        Dialog dialog;

        //GET THE NAME OF THE SOCIAL MEDIA PLATFORMS
        final String[] str = getResources().getStringArray(R.array.iconName);

        //SET THE ICONS (NOTE TO SELF: FIND SMALLER GODDAMN ICONS LMAO)
        final Integer[] icons = new Integer[] {R.drawable.tinyfb, R.drawable.twit, R.drawable.insta};

        //COMBINE WITH MAGIC FUNCTION FOUND ON STACKOVERFLOW HERE: https://stackoverflow.com/questions/8533394/icons-in-a-list-dialog
        ListAdapter adapter = new ArrayAdapterWithIcon(this, str, icons);

        //MAKE AND SHOW THE DIALOG

        dialog = new AlertDialog.Builder(this).setTitle("Follow Us on Social Media")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {

                        Intent open;

                        //WHAT HAPPENS WHEN YOU CLICK A THING
                        switch (item) {
                            case(0):
                                open = getOpenFacebookIntent(preferences.this);
                                startActivity(open);
                                break;
                            case(1):
                                open = getOpenTwitterIntent(preferences.this);
                                startActivity(open);
                                break;
                            case(2):

                                //INSTA IS WEIRD SO FUCK IT I'll JUST DO IT THIS WAY

                                Uri uri = Uri.parse("http://instagram.com/_u/wolfpakapp");
                                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                                likeIng.setPackage("com.instagram.android");

                                try {
                                    startActivity(likeIng);
                                } catch (Exception e) {
                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://instagram.com/wolfpakapp")));
                                }
                                break;
                            default:
                                //IS THERE EVEN A DEFAULT FOR THIS? WHO KNOWS? PUT IT ANYWAYS
                                break;
                        }

                    }
                }).show();

       /*
        //THIS ALLOWS YOU TO MOVE THE DIALOG AROUND THE SCREEN WITH OFFSETS

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.x = 0; // left margin
        layoutParams.y = 0; // bottom margin
        dialog.getWindow().setAttributes(layoutParams);*/


    }


    //INTENTS TO OPEN THE SOCIAL MEDIA ACCOUNTS

    //FACEBOOK
    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1444711199174503"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/wolfpak-app"));
        }
    }

    //TWITTER
    public Intent getOpenTwitterIntent(Context context) {

        try {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("twitter://user?screen_name="
                            .concat("wolfpakapp")));

        } catch (Exception e) {
            return new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/#!/".concat("wolfpakapp")));
        }

    }


}