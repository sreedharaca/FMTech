package com.google.android.finsky.protos;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class DealOfTheDay
  extends MessageNano
{
  public String colorThemeArgb = "";
  public String featuredHeader = "";
  public boolean hasColorThemeArgb = false;
  public boolean hasFeaturedHeader = false;
  
  public DealOfTheDay()
  {
    this.cachedSize = -1;
  }
  
  protected final int computeSerializedSize()
  {
    int i = super.computeSerializedSize();
    if ((this.hasFeaturedHeader) || (!this.featuredHeader.equals(""))) {
      i += CodedOutputByteBufferNano.computeStringSize(1, this.featuredHeader);
    }
    if ((this.hasColorThemeArgb) || (!this.colorThemeArgb.equals(""))) {
      i += CodedOutputByteBufferNano.computeStringSize(2, this.colorThemeArgb);
    }
    return i;
  }
  
  public final void writeTo(CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
    throws IOException
  {
    if ((this.hasFeaturedHeader) || (!this.featuredHeader.equals(""))) {
      paramCodedOutputByteBufferNano.writeString(1, this.featuredHeader);
    }
    if ((this.hasColorThemeArgb) || (!this.colorThemeArgb.equals(""))) {
      paramCodedOutputByteBufferNano.writeString(2, this.colorThemeArgb);
    }
    super.writeTo(paramCodedOutputByteBufferNano);
  }
}


/* Location:           F:\apktool\apktool\Google_Play_Store6.0.5\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.protos.DealOfTheDay
 * JD-Core Version:    0.7.0.1
 */