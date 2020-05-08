package com.example.myapplication.ui.flyOfferResultFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.example.myapplication.R;
import com.example.myapplication.apimodels.flyOffer.Datum;
import com.example.myapplication.apimodels.flyOffer.Itinerary;
import com.example.myapplication.apimodels.flyOffer.Segment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.myapplication.R.color.colorPrimaryDark;

public class SegmentMap extends View {
    Datum datum;
    int segmentCount;
    Canvas canvas;
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    Paint textPaint = new Paint();
    List<Rect> rectangles;
    List<Segment> segmentList;
    float radius;
    float rectHeight;
    List<Itinerary> itineraries;
    int flyNumber;



    public SegmentMap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(),colorPrimaryDark));
        paint.setAntiAlias(true);
        paint2.setColor(ContextCompat.getColor(getContext(), R.color.primaryGreyColor));
        paint2.setStrokeWidth(convertDpToPixel(0.5f,getContext()));
        textPaint.setTextSize(convertSpToPixel(12f,getContext()));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(ContextCompat.getColor(getContext(),R.color.primaryGreyColor));
        textPaint.setSubpixelText(true);
        textPaint.setAntiAlias(true);

        radius = convertDpToPixel(1.5f,getContext());
        rectHeight = convertDpToPixel(3f,getContext());
        Log.d("TAG", "radius " + radius + " rectHeight " + rectHeight);
    }


    int width;
    int height;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        //Log.d("TAG", "widthMeasureSpec: " + width);
        //Log.d("TAG", "heightMeasureSpec: " + height);
        if (itineraries !=null){
            setSegments();
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        canvas.drawLine(radius,height - rectHeight/2,width-radius,height - rectHeight/2,paint2);
        //canvas.drawText("VVO",100,height-rectHeight,textPaint);
        //Log.d("TAG", " Вызов onDraw");

        if (rectangles != null){
            for (Rect rectangle:rectangles){

                canvas.drawRect(rectangle,paint);
                canvas.drawCircle(rectangle.left,height-rectHeight/2,radius,paint);
                canvas.drawCircle(rectangle.right,height-rectHeight/2,radius,paint);
                Log.d("TAG", "устанавливаем прямоугольники");
            }
        }

        if (rectangles != null && rectangles.size()>1){
            for (int i = 0; i<rectangles.size()-1;i++){
                int x1 = rectangles.get(i).right;
                int x2 = rectangles.get(i+1).left;
                String transferCode = segmentList.get(i).getArrival().getIataCode();
                canvas.drawText(transferCode,(x1+x2)/2,height-2f*rectHeight,textPaint);
            }
        }
    }

    public void setDatum(Datum datum, int segmentCount) {
        this.datum = datum;
        this.segmentCount = segmentCount;
    }

    public void setSegment(List<Itinerary> itineraries, int flyNumber){
        this.itineraries = itineraries;
        this.flyNumber = flyNumber;
        segmentList =itineraries.get(flyNumber).getSegments();
    }

    public void setSegments() {
        //Получаем кол-во сегментов
        int segmentSize = segmentList.size();
        Log.d("TAG", "Количество сегметов " +segmentSize);
        //получаем время отправления(=минимальная точка диаграммы)
        double minTime = getTimeInMillisecond(segmentList.get(0).getDeparture().getAt());
        //считаем коэффициент перехода
        double k = width/(getTimeInMillisecond(segmentList.get(segmentSize - 1).getArrival().getAt())-minTime);
        ArrayList<Rect> rectangleList = new ArrayList<>();
        //Проходим по сегментам и строим прямоугольники по координатам

        for (Segment segment : segmentList) {

            int rectStart = (int) ((getTimeInMillisecond(segment.getDeparture().getAt()) - minTime)*k);
            int rectStop = (int) ((getTimeInMillisecond(segment.getArrival().getAt()) - minTime)*k);
            if (rectStart == 0){rectStart+=radius;}
            if (rectStop >= width-1){rectStop = (int) (rectStop - radius);}

            Log.d("TAG", "Прямоугольник с началом в " +(rectStart));
            Log.d("TAG", "Прямоугольник с концом в " +(rectStop));
            Rect rect = new Rect(rectStart,(int) (height - rectHeight),rectStop,height);
            rectangleList.add(rect);
        }
        rectangles = rectangleList;
        Log.d("TAG", "Устанавливаем сегменты ");
        requestLayout();
        invalidate();
    }

    public ArrayList<String> getCarrier(){
        ArrayList<String> carriers = new ArrayList<>();

        for (Segment segment:segmentList) {
            carriers.add(segment.getCarrierCode());
        }

        return carriers;
    }


    public float getTimeInMillisecond(String string) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss");
        Date date = null;
        try {
            date = sdf.parse(string);
            c.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Log.d("TAG", "testDate: " + c.getTimeInMillis());
        return c.getTimeInMillis()/1000000f;
    }

    public int getDuration(String string){
        String hours = string.substring(string.indexOf('T')+1,string.indexOf('H'));
        String minutes = string.substring(string.indexOf('H')+1,string.indexOf('M'));
        int flyDuration = Integer.valueOf(hours) * 60 + Integer.valueOf(minutes);
        return flyDuration;
    }

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    public float convertSpToPixel(float sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
