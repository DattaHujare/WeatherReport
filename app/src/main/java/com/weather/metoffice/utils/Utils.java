package com.weather.metoffice.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.weather.metoffice.R;
import com.weather.metoffice.widget.styleabletoast.StyleableToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final String OTP_DELIMITER = ":";
    //    public static String BASE_URL = "http://webwingdemo.com/";
    public static final String DATE_FORMAT_DMY = "dd-MM-yyyy";
    public static final String regexUserName = "^[A-Za-z\\s]+$";
    //    public static String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^])(?=.*\\d).*";
    public static final String PASSWORD_PATTERN = "(?=^.{6,}$)(?=.*\\d)(?=.*[-!@#$%^&*?])(?=.*[A-Z])(?=.*[a-z]).*$";
    public static String NO_INTERNET_MSG = "You don't have internet connection.Please connect to internet";
    public static String NO_INTERNET_TITLE = "No Internet Connection";
    public static String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
    public static int MIN_PASSWORD_LENGTH = 6;
    public static int MAX_PASSWORD_LENGTH = 20;
    public static int MOBILE_NO_LENGTH = 10;
    public static int MIN_HR = 1;
    public static int MAX_HR = 11;
    public static int MIN_MINUTE = 00;
    public static int MAX_MINUTE = 59;
    public static String MOBILE_PATTERN = "[0-9]+";
    public static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //    public static String BASE_URL = "http://webwingtechnologies.co.in";
    public static String BASE_URL = "http://192.168.1.118";
    public static String FRESH_CHAT_APP_ID = "34cfcd3b-4e21-4c53-a8e6-8758bffd395a";
    public static String FRESH_CHAT_APP_KEY = "53b4a671-1eb1-4fc1-82fd-398139cc240e";
    public static String UNPROPER_RESPONSE = "Unable to get proper response from server. Please, Try Again.";
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static String checkNotNull(String s) {
        if (s != null && !TextUtils.isEmpty(s) && !s.equalsIgnoreCase("null")) {
            return s;
        }
        return "";
    }

    // validating email id
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getCurrent_Date() {
        DateFormat df = new SimpleDateFormat("dd-MM-yy");
        Date dateobj = new Date();
        return df.format(dateobj);
    }

    public static boolean isValidName(String Name) {
        Pattern pattern = Pattern.compile(regexUserName);
        Matcher nameMatcher = pattern.matcher(Name);
        return nameMatcher.matches();
    }

    public static boolean isValidPassword(String Password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(Password);
        return matcher.matches();
    }

    public static String getYesterdayDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    // validating mobile no
    public static boolean isValidMobile(String mobile) {
        Pattern pattern = Pattern.compile(MOBILE_PATTERN);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    public static void device_metrics(Activity mContext) {
        //Determine density
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        if (density == DisplayMetrics.DENSITY_HIGH) {
            Toast.makeText(mContext, "DENSITY_HIGH... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_MEDIUM) {
            Toast.makeText(mContext, "DENSITY_MEDIUM... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_LOW) {
            Toast.makeText(mContext, "DENSITY_LOW... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_XHIGH) {
            Toast.makeText(mContext, "DENSITY_XHIGH... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_XXHIGH) {
            Toast.makeText(mContext, "DENSITY_XXHIGH... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_XXXHIGH) {
            Toast.makeText(mContext, "DENSITY_XXXHIGH... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        }
    }

    public static String getDateFormated(String dtStart) {
        String strDate = "";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        strDate = formatter.format(date);
        Log.e("Utils", "getDateFormated: Converted Date:" + strDate);
        return strDate;
    }

    /*public static String formatDate(String formatDate) {

        String strDate = "";

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(formatDate);
            strDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return strDate;
    }
*/
    public static String formatDate(String formatDate) {

        String strDate = "";

        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = dt.parse(formatDate);
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println(dt1.format(date));
            strDate = dt1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return strDate;
    }


    public static Date getConvertedDate(String dtStart) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Mobile No Validation
     *
     * @param no
     * @return true if it is valid else false
     */
    public static boolean isAcceptableMobile(String no) {
        if (TextUtils.isEmpty(no)) {
            System.out.println("empty string.");
            return false;
        }
        no = no.trim();
        int len = no.length();
        if (len < MOBILE_NO_LENGTH || len > MOBILE_NO_LENGTH) {
            System.out.println("Mobile No must have 10 digits");
            return false;
        }
        return true;
    }

    public static void showSuccessToast(Context context, String message) {
        StyleableToast styleableToast = new StyleableToast.Builder(context).text(message).textColor(Color.WHITE)
                .duration(Toast.LENGTH_SHORT).cornerRadius(23)
                .backgroundColor(context.getResources().getColor(R.color.colorPrimaryDark)).build();
        styleableToast.show();
    }

    public static void showErrorToast(Context context, String message) {
        StyleableToast styleableToast = new StyleableToast.Builder(context).text(message).textColor(Color.WHITE)
                .duration(Toast.LENGTH_SHORT).cornerRadius(23)
                .backgroundColor(Color.RED).build();
        styleableToast.show();
    }

    public static void showLongErrorToast(Context context, String message) {
        StyleableToast styleableToast = new StyleableToast.Builder(context).text(message).textColor(Color.WHITE)
                .duration(Toast.LENGTH_LONG).cornerRadius(23)
                .backgroundColor(Color.RED).build();
        styleableToast.show();
    }

    public static void showOtherToast(Context context, String message) {
        StyleableToast styleableToast = new StyleableToast.Builder(context).text(message).textColor(Color.WHITE)
                .duration(Toast.LENGTH_SHORT).cornerRadius(23)
                .backgroundColor(Color.BLUE).build();
        styleableToast.show();
    }

    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    // convert InputStream to String
    public static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(activity
                        .getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static void hideSoftKeyboardInFragment(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());
            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return key;
    }

    public static String format(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        return format.format(n);
    }

    public static void setupUI(final Activity context, final View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Utils.hideSoftKeyboard(context);
                    v.requestFocus();
                    // et_Searchrest.setError(null);
                    return false;
                }
            });
        }

        // If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(context, innerView);
            }
        }
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    ///
    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;
        return finalTimerString;
    }


    public static int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }

    public static int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }

    public static int measureContentWidth(ListAdapter listAdapter, Context context) {
        ViewGroup mMeasureParent = null;
        int maxWidth = 0;
        View itemView = null;
        int itemType = 0;

        final ListAdapter adapter = listAdapter;
        final int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            final int positionType = adapter.getItemViewType(i);
            if (positionType != itemType) {
                itemType = positionType;
                itemView = null;
            }

            if (mMeasureParent == null) {
                mMeasureParent = new FrameLayout(context);
            }

            itemView = adapter.getView(i, itemView, mMeasureParent);
            itemView.measure(widthMeasureSpec, heightMeasureSpec);

            final int itemWidth = itemView.getMeasuredWidth();

            if (itemWidth > maxWidth) {
                maxWidth = itemWidth;
            }
        }

        return maxWidth;
    }

    public static String GetErrorCode(Context context, String Error_Code) {

        switch (Error_Code) {
            case "106":
                return "";
            case "107":
                return "";
            case "111":
                return "";
            case "112":
                return "";
            case "121":
                return "";
            case "124":
                return "";
            case "126":
                return "";
            case "127":
                return "";
            case "129":
                return "";
            case "130":
                return "";
            case "131":
                return "";
            case "134":
                return "";
            case "136":
                return "";
            case "137":
                return "";
            case "140":
                return "";
            case "141":
                return "";
            case "142":
                return "";
            case "143":
                return "";
            case "147":
                return "";
            case "152":
                return "";
            case "153":
                return "";
            default:
                return "";
        }

    }

    public static void ShowSnack(CoordinatorLayout coordinatorLayout, String msg, int duration, int textColor) {

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, msg, duration);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(textColor);
        snackbar.show();
    }

    /**
     * Showing a Snackbar with error message
     * The error body will be in json format
     * {"error": "Error message!"}
     */
    public static void ShowError(Throwable e, Context context, CoordinatorLayout coordinatorLayout) {
        String message = "";
        try {

            if (e instanceof UnknownHostException) {
                message = context.getString(R.string.unknown_server);
            }
            if (e instanceof MalformedJsonException) {
                message = UNPROPER_RESPONSE;
            }
            if (e instanceof IOException) {
                message = context.getString(R.string.no_internet_connection);
            }
            if (e instanceof SocketTimeoutException) {
                message = context.getString(R.string.failed_to_connect);
            }
            if (e instanceof HttpException) {
                HttpException error = (HttpException) e;
                String errorBody = error.response().errorBody().string();
                JSONObject jObj = new JSONObject(errorBody);
                message = jObj.getString("error");
            } else if (e.getMessage().contains("MalformedJsonException")) {
                message = Utils.UNPROPER_RESPONSE;
            }
        } catch (IOException e1) {
            Log.e("IOException=", "" + e1);
        } catch (JSONException e1) {
            Log.e("JSONException=", "" + e1);
        } catch (Exception e1) {
            Log.e("Exception=", "" + e1);
        }

        if (TextUtils.isEmpty(message)) {
            message = context.getString(R.string.unknown_error_occurred);
        }

        Utils.ShowSnack(coordinatorLayout, message, Snackbar.LENGTH_LONG, Color.YELLOW);

    }

    public static String getTime(String olddate) {
        String newDate = null;
        //2017-08-19 06:38:47
        try {
            System.out.println(olddate);
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            date = dt.parse(olddate);
            SimpleDateFormat dt1 = new SimpleDateFormat("hh:mm aa");
            System.out.println(dt1.format(date));
            newDate = dt1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String getDate(String olddate) {
        String newDate = null;
        //2017-08-19 06:38:47
        try {
            System.out.println(olddate);
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = null;
            date = dt.parse(olddate);
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println(dt1.format(date));
            newDate = dt1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

}
