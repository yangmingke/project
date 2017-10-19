package com.ucp.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import org.apache.log4j.Logger;
import com.tools.AssertUtil;
public class StreamUtil {
	private static final Logger LOG = Logger.getLogger(StreamUtil.class);
	  public static String inStreamToString(InputStream is)
	  {
	    if (!(AssertUtil.isNull(is)))
	    {
	      byte[] bytes = getByteByStream(is);
	      try
	      {
	        return new String(bytes, "utf-8");
	      }
	      catch (UnsupportedEncodingException e)
	      {
	        LOG.error("Failed create string instance.", e);
	      }
	    }
	    return null;
	  }

	  public static byte[] getByteByStream(InputStream is)
	  {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    try
	    {
	      int num;
	      byte[] buf = new byte[1024];

	      while ((num = is.read(buf, 0, buf.length)) != -1)
	      {
	        out.write(buf, 0, num);
	      }
	    }
	    catch (IOException e)
	    {
	      LOG.error("Failed read data from inputstram.", e);

	      close(out); } finally { close(out);
	    }
	    return out.toByteArray();
	  }

	  public static void close(InputStream input)
	  {
	    if (input != null)
	      try {
	        input.close();
	        input = null;
	      }
	      catch (Exception localException)
	      {
	      }
	  }

	  public static void close(OutputStream output)
	  {
	    if (output != null)
	      try {
	        output.close();
	        output = null;
	      }
	      catch (Exception localException)
	      {
	      }
	  }

	  public static void close(HttpURLConnection conn)
	  {
	    if (conn != null) {
	      conn.disconnect();
	      conn = null;
	    }
	  }

	  public static void close(Writer writer)
	  {
	    if (writer != null)
	      try {
	        writer.close();
	        writer = null;
	      }
	      catch (Exception localException)
	      {
	      }
	  }

	  public static void close(Reader reader)
	  {
	    if (reader != null)
	      try {
	        reader.close();
	        reader = null;
	      }
	      catch (Exception localException)
	      {
	      }
	  }
}
