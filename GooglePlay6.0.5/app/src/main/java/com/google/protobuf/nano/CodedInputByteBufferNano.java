package com.google.protobuf.nano;

import java.io.IOException;

public final class CodedInputByteBufferNano
{
  private final byte[] buffer;
  private int bufferPos;
  private int bufferSize;
  private int bufferSizeAfterLimit;
  private int bufferStart;
  private int currentLimit = 2147483647;
  private int lastTag;
  private int recursionDepth;
  private int recursionLimit = 64;
  private int sizeLimit = 67108864;
  
  private CodedInputByteBufferNano(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.bufferStart = paramInt1;
    this.bufferSize = (paramInt1 + paramInt2);
    this.bufferPos = paramInt1;
  }
  
  public static CodedInputByteBufferNano newInstance(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new CodedInputByteBufferNano(paramArrayOfByte, 0, paramInt2);
  }
  
  private byte readRawByte()
    throws IOException
  {
    if (this.bufferPos == this.bufferSize) {
      throw InvalidProtocolBufferNanoException.truncatedMessage();
    }
    byte[] arrayOfByte = this.buffer;
    int i = this.bufferPos;
    this.bufferPos = (i + 1);
    return arrayOfByte[i];
  }
  
  private byte[] readRawBytes(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw InvalidProtocolBufferNanoException.negativeSize();
    }
    if (paramInt + this.bufferPos > this.currentLimit)
    {
      skipRawBytes(this.currentLimit - this.bufferPos);
      throw InvalidProtocolBufferNanoException.truncatedMessage();
    }
    if (paramInt <= this.bufferSize - this.bufferPos)
    {
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(this.buffer, this.bufferPos, arrayOfByte, 0, paramInt);
      this.bufferPos = (paramInt + this.bufferPos);
      return arrayOfByte;
    }
    throw InvalidProtocolBufferNanoException.truncatedMessage();
  }
  
  private void recomputeBufferSizeAfterLimit()
  {
    this.bufferSize += this.bufferSizeAfterLimit;
    int i = this.bufferSize;
    if (i > this.currentLimit)
    {
      this.bufferSizeAfterLimit = (i - this.currentLimit);
      this.bufferSize -= this.bufferSizeAfterLimit;
      return;
    }
    this.bufferSizeAfterLimit = 0;
  }
  
  private void skipRawBytes(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw InvalidProtocolBufferNanoException.negativeSize();
    }
    if (paramInt + this.bufferPos > this.currentLimit)
    {
      skipRawBytes(this.currentLimit - this.bufferPos);
      throw InvalidProtocolBufferNanoException.truncatedMessage();
    }
    if (paramInt <= this.bufferSize - this.bufferPos)
    {
      this.bufferPos = (paramInt + this.bufferPos);
      return;
    }
    throw InvalidProtocolBufferNanoException.truncatedMessage();
  }
  
  public final void checkLastTagWas(int paramInt)
    throws InvalidProtocolBufferNanoException
  {
    if (this.lastTag != paramInt) {
      throw InvalidProtocolBufferNanoException.invalidEndTag();
    }
  }
  
  public final int getBytesUntilLimit()
  {
    if (this.currentLimit == 2147483647) {
      return -1;
    }
    int i = this.bufferPos;
    return this.currentLimit - i;
  }
  
  public final int getPosition()
  {
    return this.bufferPos - this.bufferStart;
  }
  
  public final void popLimit(int paramInt)
  {
    this.currentLimit = paramInt;
    recomputeBufferSizeAfterLimit();
  }
  
  public final int pushLimit(int paramInt)
    throws InvalidProtocolBufferNanoException
  {
    if (paramInt < 0) {
      throw InvalidProtocolBufferNanoException.negativeSize();
    }
    int i = paramInt + this.bufferPos;
    int j = this.currentLimit;
    if (i > j) {
      throw InvalidProtocolBufferNanoException.truncatedMessage();
    }
    this.currentLimit = i;
    recomputeBufferSizeAfterLimit();
    return j;
  }
  
  public final boolean readBool()
    throws IOException
  {
    return readRawVarint32() != 0;
  }
  
  public final byte[] readBytes()
    throws IOException
  {
    int i = readRawVarint32();
    if ((i <= this.bufferSize - this.bufferPos) && (i > 0))
    {
      byte[] arrayOfByte = new byte[i];
      System.arraycopy(this.buffer, this.bufferPos, arrayOfByte, 0, i);
      this.bufferPos = (i + this.bufferPos);
      return arrayOfByte;
    }
    if (i == 0) {
      return WireFormatNano.EMPTY_BYTES;
    }
    return readRawBytes(i);
  }
  
  public final void readGroup(MessageNano paramMessageNano, int paramInt)
    throws IOException
  {
    if (this.recursionDepth >= this.recursionLimit) {
      throw InvalidProtocolBufferNanoException.recursionLimitExceeded();
    }
    this.recursionDepth = (1 + this.recursionDepth);
    paramMessageNano.mergeFrom(this);
    checkLastTagWas(WireFormatNano.makeTag(paramInt, 4));
    this.recursionDepth = (-1 + this.recursionDepth);
  }
  
  public final void readMessage(MessageNano paramMessageNano)
    throws IOException
  {
    int i = readRawVarint32();
    if (this.recursionDepth >= this.recursionLimit) {
      throw InvalidProtocolBufferNanoException.recursionLimitExceeded();
    }
    int j = pushLimit(i);
    this.recursionDepth = (1 + this.recursionDepth);
    paramMessageNano.mergeFrom(this);
    checkLastTagWas(0);
    this.recursionDepth = (-1 + this.recursionDepth);
    popLimit(j);
  }
  
  public final int readRawLittleEndian32()
    throws IOException
  {
    int i = readRawByte();
    int j = readRawByte();
    int k = readRawByte();
    int m = readRawByte();
    return i & 0xFF | (j & 0xFF) << 8 | (k & 0xFF) << 16 | (m & 0xFF) << 24;
  }
  
  public final long readRawLittleEndian64()
    throws IOException
  {
    int i = readRawByte();
    int j = readRawByte();
    int k = readRawByte();
    int m = readRawByte();
    int n = readRawByte();
    int i1 = readRawByte();
    int i2 = readRawByte();
    int i3 = readRawByte();
    return 0xFF & i | (0xFF & j) << 8 | (0xFF & k) << 16 | (0xFF & m) << 24 | (0xFF & n) << 32 | (0xFF & i1) << 40 | (0xFF & i2) << 48 | (0xFF & i3) << 56;
  }
  
  public final int readRawVarint32()
    throws IOException
  {
    int i = readRawByte();
    int i5;
    if (i >= 0) {
      i5 = i;
    }
    int i4;
    do
    {
      return i5;
      int j = i & 0x7F;
      int k = readRawByte();
      if (k >= 0) {
        return j | k << 7;
      }
      int m = j | (k & 0x7F) << 7;
      int n = readRawByte();
      if (n >= 0) {
        return m | n << 14;
      }
      int i1 = m | (n & 0x7F) << 14;
      int i2 = readRawByte();
      if (i2 >= 0) {
        return i1 | i2 << 21;
      }
      int i3 = i1 | (i2 & 0x7F) << 21;
      i4 = readRawByte();
      i5 = i3 | i4 << 28;
    } while (i4 >= 0);
    for (int i6 = 0;; i6++)
    {
      if (i6 >= 5) {
        break label156;
      }
      if (readRawByte() >= 0) {
        break;
      }
    }
    label156:
    throw InvalidProtocolBufferNanoException.malformedVarint();
  }
  
  public final long readRawVarint64()
    throws IOException
  {
    int i = 0;
    long l = 0L;
    while (i < 64)
    {
      int j = readRawByte();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw InvalidProtocolBufferNanoException.malformedVarint();
  }
  
  public final String readString()
    throws IOException
  {
    int i = readRawVarint32();
    if ((i <= this.bufferSize - this.bufferPos) && (i > 0))
    {
      String str = new String(this.buffer, this.bufferPos, i, "UTF-8");
      this.bufferPos = (i + this.bufferPos);
      return str;
    }
    return new String(readRawBytes(i), "UTF-8");
  }
  
  public final int readTag()
    throws IOException
  {
    if (this.bufferPos == this.bufferSize) {}
    for (int i = 1; i != 0; i = 0)
    {
      this.lastTag = 0;
      return 0;
    }
    this.lastTag = readRawVarint32();
    if (this.lastTag == 0) {
      throw InvalidProtocolBufferNanoException.invalidTag();
    }
    return this.lastTag;
  }
  
  public final void rewindToPosition(int paramInt)
  {
    if (paramInt > this.bufferPos - this.bufferStart) {
      throw new IllegalArgumentException("Position " + paramInt + " is beyond current " + (this.bufferPos - this.bufferStart));
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException("Bad position " + paramInt);
    }
    this.bufferPos = (paramInt + this.bufferStart);
  }
  
  public final boolean skipField(int paramInt)
    throws IOException
  {
    switch (WireFormatNano.getTagWireType(paramInt))
    {
    default: 
      throw InvalidProtocolBufferNanoException.invalidWireType();
    case 0: 
      readRawVarint32();
      return true;
    case 1: 
      readRawLittleEndian64();
      return true;
    case 2: 
      skipRawBytes(readRawVarint32());
      return true;
    case 3: 
      int i;
      do
      {
        i = readTag();
      } while ((i != 0) && (skipField(i)));
      checkLastTagWas(WireFormatNano.makeTag(WireFormatNano.getTagFieldNumber(paramInt), 4));
      return true;
    case 4: 
      return false;
    }
    readRawLittleEndian32();
    return true;
  }
}


/* Location:           F:\apktool\apktool\Google_Play_Store6.0.5\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.nano.CodedInputByteBufferNano
 * JD-Core Version:    0.7.0.1
 */