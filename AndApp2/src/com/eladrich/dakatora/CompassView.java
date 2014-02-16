package com.eladrich.dakatora;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class CompassView extends View
{
  private final float HEIGHT_MOVE = 0.130312F;
  private final float WIDTH_MOVE = 0.016997F;
  private Bitmap baseBitmap;
  private int baseDifX;
  private Bitmap baseHighlightBitmap;
  Matrix baseMatrix;
  private boolean firstTime;
  private boolean isHighlight;
  private boolean isTfila;
  private int northDifX;
  private int northDifY;
  private float northDirection = 0.0F;
  private Bitmap northNeedleBitmap;
  private int northNeedleHeight;
  Matrix northNeedleMatrix;
  private int northNeedleWidth;
  private Paint paint = new Paint(1);
  private float scaleHeight;
  private float scaleWidth;
  private int tfilaDifX;
  private int tfilaDifY;
  private float tfilaDirection = 0.0F;
  private Bitmap tfilaNeedleBitmap;
  private int tfilaNeedleHeight;
  Matrix tfilaNeedleMatrix;
  private int tfilaNeedleWidth;

  public CompassView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public CompassView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public CompassView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void init()
  {
    this.tfilaNeedleBitmap = BitmapFactory.decodeResource(getResources(), 2130837715);
    this.northNeedleBitmap = BitmapFactory.decodeResource(getResources(), 2130837689);
    this.baseBitmap = BitmapFactory.decodeResource(getResources(), 2130837598);
    this.baseHighlightBitmap = BitmapFactory.decodeResource(getResources(), 2130837599);
    this.tfilaNeedleWidth = this.tfilaNeedleBitmap.getWidth();
    this.tfilaNeedleHeight = this.tfilaNeedleBitmap.getHeight();
    this.northNeedleWidth = this.northNeedleBitmap.getWidth();
    this.northNeedleHeight = this.northNeedleBitmap.getHeight();
    this.paint.setStyle(Paint.Style.STROKE);
    this.paint.setStrokeWidth(3.0F);
    this.paint.setColor(-1);
    this.paint.setTextSize(30.0F);
    this.firstTime = true;
    this.isTfila = true;
    this.baseMatrix = new Matrix();
    this.northNeedleMatrix = new Matrix();
    this.tfilaNeedleMatrix = new Matrix();
  }

  private int measureHeight(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    int j = View.MeasureSpec.getSize(paramInt);
    if (i == 0);
    for (int k = this.baseBitmap.getHeight(); ; k = j)
      return k;
  }

  private int measureWidth(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    int j = View.MeasureSpec.getSize(paramInt);
    if (i == 0);
    for (int k = this.baseBitmap.getWidth(); ; k = j)
      return k;
  }

  public void highlightBase(boolean paramBoolean)
  {
    if (!this.isTfila)
      this.isHighlight = false;
    while (true)
    {
      return;
      if ((paramBoolean) && (!this.isHighlight))
      {
        this.isHighlight = true;
        continue;
      }
      if ((paramBoolean) || (!this.isHighlight))
        continue;
      this.isHighlight = false;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    this.baseMatrix.setScale(this.scaleWidth, this.scaleHeight);
    this.baseMatrix.postTranslate(this.baseDifX / 2, 0.0F);
    if (this.isHighlight)
      paramCanvas.drawBitmap(this.baseHighlightBitmap, this.baseMatrix, null);
    while (true)
    {
      this.firstTime = false;
      if (!this.firstTime)
      {
        this.northNeedleMatrix.setScale(this.scaleWidth, this.scaleHeight, this.northNeedleWidth / 2, this.northNeedleHeight / 2);
        this.northNeedleMatrix.preRotate(-this.northDirection, this.northNeedleWidth / 2, this.northNeedleHeight / 2);
        this.northNeedleMatrix.postTranslate(this.northDifX / 2 + 0.016997F * this.northNeedleWidth * this.scaleWidth, this.northDifY / 2 + 0.130312F * this.northNeedleHeight * this.scaleHeight);
        paramCanvas.drawBitmap(this.northNeedleBitmap, this.northNeedleMatrix, null);
        if (this.isTfila)
        {
          this.tfilaNeedleMatrix.setScale(this.scaleWidth, this.scaleHeight, this.tfilaNeedleWidth / 2, this.tfilaNeedleHeight / 2);
          this.tfilaNeedleMatrix.preRotate(-this.tfilaDirection, this.tfilaNeedleWidth / 2, this.tfilaNeedleHeight / 2);
          this.tfilaNeedleMatrix.postTranslate(this.tfilaDifX / 2 + 0.016997F * this.tfilaNeedleWidth * this.scaleWidth, this.tfilaDifY / 2 + 0.130312F * this.tfilaNeedleHeight * this.scaleHeight);
          paramCanvas.drawBitmap(this.tfilaNeedleBitmap, this.tfilaNeedleMatrix, null);
        }
      }
      return;
      paramCanvas.drawBitmap(this.baseBitmap, this.baseMatrix, null);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = Math.min(measureWidth(paramInt1), measureHeight(paramInt2));
    int j = Math.max(this.baseBitmap.getWidth(), this.baseBitmap.getHeight());
    this.scaleWidth = (i / j);
    this.scaleHeight = (i / j);
    this.baseDifX = Math.abs(i - (int)(this.baseBitmap.getWidth() * this.scaleWidth));
    Math.abs(i - (int)(this.baseBitmap.getHeight() * this.scaleHeight));
    this.tfilaDifX = Math.abs(i - this.tfilaNeedleBitmap.getWidth());
    this.tfilaDifY = Math.abs(i - this.tfilaNeedleBitmap.getHeight());
    this.northDifX = Math.abs(i - this.northNeedleBitmap.getWidth());
    this.northDifY = Math.abs(i - this.northNeedleBitmap.getHeight());
    setMeasuredDimension(i, i);
  }

  public void updateDirection(float paramFloat1, float paramFloat2)
  {
    this.firstTime = false;
    this.tfilaDirection = paramFloat1;
    this.northDirection = paramFloat2;
    invalidate();
  }

  public void updateTfila(boolean paramBoolean)
  {
    this.isTfila = paramBoolean;
  }
}

/* Location:           D:\MySoftware\decomp\classes_dex2jar.jar
 * Qualified Name:     com.eladrich.dakatora.CompassView
 * JD-Core Version:    0.6.0
 */