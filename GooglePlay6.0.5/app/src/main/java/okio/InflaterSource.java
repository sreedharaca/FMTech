package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class InflaterSource
  implements Source
{
  private int bufferBytesHeldByInflater;
  private boolean closed;
  private final Inflater inflater;
  private final BufferedSource source;
  
  InflaterSource(BufferedSource paramBufferedSource, Inflater paramInflater)
  {
    if (paramBufferedSource == null) {
      throw new IllegalArgumentException("source == null");
    }
    if (paramInflater == null) {
      throw new IllegalArgumentException("inflater == null");
    }
    this.source = paramBufferedSource;
    this.inflater = paramInflater;
  }
  
  public InflaterSource(Source paramSource, Inflater paramInflater)
  {
    this(Okio.buffer(paramSource), paramInflater);
  }
  
  private void releaseInflatedBytes()
    throws IOException
  {
    if (this.bufferBytesHeldByInflater == 0) {
      return;
    }
    int i = this.bufferBytesHeldByInflater - this.inflater.getRemaining();
    this.bufferBytesHeldByInflater -= i;
    this.source.skip(i);
  }
  
  public final void close()
    throws IOException
  {
    if (this.closed) {
      return;
    }
    this.inflater.end();
    this.closed = true;
    this.source.close();
  }
  
  public final long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("byteCount < 0: " + paramLong);
    }
    if (this.closed) {
      throw new IllegalStateException("closed");
    }
    if (paramLong == 0L) {
      return 0L;
    }
    for (;;)
    {
      boolean bool = refill();
      try
      {
        Segment localSegment = paramBuffer.writableSegment(1);
        int i = this.inflater.inflate(localSegment.data, localSegment.limit, 2048 - localSegment.limit);
        if (i > 0)
        {
          localSegment.limit = (i + localSegment.limit);
          paramBuffer.size += i;
          return i;
        }
        if ((this.inflater.finished()) || (this.inflater.needsDictionary()))
        {
          releaseInflatedBytes();
          return -1L;
        }
        if (!bool) {
          continue;
        }
        throw new EOFException("source exhausted prematurely");
      }
      catch (DataFormatException localDataFormatException)
      {
        throw new IOException(localDataFormatException);
      }
    }
  }
  
  public final boolean refill()
    throws IOException
  {
    if (!this.inflater.needsInput()) {
      return false;
    }
    releaseInflatedBytes();
    if (this.inflater.getRemaining() != 0) {
      throw new IllegalStateException("?");
    }
    if (this.source.exhausted()) {
      return true;
    }
    Segment localSegment = this.source.buffer().head;
    this.bufferBytesHeldByInflater = (localSegment.limit - localSegment.pos);
    this.inflater.setInput(localSegment.data, localSegment.pos, this.bufferBytesHeldByInflater);
    return false;
  }
  
  public final Timeout timeout()
  {
    return this.source.timeout();
  }
}


/* Location:           F:\apktool\apktool\Google_Play_Store6.0.5\classes-dex2jar.jar
 * Qualified Name:     okio.InflaterSource
 * JD-Core Version:    0.7.0.1
 */