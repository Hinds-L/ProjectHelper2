package com.bluecirclesquare.projecthelper2.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.bluecirclesquare.projecthelper2.model.dao.InvoiceDao;
import com.bluecirclesquare.projecthelper2.model.dao.JobDao;
import com.bluecirclesquare.projecthelper2.model.dao.QuoteDao;
import com.bluecirclesquare.projecthelper2.model.db.ProjectDatabase.Converters;

import com.bluecirclesquare.projecthelper2.model.entity.Invoice;
import com.bluecirclesquare.projecthelper2.model.entity.Job;
import com.bluecirclesquare.projecthelper2.model.entity.Quote;
import java.util.Calendar;
import java.util.Date;


@Database(entities = {Invoice.class, Job.class, Quote.class}, version = 1, exportSchema = true)
@TypeConverters(Converters.class)
public abstract class ProjectDatabase extends RoomDatabase {

  private static final String DATABASE_NAME = "invoice_db";

  private static long invoiceId = 0;

  private static ProjectDatabase instance = null;
  private Calendar calendar = null;


  public static synchronized ProjectDatabase getInstance(Context context) {

    if (instance == null) {

      instance = Room.databaseBuilder(context.getApplicationContext(),

          ProjectDatabase.class,
          DATABASE_NAME)

          .addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return instance;
  }

  public static synchronized void forgetInstance(Context context) {

    instance = null;

  }

  public abstract InvoiceDao getInvoiceDao();

  public abstract JobDao getJobDao();

  public abstract QuoteDao getQuoteDao();

  private static class Callback extends RoomDatabase.Callback {


    private Context context;


    public Callback(Context context) {

      this.context = context;

    }


    @Override

    public void onCreate(@NonNull SupportSQLiteDatabase db) {

      super.onCreate(db);

      new PrepopulateTask(context).execute();

    }


    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
    }
  }

  private static class PrepopulateTask extends AsyncTask<Void, Void, Void> {

    private Context context;


    public PrepopulateTask(Context context) {

      this.context = context;

    }


    @Override

    protected Void doInBackground(Void... voids) {

      ProjectDatabase db = getInstance(context);

      InvoiceDao invoiceDao = db.getInvoiceDao();
      JobDao jobDao = db.getJobDao();
      QuoteDao quoteDao = db.getQuoteDao();

      Quote quote = new Quote();
      quote.setAmount(23);
      quote.setContact("Leslie Hinds");
      quote.setDescription("Roman Shades");
      long quoteId = quoteDao.insert(quote);

      Job job = new Job();
      job.setAddress("41 Chivas Circle");
      job.setQuoteId(quoteId);
      long jobId = jobDao.insert(job);

      Invoice invoice = new Invoice();
      invoice.setJobId(jobId);
      invoice.setPrice(15);
      invoice.setAddress("Space Mountain");
      long invoiceId = invoiceDao.insert(invoice);

      forgetInstance(context);

      return null;

    }

  }

  public static class Converters {

    @TypeConverter
    public static Date dateFromLong(Long time) {
      return (time != null) ? new Date(time) : null;
    }

    @TypeConverter
    public static Long longFromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }

  }

}

